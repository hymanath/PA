package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class BoothDataReaderForAP2017 {
	
    public static void readBoothData(File inputDir,BufferedWriter writer) throws IOException {
        PDDocument pd = null;
        try {

                StringBuilder sb = null;
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "PDF");
                    }
                })) {
                	try{
                	sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    
                    sb = sb.delete(sb.indexOf("Third Gender"), sb.length()-1);
                    String str = sb.substring(sb.lastIndexOf("Summary Revision 2017")+21).trim();
                    str = str.replaceAll("  "," ");
                    //System.out.println(str);
                    
                    String arr[] = str.split("\r\n");
                    String arr2[] = arr[1].trim().split(" ");
                    
                    String start = arr2[0];
                    String end = arr2[1];
                    String male = arr2[2];
                    String female = arr2[3];
                    String total = arr2[4];
                    
                    System.out.println("Constituency - "+inputDir.getAbsolutePath()+"\tBooth - "+input.getName()+"\tStrat - "+start+"\tEnd - "+end+"\tMale - "+male+"\tFemale - "+female+"\tTotal - "+total);
                    writer.write("Constituency - "+inputDir.getAbsolutePath()+"\tBooth - "+input.getName()+"\tStrat - "+start+"\tEnd - "+end+"\tMale - "+male+"\tFemale - "+female+"\tTotal - "+total+"\n");
                    if (pd != null) {
                        pd.close();
                    }
                	}catch (Exception e) {e.printStackTrace();}
        }
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
    	try{
    		File mainDir  = new File(args[0]);
	    	File resultFile  = new File(args[0]+"/BoothDataReader.txt");
	    	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
	    	
	    	if(mainDir.isDirectory())
	    	{
	    		for(File conDir : mainDir.listFiles())
	    		{
	    			try{
	    				File boothDir = new File(conDir.getAbsolutePath()+"/English");
	    				
	    				if(boothDir.isDirectory())
	    					readBoothData(boothDir,bufferedWriter);
	    			}catch(Exception e)
	    			{
	    				e.printStackTrace();
	    			}
	    		}
	    	}
	    	bufferedWriter.close();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
       	
}
