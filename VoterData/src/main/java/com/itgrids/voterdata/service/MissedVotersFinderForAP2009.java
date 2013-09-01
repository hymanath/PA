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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.BoothVO;
import com.itgrids.voterdata.VO.VoterInfo;
import com.itgrids.voterdata.util.IConstants;

public class MissedVotersFinderForAP2009 {
	
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
                StringBuilder sb1 = new StringBuilder();
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
                    sb1.append(stripper.getText(pd));
                    
                    String str1 = "Supplements- I\r\nSupplements - I";
                    String str2 = "Basic roll of Special Summary";
                    String str3 = "Component - II  :  DELETIONS LIST";
                    String str4 = "Number of \r\nDeletions";
                    
                    sb.delete(0,sb.indexOf(str1)+str1.length()+1);
                    sb.delete(sb.indexOf(str2), sb.length()-1);
                    sb = new StringBuilder(sb.toString().trim());
                    
                    sb1.delete(0,sb1.indexOf(str3)+str3.length()+1);
                    sb1.delete(sb1.indexOf(str4), sb1.length()-1);
                    for(;;)
                    {
                    	if(sb1.toString().contains("Men"))
                    		sb1.delete(0,sb1.indexOf("Men")+5);
                    	else
                    		break;
                    }
                    sb1 = new StringBuilder(sb1.toString().trim());
                    int delCount = Integer.parseInt(sb1.toString().split(" ")[0]);
                    
                    String[] sbArr = sb.toString().split("\\r\\n");
                    String sbStr1 = sbArr[1].trim();
                    String sbStr2 = sbArr[sbArr.length-1].trim();
                    
                    String [] fileName = input.getName().split("-");
                    String[] voters = sbStr1.split(" ");
                    
                    List<Integer>snoList = new ArrayList<Integer>(0);
                    List<Integer>missedVoters = new ArrayList<Integer>(0);
                    
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setTotalVoters(new Integer(voters[4]));
                    boothVO.setMaleVoters(new Integer(voters[0]));
                    boothVO.setFemaleVoters(new Integer(voters[2]));
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    
                    if(delCount == 0)
                    	boothVO.setEndingSerialNo(Integer.parseInt(voters[4]));
                    else
                    	boothVO.setEndingSerialNo(Integer.parseInt(sbStr2.split(" ")[sbStr2.split(" ").length-1])+Integer.parseInt(voters[4]));
                    
                    boothSB.append("Booth - "+boothVO.getPartNo()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\tSerial No - "+boothVO.getEndingSerialNo()+"\n");
                    System.out.println("Booth - "+boothVO.getPartNo()+"\tTotal - "+boothVO.getTotalVoters()+"\tMale - "+boothVO.getMaleVoters()+"\tFemale - "+boothVO.getFemaleVoters()+"\tSerial No - "+boothVO.getEndingSerialNo());
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
            	ReadDataFromPdfForAP2009.saveVotersData(missedVotersList);
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
    		for(BoothVO boothVO : boothsInfoList)
    		{
    			if(boothVO.getMissedVotesList() != null && boothVO.getMissedVotesList().size() > 0){
    			try{
    				sb = new StringBuilder();
    				pd = PDDocument.load(new File(boothVO.getFileName()));
                    PDFTextStripper stripper = new PDFTextStripper();
                    sb.append(stripper.getText(pd));
                    sb = ReadDataFromPdfForAP2009.formatText(sb);
                    //System.out.println(sb.toString());
                    
                    if(IConstants.PATTERN == 1)
                    {
                    	Pattern p = Pattern.compile("([\\s0-9]*)\\r\\nElector's Name:\\r\\nSex:\\r\\nAge:\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([a-zA-Z\\s]*)\\r\\n");
                    	Matcher m = p.matcher(sb);
                    	Random random = new Random();
                    	while (m.find()) 
                        {
                    		try{
                    			String serialNo = m.group(1).replaceAll("\\r\\n","").trim();
                    			if(serialNo.contains(" "))
	                    			serialNo = serialNo.split(" ")[1];
                    		if(boothVO.getMissedVotesList().contains(Long.valueOf(serialNo).intValue()))
                    		{
	                    		VoterInfo voterInfo = new VoterInfo();
	                    		voterInfo.setsNo(Long.valueOf(serialNo));
	                    		voterInfo.setVoterId("ABC"+random.nextInt(10000000));
	                        	voterInfo.setGuardianRelation(m.group(4).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
	                        	voterInfo.setAge(m.group(6).replaceAll("\\r\\n","").trim());
	                        	voterInfo.setHouseNumber(m.group(5).replaceAll("\\r\\n","").trim());
	                        	voterInfo.setBoothNo(boothVO.getPartNo());
	                        	voterInfo.setBoothName(boothVO.getName());
	                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
	                        	voterInfo.setConstituency(boothVO.getConstituencyName());
	                        	voterInfo.setVoterName(m.group(2).replaceAll("\\r\\n","").trim());
	                        	voterInfo.setGuardianName(m.group(3).replaceAll("\\r\\n","").trim());
	                        	
	                        	if(m.group(7).replaceAll("\\r\\n","").trim().contains("Female"))
	                        		voterInfo.setSex("Female");
	                        	else
	                        		voterInfo.setSex("Male");
	                        	
	                        	if(voterInfo.getHouseNumber().equalsIgnoreCase(voterInfo.getGuardianName()))
	                        		voterInfo.setHouseNumber("0-00");
	                        	
	                        	missedList.add(voterInfo);
                    		}
                    		}catch(Exception e){e.printStackTrace();}
                    		
                        }
                    }
                    else if(IConstants.PATTERN == 2)
                    {
                    	Pattern p = Pattern.compile("Age:\\r\\nHouse No:\\r\\n\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\nSex:\\r\\n([a-zA-Z\\s]*)\\r\\n([0-9\\-_/A-Za-z\\.\\s\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\\\]*)\\r\\n([\\s0-9]*)\\r\\n([\\s0-9]*)\\r\\n");
                    	Matcher m = p.matcher(sb);
                    	Random random = new Random();
                    	while (m.find()) 
                        {
                    		try{
                    			
                    			String serialNo = m.group(7).replaceAll("\\r\\n","").trim();
                    			if(serialNo.contains(" "))
	                    			serialNo = serialNo.split(" ")[1];
                    		if(boothVO.getMissedVotesList().contains(Long.valueOf(serialNo).intValue()))
                    		{
	                    		VoterInfo voterInfo = new VoterInfo();
	                    		voterInfo.setsNo(Long.valueOf(serialNo));
	                    		voterInfo.setVoterId(m.group(2).replaceAll("\\r\\n","").trim());
	                        	voterInfo.setGuardianRelation(m.group(1).replaceAll("\\r\\n","").trim().replaceAll("'s Name:",""));
	                        	voterInfo.setAge(m.group(6).trim().split("\n")[1].trim());
	                        	voterInfo.setHouseNumber(m.group(6).trim().split("\n")[0].trim());
	                        	voterInfo.setSex(m.group(5).replaceAll("\\r\\n","").trim());
	                        	voterInfo.setBoothNo(boothVO.getPartNo());
	                        	voterInfo.setBoothName(boothVO.getName());
	                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
	                        	voterInfo.setConstituency(boothVO.getConstituencyName());
	                        	
	                        	if(!m.group(3).contains("\r\n"))
	                        	{
	                        		voterInfo.setVoterName(m.group(3).replaceAll("\\r\\n","").trim());
		                        	voterInfo.setGuardianName(m.group(4).replaceAll("\\r\\n","").trim());
	                        	}
	                        	else {
	                        		String[] nameArr = m.group(3).trim().split("\\r\\n");
	                        		voterInfo.setVoterName(m.group(3).replaceAll("\\r\\n","").trim().replaceAll(nameArr[nameArr.length-1].trim(), "").trim());
	                        		voterInfo.setGuardianName(nameArr[nameArr.length-1].trim()+" "+m.group(4).replaceAll("\\r\\n","").trim());
								}
	                        	
	                        	if(voterInfo.getHouseNumber().equalsIgnoreCase(voterInfo.getGuardianName()))
	                        		voterInfo.setHouseNumber("0-00");
	                        	
	                        	missedList.add(voterInfo);
                    		}
                    		}catch(Exception e){e.printStackTrace();}
                    		
                        } 
                    }
                    
                    else if(IConstants.PATTERN == 3)
                    {
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                    		int startIndex = sb.indexOf(" "+Integer.valueOf(sno-1).toString()+" \r\n\r\nAge:");
                    		int endIndex = sb.indexOf(" "+sno.toString()+" ",startIndex);
                    		
                        	
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	System.out.println(reqStr);
                        	
                        	String arr[] = reqStr.split("\\r\\n");
                        	int kk = 0;
                        	for(String str2 : arr)
                        		System.out.println(++kk +" --- "+str2);
                        	
                        	                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	int genderIndex = 0;
                        	int rsIndex = 0;
                        	
                        	voterInfo.setsNo(new Long(arr[0].trim())+1);
                        	voterInfo.setVoterId(arr[7].trim());
                        	
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	voterInfo.setVoterName(arr[8].trim()+" "+arr[9].trim());
                        	voterInfo.setAge(arr[15].trim());
                        	voterInfo.setHouseNumber(arr[14].trim());
                        	voterInfo.setSex(arr[13].trim());
                        	voterInfo.setGuardianRelation(arr[5].trim().replaceAll("'s Name:",""));
                        	voterInfo.setGuardianName(arr[10].trim()+" "+arr[11].trim());
                        	
                        	missedList.add(voterInfo);
                    		}catch (Exception e) {
                    			e.printStackTrace();
                    		}
                        }
                    }
                    else if(IConstants.PATTERN == 4)
                    {
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                    		int startIndex = sb.indexOf(" "+Integer.valueOf(sno-1).toString()+" \r\n\r\nAge:");
                    		int endIndex = sb.indexOf(" "+sno.toString()+" ",startIndex);
                    		
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	System.out.println(reqStr);
                        	
                        	String arr[] = reqStr.split("\\r\\n");
                        	int kk = 0;
                        	for(String str2 : arr)
                        		System.out.println(++kk +" --- "+str2);
                        	
                        	                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	int genderIndex = 0;
                        	int rsIndex = 0;
                        	
                        	voterInfo.setsNo(new Long(arr[0].trim())+1);
                        	voterInfo.setVoterId(arr[7].trim());
                        	voterInfo.setGuardianRelation(arr[5].trim().replaceAll("'s Name:",""));
                        	
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	if(arr.length == 14)
                        	{
	                        	voterInfo.setVoterName(arr[8].trim());
	                        	voterInfo.setAge(arr[13].trim());
	                        	voterInfo.setHouseNumber(arr[12].trim());
	                        	voterInfo.setSex(arr[11].trim());
	                        	voterInfo.setGuardianRelation(arr[5].trim().replaceAll("'s Name:",""));
	                        	voterInfo.setGuardianName(arr[9].trim());
                        	}
                        	if(arr.length == 15)
                        	{
                        		if(arr[8].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[8].trim()+" "+arr[9].trim());
		                        	voterInfo.setAge(arr[14].trim());
		                        	voterInfo.setHouseNumber(arr[13].trim());
		                        	voterInfo.setSex(arr[12].trim());
		                        	voterInfo.setGuardianName(arr[10].trim());
                        		}
                        		else if(arr[9].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[8].trim());
		                        	voterInfo.setAge(arr[14].trim());
		                        	voterInfo.setHouseNumber(arr[13].trim());
		                        	voterInfo.setSex(arr[12].trim());
		                        	voterInfo.setGuardianName(arr[9].trim()+" "+arr[10].trim());
                        		}
                        	}
                        	missedList.add(voterInfo);
                    		}catch (Exception e) {
                    			e.printStackTrace();
                    		}
                        } 
                    }
                    else if(IConstants.PATTERN == 5)
                    {//Pattern For Voter Name Missing
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                    		int startIndex = sb.indexOf(" "+Integer.valueOf(sno).toString()+"\r\nElector's Name:");
                    		int endIndex = sb.indexOf(" "+Integer.valueOf(sno+1).toString()+"\r\nElector's Name:",startIndex);
                    		
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	System.out.println(reqStr);
                        	
                        	String arr[] = reqStr.split("\\r\\n");
                        	                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	voterInfo.setsNo(new Long(arr[0].trim()));
                        	voterInfo.setVoterId(arr[5].trim());
                        	voterInfo.setGuardianRelation(arr[7].trim().replaceAll("'s Name:",""));
                        	voterInfo.setVoterName(arr[6].trim());
                        	voterInfo.setAge(arr[9].trim());
                        	voterInfo.setHouseNumber(arr[8].trim());
                        	voterInfo.setSex(arr[10].trim());
                        	voterInfo.setGuardianName(arr[6].trim());
                        	
                        	missedList.add(voterInfo);
                    		}catch (Exception e) {
                    			e.printStackTrace();
                    		}
                        } 
                    }
                    else if(IConstants.PATTERN == 6)
                    {
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                    		int startIndex = sb.indexOf(" "+Integer.valueOf(sno).toString()+"\r\nElector's Name:");
                    		int endIndex = sb.indexOf(" "+Integer.valueOf(sno+1).toString()+"\r\nElector's Name:",startIndex);
                    		
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	//System.out.println(reqStr);
                        	int midIndex = reqStr.indexOf("'s Name:",25);
                        	
                        	String reqStr1 = reqStr.substring(0,midIndex).trim();
                        	String reqStr2 = reqStr.substring(midIndex+8,reqStr.length()).trim();
                        	
                        	String arr[] = reqStr1.split("\\r\\n");
                        	String arr2[] = reqStr2.split("\\r\\n");
                        	
                        	/*int kk = 0;
                        	for(String str2 : arr)
                        		System.out.println(++kk +" --- "+str2);*/
                        	
                        	                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	voterInfo.setsNo(new Long(arr[0].trim()));
                        	voterInfo.setVoterId(arr[5].trim());
                        	
                        	voterInfo.setHouseNumber(arr2[0].trim());
                        	voterInfo.setAge(arr2[1].trim());
                        	voterInfo.setSex(arr2[2].trim());
                        	
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	if(arr.length == 9)
                        	{
	                        	voterInfo.setVoterName(arr[6].trim());
	                        	voterInfo.setGuardianName(arr[7].trim());
	                        	voterInfo.setGuardianRelation(arr[8].trim());
                        	}
                        	if(arr.length == 10)
                        	{
                        		if(arr[6].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[6].trim()+" "+arr[7].trim());
		                        	voterInfo.setGuardianName(arr[8].trim());
                        		}
                        		else if(arr[7].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[6].trim());
		                        	voterInfo.setGuardianName(arr[7].trim()+" "+arr[8].trim());
                        		}
                        		voterInfo.setGuardianRelation(arr[9].trim());
                        	}
                        	missedList.add(voterInfo);
                    		}catch (Exception e) {
                    			e.printStackTrace();
                    		}
                        } 
                    }
                    
                    else if(IConstants.PATTERN == 7)
                    {
                    	Random random = new Random();
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                    		int startIndex = sb.indexOf(" "+Integer.valueOf(sno-1).toString()+" \r\n\r\nAge:");
                    		int endIndex = sb.indexOf(" "+sno.toString()+" ",startIndex);
                    		
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	System.out.println(reqStr);
                        	reqStr = reqStr.replaceAll("Elector's Name: ", "Elector's Name: \r\n");
                        	
                        	String arr[] = reqStr.split("\\r\\n");
                        	int kk = 0;
                        	for(String str2 : arr)
                        		System.out.println(++kk +" --- "+str2);
                        	
                        	                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	int genderIndex = 0;
                        	int rsIndex = 0;
                        	
                        	voterInfo.setsNo(new Long(arr[0].trim())+1);
                        	voterInfo.setVoterId("ABC"+random.nextInt(10000000));
                        	voterInfo.setGuardianRelation(arr[5].trim().replaceAll("'s Name:",""));
                        	
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	/*if(arr.length == 13)
                        	{
	                        	voterInfo.setVoterName(arr[8].trim());
	                        	voterInfo.setAge(arr[13].trim());
	                        	voterInfo.setHouseNumber(arr[12].trim());
	                        	voterInfo.setSex(arr[11].trim());
	                        	voterInfo.setGuardianRelation(arr[5].trim().replaceAll("'s Name:",""));
	                        	voterInfo.setGuardianName(arr[9].trim());
                        	}*/
                        	if(arr.length == 14)
                        	{
                        		if(arr[7].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[7].trim()+" "+arr[8].trim());
		                        	voterInfo.setAge(arr[13].trim());
		                        	voterInfo.setHouseNumber(arr[12].trim());
		                        	voterInfo.setSex(arr[11].trim());
		                        	voterInfo.setGuardianName(arr[9].trim());
                        		}
                        		else if(arr[9].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[7].trim());
		                        	voterInfo.setAge(arr[13].trim());
		                        	voterInfo.setHouseNumber(arr[12].trim());
		                        	voterInfo.setSex(arr[11].trim());
		                        	voterInfo.setGuardianName(arr[8].trim()+" "+arr[9].trim());
                        		}
                        	}
                        	missedList.add(voterInfo);
                    		}catch (Exception e) {
                    			e.printStackTrace();
                    		}
                        } 
                    }
                    else if(IConstants.PATTERN == 8)
                    {
                    	Random random = new Random();
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                    		int startIndex = sb.indexOf(" "+Integer.valueOf(sno-1).toString()+" ");
                    		int endIndex = sb.indexOf(" "+Integer.valueOf(sno).toString()+" ",startIndex);
                    		
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	reqStr = reqStr.substring(reqStr.indexOf("House No:")+10).trim();
                        	System.out.println(reqStr);
                        	
                        	String arr[] = reqStr.split("\\r\\n");
                        	int kk = 0;
                        	for(String str2 : arr)
                        		System.out.println(++kk +" --- "+str2);
                        	
                        	                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	
                        	voterInfo.setsNo(sno.longValue());
                        	voterInfo.setVoterId(arr[2].trim());
                        	voterInfo.setGuardianRelation(arr[0].trim().replaceAll("'s Name:",""));
                        	
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	if(arr.length == 9)
                        	{
	                        	voterInfo.setVoterName(arr[3].trim());
	                        	voterInfo.setAge(arr[10].trim());
	                        	voterInfo.setHouseNumber(arr[9].trim());
	                        	voterInfo.setSex(arr[8].trim());
	                        	voterInfo.setGuardianName(arr[4].trim());
                        	}
                        	if(arr.length == 10)
                        	{
                        		if(arr[3].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[3].trim()+" "+arr[4].trim());
		                        	voterInfo.setGuardianName(arr[5].trim());
                        		}
                        		else if(arr[4].endsWith(" "))
                        		{
		                        	voterInfo.setVoterName(arr[3].trim());
		                        	voterInfo.setGuardianName(arr[4].trim()+" "+arr[5].trim());
                        		}
                        		voterInfo.setAge(arr[9].trim());
	                        	voterInfo.setHouseNumber(arr[8].trim());
	                        	voterInfo.setSex(arr[7].trim());
                        	}
                        	if(arr.length == 11)
                        	{
                        		voterInfo.setVoterName(arr[3].trim()+" "+arr[4].trim());
	                        	voterInfo.setGuardianName(arr[5].trim()+" "+arr[6].trim());
                        		voterInfo.setAge(arr[10].trim());
	                        	voterInfo.setHouseNumber(arr[9].trim());
	                        	voterInfo.setSex(arr[8].trim());
                        	}
                        	missedList.add(voterInfo);
                    		}catch (Exception e) {
                    			e.printStackTrace();
                    		}
                        } 
                    }
                    
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
