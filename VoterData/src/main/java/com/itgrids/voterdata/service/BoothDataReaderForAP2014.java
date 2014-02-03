package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.BoothVO;

public class BoothDataReaderForAP2014 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        try {

                File inputDir = new File(args[0]);
                File BoothInfo  = new File(args[0]+"/BoothDataReader.txt");
                BufferedWriter boothInfoReader = new BufferedWriter(new FileWriter(BoothInfo));
                StringBuilder sb = null;
                StringBuilder boothSB = new StringBuilder();
                List<BoothVO> boothsInfoList = new ArrayList<BoothVO>(0);
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {
                	try{
                	stmt = conn.createStatement();
                	sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    String constituencyId = null;
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    sb = sb.delete(sb.indexOf("Elector's Name:\r\n"), sb.length()-1);
                    String str1 = "Summary Revision 2014";
                    String str2 = "Others\r\n";
                    String str3 = "District\r\n:";
                    
                    int startNo = sb.lastIndexOf(str1)+str1.length()+1;
                    String sbStr = sb.substring(startNo, sb.indexOf(str2,startNo)).trim();
                    
                    int mdstart = sb.indexOf(str3)+str3.length()+1;
                    int mdstartNo = sb.indexOf("\r\n",mdstart)+1;
                    String sbStr2 = sb.substring(mdstartNo, sb.indexOf("\r\n",mdstartNo)).trim();
                    
                    if(sbStr.contains("\r\n"))
                    	sbStr = sbStr.substring(sbStr.indexOf("\r\n")+1).trim();
                    if(sbStr.contains("\r\n"))
                    	sbStr = sbStr.substring(sbStr.indexOf("\r\n")+1).trim();
                    
                    String [] fileName = input.getName().split("-");
                    
                    sbStr = sbStr.replaceAll("\\r\\n","").trim();
                    sbStr = sbStr.replaceAll("  "," ").trim();
                    String[] voters = sbStr.split(" ");
                    
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setTotalVoters(new Integer(voters[4].trim()));
                    boothVO.setMaleVoters(new Integer(voters[2].trim()));
                    boothVO.setFemaleVoters(new Integer(voters[3].trim()));
                    boothVO.setOtherVoters(boothVO.getTotalVoters() - (boothVO.getMaleVoters()+boothVO.getFemaleVoters()));
                    boothVO.setStartingSerialNo(new Integer(voters[0].trim()));
                    boothVO.setEndingSerialNo(new Integer(voters[1].trim()));
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    boothVO.setMandalName(sbStr2.trim());
                    
                    boothSB.append("Mandal - "+boothVO.getMandalName()+"\tBooth - "+boothVO.getPartNo()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\tOthers - "+boothVO.getOtherVoters()+"\tStartind Serial No - "+boothVO.getStartingSerialNo()+"\tEnding Serail No - "+boothVO.getEndingSerialNo()+"\n");
                    System.out.println("Mandal - "+boothVO.getMandalName()+"\tBooth - "+boothVO.getPartNo()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\tOthers - "+boothVO.getOtherVoters()+"\tStartind Serial No - "+boothVO.getStartingSerialNo()+"\tEnding Serail No - "+boothVO.getEndingSerialNo());
                    boothsInfoList.add(boothVO);
                    if (pd != null) {
                        pd.close();
                    }
                	}catch (Exception e) {e.printStackTrace();}
        }
            boothInfoReader.write(boothSB.toString());
            boothInfoReader.close();
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       	
}
