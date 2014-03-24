package com.itgrids.voterdata.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistrictWiseBoothDataReaderFor2014 {
	
    public static void main(String args[]) throws IOException {
        try {
        	List<String> districtList = new ArrayList<String>(0);
        	districtList.add("Missed");
        	
        	for(String district : districtList)
        	{
        		try{
        		System.out.println("Reading Data From "+district+" District");
        		File inputDir = new File(args[0]+"\\"+district);
        		
        		for(File conDir : inputDir.listFiles())
        		{
        			try{
        			String[] constituency = {conDir.getAbsolutePath()};
            		BoothCompleteDataReaderForAP2014.main(constituency);
        			}catch(Exception e) {e.printStackTrace();}
        		}
        		
        		}catch(Exception e) {e.printStackTrace();
                }
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
       	
}
