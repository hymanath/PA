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

public class ReadAddedVoterDataFromPdfAP2015 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
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
	    			String insertQuery = "INSERT INTO voter_temp(voter_id, name, sex, age, house_no, guardian_name, relation, constituency_id, " +
	    				" constituency_name, booth_id, booth_name,sno) VALUES ('"+info.getVoterId()+"','"+info.getVoterName()+"','"+info.getSex()+
	    				"','"+info.getAge()+"','"+info.getHouseNumber()+"','"+info.getGuardianName()+"','"+info.getGuardianRelation()+
	    				"','"+info.getConstituencyId()+"','"+info.getConstituency()+"','"+info.getBoothNo()+"','"+info.getBoothName().replaceAll(".pdf","")+"',"+info.getsNo()+")";
	    			stmt.executeUpdate(insertQuery);
	    			}catch(Exception e)
	    			{
	    				System.out.println("Exception Occured While Saving the Voter ID -"+info.getVoterId()+"("+info.getVoterName()+") In Booth - "+info.getBoothNo());
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
        
        Pattern p = Pattern.compile("House No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\nSex:\\s([a-zA-Z]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)\\r\\n\\s([0-9]*)\\s\\r\\nElector's Name:");
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
                    //sb = formatText(sb);
                    
                    sb = new StringBuilder(sb.substring(sb.indexOf("Component - I  :  ADDITIONS LIST"), sb.indexOf("Component - II  :  DELETIONS LIST")));
                    //outwriter.write(sb.toString());
                    String fileName = input.getName();
                    
                    Matcher m = p.matcher(sb);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        try{
                        	i++;
	                        voter = new VoterInfo();
	                        voter.setGuardianRelation(m.group(1).replaceAll("'s Name:","").trim());
	                        voter.setVoterId(m.group(2).replaceAll("\\r\\n","").trim());
	                        voter.setVoterName(m.group(3).replaceAll("\\r\\n","").trim());
	                        voter.setGuardianName(m.group(4).replaceAll("\\r\\n","").trim());
	                        voter.setSex(m.group(5).replaceAll("\\r\\n","").trim());
	                        voter.setHouseNumber(m.group(6).replaceAll("\\r\\n","").trim());
	                        voter.setAge(m.group(7).replaceAll("\\r\\n","").trim());
	                        voter.setsNo(Long.valueOf(m.group(8).replaceAll("\\r\\n","").trim()));
	                        
	                        voter.setConstituencyId("176");
	                        voter.setConstituency("POLAVARAM");
	                        voter.setBoothNo(fileName.replaceAll(".pdf",""));
	                        voter.setBoothName(fileName.replaceAll(".pdf",""));
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = "Booth No -- " + voter.getBoothNo() + "\tVoter Id - "+voter.getVoterId();
	                        System.out.println(voterInfo);
	                        vlsb.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    saveVotersData(voterInfoList);
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
