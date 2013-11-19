package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class BoothDataVerificationForAP2013 {
	
        public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int pdfPartNo = 0;
        int filePartNo = 0;
        int index = 0;
        StringBuilder sb2 = new StringBuilder();
        
        Pattern p = Pattern.compile("([\\s0-9]*)Part No.\\r\\n");
        
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "BoothDataVerificationResult.txt")));
            File inputDir = new File(args[0]);
            List<File> notMatchedFiles = new ArrayList<File>(0);
            
            
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
                
                while (m.find()) 
                {
                	if(i == 1)
                	{
                		try{
                		String partNoStr = m.group(1).replaceAll("\\r\\n","").trim();
                		
                		if(partNoStr.contains(" "))
                		{
                			pdfPartNo = Integer.parseInt(partNoStr.substring(partNoStr.lastIndexOf(" "), partNoStr.length()).trim());
                		}
                		else
                			pdfPartNo = Integer.parseInt(m.group(1).replaceAll("\\r\\n","").trim());
                		
                		}catch (Exception e) {
                			System.out.println("Exception Occured while processing File - "+input.getName());
                			sb2.append("Exception Occured while processing File - "+input.getName());
							e.printStackTrace();
						}
                		break;
                	}
                	i++;
                }
                
                filePartNo = Integer.parseInt(fileName[2].trim());
                String str = new String("");
                
                if(pdfPartNo == filePartNo)
                	str = (index+")\tYES - "+input.getName()+" (F-"+filePartNo+" = "+pdfPartNo+"-P )")+"\n";
                else
                {
                	str = (index+")\tNO  - "+input.getName()+" - Not Matched (F-"+filePartNo+" != P-"+pdfPartNo+")")+"\n";
                	notMatchedFiles.add(input);
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
            
            if(notMatchedFiles.size() > 0)
            {
            	for(File file : notMatchedFiles)
            		try{
            		file.delete();
            		}catch (Exception e) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
