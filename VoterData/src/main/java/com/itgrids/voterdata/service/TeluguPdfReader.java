package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class TeluguPdfReader {
	static List<Integer> ignoreList = new ArrayList<Integer>(0);
   
  public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int totalVotersCount = 0;
        int i = 0;
        
        Pattern p = Pattern.compile("([\\s0-9]*)\\r\\n([A-Z\\d]*)");
        try {
    		
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "VotersList.txt")));
               
                File inputDir = new File(args[0]);
                String voterInfo = "";
                
                StringBuilder sb2 = new StringBuilder();
                File resultFile  = new File(args[0]+"/VoterData.txt");
                
                BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
                Long startTime = new Date().getTime();
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {

                    StringBuilder sb = new StringBuilder();
                    
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    outwriter.write(sb.toString());
                    System.out.println(sb.toString());
                    sb = formatText(sb);
                    outwriter.write(sb.toString());
                    
                    String fileName = input.getName().replace(".pdf","");
                    
                    Matcher m = p.matcher(sb);
                    
                    while (m.find()) 
                    {
                        try{
                        	
                        	String age = m.group(1).toString().trim();
                        	String voterId = m.group(2).toString().trim();
                        	
                        	if(age.length() >1 && voterId.length() > 4)
                        	{
                        		i++;
                        		totalVotersCount++;
                        		
                        		voterInfo = fileName+"\t"+i+"\t"+voterId;
                        		System.out.println(voterInfo);
                        		sb2.append(voterInfo+"\n");
                        	}
                        }catch(Exception e)
                        {
                        	e.printStackTrace();
                        }
                    }
                    if (pd != null) {
                        pd.close();
                    }
            }
                
            writer.write(sb2.toString());
            
            System.out.println("Total No of Voters:" + totalVotersCount);
            System.out.println("Total Time Taken in Minutes - "+((new Date().getTime())-startTime)/(1000*60));
            
            writer.close();
            outwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static StringBuilder formatText(StringBuilder builder)
    {
    	try{
    		String str = builder.toString();
    		str = str.replaceAll("  ", " ");
    		str = str.replaceAll("  ", " ");
    		str = str.replaceAll("  ", " ");
    		str = str.replaceAll("  ", " ");
    		str = str.replaceAll("  ", " ");
    		str = str.replaceAll("  ", " ");
    		
    		return new StringBuilder(str);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return builder;
    	}
    }
    
}
