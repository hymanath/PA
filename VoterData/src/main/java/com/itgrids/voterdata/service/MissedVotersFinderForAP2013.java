package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.BoothVO;
import com.itgrids.voterdata.VO.VoterInfo;
import com.itgrids.voterdata.util.IConstants;

public class MissedVotersFinderForAP2013 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        try {

                File inputDir = new File(args[0]);
                File resultFile  = new File(args[0]+"/VotesMissed.txt");
                File BoothInfo  = new File(args[0]+"/BoothInfo.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                BufferedWriter boothInfoReader = new BufferedWriter(new FileWriter(BoothInfo));
                StringBuilder sb = null;
                StringBuilder sb2 = new StringBuilder();
                StringBuilder boothSB = new StringBuilder();
                int totalMissedVotes = 0;
                List<BoothVO> boothsInfoList = new ArrayList<BoothVO>(0);
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {
                	try{
                	stmt = conn.createStatement();
                	sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    String constituencyId = null;
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    String str1 = "Male Female Total\r\n";
                    String str2 = ":\r\n:\r\n:";
                    
                    int startNo = sb.indexOf(str1)+str1.length()+1;
                    String sbStr = sb.substring(startNo, sb.indexOf(str2,startNo)).trim();
                                        
                    if(sbStr.contains("\r\n"))
                    	sbStr = sbStr.substring(sb.indexOf("\r\n")+1);
                    
                    String [] fileName = input.getName().split("-");
                    
                    sbStr = sbStr.replaceAll("\\r\\n","").trim();
                    sbStr = sbStr.replaceAll("  "," ").trim();
                    String[] voters = sbStr.split(" ");
                    
                    List<Integer>snoList = new ArrayList<Integer>(0);
                    List<Integer>missedVoters = new ArrayList<Integer>(0);
                    
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                   
                    boothVO.setTotalVoters(new Integer(voters[4].trim()));
                    boothVO.setMaleVoters(new Integer(voters[2].trim()));
                    boothVO.setFemaleVoters(new Integer(voters[3].trim()));
                    boothVO.setOtherVoters(boothVO.getTotalVoters() - (boothVO.getMaleVoters()+boothVO.getFemaleVoters()));
                    boothVO.setStartingSerialNo(new Integer(voters[1].trim()));
                    boothVO.setEndingSerialNo(new Integer(voters[0].trim()));
                    
                    boothSB.append("Booth - "+boothVO.getPartNo()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\t Others - "+boothVO.getOtherVoters()+"\t Start SNO - "+boothVO.getStartingSerialNo()+"\tEnd SNO - "+boothVO.getEndingSerialNo()+"\n");
                    System.out.println("Booth - "+boothVO.getPartNo()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\t Others - "+boothVO.getOtherVoters()+"\t Start SNO - "+boothVO.getStartingSerialNo()+"\tEnd SNO - "+boothVO.getEndingSerialNo());
                    ResultSet rs = stmt.executeQuery("select sno from voter_temp where constituency_id = '"+constituencyId+"'" +
                    		" and booth_id = '"+boothVO.getPartNo()+"'");
                    while(rs.next())
                    	snoList.add(rs.getInt("sno"));
                    
                    for(int i=1;i<=boothVO.getEndingSerialNo();i++)
                    	if(!snoList.contains(i))
                    	{
                    		missedVoters.add(i);
                    		totalMissedVotes++;
                    	}
                    
                    boothVO.setInsertedVotes(snoList.size());
                    boothVO.setMissedVotes(boothVO.getEndingSerialNo() - boothVO.getInsertedVotes());
                    boothVO.setMissedVotesList(missedVoters);
                    
                    if(missedVoters.size() > 0)
                    	boothVO.setVotesMissed(true);
                    
                    boothsInfoList.add(boothVO);
                    if (pd != null) {
                        pd.close();
                    }
                	}catch (Exception e) {
                		e.printStackTrace();
                	}
        }
            boothInfoReader.write(boothSB.toString());
            boothInfoReader.close();
            sb2.append("Booth Details Of Missed Voters : ("+totalMissedVotes+")\n");
            sb2.append("--------------------------------------------------\n");
            
            int index = 1;
            for(BoothVO boothVO : boothsInfoList)
            {
            	if(boothVO.isVotesMissed())
            	{
            		sb2.append(index+") Booth Part No - "+boothVO.getPartNo()+"\tName - "+boothVO.getName()+"\n");
            		sb2.append("\tTotal Voters   : "+boothVO.getTotalVoters()+"\n");
            		sb2.append("\tInserted Votes : "+boothVO.getInsertedVotes()+"\n");
            		sb2.append("\tMissed Votes   : "+boothVO.getMissedVotes()+"\n");
            		sb2.append("\tMissed Votes SNo  : ");
            		for(int sno : boothVO.getMissedVotesList())
            			sb2.append(sno+", ");
            		sb2.append("\n");
            		index++;
            		totalMissedVotes = totalMissedVotes + boothVO.getMissedVotesList().size();
            	}
            }
            sb2.append("--------------------------------------------------\n\n");
            sb2.append("All Booth Details:\n");
            sb2.append("--------------------------------------------------\n");
           
            for(int j=0;j<boothsInfoList.size();j++)
            {
            	BoothVO boothVO = boothsInfoList.get(j);
        		sb2.append(j+1+") Booth Part No - "+boothVO.getPartNo()+"\tName - "+boothVO.getName()+"\n");
        		sb2.append("\tTotal Voters   : "+boothVO.getTotalVoters()+"\n");
        		sb2.append("\tInserted Votes : "+boothVO.getInsertedVotes()+"\n");
        		sb2.append("\tMissed Votes   : "+boothVO.getMissedVotes()+"\n");
        		sb2.append("\tMissed Votes SNo  : ");
        		for(int sno : boothVO.getMissedVotesList())
        			sb2.append(sno+", ");
        		sb2.append("\n");
            }
            sb2.append("--------------------------------------------------\n");
            outwriter.write(sb2.toString());
            outwriter.close();
            System.out.println(sb2.toString());
            
            List<VoterInfo> missedVotersList = getMissedVoters(boothsInfoList);
            if(missedVotersList != null && missedVotersList.size() > 0)
            {
            	int missedIndex = 0;
            	StringBuilder sb3 = new StringBuilder();
                BufferedWriter outwriter2 = new BufferedWriter(new FileWriter(new File(args[0]+"/MissedVoterDetails.txt")));
            	
            	for(VoterInfo info : missedVotersList)
            	{
            		sb3.append(++missedIndex+")\tBooth Id - "+info.getBoothNo()+"\t"
            				+"Booth - "+info.getBoothName()+"\t\t"+
            				"SNO - "+info.getsNo()+"\t"+
            				"Name - "+info.getVoterName()+"\t\t"+
            				"Voter ID - "+info.getVoterId()+"\t"+
            				"Age - "+info.getAge()+"\t"+
            				"House No - "+info.getHouseNumber()+"\t\t"+
            				"Gender - "+info.getSex()+"\t"+
            				"Relation name - "+info.getGuardianName()+"\t"+
            				"Relation - "+info.getGuardianRelation()+"\n");
            	}
            	System.out.println(sb3.toString());
            	outwriter2.write(sb3.toString());
            	outwriter2.close();
            	ConvertVoterDataFromPdfToText.saveVotersData(missedVotersList);
            }
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unused")
	public static List<VoterInfo> getMissedVoters(List<BoothVO> boothsInfoList)
    {
    	List<VoterInfo> missedList = new ArrayList<VoterInfo>(0);
    	try{
    		StringBuilder sb = null;
    		PDDocument pd = null;
    		//BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File(IConstants.DOWNLOAD_DIRECTORY+"VotesMissedData.txt")));
            
    		for(BoothVO boothVO : boothsInfoList)
    		{
    			if(boothVO.getMissedVotesList() != null && boothVO.getMissedVotesList().size() > 0){
    			try{
    				sb = new StringBuilder();
    				pd = PDDocument.load(new File(boothVO.getFileName()));
                    PDFTextStripper stripper = new PDFTextStripper();
                    sb.append(stripper.getText(pd));
                    sb = ReadVoterDataFromPdfForAP2013.formatText(sb);
                    sb = ReadVoterDataFromPdfForAP2013.formatText2(sb);
                    //outwriter.write(sb.toString());
                    
                    if(IConstants.PATTERN == 1)//House_no Missed Pattern
                    {
                    	Pattern p = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n\\s([0-9]*)");
                    	Matcher m = p.matcher(sb);
                    	while (m.find()) 
                        {
                    		try{
	                    		if(boothVO.getMissedVotesList().contains(new Long(m.group(1).replaceAll("\\r\\n","").trim()).intValue()))
	                    		{
		                    		VoterInfo voterInfo = new VoterInfo();
		                    		voterInfo.setsNo(new Long(m.group(1).replaceAll("\\r\\n","").trim()));
		                    		voterInfo.setSex(m.group(2).replaceAll("\\r\\n","").trim());
		                    		voterInfo.setVoterId(m.group(3).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setGuardianRelation(m.group(4).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
		                        	voterInfo.setAge(m.group(7).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setHouseNumber("0-00");
		                        	voterInfo.setVoterName(m.group(5).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setGuardianName(m.group(6).replaceAll("\\r\\n","").trim());
		                        	
		                        	voterInfo.setBoothNo(boothVO.getPartNo());
		                        	voterInfo.setBoothName(boothVO.getName());
		                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
		                        	voterInfo.setConstituency(boothVO.getConstituencyName());
		                        	
		                        	missedList.add(voterInfo);
	                    		}
                    		}catch(Exception e){
                    			e.printStackTrace();
                    		}
                        }
                    }
                    
                    else if(IConstants.PATTERN == 2)//Sex Field Missed Pattern
                    {
                    	Pattern p = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
                    	Matcher m = p.matcher(sb);
                    	while (m.find()) 
                        {
                    		try{
	                    		if(boothVO.getMissedVotesList().contains(new Long(m.group(1).replaceAll("\\r\\n","").trim()).intValue()))
	                    		{
		                    		VoterInfo voterInfo = new VoterInfo();
		                    		voterInfo.setsNo(new Long(m.group(1).replaceAll("\\r\\n","").trim()));
		                    		voterInfo.setVoterId(m.group(2).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setGuardianRelation(m.group(3).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
		                        	voterInfo.setAge(m.group(7).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setHouseNumber(m.group(6).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setVoterName(m.group(4).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setGuardianName(m.group(5).replaceAll("\\r\\n","").trim());
		                        	
		                        	if(voterInfo.getGuardianRelation().equalsIgnoreCase("Husband"))
		                        		voterInfo.setSex("Female");
		                        	else
		                        		voterInfo.setSex("Male");
		                        	voterInfo.setBoothNo(boothVO.getPartNo());
		                        	voterInfo.setBoothName(boothVO.getName());
		                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
		                        	voterInfo.setConstituency(boothVO.getConstituencyName());
		                        	
		                        	missedList.add(voterInfo);
	                    		}
                    		}catch(Exception e){
                    			e.printStackTrace();
                    		}
                        }
                    }
                    
                    else if(IConstants.PATTERN == 3)//Relative Name Field Missed Pattern
                    {
                    	Pattern p = Pattern.compile("([0-9]*)\\r\\nAge:\\r\\nSex:\\r\\n([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n\\s([0-9]*)");
                    	Matcher m = p.matcher(sb);
                    	while (m.find()) 
                        {
                    		try{
	                    		if(boothVO.getMissedVotesList().contains(new Long(m.group(1).replaceAll("\\r\\n","").trim()).intValue()))
	                    		{
		                    		VoterInfo voterInfo = new VoterInfo();
		                    		voterInfo.setsNo(new Long(m.group(1).replaceAll("\\r\\n","").trim()));
		                    		voterInfo.setSex(m.group(2).replaceAll("\\r\\n","").trim());
		                    		voterInfo.setVoterId(m.group(3).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setGuardianRelation(m.group(4).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
		                        	voterInfo.setAge(m.group(7).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setHouseNumber(m.group(6).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setVoterName(m.group(5).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setGuardianName("N/A");
		                        	
		                        	voterInfo.setBoothNo(boothVO.getPartNo());
		                        	voterInfo.setBoothName(boothVO.getName());
		                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
		                        	voterInfo.setConstituency(boothVO.getConstituencyName());
		                        	
		                        	missedList.add(voterInfo);
	                    		}
                    		}catch(Exception e){
                    			e.printStackTrace();
                    		}
                        }
                    }
                    
                    else if(IConstants.PATTERN == 4)//Common Pattern
                    {
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                        	int startIndex = sb.indexOf(sno.toString()+"\r\nAge:\r\nSex:");
                        	int endIndex = sb.indexOf(new Integer(sno.intValue()+1).toString()+"\r\nAge:\r\nSex:");
                        		
                        	if(endIndex == -1)
                        		endIndex = sb.indexOf("Age As On",startIndex);
                        	
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	if(reqStr.contains("Age As On"))
                        		reqStr = reqStr.substring(0,reqStr.indexOf("Age As On")).trim();
                        	
                        	String arr[] = reqStr.split("\\r\\n");
                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	voterInfo.setsNo(new Long(arr[0].trim()));
                        	voterInfo.setSex(arr[3].trim());
                        	voterInfo.setVoterId(arr[4].trim());
                        	voterInfo.setGuardianRelation(arr[6].replaceAll("'s Name:",""));
                        	voterInfo.setAge(arr[arr.length-1].trim());
                        	voterInfo.setHouseNumber(arr[arr.length-2].trim());
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	if(arr.length == 12)
                        	{
	                        	voterInfo.setVoterName(arr[8].trim());
	                        	voterInfo.setGuardianName(arr[9].trim());
                        	}
                        	else if(arr.length == 13)
                        	{
	                        	
                        		if(arr[8].endsWith(" "))
                        		{
                        			voterInfo.setVoterName(arr[8].trim()+" "+arr[9].trim());
                        			voterInfo.setGuardianName(arr[10].trim());
                        		}
                        		else
                        		{
	                        		voterInfo.setVoterName(arr[8].trim());
	                        		voterInfo.setGuardianName(arr[9].trim()+" "+arr[10].trim());
                        		}
                        	}
                        	else if(arr.length == 14)
                        	{
	                        	voterInfo.setVoterName(arr[8].trim()+" "+arr[9].trim());
	                        	voterInfo.setGuardianName(arr[10].trim()+" "+arr[11].trim());
                        	}
                        	else if(arr.length == 15)
                        	{
	                        	voterInfo.setVoterName(arr[8].trim()+" "+arr[9].trim());
	                        	voterInfo.setGuardianName(arr[10].trim()+" "+arr[11].trim());
	                        	voterInfo.setHouseNumber(arr[12].trim()+" "+arr[13].trim());
	                        	
                        	}
                        	missedList.add(voterInfo);
                    		}catch(Exception e)
                    		{
                    			e.printStackTrace();
                    		}
                        }
                    }
                    //outwriter.close();
                    pd.close();
    				
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		}
    		return missedList;
    	}catch (Exception e) {
    		System.out.println(e);
    		return missedList;
    	}
    }

}
