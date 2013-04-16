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

public class ReadDataFromPdfForKarnataka {
	
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

    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        Pattern p = Pattern.compile("([0-9]*)\\r\\nName :\\r\\nHouse No.:([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Z\\d\\-]*)\\r\\nSex:([\\sa-zA-Z]*)\\r\\nAge:([\\s0-9]*)\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\n");
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
                    sb.delete(sb.indexOf("Deletions List"),sb.length());
                    //outwriter.write(sb.toString());                
                    String [] fileName = input.getName().split("-");
                    Matcher m = p.matcher(sb);
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        i++;
                        voter = new VoterInfo();
                        voter.setsNo(Long.valueOf(m.group(1).replaceAll("\\r\\n","").trim()));
                        voter.setHouseNumber(m.group(2).replaceAll("\\r\\n","").trim());
                        voter.setGuardianName(m.group(3).replaceAll("\\r\\n","").trim());
                        voter.setVoterName(m.group(4).replaceAll("\\r\\n","").trim());
                        voter.setVoterId(m.group(5).replaceAll("\\r\\n","").trim());
                        voter.setSex(m.group(6).replaceAll("\\r\\n","").trim());
                        voter.setAge(m.group(7).replaceAll("\\r\\n","").trim());
                        voter.setGuardianRelation(m.group(8).replaceAll("'s Name:","").replaceAll("\\r\\n","").trim());
                        
                        voter.setConstituencyId(fileName[0]);
                        voter.setConstituency(fileName[1]);
                        voter.setBoothNo(fileName[2]);
                        voter.setBoothName(fileName[3]);
                        
                        if(voter.getVoterId().equals("-"))
                        	voter.setVoterId("ABC"+random.nextInt(100000));
                        else if(voter.getVoterId().contains("-"))
                        	voter.setVoterId(voter.getVoterId().replaceAll("-",""));
                        
                        totalVotersCount++;
                        voterInfoList.add(voter);
                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
                        System.out.println(voterInfo);
                        sb2.append(voterInfo+"\n");
                    }
                    if (pd != null) {
                        pd.close();
                    }
                    saveVotersData(voterInfoList);  
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
    		str = str.replaceAll("Age:", "\r\nAge:");
    		str = str.replaceAll("'s \r\nName:", "'s Name:");
    		str = str.replaceAll("Photo\r\n Not \r\n Available\r\n", "");
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }

}
