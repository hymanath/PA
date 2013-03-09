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
import java.sql.ResultSet;
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
    
    public static List<Long> updateSerialNumber(String constituencyId, String boothId, Map<String,Long> votersSerailNoMap)
    {
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		List<Long> missedIds = new ArrayList<Long>(0);
    		Map<String,Long> serialNosMap = new HashMap<String, Long>(0);
    		Map<String,Long> diffSnosMap = new HashMap<String, Long>(0);
    		
    		String selectQuery = " Select voter_id, sno from voter_temp where constituency_id = '"+constituencyId+"' and booth_id = '"+boothId+"'";
    		ResultSet rs = stmt.executeQuery(selectQuery);
    		
    		while(rs.next())
    		{
    			serialNosMap.put(rs.getString("voter_id"),new Integer(rs.getInt("sno")).longValue());
    		}
    		
    		for(Map.Entry<String,Long> entry : votersSerailNoMap.entrySet())
    		{
    			String voterId = entry.getKey();
    			Long sno = entry.getValue();
    			
    			Long sno2 = serialNosMap.get(voterId);
    			if(sno2 == null)
    				missedIds.add(sno);
    			else if(!sno.equals(sno2))
    				diffSnosMap.put(voterId, sno);
    			
    		}
    		if(diffSnosMap.size() > 0)
    		{
    			System.out.println("In Booth : "+boothId+" "+diffSnosMap.size()+" Voter SNOs will be Updated");
	    		for(Map.Entry<String,Long> entry : diffSnosMap.entrySet())
	    		{
	    			try{
	    			String updateQuery = "update voter_temp set sno = "+entry.getValue()+" where voter_id = '"+entry.getKey()+"'";
	    			int result = stmt.executeUpdate(updateQuery);
	    			
	    			if(result == 0)
	    				missedIds.add(entry.getValue());
	    				
	    			}catch(Exception e)
	    			{
	    				System.out.println("Exception Occured While Saving the Voter ID -- "+entry.getKey()+" -- "+entry.getValue());
	    				System.out.println("Exception is -"+e);
	    			}
	    		}
    		}
    		
    		return missedIds;
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
        
        Pattern p1 = Pattern.compile("([\\s0-9]*)\\r\\nElector's Name:\\r\\nSex:Age:\\r\\nHouse No:\\r\\n([A-Z\\d]*)\\r\\n");
        Pattern p2 = Pattern.compile("Elector's Name:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\nSex:\\s([a-zA-Z]*)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([\\s0-9]*)\\r\\n");
        Pattern p3 = Pattern.compile("([\\s0-9]*)\\r\\nAge:\\r\\nHouse No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n");
        Pattern p4 = Pattern.compile("\\r\\n([\\s0-9]*)\\r\\nAge:\\r\\nHouse No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n");
        
        try {

                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                File inputDir = new File(args[0]);
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
                    
                    System.out.println("Reading "+input.getName()+"..........");
                    
                    while(m1.find())
                    {
                    	try{
                    		String sno = m1.group(1).trim();
                    		while(sno.contains("\n"))
                    			sno = sno.substring(sno.indexOf("\n")).trim();
                    		votersSerailNoMap.put(m1.group(2).replaceAll("\\r\\n","").trim(), Long.valueOf(sno.replaceAll("\\r\\n","").trim()));
                    	}catch (Exception e) {
                    		System.out.println(m1.group(1));
                    		System.out.println("Exception in M1");
                    	}
                    }
                    while(m2.find())
                    {
                    	try{
                    	votersSerailNoMap.put(m2.group(1).replaceAll("\\r\\n","").trim(), Long.valueOf(m2.group(7).replaceAll("\\r\\n","").trim()));
                    	}catch (Exception e) {
                    		System.out.println("Exception in M2");
                    	}
                    }
                    
                    while(m3.find())
                    {
                    	try{
                    		String sno = m3.group(1).replaceAll("\\r\\n","").trim();
                    		if(sno.contains(" "))
                    			sno = sno.substring(sno.indexOf(" ")+1);
                    		votersSerailNoMap.put(m3.group(3).replaceAll("\\r\\n","").trim(), Long.valueOf(sno));
                    	
                    }catch (Exception e) {
                    	System.out.println("Exception in M3");
                    }
                    }
                    while(m4.find())
                    {
                    	try{
                    	String sNoStr = m4.group(1).trim();
                    	while(sNoStr.contains("\n"))
                    		sNoStr = sNoStr.substring(sNoStr.indexOf("\n")).trim();
                    	votersSerailNoMap.put(m4.group(3).replaceAll("\\r\\n","").trim(), Long.valueOf(sNoStr));
                    	}catch (Exception e) {
                    		System.out.println("Exception in M4");
                    	}
                    }
                    
                    if (pd != null) {
                        pd.close();
                    }
                    
                    List<Long>missedIds =  updateSerialNumber(fileName[0],fileName[2],votersSerailNoMap);
                    for(long k = 1L;k<=votersSerailNoMap.size();k++)
                    {
                    	if(!votersSerailNoMap.containsValue(k))
                    		System.out.println(k+" Not Read");
                    }
                    sb2.append("\nBooth - "+fileName[2]+" : Total - ("+votersSerailNoMap.size()+")\tMissed Voters -("+missedIds.size()+")\n");
                    if(missedIds.size() > 0)
                    {
                    	sb2.append("\tMissed Voters SNO : ");
                    	for(Long sno : missedIds)
                    		sb2.append(sno+", ");
                    }
            }
            outwriter.write(sb2.toString());
            writer.close();
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
