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

public class ReadVoterDataFromPdfForAP2016 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
   public static Integer saveVotersData(List<VoterInfo> votersInfoList)
    {
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		for(VoterInfo info : votersInfoList)
    		{
    			try{
    			String insertQuery = "INSERT INTO voter_temp(voter_id,constituency_id,constituency_name, booth_id, booth_name) VALUES ('"+info.getVoterId()+"','"+info.getConstituencyId()+"','"+info.getConstituency()+"','"+info.getBoothNo()+"','"+info.getBoothName()+"')";
    			stmt.executeUpdate(insertQuery);
    			}catch(Exception e)
    			{
    				System.out.println("Exception Occured While Saving the Voter ID -"+info.getVoterId()+" In Booth - "+info.getBoothNo());
    				System.out.println("Exception is -"+e);
    			}
    		}
    		
    		return null;
    	}catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    	finally{
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
   
   public static Integer saveSampleVotersData(String str)
   {
   	try{
   		Class.forName("com.mysql.jdbc.Driver");
   		conn = DriverManager.getConnection(DB_URL,USER,PASS);
   		stmt = conn.createStatement();
   		
   			try{
   			String insertQuery = "INSERT INTO sample(sample_text) VALUES ('"+str+"')";
   			stmt.executeUpdate(insertQuery);
   			}catch(Exception e)
   			{
   				System.out.println("Exception is -"+e);
   			}
   		
   		return null;
   	}catch (Exception e) {
   		e.printStackTrace();
   		return null;
   	}
   	finally{
   		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
   	}
   }
   
  public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        
        Pattern p = Pattern.compile("House No:\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n");
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                //  PDF file from where the voter data is extracted
                File inputDir = new File(args[0]);
                String voterInfo = "";
                StringBuilder sb2 = new StringBuilder();
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
                    outwriter.write(sb.toString());
                    //System.out.println("File text:"+stripper.getText(pd));
                    //sb = formatText(sb);
                    
                    /*sb = new StringBuilder(sb.substring(sb.indexOf("Component - II  :  DELETIONS LIST"), sb.indexOf("Component - III  :  CORRECTION LIST")));
                    outwriter.write(sb.toString());
                    String [] fileName = input.getName().split("-");
                    
                    Matcher m = p.matcher(sb);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        try{
                        	i++;
	                        voter = new VoterInfo();
	                        
	                        voter.setVoterId(m.group(1).replaceAll("\\r\\n","").trim());
	                        
	                        voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3].replaceAll(".pdf",""));
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\tBooth Name -- " + voter.getBoothName() + "\tVoter Id - "+voter.getVoterId();
	                        System.out.println(voterInfo);
	                        sb2.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }*/
                    //saveVotersData(voterInfoList);
                    //deleteVotersData(voterInfoList);
                    if (pd != null) {
                        pd.close();
                    }
                      
            }
            //outwriter.write(sb2.toString());
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
