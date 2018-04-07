package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ReadVoterDataFromPdfForAP2017AUGDeletion {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
    
		public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        
        Pattern p = Pattern.compile("Elector's Name:\\r\\n([A-Z\\d]*)\\r\\n");
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "AddedVotersList.txt")));
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File(args[0]+"/VoterData.txt")));
                
                File inputDir = new File(args[0]);
                String voterInfo = "";
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "PDF");
                    }
                })) {

                    StringBuilder sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    int startIndex = sb.indexOf("List of Additions, Deletions and Corrections(Supplement-1)");
                    int endIndex = sb.indexOf("Component - II  :  DELETIONS LIST ( Supplement No. : 2 )",startIndex);
                    String sbStr = sb.substring(startIndex,endIndex);
                    sbStr = formatText(sbStr);
                    outwriter.write(sbStr);
                    
                    String [] fileName = input.getName().split("-");
                    
                    Matcher m = p.matcher(sbStr);
                    
                    while (m.find()) 
                    {
                        try{
	                    	i++;
	                    	totalVotersCount++;
	                    	System.out.println(i+" --> "+m.group(1));
	                        voterInfo = fileName[2]+"\t"+m.group(1);
	                        writer.write(voterInfo+"\n");
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                    if (pd != null) {
                        pd.close();
                    }
                      
            }
            System.out.println("Total No of Voters:" + totalVotersCount);
            writer.close();
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String formatText(String str)
    {
    	try{
    		str = str.replaceAll("Sex: ","Sex:");
    		str = str.replaceAll("\r\n ","\r\n");
    		return str;
    	}catch (Exception e) {
    		e.printStackTrace();
    		return str;
    	}
    }
    
}
