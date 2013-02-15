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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.BoothVO;

public class VotersModificationIdentifier {
	
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
                    File resultFile  = new File(args[0]+"/VoterData.txt");
                    BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                    outwriter.write(sb2.toString());
                    outwriter.close();
                    
                    String str1 = "Component - I";
                    String str2 = "Component - II";
                    String str3 = "District\r\n:";
                    String str4 = "Roll Identification: Basic roll of  Special";
                    
                    String additionsStr = sb.substring(sb.indexOf(str1), sb.indexOf(str2));
                    //System.out.println(additionsStr);
                    
                    Pattern p = Pattern.compile("Age:\\r\\nHouse No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\nSex:\\s([a-zA-Z]*)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\(\\)\\/\\*\\,\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([\\s0-9]*)\\r\\n");
                	Matcher m = p.matcher(additionsStr);
                	
                	while (m.find()) 
                    {
                		System.out.println((m.group(1).replaceAll("\\r\\n","").trim()));
                		System.out.println((m.group(2).replaceAll("\\r\\n","").trim()));
                		System.out.println((m.group(3).replaceAll("\\r\\n","").trim()));
                		System.out.println((m.group(4).replaceAll("\\r\\n","").trim()));
                		System.out.println((m.group(5).replaceAll("\\r\\n","").trim()));
                		System.out.println((m.group(6).replaceAll("\\r\\n","").trim()));
                		System.out.println((m.group(7).replaceAll("\\r\\n","").trim()));
                		System.out.println((m.group(8).replaceAll("\\r\\n","").trim()));
                    }
                	
                    String [] fileName = input.getName().split("-");
                    
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    
                    boothSB.append("Booth - "+boothVO.getPartNo()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\n");
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
