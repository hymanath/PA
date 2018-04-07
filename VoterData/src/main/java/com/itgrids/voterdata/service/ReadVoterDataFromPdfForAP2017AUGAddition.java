package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
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

public class ReadVoterDataFromPdfForAP2017AUGAddition {
	
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
        
        Pattern p = Pattern.compile("Age:\\r\\nHouse No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\nSex:([a-zA-Z]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\\\s]*)\\r\\n([0-9]*)\\r\\n([0-9]*)");
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File(args[0]+"/VoterData.txt")));
                
                File inputDir = new File(args[0]);
                String voterInfo = "";
                
                Long startTime = new Date().getTime();
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "PDF");
                    }
                })) {

                    StringBuilder sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    int startIndex = sb.indexOf("List of Additions, Deletions and Corrections(Supplement-2)");
                    int endIndex = sb.indexOf("Component - II  :  DELETIONS LIST ( Supplement No. : 2 )",startIndex);
                    String sbStr = sb.substring(startIndex,endIndex);
                    sbStr = formatText(sbStr);
                    outwriter.write(sbStr);      
                    
                    String [] fileName = input.getName().split("-");
                    
                    Matcher m = p.matcher(sbStr);
                    
                    VoterInfo voter;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        try{
	                    	i++;
	                    	
	                        voter = new VoterInfo();
	                        
	                        voter.setsNo(Long.valueOf(m.group(8).replaceAll("\\r\\n","").trim()));
	                        voter.setVoterId(m.group(2).replaceAll("\\r\\n","").trim());
	                        voter.setGuardianRelation(m.group(1).substring(0, m.group(1).indexOf("'s Name")).replaceAll("\\r\\n","").trim());
	                        voter.setHouseNumber(m.group(6).replaceAll("\\r\\n","").trim());
	                        voter.setAge(m.group(7).replaceAll("\\r\\n","").trim());
	                        voter.setSex(m.group(5).replaceAll("\\r\\n","").trim());
	                        
	                        String voterName = m.group(3);
	                        String relativeName = m.group(4);
	                        
	                        String[] nameArr = voterName.split("\\r\\n");
	                        
	                        if(nameArr.length > 1 && nameArr[nameArr.length-1].endsWith(" "))
	                        {
	                        	String voterNameStr = "";
	                        	for(int k=0;k<nameArr.length-1;k++)
	                        		voterNameStr = voterNameStr + nameArr[k].trim()+" ";
	                        	
	                        	voterName = voterNameStr.trim();
	                        	relativeName = nameArr[nameArr.length-1]+relativeName;
	                        }
	                        
                        	voter.setVoterName(voterName.replaceAll("\\r\\n","").trim());
                        	voter.setGuardianName(relativeName.replaceAll("\\r\\n","").trim());
	                        
	                        voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3].replaceAll(".PDF",""));
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	                        System.out.println(voterInfo);
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
            System.out.println("Total Time Taken in Minutes -"+((new Date().getTime())-startTime)/1000*60);
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
