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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.VoterInfo;

public class ReadCantonmentVoterData {
	
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
    			String insertQuery = "INSERT INTO cantonment_data(ward_no, part_no, serial_no, age, house_no, name, relative_name) VALUES " +
    					" ('"+info.getConstituencyId()+"','"+info.getBoothNo()+"','"+info.getsNo()+"','"+info.getAge()+"','"+info.getHouseNumber()+"','"+info.getVoterName()+"','"+info.getGuardianName()+"')";
    			stmt.executeUpdate(insertQuery);
    			}catch(Exception e)
    			{
    				System.out.println("Exception Occured While Saving the Serial No ID -"+info.getsNo()+"("+info.getVoterName()+") In Booth - "+info.getBoothNo());
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
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                File inputDir = new File(args[0]);
                String voterInfo = "";
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
                    //sb = formatText(sb);
                    //sb = formatText2(sb);
                    
                    //outwriter.write(sb.toString());                   
                    String [] fileName = input.getName().split("-");
                    
                    String [] data = sb.toString().split("\n");
                    List<String[]> dataList = new ArrayList<String[]>(0);
                    
                    for(String row : data)
                    {
                    	try{
                    		String[] rowArr = row.trim().split(" ");
                    		if(rowArr.length > 2 && isNumber(rowArr[0]) && isNumber(rowArr[rowArr.length-1]))
                    			dataList.add(rowArr);
                    		
                    	}catch(Exception e)
                    	{
                    		e.printStackTrace();
                    	}
                    }
                    
                    /*for(String[] arr : dataList)
                	{
                    	System.out.println();
                    	for(String str : arr)
                    		System.out.print("\t"+str);
                	}*/
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(0);
                    for(String[] arr : dataList)
                	{
                    	try{
                    		voter = new VoterInfo();
                    		
                    		voter.setsNo(Long.valueOf(arr[0].trim()));
                    		voter.setHouseNumber(arr[1].trim());
                    		voter.setAge(arr[arr.length-1].trim());
                    		
                    		if(arr[arr.length-3].trim().length() == 1)
                    		{
                    			voter.setGuardianName(arr[arr.length-3].trim()+" "+arr[arr.length-2].trim());
                    			String voterName = "";
                    			int max = arr.length-4;
                    			for(int index=2;index<=max;index++)
                    				voterName = voterName + " "+arr[index];
                    			voter.setVoterName(voterName.trim());
                    		}
                    		else if(arr[arr.length-4].trim().length() == 1)
                    		{
                    			voter.setGuardianName(arr[arr.length-3].trim()+" "+arr[arr.length-3].trim()+" "+arr[arr.length-2].trim());
                    			String voterName = "";
                    			int max = arr.length-5;
                    			for(int index=2;index<=max;index++)
                    				voterName = voterName + " "+arr[index];
                    			voter.setVoterName(voterName.trim());
                    		}
                    		else if(arr.length >= 7)
                    		{
                    			voter.setGuardianName(arr[arr.length-3].trim()+" "+arr[arr.length-2].trim());
                    			String voterName = "";
                    			int max = arr.length-4;
                    			for(int index=2;index<=max;index++)
                    				voterName = voterName + " "+arr[index];
                    			voter.setVoterName(voterName.trim());
                    		}
                    		else
                    		{
                    			voter.setGuardianName(arr[arr.length-2].trim());
                    			String voterName = "";
                    			int max = arr.length-3;
                    			for(int index=2;index<=max;index++)
                    				voterName = voterName + " "+arr[index];
                    			voter.setVoterName(voterName.trim());
                    		}
                    		
                    		String vn = voter.getVoterName();
                    		String[] vnArr = vn.split(" ");
                    		if(vnArr[0].trim().contains("-") || vnArr[0].trim().contains("/"))
                    		{
                    			vn = "";
                    			for(int k=1;k<vnArr.length;k++)
                    				vn = vn+" "+vnArr[k];
                    			voter.setVoterName(vn.trim());
                    			voter.setHouseNumber(voter.getHouseNumber().trim()+" "+vnArr[0].trim());
                    		}
                    		
                    		voter.setBoothNo(fileName[3].replaceAll(".pdf","").trim());
	                        voter.setConstituencyId(fileName[1]);
	                        totalVotersCount++;
                    		voterInfoList.add(voter);
                    		
                    		voterInfo = i++ +"\tWard -- " + voter.getConstituencyId() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tVoter Name -- " + voter.getVoterName() + "\tRelative -- " + voter.getGuardianName() + "\tAge -- " + voter.getAge() + "\tHouse No -- " + voter.getHouseNumber() + "";
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
    
    public static boolean isNumber(String str)
    {
    	try{
    		Integer i = Integer.valueOf(str.trim());
    		if(i>0)
    		return true;
    		else
    		return false;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    }
    
}
