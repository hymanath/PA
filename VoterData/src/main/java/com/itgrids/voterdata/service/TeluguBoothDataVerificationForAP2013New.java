package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class TeluguBoothDataVerificationForAP2013New {
	
        public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int pdfPartNo = 0;
        int filePartNo = 0;
        int index = 0;
        StringBuilder sb2 = new StringBuilder();
        
        Pattern p = Pattern.compile("([\\s0-9]*)Part No.\\r\\n");
        
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "TeluguBoothDataVerificationResult.txt")));
            File inputDir = new File(args[0]);
            List<File> notMatchedFiles = new ArrayList<File>(0);
            Map<Integer,Integer> pfMap = new HashMap<Integer, Integer>(0);
            Map<Integer,String> filesMap = new HashMap<Integer, String>(0);
            Map<Integer,String> pdfNoFilesMap = new HashMap<Integer, String>(0);
            
            for (File input : inputDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String filename) {
                    return filename.endsWith('.' + "pdf");
                }
            })) {
            	try{
            	
            	int i = 0;
            	index++;
                StringBuilder sb = new StringBuilder();
                pd = PDDocument.load(input);
                PDFTextStripper stripper = new PDFTextStripper();
                sb.append(stripper.getText(pd));
                //System.out.println(sb);
                String [] fileName = input.getName().split("-");
                
                Matcher m = p.matcher(sb);
                
                String str1 = "R����T���s�j�";
                String str2 = "ry��yLRi";
                
                int startNo = sb.indexOf(str1)+str1.length()+1;
                String sbStr = sb.substring(startNo, sb.indexOf(str2,startNo)).trim();
                pdfPartNo = Integer.parseInt(sbStr); 
                filePartNo = Integer.parseInt(fileName[2].trim());
                String str = new String("");
                filesMap.put(filePartNo, input.getName());
                
                if(pdfPartNo == filePartNo)
                	str = (index+")\tYES - "+input.getName()+" (F-"+filePartNo+" = "+pdfPartNo+"-P )")+"\n";
                else
                {
                	str = (index+")\tNO  - "+input.getName()+" - Not Matched (F-"+filePartNo+" != P-"+pdfPartNo+")")+"\n";
                	notMatchedFiles.add(input);
                	pfMap.put(pdfPartNo, filePartNo);
                	pdfNoFilesMap.put(pdfPartNo, input.getName());
                }
                
                sb2.append(str);
                System.out.println(str);

                if (pd != null) {
                    pd.close();
                }
            
	        	}catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}
            }
            writer.write(sb2.toString()); 
            writer.close();
            
            if(pfMap.size() > 0)
            {
            	new File(args[0]+"\\temp").mkdir();
            	for(Map.Entry<Integer,Integer> entry : pfMap.entrySet())
            	{
            		if(filesMap.get(entry.getValue()) != null)
            		{
            			File temp = new File(args[0]+"\\"+pdfNoFilesMap.get(entry.getKey()));
            			temp.renameTo(new File(args[0]+"\\temp\\"+filesMap.get(entry.getKey())));
            		}
            	}
            	
            }
            
            if(notMatchedFiles.size() > 0)
            {
            	for(File file : notMatchedFiles)
            		try{
            		file.delete();
            		}catch (Exception e) {}
            }
            
            if(pfMap.size() > 0)
            {
            	File tempDir = new File(args[0]+"\\temp");
                
            	for(File tf : tempDir.listFiles())
            	{	
            		try{
            		tf.renameTo(new File(args[0]+"\\"+tf.getName()));
            		}catch(Exception e){}
            	}
            	tempDir.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
