package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
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

public class AP2009SupIModificationReader {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static Integer saveVotersModificationData(List<VoterInfo> votersInfoList,String status)
    {
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		if(votersInfoList.size() == 0)
    			return null;
    		String snoStr = "";
    		for(VoterInfo VI : votersInfoList)
    			snoStr += ","+VI.getsNo();
    		snoStr = snoStr.substring(1);
    		
    		String selectQuery = "SELECT sno,voter_id from voter_temp where constituency_id = '"+votersInfoList.get(0).getConstituencyId()+"' and booth_id = '"+votersInfoList.get(0).getBoothNo()+"' and sno in ("+snoStr+")";
    		ResultSet rs = stmt.executeQuery(selectQuery);
    		Map<Long,String> idsMap = new HashMap<Long, String>(0);
    		
    		while(rs.next())
    			idsMap.put(Long.valueOf((Integer.valueOf(rs.getInt("sno"))).longValue()),rs.getString("voter_id"));
    		
    		for(VoterInfo info : votersInfoList)
    		{
    			try{
    			String insertQuery = "INSERT INTO voter_modification_temp(voter_id, status, constituency_id, constituency_name, booth_id) VALUES ('"+idsMap.get(info.getsNo())+"','"+status+"','"+info.getConstituencyId()+"','"+info.getConstituency()+"','"+info.getBoothNo()+"')";
    			stmt.executeUpdate(insertQuery);
    			}catch(Exception e)
    			{
    				System.out.println("Exception Occured While Saving the Voter ID -- "+idsMap.get(info.getVoterId())+" In Booth - "+info.getBoothNo()+" -- S.No - "+info.getsNo());
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
        Pattern addPattern = Pattern.compile("Sex:\\r\\n([a-zA-Z\\s]*)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([\\s0-9]*)\\r\\n");
        Pattern delPattern = Pattern.compile("Sex:\\r\\n([a-zA-Z\\s]*)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([a-zA-Z\\s\\d]*)\\r\\n");
        
        try {
                File inputDir = new File(args[0]);
                String voterInfo = "";
                StringBuilder sb2 = new StringBuilder();
                File resultFile  = new File(args[0]+"/VoterModificationData.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                
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
                    sb = ReadDataFromPdfForAP2009.formatText(sb);
                           
                    String addStr = sb.substring(sb.indexOf("Component - I  :  ADDITIONS LIST"), 
                    		sb.indexOf("Component - II  :  DELETIONS LIST"));
                    String delStr = sb.substring(sb.indexOf("Component - II  :  DELETIONS LIST"), 
                    		sb.indexOf("Component - III  :  CORRECTION LIST"));
                    outwriter.write(addStr+"\r\n"+delStr);
                    
                    String [] fileName = input.getName().split("-");
                    System.out.println("Reading ... "+input.getName());
                    Matcher addMatch = addPattern.matcher(addStr);
                    Matcher delMatch = delPattern.matcher(delStr);
                    VoterInfo voter = null;
                    List<VoterInfo> addList = new ArrayList<VoterInfo>(0);
                    List<VoterInfo> delList = new ArrayList<VoterInfo>(0);
                    
                    while (addMatch.find()) 
                    {
                        try{
                    	i++;
                        voter = new VoterInfo();
                                               
                        Long serialNo = 0L;
                        if(addMatch.group(4).trim().equalsIgnoreCase(""))
                        	serialNo = new Long(addMatch.group(3).trim());
                        else
                        	serialNo = new Long(addMatch.group(4).trim());
                        	
                        voter.setConstituencyId(fileName[0]);
                        voter.setConstituency(fileName[1]);
                        voter.setBoothNo(fileName[2]);
                        voter.setBoothName(fileName[3]);
                        voter.setsNo(serialNo);
                        
                        totalVotersCount++;
                        addList.add(voter);
                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\t Serial No -- " + voter.getsNo()+ "";
                        System.out.println(voterInfo);
                        sb2.append(voterInfo+"\n");
                    }catch (Exception e) {
                    	e.printStackTrace();
                    }
                    }
                    
                    while (delMatch.find()) 
                    {
                        try{
                    	i++;
                        voter = new VoterInfo();
                        Long serialNo = 0L;
                        
                        if(delMatch.group(4).trim().length() == 0)
                        	serialNo = new Long(delMatch.group(3).trim());
                        else
                        	serialNo = new Long(delMatch.group(4).trim().split("  ")[1]);
                        	
                        voter.setConstituencyId(fileName[0]);
                        voter.setConstituency(fileName[1]);
                        voter.setBoothNo(fileName[2]);
                        voter.setBoothName(fileName[3]);
                        voter.setsNo(serialNo);
                        
                        totalVotersCount++;
                        delList.add(voter);
                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\t Serial No -- " + voter.getsNo()+ "";
                        System.out.println(voterInfo);
                        sb2.append(voterInfo+"\n");
                    }catch (Exception e) {
                    	e.printStackTrace();
                    }
                    }
                    if (pd != null) {
                        pd.close();
                    }
                    saveVotersModificationData(addList, "Added");
                    saveVotersModificationData(delList, "Deleted");
            }
            outwriter.write(sb2.toString());
            System.out.println("Total No of Voters:" + totalVotersCount);
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
