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

public class MissedVotersFinderForKarnataka {
	
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
                    sb = ReadDataFromPdfForKarnataka.formatText(sb);
                    
                    String str1 = "Male Female Others Total";
                    String str2 = " No.,Name and Reservation Status of  Assembly";
                    sb.delete(0,sb.indexOf(str1)+str1.length()+2);
                    sb.delete(sb.indexOf(str2), sb.length()-1);
                    //System.out.println(sb);
                    
                    String str = sb.toString().trim();
                    if(str.contains("\r\n"))
                    	str = str.substring(0,str.indexOf("\r\n"));
                    
                    String[] voters = str.split(" ");
                    
                    String [] fileName = input.getName().split("-");
                    List<Integer>snoList = new ArrayList<Integer>(0);
                    List<Integer>missedVoters = new ArrayList<Integer>(0);
                    
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setTotalVoters(new Integer(voters[5].trim()));
                    boothVO.setMaleVoters(new Integer(voters[2].trim()));
                    boothVO.setFemaleVoters(new Integer(voters[3].trim()));
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    boothVO.setEndingSerialNo(Integer.valueOf(voters[1].trim()));
                    
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
    	Random random = new Random();
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
                    sb = ReadDataFromPdfForKarnataka.formatText(sb);
                    
                    if(IConstants.PATTERN == 1)
                    {
                    	for(Integer sno : boothVO.getMissedVotesList())
                        {
                    		try{
                        	int startIndex = sb.indexOf(sno.toString()+"\r\nName :");
                        	int endIndex = sb.indexOf(new Integer(sno.intValue()+1).toString()+"\r\nName :");
                        	
                        	if(endIndex == -1)
                        		endIndex = sb.indexOf("'s Name:", startIndex)+8;
                        	
                        	String reqStr = sb.substring(startIndex,endIndex).trim();
                        	//System.out.println(reqStr);
                        	String arr[] = reqStr.split("\\r\\n");
                        	/*for(int k=0;k<arr.length;k++)
                        		System.out.println(+k+" -- "+arr[k]);*/
                        	
                        	VoterInfo voterInfo = new VoterInfo();
                        	voterInfo.setsNo(new Long(arr[0].trim()));
                        	voterInfo.setHouseNumber(arr[2].replaceAll("House No.:","").trim());
                        	
                        	if(arr[5].contains("Sex:"))
                        	{
                        		voterInfo.setGuardianName(arr[3].trim());
	                        	voterInfo.setVoterName(arr[4].trim());
	                        	voterInfo.setVoterId("-");
	                        	voterInfo.setSex(arr[5].replaceAll("Sex: ","").trim());
	                        	voterInfo.setAge(arr[6].replaceAll("Age: ","").trim());
	                        	voterInfo.setGuardianRelation(arr[7].replaceAll("'s Name:","").trim());
                        	}
                        	
                        	else if(arr[6].contains("Sex:"))
                        	{
	                        	voterInfo.setGuardianName(arr[3].trim());
	                        	voterInfo.setVoterName(arr[4].trim());
	                        	voterInfo.setVoterId(arr[5].trim());
	                        	voterInfo.setSex(arr[6].replaceAll("Sex: ","").trim());
	                        	voterInfo.setAge(arr[7].replaceAll("Age: ","").trim());
	                        	voterInfo.setGuardianRelation(arr[8].replaceAll("'s Name:","").trim());
                        	}
                        	else if(arr[7].contains("Sex:"))
                        	{
                        		/*if(!arr[3].substring(arr[3].length()-1).equalsIgnoreCase(" "))
                        		{*/
                        			voterInfo.setVoterName(arr[5].trim());
                        			voterInfo.setGuardianName(arr[3]+arr[4].trim());
                        		/*}*/
                        		voterInfo.setVoterId(arr[6].trim());
	                        	voterInfo.setSex(arr[7].replaceAll("Sex: ","").trim());
	                        	voterInfo.setAge(arr[8].replaceAll("Age: ","").trim());
	                        	voterInfo.setGuardianRelation(arr[9].replaceAll("'s Name:","").trim());
                        	}
                        	
                        	else if(arr[8].contains("Sex:"))
                        	{
                        		voterInfo.setVoterName(arr[5].trim()+" "+arr[6].trim());
                        		voterInfo.setGuardianName(arr[3].trim()+" "+arr[4].trim());
                        		voterInfo.setVoterId(arr[7].trim());
	                        	voterInfo.setSex(arr[8].replaceAll("Sex: ","").trim());
	                        	voterInfo.setAge(arr[9].replaceAll("Age: ","").trim());
	                        	voterInfo.setGuardianRelation(arr[10].replaceAll("'s Name:","").trim());
                        	}
                        	
                        	voterInfo.setBoothNo(boothVO.getPartNo());
                        	voterInfo.setBoothName(boothVO.getName());
                        	voterInfo.setConstituencyId(boothVO.getConstituencyId());
                        	voterInfo.setConstituency(boothVO.getConstituencyName());
                        	
                        	if(voterInfo.getVoterId().equals("-") || voterInfo.getVoterId().equals(""))
                        		voterInfo.setVoterId("ABC"+random.nextInt(1000000));
                            else if(voterInfo.getVoterId().contains("-"))
                            	voterInfo.setVoterId(voterInfo.getVoterId().replaceAll("-",""));
                        	
                        	voterInfo.setVoterName(voterInfo.getVoterName().replaceAll("'",""));
                        	voterInfo.setGuardianName(voterInfo.getGuardianName().replaceAll("'",""));
                        	voterInfo.setHouseNumber(voterInfo.getHouseNumber().replaceAll("'", ""));
                        	
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
