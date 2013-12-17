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

public class ReadVoterDataFromPdfForAP2013 {
	
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
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		votersInfoList = checkForVoterList(votersInfoList);
    		votersInfoList = checkForSpecialCharacters(votersInfoList);
    		
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
        
        Pattern p22 = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
        Pattern p12 = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
        Pattern p = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
        Pattern p1 = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\-\\*\\~]*)\\r\\n\\s([0-9]*)");
        Pattern p2 = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
        Pattern p3 = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
        
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
                    //System.out.println("File text:"+stripper.getText(pd));
                    sb = formatText(sb);
                    sb = formatText2(sb);
                    
                    outwriter.write(sb.toString());                   
                    String [] fileName = input.getName().split("-");
                    
                    Matcher m = p.matcher(sb);
                    Matcher m1 = p1.matcher(sb);
                    Matcher m2 = p2.matcher(sb);
                    Matcher m3 = p3.matcher(sb);
                    Matcher m22 = p22.matcher(sb);
                    Matcher m12 = p12.matcher(sb);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    List<Long> serialNosList = new ArrayList<Long>(0);
                    
                    while (m22.find()) 
                    {
                        try{
                    	i++;
                        voter = new VoterInfo();
                        
                        voter.setsNo(Long.valueOf(m22.group(1).replaceAll("\\r\\n","").trim()));
                        voter.setSex(m22.group(2).replaceAll("\\r\\n","").trim());
                        voter.setVoterId(m22.group(3).replaceAll("\\r\\n","").trim());
                        voter.setGuardianRelation(m22.group(4).substring(0, m22.group(4).indexOf("'s Name")).replaceAll("\\r\\n","").trim());
                        voter.setVoterName(m22.group(5).replaceAll("\\r\\n","").trim()+" "+m22.group(6).replaceAll("\\r\\n","").trim());
                        voter.setGuardianName(m22.group(7).replaceAll("\\r\\n","").trim()+" "+m22.group(8).replaceAll("\\r\\n","").trim());
                        voter.setHouseNumber(m22.group(9).replaceAll("\\r\\n","").trim());
                        voter.setAge(m22.group(10).replaceAll("\\r\\n","").trim());
                        
                        voter.setConstituency(fileName[1]);
                        voter.setBoothNo(fileName[2]);
                        voter.setBoothName(fileName[3]);
                        voter.setConstituencyId(fileName[0]);
                        totalVotersCount++;
                        voterInfoList.add(voter);
                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
                        System.out.println(voterInfo);
                        sb2.append(voterInfo+"\n");
                        writer.write(voterInfo+"\n");
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                    voterInfoList = checkForVoterList(voterInfoList);
                    serialNosList.addAll(getSerialNoList(voterInfoList));
                    saveVotersData(voterInfoList);
                    voterInfoList = new ArrayList<VoterInfo>(0);
                    
                    while (m12.find()) 
                    {
                        try{
                        long serialNo = new Long(m12.group(1).replaceAll("\\r\\n","").trim());
                        if(!serialNosList.contains(serialNo))
                		{
	                    	i++;
	                        voter = new VoterInfo();
	                        
	                        voter.setsNo(Long.valueOf(m12.group(1).replaceAll("\\r\\n","").trim()));
	                        voter.setSex(m12.group(2).replaceAll("\\r\\n","").trim());
	                        voter.setVoterId(m12.group(3).replaceAll("\\r\\n","").trim());
	                        voter.setGuardianRelation(m12.group(4).substring(0, m12.group(4).indexOf("'s Name")).replaceAll("\\r\\n","").trim());
	                        voter.setHouseNumber(m12.group(8).replaceAll("\\r\\n","").trim());
	                        voter.setAge(m12.group(9).replaceAll("\\r\\n","").trim());
	                        
	                        if(m12.group(6).endsWith(" "))
	                        {
		                        voter.setVoterName(m12.group(5).replaceAll("\\r\\n","").trim());
		                        voter.setGuardianName(m12.group(6).replaceAll("\\r\\n","").trim()+" "+m12.group(7).replaceAll("\\r\\n","").trim());
	                        }
	                        else
	                        {
	                        	voter.setVoterName(m12.group(5).replaceAll("\\r\\n","").trim()+" "+m12.group(6).replaceAll("\\r\\n","").trim());
		                        voter.setGuardianName(m12.group(7).replaceAll("\\r\\n","").trim());
	                        }
	                        
	                        voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3]);
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	                        System.out.println(voterInfo);
	                        sb2.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                		}
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                    voterInfoList = checkForVoterList(voterInfoList);
                    serialNosList.addAll(getSerialNoList(voterInfoList));
                    saveVotersData(voterInfoList);
                    voterInfoList = new ArrayList<VoterInfo>(0);
                    
                    while (m.find()) 
                    {
                        try{
                        long serialNo = new Long(m.group(1).replaceAll("\\r\\n","").trim());
                        if(!serialNosList.contains(serialNo))
                		{
	                    	i++;
	                        voter = new VoterInfo();
	                        
	                        voter.setsNo(Long.valueOf(m.group(1).replaceAll("\\r\\n","").trim()));
	                        voter.setSex(m.group(2).replaceAll("\\r\\n","").trim());
	                        voter.setVoterId(m.group(3).replaceAll("\\r\\n","").trim());
	                        voter.setGuardianRelation(m.group(4).substring(0, m.group(4).indexOf("'s Name")).replaceAll("\\r\\n","").trim());
	                        voter.setVoterName(m.group(5).replaceAll("\\r\\n","").trim());
	                        voter.setGuardianName(m.group(6).replaceAll("\\r\\n","").trim());
	                        voter.setHouseNumber(m.group(7).replaceAll("\\r\\n","").trim());
	                        voter.setAge(m.group(8).replaceAll("\\r\\n","").trim());
	                        
	                        voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3]);
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	                        System.out.println(voterInfo);
	                        sb2.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                		}
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                    voterInfoList = checkForVoterList(voterInfoList);
                    serialNosList.addAll(getSerialNoList(voterInfoList));
                    saveVotersData(voterInfoList);
                    voterInfoList = new ArrayList<VoterInfo>(0);
                    
                    while (m1.find()) 
                    {
                	try{
                		long serialNo = new Long(m1.group(1).replaceAll("\\r\\n","").trim());
                		if(!serialNosList.contains(serialNo))
                		{
                			voter = new VoterInfo();
                			i++;
	                		voter.setsNo(serialNo);
	                		voter.setSex(m1.group(2).replaceAll("\\r\\n","").trim());
	                		voter.setVoterId(m1.group(3).replaceAll("\\r\\n","").trim());
	                		voter.setGuardianRelation(m1.group(4).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
	                		voter.setAge(m1.group(7).replaceAll("\\r\\n","").trim());
	                		voter.setHouseNumber("0-00");
	                		voter.setVoterName(m1.group(5).replaceAll("\\r\\n","").trim());
	                		voter.setGuardianName(m1.group(6).replaceAll("\\r\\n","").trim());
	                    	
	                		voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3]);
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	                        System.out.println(voterInfo);
	                        sb2.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                		}
                		}catch(Exception e){
                			e.printStackTrace();
                	}
                    }
                    
                    voterInfoList = checkForVoterList(voterInfoList);
                    serialNosList.addAll(getSerialNoList(voterInfoList));
                    saveVotersData(voterInfoList);
                    voterInfoList = new ArrayList<VoterInfo>(0);
                    
                    while (m2.find()) 
                    {
                	try{
                		long serialNo = new Long(m2.group(1).replaceAll("\\r\\n","").trim());
                		if(!serialNosList.contains(serialNo))
                		{
                			voter = new VoterInfo();
                    		i++;
	                		voter.setsNo(serialNo);
	                		
	                		voter.setVoterId(m2.group(2).replaceAll("\\r\\n","").trim());
	                		voter.setGuardianRelation(m2.group(3).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
	                		voter.setAge(m2.group(7).replaceAll("\\r\\n","").trim());
	                		voter.setHouseNumber(m2.group(6).replaceAll("\\r\\n","").trim());
	                		voter.setVoterName(m2.group(4).replaceAll("\\r\\n","").trim());
	                		voter.setGuardianName(m2.group(5).replaceAll("\\r\\n","").trim());
	                    	
	                    	if(voter.getGuardianRelation().equalsIgnoreCase("Husband"))
	                    		voter.setSex("Female");
	                    	else
	                    		voter.setSex("Male");
	                    	
	                		voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3]);
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	                        System.out.println(voterInfo);
	                        sb2.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                		}
                		}catch(Exception e){
                			e.printStackTrace();
                	}
                    }
                    
                    voterInfoList = checkForVoterList(voterInfoList);
                    serialNosList.addAll(getSerialNoList(voterInfoList));
                    saveVotersData(voterInfoList);
                    voterInfoList = new ArrayList<VoterInfo>(0);
                    
                    while (m3.find()) 
                    {
                	try{
                		long serialNo = new Long(m3.group(1).replaceAll("\\r\\n","").trim());
                		if(!serialNosList.contains(serialNo))
                		{
                			voter = new VoterInfo();
                    		i++;
	                		voter.setsNo(serialNo);
	                		voter.setSex(m3.group(2).replaceAll("\\r\\n","").trim());
	                		voter.setVoterId(m3.group(3).replaceAll("\\r\\n","").trim());
	                		voter.setGuardianRelation(m3.group(4).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
	                		voter.setAge(m3.group(7).replaceAll("\\r\\n","").trim());
	                		voter.setHouseNumber(m3.group(6).replaceAll("\\r\\n","").trim());
	                		voter.setVoterName(m3.group(5).replaceAll("\\r\\n","").trim());
	                		voter.setGuardianName("N/A");
	                    	
	                		voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3]);
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	                        System.out.println(voterInfo);
	                        sb2.append(voterInfo+"\n");
	                        writer.write(voterInfo+"\n");
                		}
                		}catch(Exception e){
                			e.printStackTrace();
                	}
                    }
                    saveVotersData(voterInfoList);
                    if (pd != null) {
                        pd.close();
                    }
                      
            }
            outwriter.write(sb2.toString());
            System.out.println("Total No of Voters:" + totalVotersCount);
            System.out.println("Total Time Taken in Minutes -"+((new Date().getTime())-startTime)/1000*60);
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
    		str = str.replaceAll(" Sex:", "\r\nSex:");
    		str = str.replaceAll(" \r\nAge:", "\r\nAge:");
    		str = str.replaceAll(" Male", "\r\nMale");
    		str = str.replaceAll(" Female", "\r\nFemale");
    		str = str.replaceAll(" '", " ");
    		str = str.replaceAll("' ", " ");
    		str = str.replaceAll("Age As On", "\r\nAge As On");
    		
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }
    
    public static StringBuilder formatText2(StringBuilder builder)
    {
    	try{
    		String str = builder.toString();
    		Integer index = 1; 
    		for(;index<=2000;index++)
    		{
    			str = str.replace(" "+index.toString()+"\r\nAge:", "\r\n"+index.toString()+"\r\nAge:");
    		}
    		
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }
    
    public static List<VoterInfo> checkForVoterList(List<VoterInfo> votersInfoList)
    {
    	try{
    		List<VoterInfo> votersList = new ArrayList<VoterInfo>(0);
    		
    		if(votersInfoList != null && votersInfoList.size() > 0)
    		{
    			List<String> genderList = new ArrayList<String>();
    			genderList.add("Male");
    			genderList.add("Female");
    			
    			List<String> relationsList = new ArrayList<String>();
    			relationsList.add("Mother");
    			relationsList.add("Father");
    			relationsList.add("Husband");
    			relationsList.add("Other");
    			
    			StringBuilder str = new StringBuilder();
    			
    			for(VoterInfo info : votersInfoList)
    			{
    				try{
    				boolean flag = true;
    				int age = Integer.valueOf(info.getAge().trim());
    				
    				String voterId = info.getVoterId().trim().replaceAll("'", "");
    				String name = info.getVoterName().trim().replaceAll("'", "");
    				String sex = info.getSex().trim().replaceAll("'", "");
    				String houseNo = info.getHouseNumber().trim().replaceAll("'", "");
    				String relativeName = info.getGuardianName().trim().replaceAll("'", "");
    				String relation = info.getGuardianRelation().trim().replaceAll("'", "");
    				
    				info.setVoterId(voterId);
    				info.setVoterName(name);
    				info.setSex(sex);
    				info.setHouseNumber(houseNo);
    				info.setGuardianName(relativeName);
    				info.setGuardianRelation(relation);
    				
    				if(voterId == null || voterId.length() == 0 || voterId.equalsIgnoreCase("null"))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(name == null || name.length() == 0 || name.equalsIgnoreCase("null"))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(sex == null || sex.length() == 0 || sex.equalsIgnoreCase("null") || !genderList.contains(info.getSex().trim()))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(info.getAge() == null || info.getAge().trim().length() == 0 || info.getAge().trim().equalsIgnoreCase("null")
    						|| age < 18 || age > 200)
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(houseNo == null || houseNo.length() == 0 || houseNo.equalsIgnoreCase("null") || houseNo.length() > 100)
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(relativeName == null || relativeName.length() == 0 || relativeName.equalsIgnoreCase("null"))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(relation == null || relation.length() == 0 || relation.equalsIgnoreCase("null") || !relationsList.contains(relation))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				//System.out.println(str);
    				if(flag)
    					votersList.add(info);
    				}catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		}
    		
    		return votersList;
    	}catch(Exception e)
    	{
    		System.out.println("Exception Occured in checkForVoterList Method, So Same Voter List is returned");
    		System.out.println(e);
    		return votersInfoList;
    	}
    }
    
    public static String printVoter(VoterInfo info)
    {
    	try{
    		//String str = "Booth - "+info.getBoothNo()+"\tSerial No - "+info.getsNo()+"\tName - "+info.getVoterName()+"\tHouse No - "+info.getHouseNumber()+"\tGender - "+info.getSex()+"\tAge - "+info.getAge()+"\tVoter Id - "+info.getVoterId()+"\tRelative Name - "+info.getGuardianName()+"\tRelation - "+info.getGuardianRelation()
    			//	+"\tBooth Name - "+info.getBoothName()+"\tConstituency Id - "+info.getConstituencyId()+"\tConstituency - "+info.getConstituency();
    		//System.out.println(str+"\n");
    		return "";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "";
    	}
    }
    
    public static List<Long> getSerialNoList(List<VoterInfo> list)
    {
    	List<Long> serialNoList = new ArrayList<Long>(0);
    	try{
    		for(VoterInfo info : list)
    			serialNoList.add(info.getsNo());
    		return serialNoList;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return serialNoList;
    	}
    }
    
    public static List<VoterInfo> checkForSpecialCharacters(List<VoterInfo> list)
    {
    	try{
    		for(VoterInfo info : list)
    		{
    			try{
    			info.setVoterId(info.getVoterId().replaceAll("'", ""));
    			info.setVoterName(info.getVoterName().replaceAll("'", ""));
    			info.setHouseNumber(info.getHouseNumber().replaceAll("'", ""));
    			info.setGuardianName(info.getGuardianName().replaceAll("'", ""));
    			info.setBoothName(info.getBoothName().replaceAll("'", ""));
    			
    			/*info.setVoterId(info.getVoterId().replaceAll("\\", ""));
    			info.setVoterName(info.getVoterName().replaceAll("\\", ""));
    			info.setHouseNumber(info.getHouseNumber().replaceAll("\\", ""));
    			info.setGuardianName(info.getGuardianName().replaceAll("\\", ""));
    			info.setBoothName(info.getBoothName().replaceAll("\\", ""));*/
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		return list;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return list;
    	}
    }
    
}
