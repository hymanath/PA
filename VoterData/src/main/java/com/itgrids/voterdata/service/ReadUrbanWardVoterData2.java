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

public class ReadUrbanWardVoterData2 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3372/dakavara_pa";
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
    			String insertQuery = "INSERT INTO ward_voter(muncipality, ward_no, voter_id) VALUES ('"+info.getConstituency()+"','"+info.getBoothNo()+"','"+info.getVoterId()+"')";
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
    
    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        
        Pattern p = Pattern.compile("([A-Z\\d]*)\\r\\n");
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                //  PDF file from where the voter data is extracted
                File inputDir = new File(args[0]);
                String voterInfo = "";
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
                    //System.out.println("File text:"+stripper.getText(pd));
                    
                    sb = formatText(sb);
                    
                    outwriter.write(sb.toString());                   
                    String [] fileName = input.getName().split("-");
                    
                   /* int strLen = sb.toString().length();
                    String lstr = sb.toString().substring(strLen-100,strLen);
                    //System.out.println(lstr);
                    //saveCounts(fileName[0].trim(),fileName[2].trim().replaceAll(".pdf",""),lstr);
*/
                    Matcher m = p.matcher(sb);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        try{
                        String voterId = m.group(1).replaceAll("\\r\\n","").trim();
                        if(validateVoterId(voterId))
                        {
	                    	i++;
	                        voter = new VoterInfo();
	                        voter.setVoterId(voterId.trim());
	                        
	                        voter.setConstituency(fileName[0].trim());
	                        voter.setBoothNo(fileName[2].replaceAll(".pdf","").trim());
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tMuncipality -- " + voter.getConstituency() + "\tWard No -- " + voter.getBoothNo().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId();
	                        System.out.println(voterInfo);
	                        writer.write(voterInfo+"\n");
                        }
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                    //saveVotersData(voterInfoList);
                    if (pd != null) {
                        pd.close();
                    }
                      
            }
            System.out.println("Total No of Voters:" + totalVotersCount);
            System.out.println("Total Time Taken in Minutes -"+((new Date().getTime())-startTime)/1000*60*60);
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
    		str = str.replaceAll("\t", "");
    		
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }
    
    public static boolean validateVoterId(String voterId)
    {
    	try{
    		if(voterId.trim().length() < 8)
    			return false;
    		else if(Character.isLetter(voterId.charAt(0)) && Character.isLetter(voterId.charAt(1))
    				&& Character.isDigit(voterId.charAt(voterId.trim().length()-1)))
    			return true;
    		else
    			return false;
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}
