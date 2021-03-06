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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.VoterInfo;

public class ReadVoterDataFromPdfForAP2013Old {
	
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
    
    /*public static List<VoterInfo> checkForVoterList(List<VoterInfo> votersInfoList)
    {
    	try{
    		List<VoterInfo> votersList = new ArrayList<VoterInfo>(0);
    		
    		if(votersInfoList != null && votersInfoList.size() > 0)
    		{
    			for(VoterInfo info : votersInfoList)
    			{
    				if(info.getAge() !)
    			}
    		}
    		
    		return votersList;
    	}catch(Exception e)
    	{
    		System.out.println("Exception Occured in checkForVoterList Method, So Same Voter List is returned");
    		System.out.println(e);
    		return votersInfoList;
    	}
    }*/
    
    
    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        
        //Pattern p2 = Pattern.compile("([\\s0-9a-zA-Z]*)\\r\\nElector's Name:\\r\\nSex:Age:\\r\\nHouse No:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([a-zA-Z]*)\\r\\n");
        Pattern p = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                //  PDF file from where the voter data is extracted
                File inputDir = new File(args[0]);
                String voterInfo = "";
                StringBuilder sb2 = new StringBuilder();
                File resultFile  = new File(args[0]+"/VoterData.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                
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
                    VoterInfo voter = null;
                    long lastSno = 0L;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        try{
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
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    if (pd != null) {
                        pd.close();
                    }
                    saveVotersData(voterInfoList);  
            }
            outwriter.write(sb2.toString());
            System.out.println("Total No of Voters:" + totalVotersCount);
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
    
}
