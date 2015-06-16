package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.VoterInfo;

public class ReadVoterDataFromPdfAP2015 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3372/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
		public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        
        Pattern p = Pattern.compile("Sex:Age:\\r\\nHouse No:\\r\\n([A-Z\\d]*)\\r\\n");
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                File inputDir = new File(args[0]);
                String voterInfo = "";
                StringBuilder vlsb = new StringBuilder();
                File resultFile  = new File(args[0]+"/VoterData.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                Long startTime = new Date().getTime();
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {

                    StringBuilder sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    String fileName = input.getName();
                    
                    Matcher m = p.matcher(sb);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    int sno = 0;
                    while (m.find()) 
                    {
                        try{
                        	i++;
	                        voter = new VoterInfo();
	                        
	                        voter.setVoterId(m.group(1).replaceAll("\\r\\n","").trim());
	                        voter.setBoothNo(fileName.replaceAll(".pdf",""));
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = "SNO -- " + ++sno + "\tBooth No -- " + voter.getBoothNo() + "\tVoter Id - "+voter.getVoterId();
	                        System.out.println(voterInfo);
	                        vlsb.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    //saveVotersData(voterInfoList);
                    //deleteVotersData(voterInfoList);
                    if (pd != null) {
                        pd.close();
                    }
                      
            }
            System.out.println("Total No of Voters:" + totalVotersCount);
            System.out.println("Total Time Taken in Minutes - "+((new Date().getTime())-startTime)/(1000*60));
            writer.close();
            outwriter.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static StringBuilder formatText(StringBuilder builder)
    {
    	try{
    		String str = builder.toString();
    		str = str.replaceAll("Father's Name:\r\n", "");
    		str = str.replaceAll("Husband's Name:\r\n", "");
    		str = str.replaceAll("Mother's Name:\r\n", "");
    		str = str.replaceAll("Other's Name:\r\n", "");
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }
    
}
