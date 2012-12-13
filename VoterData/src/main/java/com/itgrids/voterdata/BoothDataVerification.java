package com.itgrids.voterdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class BoothDataVerification {
	
        public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        int pdfPartNo = 0;
        int filePartNo = 0;
        int index = 0;
        
        //Pattern p = Pattern.compile("Age:\\sSex:\\s([a-zA-Z]*)\\r\\n([A-Z\\d]*)\\r\\nElector's Name:\\r\\n(Husband's Name:|Father's Name:|Mother's Name:|Other's Name:)\\r\\nHouse No:\\r\\n([A-Za-z\\.\\s\\r\\n]*)\\r\\n([A-Za-z\\.\\s]*)\\r\\n([0-9\\-_/A-Za-z]*)\\r\\n([\\s0-9]*)\\r\\n([\\s0-9a-zA-Z]*)\\r\\n");
        
        Pattern p = Pattern.compile("([\\s0-9]*)Part No.\\r\\n");
        
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0] + "/" + "result.txt")));
            File inputDir = new File(args[0]);
            StringBuilder sb2 = new StringBuilder();
            
            for (File input : inputDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String filename) {
                    return filename.endsWith('.' + "pdf");
                }
            })) {
            	
            	int i = 0;
            	index++;
                StringBuilder sb = new StringBuilder();
                pd = PDDocument.load(input);
                PDFTextStripper stripper = new PDFTextStripper();
                sb.append(stripper.getText(pd));
               
                String [] fileName = input.getName().split("-");
                
                Matcher m = p.matcher(sb);
                
                while (m.find()) 
                {
                	if(i == 1)
                	{
                		try{
                		pdfPartNo = Integer.parseInt(m.group(1).replaceAll("\\r\\n","").trim());
                		}catch (Exception e) {
                			System.out.println("Exception Occured while processing File - "+input.getName());
							e.printStackTrace();
						}
                	}
                	i++;
                }
                
                filePartNo = Integer.parseInt(fileName[2].trim());
                String str = new String("");
                
                if(pdfPartNo == filePartNo)
                	str = (index+")\tYES - "+input.getName()+" (F-"+filePartNo+" = "+pdfPartNo+"-P )")+"\n";
                else
                	str = (index+")\tNO  - "+input.getName()+" - Not Matched (F-"+filePartNo+" != P-"+pdfPartNo+")")+"\n";
                
                sb2.append(str);
                System.out.println(str);

                if (pd != null) {
                    pd.close();
                }
            }
            writer.write(sb2.toString()); 
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
