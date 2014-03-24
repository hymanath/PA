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

public class WardDataReader {
	
    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        Pattern p = Pattern.compile("\\s([0-9]*)\\r\\n([A-Z\\d]*)\\r\\n");
        
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
                //  PDF file from where the voter data is extracted
                File inputDir = new File(args[0]);
                String voterInfo = "";
                StringBuilder sb2 = new StringBuilder();
                File resultFile  = new File(args[0]+"/VoterData.txt");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
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
                    outwriter.write(sb.toString());                   
                    String [] fileName = input.getName().split("-");
                    
                    Matcher m = p.matcher(sb);
                    
                    VoterInfo voter = null;
                    List<VoterInfo> voterInfoList = new ArrayList<VoterInfo>(10);
                    
                    while (m.find()) 
                    {
                        try{
                        	if(!m.group(2).replaceAll("\\r\\n","").trim().equalsIgnoreCase("SLNO"))
                        	{	
		                    	i++;
		                        voter = new VoterInfo();
		                        
		                        voter.setsNo(Long.valueOf(m.group(1).replaceAll("\\r\\n","").trim()));
		                        voter.setVoterId(m.group(2).replaceAll("\\r\\n","").trim());
		                        
		                        voter.setConstituency(fileName[1]);
		                        voter.setBoothNo(fileName[2]);
		                        voter.setBoothName(fileName[3]);
		                        voter.setConstituencyId(fileName[0]);
		                        totalVotersCount++;
		                        voterInfoList.add(voter);
		                        voterInfo = i +"\tWard No -- \t" + voter.getBoothNo() +"\tVoter Id -- \t"+voter.getVoterId();
		                        System.out.println(voterInfo);
		                        sb2.append(voterInfo+"\n");
		                        writer.write(voterInfo+"\n");
                        	}
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    
                    //saveVotersData(voterInfoList);
                    if (pd != null) {
                        pd.close();
                    }
                      
            }
            outwriter.write(sb2.toString());
            System.out.println("Total No of Voters:" + totalVotersCount);
            System.out.println("Total Time Taken in Minutes -"+((new Date().getTime())-startTime)/1000*60);
            writer.close();
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
