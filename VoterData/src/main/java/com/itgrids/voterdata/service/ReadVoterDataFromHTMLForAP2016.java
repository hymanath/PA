package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.VoterInfo;

public class ReadVoterDataFromHTMLForAP2016 {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://192.168.11.4:3306/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "kamalaakar";
		
		static Connection conn = null;
		static Statement stmt = null;
	
    public static String readFromFile(File file,
                                      Charset charset) throws IOException {
        InputStream in = new FileInputStream(file);
        Closeable stream = in;
        try {
            Reader reader = new InputStreamReader(in, charset);
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
    		
    		//votersInfoList = checkForVoterList(votersInfoList);
    		votersInfoList = checkForSpecialCharacters(votersInfoList);
    		
    		for(VoterInfo info : votersInfoList)
    		{
    			try{
    			String insertQuery = "INSERT INTO voter_temp(voter_id, name, sex, age, house_no, guardian_name, relation, constituency_id, " +
    				" constituency_name, booth_id, booth_name,sno) VALUES ('"+info.getVoterId()+"','"+info.getVoterName()+"','"+info.getSex()+
    				"','"+info.getAge()+"','"+info.getHouseNumber()+"','"+info.getGuardianName()+"','"+info.getGuardianRelation()+
    				"','"+info.getConstituencyId()+"','"+info.getConstituency()+"','"+info.getBoothNo()+"','"+info.getBoothName().replaceAll(".pdf","")+"',"+info.getsNo()+")";
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
        
        Pattern p = Pattern.compile("([0-9]*)\\r\\nAge: Sex:\\s([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([A-Za-z\\.\\s\\-\\*\\~]*)\\r\\n([0-9\\-_/A-Za-z\\.\\?\\+\\=\\`\\/\\*\\&\\,\\:\\;\\(\\)\\\\]*)\\r\\n([0-9])");
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                //  PDF file from where the voter data is extracted
                File inputDir = new File(args[0]);
                String voterInfo = "";
                File resultFile  = new File(args[0]+"/VoterData.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                Long startTime = new Date().getTime();
                List<String> missedList = new ArrayList<String>(0);
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "htm");
                    }
                })) {
                	int i = 0;
                    StringBuilder sb = new StringBuilder();
                    /*pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));*/
                    //System.out.println("File text:"+stripper.getText(pd));
                    
                    //sb = formatText1(sb);
                    
                    BufferedReader br = new BufferedReader(new FileReader(input));
                    String line = null;
                    
                    while((line = br.readLine()) != null)
                    	if(line.trim().length() != 0)
                    		sb.append(line.trim()+"\n");
                    
                    int ksInd = sb.indexOf("<h2>GREATER HYDERABAD MUNICIPAL CORPORATION - 2016</h2>");
                    sb = new StringBuilder(sb.substring(ksInd));
                    
                    sb = formatText(sb);
                    outwriter.write(sb.toString()); 
                    
                    String [] fileName = input.getName().split("-");
                    List<Integer> boothSnoList = new ArrayList<Integer>(0); 
                    
                    int totalStrInd = sb.indexOf("Net Electors\r\nMale Female Total")+33;
                    int totalStrInd2 = sb.indexOf("\r\n",totalStrInd);
                    
                    String totalStr = sb.substring(totalStrInd,totalStrInd2);
                    String[] arr = totalStr.split(" ");
                    int totVoters = Integer.valueOf(arr[0]);
                    //System.out.println(totVoters);
                    
                    Matcher m = p.matcher(sb);
                    
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    List<Integer> serialNosList = new ArrayList<Integer>(0);
                    
                    while (m.find()) 
                    {
                        try{
	                    	i++;
	                    	serialNosList.add(Integer.valueOf(m.group(1).trim()));
	                    	
	                    	VoterInfo voter = new VoterInfo();
	                        
	                        voter.setsNo(Long.valueOf(m.group(1).replaceAll("\r\n","").trim()));
	                        
	                        /*if(voter.getsNo().longValue() == 995l)
	                        	System.out.println("Kamal");*/
	                        voter.setSex(m.group(2).replaceAll("Age: Sex: ","").replaceAll("\r\n","").trim());
	                        voter.setVoterId(m.group(3).replaceAll("\r\n","").trim());
	                        voter.setGuardianRelation(m.group(4).replaceAll("'s Name","").trim());
	                        voter.setVoterName(m.group(5).replaceAll("\r\n","").trim());
	                        voter.setGuardianName(m.group(6).replaceAll("\r\n","").trim());
	                        voter.setHouseNumber(m.group(7).replaceAll("\r\n","").trim());
	                        voter.setAge(m.group(8).replaceAll("\r\n","").trim());
	                        
	                        voter.setConstituency(fileName[1]);
	                        voter.setBoothNo(fileName[2]);
	                        voter.setBoothName(fileName[3]);
	                        voter.setConstituencyId(fileName[0]);
	                        totalVotersCount++;
	                        voterInfoList.add(voter);
	                        boothSnoList.add(voter.getsNo().intValue());
	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	                        System.out.println(voterInfo);
	                        writer.write(voterInfo+"\n");
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                   /* for(int index=1;index<=totVoters;index++)
                    {
                    	try{
                    		if(!serialNosList.contains(index))
                    		{
                    			VoterInfo voter = new VoterInfo();
                    			
                    			if(index == 1l)
    	                        	System.out.println("Kamal");
                    			
                    			int inSInd = sb.indexOf(index+"\r\nAge: Sex:");
                    			int inEInd = 0;
                    			
                    			if(index == totVoters)
                    				inEInd = sb.indexOf("Age As On",inSInd);
                    			else
                    				inEInd = sb.indexOf(index+1+"\r\nAge: Sex:",inSInd);
                    			
                    			String inStr = sb.substring(inSInd,inEInd);
                    			//System.out.println(inStr);
                    			
                    			if(inStr.contains("Age As On"))
                    				inStr = inStr.substring(0,inStr.indexOf("Age As On"));
                    			
                    			voter = new VoterInfo();
    	                        String[] inArr =inStr.trim().split("\r\n");
    	                        
    	                        if(inArr.length > 0)
    	                        {
	    	                        i++;
	    	                    	serialNosList.add(Integer.valueOf(inArr[0].trim()));
	    	                    			
	    	                        voter.setsNo(Long.valueOf(inArr[0].trim()));
	    	                        voter.setSex(inArr[1].replace("Age: Sex: ","").trim());
	    	                        voter.setVoterId(inArr[2].trim());
	    	                        voter.setGuardianRelation(inArr[4].replace("'s Name:","").trim());
	    	                        
	    	                        if(inArr.length == 10)
	    	                        {
	    	                        	voter.setVoterName(inArr[6].trim());
	    	                        	voter.setGuardianName(inArr[7].trim());
	    	                        	voter.setHouseNumber(inArr[8].trim());
	    	                        	voter.setAge(inArr[9].trim());
	    	                        }
	    	                        else if(inArr.length == 11)
	    	                        {
	    	                        	voter.setVoterName(inArr[6].trim()+" "+inArr[7].trim());
	    	                        	voter.setGuardianName(inArr[8].trim());
	    	                        	voter.setHouseNumber(inArr[9].trim());
	    	                        	voter.setAge(inArr[10].trim());
	    	                        }
	    	                        else if(inArr.length == 12)
	    	                        {
	    	                        	voter.setVoterName(inArr[6].trim()+" "+inArr[7].trim());
	    	                        	voter.setGuardianName(inArr[8].trim()+" "+inArr[9].trim());
	    	                        	voter.setHouseNumber(inArr[10].trim());
	    	                        	voter.setAge(inArr[11].trim());
	    	                        }
	    	                        else if(inArr.length == 13)
	    	                        {
	    	                        	voter.setVoterName(inArr[6].trim()+" "+inArr[7].trim());
	    	                        	voter.setGuardianName(inArr[8].trim()+" "+inArr[9].trim());
	    	                        	voter.setHouseNumber(inArr[10].trim()+" "+inArr[11].trim());
	    	                        	voter.setAge(inArr[12].trim());
	    	                        }
	    	                        
	    	                        voter.setConstituency(fileName[1]);
	    	                        voter.setBoothNo(fileName[2]);
	    	                        voter.setBoothName(fileName[3]);
	    	                        voter.setConstituencyId(fileName[0]);
	    	                        
	    	                        totalVotersCount++;
	    	                        
	    	                        voterInfoList.add(voter);
	    	                        boothSnoList.add(voter.getsNo().intValue());
	    	                        
	    	                        voterInfo = i +"\tConstituency -- " + voter.getConstituency() + "\tBooth No -- " + voter.getBoothNo() + "\t Serial No -- "+voter.getsNo()+"\tBooth Name -- " + voter.getBoothName().replaceAll(".pdf","") + "\tvoter ID -- " + voter.getVoterId() + "\tVoter Name -- " + voter.getVoterName() + "\tAge -- " + voter.getAge() + "\tSex -- " + voter.getSex() + "\tHouse No -- " + voter.getHouseNumber() + "\t Relation -- " + voter.getGuardianRelation() + "\tGuardian Name -- " + voter.getGuardianName() + "";
	    	                        System.out.println(voterInfo);
	    	                        
	    	                        writer.write(voterInfo+"\n");
    	                        }
                    		}
                    	}catch(Exception e)
                    	{
                    		e.printStackTrace();
                    	}
                    }*/
                    
                    for(int index=1;index<=totVoters;index++)
                    {
                    	if(!boothSnoList.contains(index))
                    		missedList.add(fileName[2]+"-"+index);
                    }
                    
                    saveVotersData(voterInfoList);
                    if (pd != null) {
                        pd.close();
                    }
                    /*for(Integer voterId : serialNosList)
                    	System.out.println(voterId);*/       
                    System.out.println("Total Voters in Booth --> "+i); 
            }
            
            System.out.println("Total No of Voters:" + totalVotersCount);
            System.out.println("Total Time Taken in Minutes -"+((new Date().getTime())-startTime)/1000*60);
            writer.close();
            outwriter.close();
            
            System.out.println("Missed Voters ----------------------------");
            for(String misSno : missedList)
            	System.out.println(misSno);
            System.out.println("------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static StringBuilder formatText(StringBuilder builder)
    {
    	try{
    		String str = builder.toString();
    		/*str = str.replaceAll("  "," ");
    		str = str.replaceAll(" \r\n","\r\n");
    		str = str.replaceAll("\r\n ","\r\n");*/
    		
    		str = str.replaceAll("<td colspan=\"3\" align=\"right\" nowrap=\"nowrap\">", "");
    		str = str.replaceAll("<td align=\"left\" valign=\"top\">", "");
    		str = str.replaceAll("<td colspan=\"3\" align=\"left\" valign=\"top\">", "");
    		str = str.replaceAll("<td align=\"left\" valign=\"top\">", "");
    		str = str.replaceAll("<td colspan=\"2\" align=\"left\" valign=\"top\">", "");
    		str = str.replaceAll("<div style=\"height: 25px; overflow:hidden\">", "");
    		str = str.replaceAll("<td rowspan=\"5\" align=\"left\">", "");
    		str = str.replaceAll("&nbsp;", "");
    		str = str.replaceAll("<td colspan=\"2\" align=\"left\">", "");
    		str = str.replaceAll("<div style=\"height: 11px; overflow:hidden\">", "");
    		str = str.replaceAll("\n\n\n", "\n");
    		str = str.replaceAll("\n\n", "\n");
    		str = str.replaceAll("\n\n", "\n");
    		
    		str = str.replaceAll("\n</td>\n</tr>\n<tr>","");
    		str = str.replaceAll("\n</div>\n</td>\n</tr>\n<tr>\n<div>","");
    		str = str.replaceAll("\n</div></td>\n","\n");
    		str = str.replaceAll("\n</div></td>\n</td>\n</tr>\n<tr>","");
    		str = str.replaceAll("\n</div>\n</td>\n</tr>\n<tr>","");
    		str = str.replaceAll("\n</div></td>\n</tr>\n<tr>\n<td align=\"left\">","");
    		/*str = str.replaceAll("", "");
    		str = str.replaceAll("", "");
    		str = str.replaceAll("", "");
    		str = str.replaceAll("", "");
    		str = str.replaceAll("", "");
    		str = str.replaceAll("", "");
    		str = str.replaceAll("", "");
    		str = str.replaceAll("", "");*/
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }
    
    public static List<VoterInfo> checkForVoterList(List<VoterInfo> votersInfoList)
    {
    	try{
    		List<VoterInfo> votersList = new ArrayList<VoterInfo>(0);
    		
    		if(votersInfoList != null && votersInfoList.size() > 0)
    		{
    			List<String> genderList = new ArrayList<String>();
    			genderList.add("Male");
    			genderList.add("Female");
    			
    			List<String> relationsList = new ArrayList<String>();
    			relationsList.add("Mother");
    			relationsList.add("Father");
    			relationsList.add("Husband");
    			relationsList.add("Other");
    			
    			StringBuilder str = new StringBuilder();
    			
    			for(VoterInfo info : votersInfoList)
    			{
    				try{
    				boolean flag = true;
    				int age = Integer.valueOf(info.getAge().trim());
    				
    				String voterId = info.getVoterId().trim().replaceAll("'", "");
    				String name = info.getVoterName().trim().replaceAll("'", "");
    				String sex = info.getSex().trim().replaceAll("'", "");
    				String houseNo = info.getHouseNumber().trim().replaceAll("'", "");
    				String relativeName = info.getGuardianName().trim().replaceAll("'", "");
    				String relation = info.getGuardianRelation().trim().replaceAll("'", "");
    				
    				voterId = voterId.replaceAll(",", "");
    				name = name.replaceAll(",", "");
    				sex = sex.replaceAll(",", "");
    				houseNo = houseNo.replaceAll(",", "");
    				relativeName = relativeName.replaceAll(",", "");
    				relation = relation.replaceAll(",", "");
    				
    				info.setVoterId(voterId);
    				info.setVoterName(name);
    				info.setSex(sex);
    				info.setHouseNumber(houseNo);
    				info.setGuardianName(relativeName);
    				info.setGuardianRelation(relation);
    				
    				if(voterId == null || voterId.length() == 0 || voterId.equalsIgnoreCase("null"))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(name == null || name.length() == 0 || name.equalsIgnoreCase("null"))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(sex == null || sex.length() == 0 || sex.equalsIgnoreCase("null") || !genderList.contains(info.getSex().trim()))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(info.getAge() == null || info.getAge().trim().length() == 0 || info.getAge().trim().equalsIgnoreCase("null")
    						|| age < 18 || age > 200)
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(houseNo == null || houseNo.length() == 0 || houseNo.equalsIgnoreCase("null") || houseNo.length() > 100)
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(relativeName == null || relativeName.length() == 0 || relativeName.equalsIgnoreCase("null"))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				
    				if(relation == null || relation.length() == 0 || relation.equalsIgnoreCase("null") || !relationsList.contains(relation))
    				{
    					flag = false;
    					str.append(printVoter(info));
    				}
    				//System.out.println(str);
    				if(flag)
    					votersList.add(info);
    				}catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		}
    		
    		return votersList;
    	}catch(Exception e)
    	{
    		System.out.println("Exception Occured in checkForVoterList Method, So Same Voter List is returned");
    		System.out.println(e);
    		return votersInfoList;
    	}
    }
    
    public static String printVoter(VoterInfo info)
    {
    	try{
    		//String str = "Booth - "+info.getBoothNo()+"\tSerial No - "+info.getsNo()+"\tName - "+info.getVoterName()+"\tHouse No - "+info.getHouseNumber()+"\tGender - "+info.getSex()+"\tAge - "+info.getAge()+"\tVoter Id - "+info.getVoterId()+"\tRelative Name - "+info.getGuardianName()+"\tRelation - "+info.getGuardianRelation()
    			//	+"\tBooth Name - "+info.getBoothName()+"\tConstituency Id - "+info.getConstituencyId()+"\tConstituency - "+info.getConstituency();
    		//System.out.println(str+"\n");
    		return "";
    	}catch(Exception e){
    		e.printStackTrace();
    		return "";
    	}
    }
    
    public static List<Long> getSerialNoList(List<VoterInfo> list)
    {
    	List<Long> serialNoList = new ArrayList<Long>(0);
    	try{
    		for(VoterInfo info : list)
    			serialNoList.add(info.getsNo());
    		return serialNoList;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return serialNoList;
    	}
    }
    
    public static List<VoterInfo> checkForSpecialCharacters(List<VoterInfo> list)
    {
    	try{
    		for(VoterInfo info : list)
    		{
    			try{
    			info.setVoterId(info.getVoterId().replaceAll("'", ""));
    			info.setVoterName(info.getVoterName().replaceAll("'", ""));
    			info.setHouseNumber(info.getHouseNumber().replaceAll("'", ""));
    			info.setGuardianName(info.getGuardianName().replaceAll("'", ""));
    			info.setBoothName(info.getBoothName().replaceAll("'", ""));
    			
    			/*info.setVoterId(info.getVoterId().replaceAll("\\", ""));
    			info.setVoterName(info.getVoterName().replaceAll("\\", ""));
    			info.setHouseNumber(info.getHouseNumber().replaceAll("\\", ""));
    			info.setGuardianName(info.getGuardianName().replaceAll("\\", ""));
    			info.setBoothName(info.getBoothName().replaceAll("\\", ""));*/
    			}catch(Exception e)
    			{
    				System.out.println("---> "+info.getVoterId()+" <-----");
    				e.printStackTrace();
    			}
    		}
    		return list;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return list;
    	}
    }
    
}
