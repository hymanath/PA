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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.VoterInfo;

public class SerialNumberUpdater {
	
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
    
    public static Integer updateSerialNUmber(Map<String,Long> votersSerailNoMap)
    {
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		for(Map.Entry<String,Long> entry : votersSerailNoMap.entrySet())
    		{
    			try{
    			String updateQuery = "update voter_temp set sno = "+entry.getValue()+" where voter_id = '"+entry.getKey()+"'";
    			int result = stmt.executeUpdate(updateQuery);
    			if(result > 0)
    				System.out.println(result+" record updated");
    			}catch(Exception e)
    			{
    				System.out.println("Exception Occured While Saving the Voter ID -"+entry.getKey());
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
				
			}
    	}
    }

    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int i = 0;
        
        Pattern p1 = Pattern.compile("\\r\\n([\\s0-9]*)\\r\\nElector's Name:\\r\\nSex:Age:\\r\\nHouse No:\\r\\n([A-Z\\d]*)\\r\\n");
        Pattern p2 = Pattern.compile("Elector's Name:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\nSex:\\s([a-zA-Z]*)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\,\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([\\s0-9]*)\\r\\n");
        Pattern p3 = Pattern.compile("([\\s0-9]*)\\r\\nAge:\\r\\nHouse No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n");
        Pattern p4 = Pattern.compile("\\r\\n([\\s0-9]*)\\r\\nAge:\\r\\nHouse No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n");
        
        try {

                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
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
                    Map<String,Long> votersSerailNoMap = new HashMap<String,Long>(0);
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();
                    sb.append(stripper.getText(pd));
                    
                    String pattern1Str = sb.substring(0,sb.indexOf("Component - I"));
                    String pattern2Str = sb.substring(sb.indexOf("Component - I"),sb.indexOf("Component - II"));
                    String pattern3Str = sb.substring(sb.indexOf("Component - II"),sb.indexOf("Component - III"));
                    String pattern4Str = sb.substring(sb.indexOf("Component - III"));
                    
                    String [] fileName = input.getName().split("-");
                    Matcher m1 = p1.matcher(pattern1Str);
                    Matcher m2 = p2.matcher(pattern2Str);
                    Matcher m3 = p3.matcher(pattern3Str);
                    Matcher m4 = p4.matcher(pattern4Str);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    outwriter.write(pattern2Str);
                    
                    while(m1.find())
                    {
                    	votersSerailNoMap.put(m1.group(2).replaceAll("\\r\\n","").trim(), Long.valueOf(m1.group(1).replaceAll("\\r\\n","").trim()));
                    }
                    while(m2.find())
                    {
                    	votersSerailNoMap.put(m2.group(1).replaceAll("\\r\\n","").trim(), Long.valueOf(m2.group(7).replaceAll("\\r\\n","").trim()));
                    }
                    
                    while(m3.find())
                    {
                    	votersSerailNoMap.put(m3.group(3).replaceAll("\\r\\n","").trim(), Long.valueOf(m3.group(1).replaceAll("\\r\\n","").trim()));
                    }
                    while(m4.find())
                    {
                    	System.out.println(m4.group(3).replaceAll("\\r\\n","").trim());
                    	String sNoStr = m4.group(1).trim();
                    	if(sNoStr.contains("\r\n"))
                    		sNoStr = sNoStr.substring(sNoStr.indexOf("\r\n"));
                    	sNoStr = sNoStr.replaceAll("\\r\\n","").trim();
                    	System.out.println(sNoStr);
                    	votersSerailNoMap.put(m4.group(3).replaceAll("\\r\\n","").trim(), Long.valueOf(sNoStr));
                    }
                    
                    if (pd != null) {
                        pd.close();
                    }
                    updateSerialNUmber(votersSerailNoMap);
                    //outwriter.write(sb.toString());
            }
            outwriter.write(sb2.toString());
            writer.close();
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
