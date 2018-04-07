package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
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

public class ReadUrbanWardVoterData2017 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static Integer saveVotersData(String wardNo,List<String> votersList)
    {
    	try{
    		Class.forName(JDBC_DRIVER);
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		for(String voterId : votersList)
    		{
    			try{
    			String insertQuery = "INSERT INTO ward_voter2(muncipality,ward_no,voter_id,local_election_body_id) VALUES ('Kakinada','"+wardNo+"','"+voterId+"','113')";
    			stmt.executeUpdate(insertQuery);
    			}catch(Exception e)
    			{
    				System.out.println("Exception Occured While Saving the Voter ID -"+voterId+" In Ward - "+wardNo);
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
        
        Pattern p = Pattern.compile("([A-Z\\d]*)\\r\\n");
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersListWithSerialNo.txt")));
                File inputDir = new File(args[0]);
                String voterInfo = "";
                
                for (File input : inputDir.listFiles(new FilenameFilter() {

                	public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {
                	String fileName = input.getName().replaceAll(".pdf","");
                    StringBuilder sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    
                    File resultFile  = new File(args[0]+"/"+fileName+"_VoterData.txt");
                    BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                    
                    outwriter.write(sb.toString());                   
                    
                    Matcher m = p.matcher(sb);
                    
                    List<String> list = new ArrayList<String>(0);
                    int wvIndex = 0;
                    while (m.find()) 
                    {
                        try{
                        String voterId = m.group(1).replaceAll("\\r\\n","").replaceAll(" ","").trim();
                        if(voterId.trim().length() > 5 && voterId.trim().length() <= 15)
                        {	
                            totalVotersCount++;
                            wvIndex++;
                            
	                        voterInfo = wvIndex+")\tWard No--"+fileName+"\tvoter ID--"+voterId;
	                        System.out.println(voterInfo);
	                        writer.write(voterInfo+"\n");
	                        list.add(voterId);
                        }
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                    //saveVotersData(fileName,list);
                    outwriter.close();
                    
                    BufferedReader br = new BufferedReader(new FileReader(resultFile.getAbsoluteFile()));
                    
                    String line = null;
                    
                    StringBuilder sbn = new StringBuilder();
                    
                    while ((line = br.readLine()) != null) {
                    	String str = line.trim();
        				//System.out.println(str);
        				if(str.contains("??? ?. ????:"))
        				{
        					writer2.write(getLastStr(str));
        					sbn.append(str);
        				}
        				if(str.contains("??? ??. :"))
        				{
        					writer2.write("\t"+getLastStr(str)+"\n");
        					sbn.append("-->"+str+"\n");
        				}
        				
        			}
                    //System.out.println(sbn);
                    if (pd != null) {
                        pd.close();
                    }
            }
            System.out.println("Total No of Voters:" + totalVotersCount);
            writer.close();
            writer2.close();
            
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
    
    public static StringBuilder formatText(StringBuilder builder)
    {
    	try{
    		String str = builder.toString();
    		str = str.replaceAll("??? ?? :??? ?????\n", "");
    		str = str.replaceAll("??? / ??? ?? :???  ?? ?????\n", "");
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }
    
    public static String getLastStr(String str)
    {
    	try{
    		
    		if(str == null)
    			return "";
    		
    		String[] strArr = str.trim().split(" ");
    		return strArr[strArr.length -1];
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return "";
    }
        
}
