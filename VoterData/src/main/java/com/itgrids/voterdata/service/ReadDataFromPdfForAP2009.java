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
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.VoterInfo;

public class ReadDataFromPdfForAP2009 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static String readFromFile(File file,
                                      Charset charset) throws IOException {
        InputStream in = new FileInputStream(file);
        Closeable stream = in;
        try {
            Reader reader = new InputStreamReader(in, charset);
            stream = reader;
            StringBuilder inputBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            while (true) {
                int readCount = reader.read(buffer);
                if (readCount < 0) {
                    break;
                }
                inputBuilder.append(buffer, 0, readCount);
            }
            return inputBuilder.toString();
        } finally {
            stream.close();
        }
    }
    
    public static Integer saveVotersData(List<VoterInfo> votersInfoList)
    {
    	Random random = new Random();
    	boolean executeAgain = false;
    	List<VoterInfo> tempList = new ArrayList<VoterInfo>(0);
    	
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
    				System.out.println("Exception Occured While Saving the Voter ID -"+info.getVoterId()+"("+info.getVoterName()+") In Booth - "+info.getBoothNo()+" -- S.No - "+info.getsNo());
    				System.out.println("Exception is -"+e);
    				if(e.getMessage().contains("VoterID_Booth"))
    				{
    					executeAgain = true;
    					info.setVoterId("ABC"+random.nextInt(10000000));
    					tempList.add(info);
    				}
    			}
    		}
    		
    		for(VoterInfo info : tempList)
    		{
    			try{
    			String insertQuery = "INSERT INTO voter_temp(voter_id, name, sex, age, house_no, guardian_name, relation, constituency_id, " +
    				" constituency_name, booth_id, booth_name,sno) VALUES ('"+info.getVoterId()+"','"+info.getVoterName()+"','"+info.getSex()+
    				"','"+info.getAge()+"','"+info.getHouseNumber()+"','"+info.getGuardianName()+"','"+info.getGuardianRelation()+
    				"','"+info.getConstituencyId()+"','"+info.getConstituency()+"','"+info.getBoothNo()+"','"+info.getBoothName().replaceAll(".pdf","")+"',"+info.getsNo()+")";
    			stmt.executeUpdate(insertQuery);
    			}catch(Exception e)
    			{
    				System.out.println("Exception Occured While Saving the Voter ID -"+info.getVoterId()+"("+info.getVoterName()+") In Booth - "+info.getBoothNo()+" -- S.No - "+info.getsNo());
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
        Pattern p = Pattern.compile("([\\s0-9]*)\\r\\nElector's Name:\\r\\nSex:\\r\\nAge:\\r\\nHouse No:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([a-zA-Z\\s]*)\\r\\n");
        try {
                File inputDir = new File(args[0]);
                String voterInfo = "";
                StringBuilder sb2 = new StringBuilder();
                File resultFile  = new File(args[0]+"/VoterData.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                Random random = new Random();
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
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
                    System.out.println("Reading ... "+input.getName());
                    Matcher m = p.matcher(sb);
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        try{
                    	i++;
                        voter = new VoterInfo();
                        String serialNo = m.group(1).replaceAll("\\r\\n","").trim();
                        if(serialNo.contains(" "))
                        {
                        	serialNo = serialNo.split(" ")[1].trim();
                        }
                        voter.setsNo(Long.valueOf(serialNo));
                        voter.setVoterId(m.group(2).replaceAll("\\r\\n","").trim());
                        voter.setGuardianRelation(m.group(5).replaceAll("'s Name:","").replaceAll("\\r\\n","").trim());
                        voter.setHouseNumber(m.group(6).replaceAll("\\r\\n","").trim());
                        voter.setAge(m.group(7).replaceAll("\\r\\n","").trim());
                        
                        if(m.group(8).replaceAll("\\r\\n","").trim().contains("Female"))
                        	voter.setSex("Female");
                        else
                        	voter.setSex("Male");
                        
                        String voterName = m.group(3).trim();
                        if(voterName.contains("\r\n"))
                        {
                        	String[] str = voterName.split("\r\n");
                        	if(str.length == 3)
                    		{
                        		if(str[0].endsWith(" "))
                        		{
                        			voter.setVoterName(str[0].trim()+" "+str[1].trim());
                            		voter.setGuardianName(str[2].trim());
                        		}
                        		else
                        		{
                        			voter.setVoterName(str[0].trim());
                            		voter.setGuardianName(str[1].trim()+" "+str[2].trim());
                        		}
                    		}
                        	else if(str.length == 4)
                        	{
                        		voter.setVoterName(str[0].trim()+" "+str[1].trim());
                        		voter.setGuardianName(str[2].trim()+" "+str[3].trim());
                        	}
                        	else if(str.length == 2)
                        	{
                        		voter.setVoterName(m.group(3).replaceAll("\\r\\n"," ").trim());
                                voter.setGuardianName(m.group(4).replaceAll("\\r\\n"," ").trim());
                        	}
                        }
                        else
                        {
                        	voter.setVoterName(m.group(3).replaceAll("\\r\\n"," ").trim());
                            voter.setGuardianName(m.group(4).replaceAll("\\r\\n"," ").trim());
                        }
                        
                        if(voter.getVoterId() == null)
                        	voter.setVoterId("ABC"+random.nextInt(10000000));
                        
                        voter.setConstituencyId(fileName[0]);
                        voter.setConstituency(fileName[1]);
                        voter.setBoothNo(fileName[2]);
                        voter.setBoothName(fileName[3]);
                        
                        totalVotersCount++;
                        voterInfoList.add(voter);
                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
                        System.out.println(voterInfo);
                        sb2.append(voterInfo+"\n");
                    }catch (Exception e) {
                    	e.printStackTrace();
                    }
                    }
                    if (pd != null) {
                        pd.close();
                    }
                    //saveVotersData(voterInfoList);  
            }
            outwriter.write(sb2.toString());
            System.out.println("Total No of Voters:" + totalVotersCount);
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static StringBuilder formatText(StringBuilder builder)
    {
    	try{
    		String str = builder.toString();
    		str = str.replaceAll("Age", "\r\nAge");
    		str = str.replaceAll("'s \r\nName ", "'s Name\r\n");
    		str = str.replaceAll("Photo as in \r\nCorrection \r\nList\r\n", "");
    		str = str.replaceAll("Husband's Name:", "\r\nHusband's Name:");
    		str = str.replaceAll("Father's Name:", "\r\nFather's Name:");
    		str = str.replaceAll("Mother's Name:", "\r\nMother's Name:");
    		str = str.replaceAll("Other's Name:", "\r\nOther's Name:");
    		str = str.replaceAll(" Female", "\r\nFemale");
    		str = str.replaceAll(" Male", "\r\nMale");
    		str = str.replaceAll(" \r\nElector's Name:", "\r\nElector's Name:");
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }

}
