package com.itgrids.voterdata;

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

public class MissedVotersFinder {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        Pattern p = Pattern.compile("Male Female Total\\r\\n([\\s0-9a-zA-Z]*)\\r\\n:");
       
        try {

                File inputDir = new File(args[0]);
                File resultFile  = new File(args[0]+"/VotesMissed.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                StringBuilder sb = null;
                StringBuilder sb2 = new StringBuilder();
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
                	
                	stmt = conn.createStatement();
                	sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    String constituencyId = null;
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    sb.indexOf("Draft Photo Electoral Roll",100);
                    sb.delete(sb.indexOf("Draft Photo Electoral Roll",100), sb.length()-1);
                    sb.delete(0,sb.indexOf("Male Female Total"));
                    
                    //System.out.println(sb.toString());
                                       
                    String [] fileName = input.getName().split("-");
                    Matcher m = p.matcher(sb);
                    
                    m.find();
                    String str = m.group(1).replaceAll("\\r\\n","").trim();
                    System.out.println(str);
                    String[] voters = str.split(" ");
                    
                    List<Integer>snoList = new ArrayList<Integer>(0);
                    List<Integer>missedVoters = new ArrayList<Integer>(0);
                    
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setTotalVoters(new Integer(voters[8]));
                    boothVO.setMaleVoters(new Integer(voters[4]));
                    boothVO.setFemaleVoters(new Integer(voters[6]));
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    
                    ResultSet rs = stmt.executeQuery("select sno from voter_temp where constituency_id = '"+constituencyId+"'" +
                    		" and booth_id = '"+boothVO.getPartNo()+"'");
                    while(rs.next())
                    	snoList.add(rs.getInt("sno"));
                    
                    for(int i=1;i<=boothVO.getTotalVoters();i++)
                    	if(!snoList.contains(i))
                    	{
                    		missedVoters.add(i);
                    		totalMissedVotes++;
                    	}
                    
                    boothVO.setInsertedVotes(snoList.size());
                    boothVO.setMissedVotes(boothVO.getTotalVoters() - boothVO.getInsertedVotes());
                    boothVO.setMissedVotesList(missedVoters);
                    
                    if(missedVoters.size() > 0)
                    	boothVO.setVotesMissed(true);
                    
                    boothsInfoList.add(boothVO);
                    if (pd != null) {
                        pd.close();
                    }
            }
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
    
    public static List<VoterInfo> getMissedVoters(List<BoothVO> boothsInfoList)
    {
    	List<VoterInfo> missedList = new ArrayList<VoterInfo>(0);
    	try{
    		StringBuilder sb = null;
    		PDDocument pd = null;
    		File resultFile  = new File("E:/Voters/VotesMissed2.txt");
            BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
    		for(BoothVO boothVO : boothsInfoList)
    		{
    			if(boothVO.getMissedVotesList() != null && boothVO.getMissedVotesList().size() > 0){
    			try{
    				sb = new StringBuilder();
    				pd = PDDocument.load(new File(boothVO.getFileName()));
                    PDFTextStripper stripper = new PDFTextStripper();
                    sb.append(stripper.getText(pd));
                    System.out.println(sb.toString());
                    
                    for(Integer sno : boothVO.getMissedVotesList())
                    {
                    	int startIndex = sb.indexOf(sno.toString()+" \r\nAge:");
                    	int endIndex = sb.indexOf(new Integer(sno.intValue()+1).toString()+" \r\nAge:");
                    	if(endIndex == -1)
                    		endIndex = sb.indexOf("Age As On",startIndex);
                    	String reqStr = sb.substring(startIndex,endIndex).trim();
                    	System.out.println(reqStr);
                    	String arr[] = reqStr.split("\\r\\n");
                    	for(int k=0;k<arr.length;k++)
                    		System.out.println(+k+" -- "+arr[k]);
                    	
                    	VoterInfo voterInfo = new VoterInfo();
                    	voterInfo.setsNo(new Long(arr[0].trim()));
                    	voterInfo.setSex(arr[1].split(" ")[2]);
                    	voterInfo.setVoterId(arr[2].trim());
                    	voterInfo.setGuardianRelation(arr[4].replaceAll("'s Name:",""));
                    	voterInfo.setAge(arr[arr.length-1].trim());
                    	voterInfo.setHouseNumber(arr[arr.length-2].trim());
                    	voterInfo.setBoothNo(boothVO.getPartNo());
                    	voterInfo.setBoothName(boothVO.getName());
                    	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                    	voterInfo.setConstituency(boothVO.getConstituencyName());
                    	
                    	if(arr[6].endsWith(" "))
                    	{
                    		voterInfo.setVoterName(arr[6].trim()+" "+arr[7].trim());
                    		if(arr[8].endsWith(" ") && !arr[9].trim().equalsIgnoreCase(voterInfo.getHouseNumber()))
                    			voterInfo.setGuardianName(arr[8].trim()+" "+arr[9].trim());
                    		else
                    			voterInfo.setGuardianName(arr[8].trim());
                    	}
                    	else
                    	{
                    		voterInfo.setVoterName(arr[6].trim());
                    		if(arr[7].endsWith(" ") && !arr[8].trim().equalsIgnoreCase(voterInfo.getHouseNumber()))
                    			voterInfo.setGuardianName(arr[7].trim()+" "+arr[8].trim());
                    		else
                    			voterInfo.setGuardianName(arr[7].trim());
                    	}
                    	
                    	if(voterInfo.getHouseNumber().equalsIgnoreCase(voterInfo.getGuardianName()))
                    		voterInfo.setHouseNumber("0-00");
                    	missedList.add(voterInfo);
                    	//outwriter.write(sb.toString());
                        //outwriter.close();
                    }
                    
                    pd.close();
    				
    			}catch(Exception e)
    			{}
    		}
    		}
    		return missedList;
    	}catch (Exception e) {
    		System.out.println(e);
    		return missedList;
    	}
    }

}
