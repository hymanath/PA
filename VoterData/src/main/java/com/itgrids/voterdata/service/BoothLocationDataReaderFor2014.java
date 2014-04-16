package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itgrids.voterdata.VO.BoothVO;
import com.itgrids.voterdata.VO.LocationVO;

public class BoothLocationDataReaderFor2014 {
	
    public static void main(String args[]) throws IOException {
        PDDocument pd = null;
        try {

                File inputDir = new File(args[0]);
                File BoothInfo  = new File(args[0]+"/BoothLocationDataReader.txt");
                BufferedWriter boothInfoReader = new BufferedWriter(new FileWriter(BoothInfo));
                StringBuilder sb = null;
                StringBuilder boothSB = new StringBuilder();
                List<BoothVO> boothsInfoList = new ArrayList<BoothVO>(0);
                
                for (File input : inputDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String filename) {
                        return filename.endsWith('.' + "pdf");
                    }
                })) {
                	try{
                	sb = new StringBuilder();
                    pd = PDDocument.load(input);
                    String constituencyId = null;
                    PDFTextStripper stripper = new PDFTextStripper();

                    sb.append(stripper.getText(pd));
                    sb = sb.delete(sb.indexOf("Elector's Name:\r\n"), sb.length()-1);
                    
                    String str1 = "District\r\n:";
                    String str2 = "Roll Identification:";
                    String str3 = "Police Station\r\nMain Town";
                    String str4 = "3. POLLING STATION DETAILS";
                    
                    String locStr = sb.substring(sb.lastIndexOf(str3)+str3.length()+1, 
                    		sb.indexOf(str4,sb.lastIndexOf(str3)+str3.length()+1)).trim();
                    String placeStr = sb.substring(sb.lastIndexOf(str1)+str1.length()+1,
                    		sb.indexOf(str2,sb.lastIndexOf(str1)+str1.length()+1)).trim();
                    
                    String [] fileName = input.getName().split("-");
                    constituencyId = fileName[0];
                    BoothVO boothVO = new BoothVO();
                    boothVO.setFileName(args[0]+"\\"+input.getName());
                    boothVO.setName(fileName[3].substring(0,fileName[3].length()-4).trim());
                    boothVO.setPartNo(fileName[2].trim());
                    boothVO.setConstituencyId(constituencyId);
                    boothVO.setConstituencyName(fileName[1].trim());
                    
                    String [] placeArr = placeStr.split("\r\n");
                    
                    boothVO.setMailTown(placeArr[0].trim());
                    boothVO.setMandalName(placeArr[1].trim());
                    boothVO.setDistrict(placeArr[3].trim());
                    boothVO.setPincode(placeArr[placeArr.length-1].trim());
                    
                    String [] locArr = locStr.split("\r\n");
                    List<LocationVO> locList = new ArrayList<LocationVO>(0);
                    
                    for(String location : locArr)
                    {
                    	String [] loc2Arr = location.split(",");
                    	String houseNo = null;
                    	
                    	for(String houseStr : loc2Arr)
                    	{
                    		try{
	                    		if(houseStr.contains(". ") && houseStr.indexOf(". ") <= 1)
	                    			houseStr = houseStr.substring(houseStr.indexOf(". ")+1);
	                    		
	                    		houseStr = removeSpecialChars(houseStr);
	                    		
	                    		if(isHouseNo(houseStr.trim()) && houseStr.trim() != null)
	                    			houseNo = houseStr.trim();
                    		}catch(Exception e){e.printStackTrace();}
                    		
                    	}
                    	
                    	for(String houseStr : loc2Arr)
                    	{
                    		try{
                    		if(houseStr.contains(". ") && houseStr.indexOf(". ") <= 1)
                    			houseStr = houseStr.substring(houseStr.indexOf(". ")+1);
                    		
                    		houseStr = removeSpecialChars(houseStr);
                    		
                    		if(!isHouseNo(houseStr) && houseStr.trim().length() > 0 && !houseStr.contains("Number"))
                    		{
                    			LocationVO lvo = new LocationVO();
                				lvo.setLocation(houseStr);
                				lvo.setHouseNo(houseNo);
                				locList.add(lvo);
                    		}	
                    		}catch(Exception e){e.printStackTrace();}
                    	}
                    }
                    boothVO.setLocations(locList);
                    
                    for(int i = 0;i<boothVO.getLocations().size();i++)
                    {
                    	String hNo = boothVO.getLocations().get(i).getHouseNo() != null ? 
                    			boothVO.getLocations().get(i).getHouseNo() : " ";
                    	
                    	hNo = hNo.trim();
                    	if(hNo.trim().length() > 0 && hNo.trim().contains(" All By"))
                    		hNo = hNo.substring(0,hNo.indexOf("All By")).trim();
                    	if(hNo.trim().length() > 0 && hNo.trim().contains("Bye All"))
                    		hNo = hNo.substring(0,hNo.indexOf("Bye All")).trim();
                    	if(hNo.trim().length() > 0 && hNo.trim().contains("By All"))
                    		hNo = hNo.substring(0,hNo.indexOf("By All")).trim();
                    	if(hNo.trim().length() > 0 && hNo.trim().contains("By No"))
                    		hNo = hNo.substring(0,hNo.indexOf("By No")).trim();
                    	if(hNo.trim().length() > 0 && hNo.trim().contains(" By"))
                    		hNo = hNo.substring(0,hNo.indexOf(" By")).trim();
                    	
	                    boothSB.append(boothVO.getDistrict()+"\t"+boothVO.getConstituencyName()+"\t"+boothVO.getConstituencyId()+"\t"+boothVO.getMandalName()+"\t"+boothVO.getLocations().get(i).getLocation()+"\t#"+hNo+"\t"+boothVO.getMailTown()+"\t"+boothVO.getPartNo()+"\t"+boothVO.getPincode()+"\n");
	                    System.out.println(boothVO.getDistrict()+"\t"+boothVO.getConstituencyName()+"\t"+boothVO.getConstituencyId()+"\t"+boothVO.getMandalName()+"\t"+boothVO.getLocations().get(i).getLocation()+"\t#"+hNo+"\t"+boothVO.getMailTown()+"\t"+boothVO.getPartNo()+"\t"+boothVO.getPincode());
                    }
                    boothsInfoList.add(boothVO);
                    if (pd != null) {
                        pd.close();
                    }
                    System.gc();
                	}catch (Exception e) {e.printStackTrace();}
        }
            boothInfoReader.write(boothSB.toString());
            boothInfoReader.close();
            	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isHouseNo(String houseNo)
    {
    	try{
    		if(houseNo.contains("-") || houseNo.contains("/"))
    			return true;
    		char[] chars = houseNo.toCharArray();
    		for(char c : chars)
    		{
    			if(Character.getType(c) == 9 && houseNo.contains(" To "))
    			{
    				return true;
    			}
    		}
    		return false;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public static String removeSpecialChars(String str)
    {
    	try{
    		str = str.trim();
    		str = str.replaceAll("\"", "");
    		str = str.replace("	","");
    		
    		if(str.contains("\r\n"))
    			str = str.substring(0,str.indexOf("\r\n"));
    		
    		return str;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return str;
    	}
    }
       	
}
