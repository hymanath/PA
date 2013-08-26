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

public class APTeluguVoterDataReader {
	
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
    			String insertQuery = "INSERT INTO voter_language_temp(voter_ID, voter_name, relative_name, language_id) VALUES ('"+info.getVoterId()+"','"+info.getVoterName()+"','"+info.getGuardianName()+"',1)";
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
        Pattern p = Pattern.compile("([\\s0-9]*)\\s([A-Z\\d]*)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\sxmso\\r\\n");
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
                    Matcher m = p.matcher(sb);
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    List<String> voterIdsList = new ArrayList<String>(0);
                    
                    while (m.find()) 
                    {
                    	voterIdsList.add(m.group(2).trim());
                    }
                    for(int index=0;index<voterIdsList.size();index++)
                    {
                    	try{
                    	int startIndex = sb.indexOf(voterIdsList.get(index));
                		int endIndex = sb.indexOf(voterIdsList.get(index+1));
                		
                		String reqStr = sb.substring(startIndex,endIndex).trim();
                		System.out.println("--> "+reqStr);
                		String[] arr = reqStr.trim().split("\\r\\n");
                		
                		if(arr.length == 6)
                		{
                			voter = new VoterInfo();
                			voter.setVoterId(arr[0].trim());
                			voter.setVoterName(arr[3].trim());
                			voter.setGuardianName(arr[4].trim());
                			voterInfoList.add(voter);
                		}
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
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static StringBuilder formatText(StringBuilder builder)
    {
    	try{
    		String str = builder.toString();
    		str = str.replaceAll("»R½Liú²T¶ }msLRiV\r\n", "");
    		str = str.replaceAll("JÈÁLRiV }msLRiV \r\n", "");
    		str = str.replaceAll("BLiÉÓÁ ®©sLiÊÁLRiV\r\n", "");
    		str = str.replaceAll("ª«s¸R¶VxqsV=\r\n", "");
    		str = str.replaceAll("ÖÁLigRiª«sVV \r\n", "");
    		
    		
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }

}
