package com.itgrids.voterdata.service;

import java.io.File;
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

public class DeleteVotersForKarnataka {
	
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
                StringBuilder sb = null;
                List<BoothVO> boothsInfoList = new ArrayList<BoothVO>(0);
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {
                	try{
                	sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    String constituencyId = null;
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    sb = ReadDataFromPdfForKarnataka.formatText(sb);
                    
                    String str1 = "Deletions List";
                    String str2 = "Modifications List";
                    sb.delete(0,sb.indexOf(str1)+str1.length()+2);
                    sb.delete(sb.indexOf(str2), sb.length()-1);
                    //System.out.println(sb);
                    
                    String [] fileName = input.getName().split("-");
                    List<Integer>missedVoters = new ArrayList<Integer>(0);
                    
                    System.out.println("Processing File - "+input.getName());
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    
                    Pattern p = Pattern.compile("([\\s0-9]*)\\r\\nName :\\r\\n");
                    Matcher m = p.matcher(sb);
                    
                    while (m.find()) 
                    	missedVoters.add(Integer.valueOf(m.group(1).trim()));
                    
                    boothVO.setMissedVotesList(missedVoters);
                    boothsInfoList.add(boothVO);
                    if (pd != null) {
                        pd.close();
                    }
                	}catch (Exception e) {
                		e.printStackTrace();
                	}
        }
        deleteVoters(boothsInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean deleteVoters(List<BoothVO> boothsList)
    {
    	try{
    		 Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
             stmt = conn.createStatement();
             
             for(BoothVO boothVO : boothsList)
             {
            	 if(boothVO.getMissedVotesList() != null && boothVO.getMissedVotesList().size() > 0)
            	 {
            		 for(Integer sno : boothVO.getMissedVotesList())
            		 {
            			 try{
            			 String sql = " delete from voter_temp where constituency_id = '"+boothVO.getConstituencyId()+"' and booth_id = '"+boothVO.getPartNo()+"' " +
            			 		" and sno = '"+sno+"'";
            			 stmt.executeUpdate(sql);
            			 }catch (Exception e) {
            				 e.printStackTrace();
            			 }
            		 }
            	 }
             }
             
    		return true;
    	}catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
}
