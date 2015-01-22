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

public class ReadUrbanWardVoterData {
	
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
    			String insertQuery = "INSERT INTO ward_voter(muncipality, ward_no, voter_id, uid,serial_no) VALUES ('"+info.getConstituency()+"','"+info.getBoothNo()+"','"+info.getVoterId()+"','"+info.getUid()+"','"+info.getsNo()+"')";
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
        
        Pattern p = Pattern.compile("([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\s([0-9]*)\\r\\n");
        
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
                    
                    //outwriter.write(sb.toString());                   
                    String [] fileName = input.getName().split("-");
                    
                    int strLen = sb.toString().length();
                    String lstr = sb.toString().substring(strLen-100,strLen);
                    //System.out.println(lstr);
                    saveCounts(fileName[0].trim(),fileName[2].trim().replaceAll(".pdf",""),lstr);

                    Matcher m = p.matcher(sb);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    List<String> list = null;
                    
                    while (m.find()) 
                    {
                        try{
                        String str = m.group(1).replaceAll("\\r\\n","").trim();
                        if(str.contains("-") && str.contains("/"))
                        {
	                        list = splitString(str);
	                    	i++;
	                        voter = new VoterInfo();
	                        voter.setUid(list.get(0));
	                        voter.setVoterId(list.get(1));
	                        voter.setsNo(Long.valueOf(m.group(2).replaceAll("\\r\\n","").trim()));
	                        
	                        voter.setConstituency(fileName[0].trim());
	                        voter.setBoothNo(fileName[2].replaceAll(".pdf","").trim());
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tMuncipality -- " + voter.getConstituency() + "\tWard No -- " + voter.getBoothNo().replaceAll(".pdf","") + "\t Serial No -- "+voter.getsNo()+"\tvoter ID -- " + voter.getVoterId()+"\tUUID -- " + voter.getUid()+"";
	                        System.out.println(voterInfo);
	                        writer.write(voterInfo+"\n");
                        }
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
            System.out.println("Total Time Taken in Minutes -"+((new Date().getTime())-startTime)/1000*60*60);
            writer.close();
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<String> splitString(String str)
    {
    	List<String> list = new ArrayList<String>();
    	try{
    	int index = str.indexOf("/");
    	
    	for(int i=index;i<str.length();i++)
    	{
    		if(Character.isLetter(str.charAt(i)))
    		{
    			String str1 = str.substring(0,i);
    			String str2 = str.substring(i);
    			list.add(str1);
    			list.add(str2);
    			return list;
    		}
    	}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public static void saveCounts(String muncipality,String wardNo,String lstr)
    {
    	try{
    		//System.out.println(lstr);
    		int mcount = 0;
    		int fcount = 0;
    		int tcount = 0;
            lstr = lstr.replaceAll(",", "");
            String[] lsArr = lstr.split("\\r\\n");
            //System.out.println(lsArr[lsArr.length-2]);
            String[] cArr = lsArr[lsArr.length-2].split(" ");
            mcount = Integer.valueOf(cArr[1].trim());
            fcount = Integer.valueOf(cArr[2].trim());
            tcount = Integer.valueOf(cArr[3].trim());
            
            Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		try{
    			String insertQuery = "INSERT INTO ward_voter_count(muncipality, ward_no, male, female, total) VALUES ('"+muncipality+"','"+wardNo+"','"+mcount+
    				"','"+fcount+"','"+tcount+"')";
    			stmt.executeUpdate(insertQuery);
    			}catch(Exception e)
    			{
    				System.out.println("Exception is -"+e);
    			}
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
}
