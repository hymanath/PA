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

public class BoothDataReader {
	
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
                StringBuilder sb2 = null;
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
                	sb2 = new StringBuilder();
                    pd = PDDocument.load(input);
                    String constituencyId = null;
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    sb2.append(stripper.getText(pd));
                    String str1 = "deletions and corrections under Special \r\nSummary Revision 2013";
                    String str2 = " \r\n\r\nPhoto Electoral Roll - 2013";
                    String str3 = "District\r\n:";
                    String str4 = "Roll Identification: Basic roll of  Special";
                    
                    sb.delete(0,sb.indexOf(str1)+str1.length()+1);
                    sb.delete(sb.indexOf(str2), sb.length()-1);
                    sb.delete(0,sb.indexOf("\r\n")+2);
                    
                    sb2.delete(0,sb2.indexOf(str3)+str3.length()+2);
                    sb2.delete(sb2.indexOf(str4),sb2.length()-1);
                    
                    //System.out.println(sb2.toString());
                    
                    if(sb.toString().contains("\r\n"))
                    	sb.delete(0,sb.indexOf("\r\n"));
                    
                    String [] fileName = input.getName().split("-");
                    
                    String str = sb.toString();
                    str = str.replaceAll("\\r\\n","").trim();
                    String[] voters = str.split(" ");
                    
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setTotalVoters(new Integer(voters[8]));
                    boothVO.setMaleVoters(new Integer(voters[4]));
                    boothVO.setFemaleVoters(new Integer(voters[6]));
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    boothVO.setMandalName(sb2.toString().split("\r\n")[1]);
                    
                    boothSB.append("Booth - "+boothVO.getPartNo()+"\tMandal - "+boothVO.getMandalName()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\n");
                    System.out.println("Booth - "+boothVO.getPartNo()+"\tMandal - "+boothVO.getMandalName()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters());
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
