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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.VoterInfo;
import com.itgrids.voterdata.util.IConstants;

public class VotersModificationIdentifier {
	
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
                File resultFile  = new File(args[0]+"/NewVoterData.txt");
                File resultFile2  = new File(args[0]+"/deletedVoterData.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                BufferedWriter outwriter2 = new BufferedWriter(new FileWriter(resultFile2));
                StringBuilder sb = null;
                StringBuilder addVotersStr = new StringBuilder();
                StringBuilder deletedVotersStr = new StringBuilder();
                List<VoterInfo> newVoters = new ArrayList<VoterInfo>(0);
                List<VoterInfo> deletedVoters = new ArrayList<VoterInfo>(0);
                
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
                    System.out.println("Reading "+input.getName()+"......");
                    String [] fileName = input.getName().split("-");
                    constituencyId = fileName[0];
                    
                    String str1 = "Component - I";
                    String str2 = "Component - II";
                    String str3 = "Component - III";
                    
                    String additionsStr = sb.substring(sb.indexOf(str1), sb.indexOf(str2));
                    String deletionsStr = sb.substring(sb.indexOf(str2), sb.indexOf(str3));
                    //System.out.println(additionsStr);
                    //System.out.println(deletionsStr);
                    
                    Pattern p = Pattern.compile("Age:\\r\\nHouse No:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nElector's Name:\\r\\n([A-Z\\d]*)\\r\\n");
                	Matcher m = p.matcher(additionsStr);
                	
                	while (m.find()) 
                    {
                		VoterInfo voterInfo = new VoterInfo();
                		voterInfo.setBoothName(fileName[3].substring(0,fileName[3].length()-4).trim());
                		voterInfo.setBoothNo(fileName[2].trim());
                		voterInfo.setConstituency(fileName[1].trim());
                		voterInfo.setConstituencyId(constituencyId);
                		voterInfo.setVoterId((m.group(2).replaceAll("\\r\\n","").trim()));
                		newVoters.add(voterInfo);
                    }
                	
                	Matcher m2 = p.matcher(deletionsStr);
                	while (m2.find()) 
                    {
                		VoterInfo voterInfo = new VoterInfo();
                		voterInfo.setBoothName(fileName[3].substring(0,fileName[3].length()-4).trim());
                		voterInfo.setBoothNo(fileName[2].trim());
                		voterInfo.setConstituency(fileName[1].trim());
                		voterInfo.setConstituencyId(constituencyId);
                		voterInfo.setVoterId((m2.group(2).replaceAll("\\r\\n","").trim()));
                		deletedVoters.add(voterInfo);
                    }
                	
                    if (pd != null) {
                        pd.close();
                    }
                	}catch (Exception e) {e.printStackTrace();}
        }
                
        if(newVoters.size() > 0)
        {
        	int index = 0;
        	for(VoterInfo voterInfo : newVoters)
        	{
        		String tempStr = ++index+")\tPart No - "+voterInfo.getBoothNo()+"\tVoter ID - "+voterInfo.getVoterId()
        				+"\tConstituency Id - "+voterInfo.getConstituencyId()+"\tConstituency - "+voterInfo.getConstituency()+"\n";
        		System.out.print(tempStr);
        		addVotersStr.append(tempStr);
        	}
        	outwriter.write(addVotersStr.toString());
        	outwriter.close();
        	saveVotersInModificationTempTable(newVoters,IConstants.ADDED);
        }	
        if(deletedVoters.size() > 0)
        {
        	int index = 0;
        	for(VoterInfo voterInfo : deletedVoters)
        	{
        		String tempStr = ++index+")\tPart No - "+voterInfo.getBoothNo()+"\tVoter ID - "+voterInfo.getVoterId()
        				+"\tConstituency Id - "+voterInfo.getConstituencyId()+"\tConstituency - "+voterInfo.getConstituency()+"\n";
        		System.out.print(tempStr);
        		deletedVotersStr.append(tempStr);
        	}
        }
        outwriter2.write(deletedVotersStr.toString());
    	outwriter2.close();
    	saveVotersInModificationTempTable(deletedVoters,IConstants.DELETED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		
	public static void saveVotersInModificationTempTable(List<VoterInfo> votersList,String Status)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
			for(VoterInfo voterInfo : votersList)
			{
				try{
					String insertQuery = "INSERT INTO voter_modification_temp(voter_id,Status,constituency_id,constituency_name,booth_id) VALUES ('"+
							voterInfo.getVoterId()+"','"+Status+"',"+voterInfo.getConstituencyId()+",'"+voterInfo.getConstituency()+"',"+voterInfo.getBoothNo()+")";
		    		stmt.executeUpdate(insertQuery);
				}catch (Exception e) {
					System.out.println("Exception Occured While Saving the Voter ID -"+voterInfo.getVoterId());
    				System.out.println("Exception is - "+e);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
       	
}
