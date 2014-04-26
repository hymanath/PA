package com.itgrids.partyanalyst.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;



public class AgeWiseExcelsGenerationService {

	/**
	 * This service is used for creating excel sheet for Booth Wise 
	 * @param list
	 * @param sheet
	 * @param workbook
	 */
	public void generateExcelsForBoothReport1(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{
		 DecimalFormat df = new DecimalFormat("##.##");
			
		    Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		    //for data.
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)11);
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)15);
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setFont(font2);
		    
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("5. Age wise Information - Booth wise");
	    cell.setCellStyle(style2);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Panchayat");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Polling Station #");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

	    cell1 = row1.createCell(2);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
	   
	    cell1 = row1.createCell(3);
	    cell1.setCellValue("% in Constituency");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,3,3 ));
	    
	    int count = 0;
		int colCount = 4;
		for (String name : ageRanges)
		{
			    cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+1;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    count++;
			    colCount = spanCount;
			    colCount++;
		}
	    	
		Row row2 = sheet.createRow(2);
		int colSpan = 4;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
		  //  colSpan++;
	   }

		int rownum = 3;
		Long totalCountForAConstituency = 0l;
		for(AgeRangeVO VO : list)
		{
			totalCountForAConstituency = totalCountForAConstituency + VO.getTotalCount();
		}
	    for(AgeRangeVO VO : list)
	    {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	 	    cell2.setCellStyle(style1);
	 	    Cell cell3 = row4.createCell(1);
	 	    cell3.setCellValue(Long.parseLong(VO.getPanchayatName()));
	 	    cell3.setCellStyle(style1);
	 	    Cell cell4 = row4.createCell(2);
		    cell4.setCellValue(VO.getTotalCount());
		    cell4.setCellStyle(style1);
		    Cell cell5 = row4.createCell(3);
	 	    cell5.setCellValue(Double.parseDouble(df.format((VO.getTotalCount()/totalCountForAConstituency.floatValue())*100 )));
	 	    cell5.setCellStyle(style1);
	 	

	        Map<String,AgeRangeVO >  mapp=VO.getMap();

	 	    int cellnum=4;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 		 cell7.setCellStyle(style1);
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getAgeRangePerc());
	 	 		 cell8.setCellStyle(style1);
	 	   }
	 	   rownum++;
	   }
	 
}
	public void generateExcelsForBoothReport2(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{
		 DecimalFormat df = new DecimalFormat("##.##");
			
		 Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		   /* style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		    //for data.
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)11);
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)15);
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setFont(font2);
		    
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("5. Age wise(Gender) Information - Booth wise");
	    cell.setCellStyle(style2);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    int rownum=1;
	    HSSFRow  rowForReport2= sheet.createRow((short) rownum);
	    Cell cellForReport2 = rowForReport2.createCell(0);
	    cellForReport2.setCellValue("Panchayat");
	    cellForReport2.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
	    
	    Cell cellForReport21 = rowForReport2.createCell(1);
	    cellForReport21.setCellValue("Polling Station #");
	    cellForReport21.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
	    
	   // int countForReport2 = 0;
		int colCountForReport2 = 2;
		Cell cell12 = rowForReport2.createCell(colCountForReport2);
		cell12.setCellValue("Total Voters");
		cell12.setCellStyle(style);
	    int spanCount = colCountForReport2+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
	    //countForReport2++;
	    colCountForReport2 = spanCount;
	    colCountForReport2++;
	    
	    //System.out.println(colCountForReport2);
	    
		for (String name : ageRanges)
		{
			Cell cell123 = rowForReport2.createCell(colCountForReport2);
		    cell123.setCellValue(name);
		    cell123.setCellStyle(style);
		    spanCount = colCountForReport2+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
		    //countForReport2++;
		    colCountForReport2 = spanCount;
		    colCountForReport2++;
		   // System.out.println(colCountForReport2);
		}
		rownum++;
		Row row2ForReport2 = sheet.createRow(rownum++);
		
		 Cell cell143= row2ForReport2.createCell(2);
		 cell143.setCellValue("Votes");
		 cell143.setCellStyle(style);
		    
		 cell143 = row2ForReport2.createCell(3);
		 cell143.setCellValue("Male");
		 cell143.setCellStyle(style);
		    
		 cell143 = row2ForReport2.createCell(4);
		 cell143.setCellValue("Female");
		 cell143.setCellStyle(style);
		int colSpanForReport2 = 5;
		for (String name : ageRanges)
	    {
		   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
		   cell134.setCellValue("Votes");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport2.createCell(colSpanForReport2++);
		   cell134.setCellValue("Male");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport2.createCell(colSpanForReport2++);
		   cell134.setCellValue("Female");
		   cell134.setCellStyle(style);
	   }
		
		for(AgeRangeVO VO : list)
	    {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	        cell2.setCellStyle(style1);
	 	    Cell cell21 = row4.createCell(1);
	 	    cell21.setCellValue(Long.parseLong(VO.getPanchayatName()));
	 	    cell21.setCellStyle(style1);
	        Cell cell73 = row4.createCell(2);
	        cell73.setCellValue(VO.getTotalCount());
	        cell73.setCellStyle(style1); 
	 		 Cell cell83 = row4.createCell(3);
	 		cell83.setCellValue(VO.getTotalMaleCount());
	 		cell83.setCellStyle(style1); 
	 		 Cell cell93 = row4.createCell(4);
	 		 cell93.setCellValue(VO.getTotalFemaleCount());
	 		 cell93.setCellStyle(style1);
	 	    int cellnum=5;
	 	    Map<String,AgeRangeVO >  mapp=VO.getMap();
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 	     if(VO1 != null)
	 	 	     {
	 	 	    	Cell cell7 = row4.createCell(cellnum++);
	 	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount()  != null ? VO1.getParticularAgeVotersCount():0);
	 	 	 		 cell7.setCellStyle(style1);
	 	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 	 		 cell8.setCellValue(VO1.getMaleCount() != null ?VO1.getMaleCount():0);
	 	 	 		 cell8.setCellStyle(style1);
	 	 	 		 
	 	 	 		 Cell cell9 = row4.createCell(cellnum++);
	 	 	 		 cell9.setCellValue(VO1.getFemaleCount()  != null ? VO1.getFemaleCount():0);
	 	 	 		 cell9.setCellStyle(style1);
	 	 	     }
	 	 	     else
	 	 	     {
	 	 	    	Cell cell7 = row4.createCell(cellnum++);
	 	 	 		 cell7.setCellValue(0);
	 	 	 		cell7.setCellStyle(style1);
	 	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 	 		 cell8.setCellValue(0);
	 	 	 		cell8.setCellStyle(style1);
	 	 	 		 
	 	 	 		 Cell cell9 = row4.createCell(cellnum++);
	 	 	 		 cell9.setCellValue(0);
	 	 	 		cell9.setCellStyle(style1);
	 	 	     }
	 	 		 
	 	 		
	 	   }
	 	   	  
	 	     rownum++;
	   }
		
}
	public void generateExcelsForBoothReport3(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{
		 DecimalFormat df = new DecimalFormat("##.##");
			
		 Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		   /* style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		    //for data.
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)11);
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)15);
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setFont(font2);
		    
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("5. Age wise(Gender%) Information - Booth wise");
	    cell.setCellStyle(style2);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    int rownum=1;
	    HSSFRow  rowForReport3= sheet.createRow((short) rownum);
	    Cell cellForReport3 = rowForReport3.createCell(0);
	    cellForReport3.setCellValue("Panchayat");
	    cellForReport3.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
	    
	    Cell cellForReport33 = rowForReport3.createCell(1);
	    cellForReport33.setCellValue("Polling Station #");
	    cellForReport33.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
	    
	   // int countForReport2 = 0;
		int colCountForReport3 = 2;
		Cell cell122 = rowForReport3.createCell(colCountForReport3);
		cell122.setCellValue("Total Voters");
		cell122.setCellStyle(style);
	    int spanCount3 = colCountForReport3+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
	    //countForReport2++;
	    colCountForReport3 = spanCount3;
	    colCountForReport3++;
	    
	    //System.out.println(colCountForReport2);
	    
		for (String name : ageRanges)
		{
			Cell cell123 = rowForReport3.createCell(colCountForReport3);
		    cell123.setCellValue(name);
		    cell123.setCellStyle(style);
		    spanCount3 = colCountForReport3+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
		    //countForReport2++;
		    colCountForReport3 = spanCount3;
		    colCountForReport3++;
		   // System.out.println(colCountForReport2);
		}
		rownum++;
		Row row2ForReport3 = sheet.createRow(rownum++);
		
		 Cell cell13= row2ForReport3.createCell(2);
		 cell13.setCellValue("Votes");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(3);
		 cell13.setCellValue("Male");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(4);
		 cell13.setCellValue("Female");
		 cell13.setCellStyle(style);
		int colSpanForReport3 = 5;
		for (String name : ageRanges)
	    {
		   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Votes");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Male");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Female");
		   cell134.setCellStyle(style);
	   }
		
		for(AgeRangeVO VO : list)
	    {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	 	    cell2.setCellStyle(style1);
	        Cell cell17 = row4.createCell(1);
	        cell17.setCellValue(Long.parseLong(VO.getPanchayatName()));
	        cell17.setCellStyle(style1);
	        Cell cell73 = row4.createCell(2);
	        cell73.setCellValue(VO.getTotalCount());
	        cell73.setCellStyle(style1);
	 		 Cell cell83 = row4.createCell(3);
	 		 cell83.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount())));
	 		 cell83.setCellStyle(style1);	
	 		 Cell cell93 = row4.createCell(4);
	 		 cell93.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount())));
	 		 cell93.setCellStyle(style1);
	 		 Long totlCountForAPollingstation = VO.getTotalCount();
	 		
	 		Map<String,AgeRangeVO >  mapp=VO.getMap();
	 	    int cellnum=5;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 		 cell7.setCellStyle(style1);	 
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getMaleCount() != null ? Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100))  : 0.0);
	 	 		 cell8.setCellStyle(style1);

	 	 		 Cell cell9 = row4.createCell(cellnum++);
	 	  		 cell9.setCellValue(VO1.getFemaleCount() != null ? Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) : 0.0);
	 	  	 	 cell9.setCellStyle(style1);
	 	   }
	   rownum++;
	   }
}	
	/**
	 * This service is used for generating the excel report for Hamlet wise	
	 * @param list
	 * @param sheet
	 * @param workbook
	 * @param ageRanges
	 */
	 
	public void generateExcelsForHamletReport1(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{
		DecimalFormat df = new DecimalFormat("##.##");
		
	    Font font = workbook.createFont();
	    font.setFontName("Calibri");
	    font.setFontHeightInPoints((short)12);
	    CellStyle style = workbook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    //for data.
	    Font font1 = workbook.createFont();
	    font1.setFontName("Calibri");
	    font1.setFontHeightInPoints((short)11);
	    CellStyle style1 = workbook.createCellStyle();
	    style1.setFont(font1);
	    //for heading.
	    Font font2 = workbook.createFont();
	    font2.setFontName("Calibri");
	    font2.setFontHeightInPoints((short)15);
	    CellStyle style2 = workbook.createCellStyle();
	    style2.setFont(font2);
	    
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("4. Age wise Information - Hamlet wise");
	    cell.setCellStyle(style2);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Panchayat");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Hamlet");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

	    cell1 = row1.createCell(2);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
	   
	    
	    
	    int count = 0;
		int colCount = 3;
		for (String name : ageRanges)
		{
			    cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+1;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    count++;
			    colCount = spanCount;
			    colCount++;
		}
	    	
		Row row2 = sheet.createRow(2);
		int colSpan = 3;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
		  //  colSpan++;
	   }

		int rownum = 3;
		for(AgeRangeVO VO : list)
	    {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getPanchayatName());
	 	    cell2.setCellStyle(style1);
	 	   
	 	    Cell cell3 = row4.createCell(1);
	 	    cell3.setCellValue(VO.getHamletName());
	 	    cell3.setCellStyle(style1);
	 	    
	 	    Cell cell4 = row4.createCell(2);
	 	    cell4.setCellValue(VO.getTotalCount());
	 	    cell4.setCellStyle(style1);
	 		
	 	    
	        Map<String,AgeRangeVO >  mapp=VO.getMap();
	 	    int cellnum=3;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount()>0 ? VO1.getParticularAgeVotersCount() :0);
	 	 		 cell7.setCellStyle(style1);
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getAgeRangePerc()>0 ? VO1.getAgeRangePerc():0.00 );
	 	 		 cell8.setCellStyle(style1);
	 	 	 }
	 	   rownum++;
	   }
}
	public void generateExcelsForHamletReport2(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{  //report 2	
	DecimalFormat df = new DecimalFormat("##.##");
  
	    Font font = workbook.createFont();
	    font.setFontName("Calibri");
	    font.setFontHeightInPoints((short)12);
	    CellStyle style = workbook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    //for data.
	    Font font1 = workbook.createFont();
	    font1.setFontName("Calibri");
	    font1.setFontHeightInPoints((short)11);
	    CellStyle style1 = workbook.createCellStyle();
	    style1.setFont(font1);
	    //for heading.
	    Font font2 = workbook.createFont();
	    font2.setFontName("Calibri");
	    font2.setFontHeightInPoints((short)15);
	    CellStyle style2 = workbook.createCellStyle();
	    style2.setFont(font2);
	    
    HSSFRow  rowHead= sheet.createRow((short) 0);
    Cell cell = rowHead.createCell(1);
    cell.setCellValue("3. Age wise (Gender) Information - Hamlet wise");
    cell.setCellStyle(style2);
    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
    
    int rownum=1;
    HSSFRow  rowForReport2= sheet.createRow((short) rownum);
    Cell cellForReport2 = rowForReport2.createCell(0);
    cellForReport2.setCellValue("Panchayat");
    cellForReport2.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
    
    Cell cellForReport21 = rowForReport2.createCell(1);
    cellForReport21.setCellValue("Hamlet");
    cellForReport21.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
    
   // int countForReport2 = 0;
	int colCountForReport2 = 2;
	Cell cell12 = rowForReport2.createCell(colCountForReport2);
	cell12.setCellValue("Total Voters");
	cell12.setCellStyle(style);
    int spanCount = colCountForReport2+2;
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
    //countForReport2++;
    colCountForReport2 = spanCount;
    colCountForReport2++;
    
    //System.out.println(colCountForReport2);
    
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport2.createCell(colCountForReport2);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount = colCountForReport2+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
	    //countForReport2++;
	    colCountForReport2 = spanCount;
	    colCountForReport2++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport2 = sheet.createRow(rownum++);
	
	 Cell cell143= row2ForReport2.createCell(2);
	 cell143.setCellValue("Votes");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(3);
	 cell143.setCellValue("Male");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(4);
	 cell143.setCellValue("Female");
	 cell143.setCellStyle(style);
	int colSpanForReport2 = 5;
	for (String name : ageRanges)
    {
	   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
   }
	
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getPanchayatName());
 	    cell2.setCellStyle(style1);
        Cell cell21 = row4.createCell(1);
 	    cell21.setCellValue(VO.getHamletName());
 	    cell21.setCellStyle(style1);
        Cell cell73 = row4.createCell(2);
        cell73.setCellValue(VO.getTotalCount());
        cell73.setCellStyle(style1);
 		 Cell cell83 = row4.createCell(3);
 		 cell83.setCellValue(VO.getTotalMaleCount());
 		 cell83.setCellStyle(style1);
 		 Cell cell93 = row4.createCell(4);
 		 cell93.setCellValue(VO.getTotalFemaleCount());	
 		 cell93.setCellStyle(style1);
 		 int cellnum=5;
 	    Map<String,AgeRangeVO >  mapp=VO.getMap();
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =   mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
 	 		 cell7.setCellStyle(style1);
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getMaleCount()!=null&&VO1.getMaleCount()>0 ?VO1.getMaleCount():0);
 	 		 cell8.setCellStyle(style1);
 	 		 Cell cell9 = row4.createCell(cellnum++);
             cell9.setCellValue(VO1.getFemaleCount()!=null && VO1.getFemaleCount()>0 ? VO1.getFemaleCount():0);
             cell9.setCellStyle(style1);
 	 		}
 	 rownum++;
    }
	
}
	public void generateExcelsForHamletReport3(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{
		DecimalFormat df = new DecimalFormat("##.##");
		 Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		   /* style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		    //for data.
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)11);
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)15);
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setFont(font2);
		    
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("4. Age wise(Gender%) Information - Hamlet wise");
	    cell.setCellStyle(style2);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    int rownum=1;
	    HSSFRow  rowForReport3= sheet.createRow((short) rownum);
	    Cell cellForReport3 = rowForReport3.createCell(0);
	    cellForReport3.setCellValue("Panchayat");
	    cellForReport3.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
	    
	    Cell cellForReport33 = rowForReport3.createCell(1);
	    cellForReport33.setCellValue("Hamlet");
	    cellForReport33.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
	    
	   // int countForReport2 = 0;
		int colCountForReport3 = 2;
		Cell cell122 = rowForReport3.createCell(colCountForReport3);
		cell122.setCellValue("Total Voters");
		cell122.setCellStyle(style);
	    int spanCount3 = colCountForReport3+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
	    //countForReport2++;
	    colCountForReport3 = spanCount3;
	    colCountForReport3++;
	    
	    //System.out.println(colCountForReport2);
	    
		for (String name : ageRanges)
		{
			Cell cell123 = rowForReport3.createCell(colCountForReport3);
		    cell123.setCellValue(name);
		    cell123.setCellStyle(style);
		    spanCount3 = colCountForReport3+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
		    //countForReport2++;
		    colCountForReport3 = spanCount3;
		    colCountForReport3++;
		   // System.out.println(colCountForReport2);
		}
		rownum++;
		Row row2ForReport3 = sheet.createRow(rownum++);
		
		 Cell cell13= row2ForReport3.createCell(2);
		 cell13.setCellValue("Votes");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(3);
		 cell13.setCellValue("Male");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(4);
		 cell13.setCellValue("Female");
		 cell13.setCellStyle(style);
		int colSpanForReport3 = 5;
		for (String name : ageRanges)
	    {
		   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Votes");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Male");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Female");
		   cell134.setCellStyle(style);
	   }
		
		for(AgeRangeVO VO : list)
	    {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getPanchayatName());
	 	    cell2.setCellStyle(style1);
	        
	        Cell cell17 = row4.createCell(1);
	        cell17.setCellValue(VO.getHamletName());
	        cell17.setCellStyle(style1);
	        Cell cell73 = row4.createCell(2);
	        cell73.setCellValue(VO.getTotalCount());
	        cell73.setCellStyle(style1); 
	 		 Cell cell83 = row4.createCell(3);
	 		 cell83.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount())));
	 		 cell83.setCellStyle(style1);
	 		 Cell cell93 = row4.createCell(4);
	 		 cell93.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount())));
	 	 	 cell93.setCellStyle(style1);
	 	   int cellnum=5;
	 	   Map<String,AgeRangeVO >  mapp=VO.getMap();
	       for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 	 	 cell7.setCellStyle(style1);
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getMaleCount()!=null ? Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)):0.00 );
	 	 		 cell8.setCellStyle(style1);
	 	 		 Cell cell9 = row4.createCell(cellnum++);
	 	 		 cell9.setCellValue(VO1.getFemaleCount()!=null ? Double.parseDouble(df.format( VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) :0.00);
	 	 		 cell9.setCellStyle(style1);
	 	 	}
	 	rownum++;
	   }
}
	/**
	 * This service is used for generations excel for panchayat wise
	 * @param list
	 * @param sheet
	 * @param workbook
	 * @param ageRanges
	 */
	public void generateExcelsForPanchayatReport1(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{
		 DecimalFormat df = new DecimalFormat("##.##");
		 //report1
		Font font1 = workbook.createFont();
	    font1.setFontName("Calibri");
	    font1.setFontHeightInPoints((short)11);
	    CellStyle style1 = workbook.createCellStyle();
	    style1.setFont(font1);
	    
	    
	    Font font = workbook.createFont();
	    font.setFontName("Calibri");
	    font.setFontHeightInPoints((short)12);
	    CellStyle style = workbook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    
	    
	    Font font2 = workbook.createFont();
	    font2.setFontName("Calibri");
	    font2.setFontHeightInPoints((short)15);
	    CellStyle style2 = workbook.createCellStyle();
	    style2.setFont(font2);
	    
	    
	   HSSFRow  rowHead= sheet.createRow((short) 0);
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("3. Age wise Information - Panchayath");
	    cell.setCellStyle(style2);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Mandal");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Panchayat");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

	    cell1 = row1.createCell(2);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
	   
	    cell1 = row1.createCell(3);
	    cell1.setCellValue("% of age in Constituency");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,3,3));
	    
	    cell1 = row1.createCell(4);
	    cell1.setCellValue("% of age in Mandal");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,4,4));
	    
	    int count = 0;
		int colCount = 5;
		for (String name : ageRanges)
		{
			    cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+1;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    cell1.setCellStyle(style);
			    count++;
			    colCount = spanCount;
			    colCount++;
		}
	    
		Row row2 = sheet.createRow(2);
		int colSpan = 5;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
		  //  colSpan++;
	   }

		int rownum = 3;
		Long totalCountForAConstituency = 0l;
		
		for(AgeRangeVO VO : list)
		{
			totalCountForAConstituency = totalCountForAConstituency + VO.getTotalCount();
		}
		
		Map<String,Long> mandalwiseCount = new HashMap<String, Long>();
		for(AgeRangeVO VO1 :  list)
		{
			Long totalCount = mandalwiseCount.get(VO1.getTehsilName());
			if(totalCount == null)
			{
				totalCount = VO1.getTotalCount();
				mandalwiseCount.put(VO1.getTehsilName(), totalCount);
			}
			else
			{
				totalCount = totalCount + VO1.getTotalCount();
				mandalwiseCount.put(VO1.getTehsilName(), totalCount);
			}
		}
	    for(AgeRangeVO VO : list)
	    {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	 	    cell2.setCellStyle(style1);
	 	   
	 	    Cell cell3 = row4.createCell(1);
	 	    cell3.setCellValue(VO.getPanchayatName());
	 	    cell3.setCellStyle(style1);
	 	    
	 	    Cell cell4 = row4.createCell(2);
	 	    cell4.setCellValue(VO.getTotalCount());
	 	    cell4.setCellStyle(style1);
	 	    
	 	    double percantageInConstituency = Double.parseDouble(df.format((VO.getTotalCount()/totalCountForAConstituency.floatValue())*100 ));
	 	    Cell cell6= row4.createCell(3);
	 	    cell6.setCellValue(percantageInConstituency);
	 	    cell6.setCellStyle(style1);
	 	   
	 	    Cell cell73= row4.createCell(4);
	 	    Long mandalCount1 =  mandalwiseCount.get(VO.getTehsilName());
	 	    Double percentage1 = Double.parseDouble(df.format((VO.getTotalCount()/mandalCount1.doubleValue() )*100));
	 	    cell73.setCellValue(percentage1);
	 	    cell73.setCellStyle(style1);
	 	   
	        Map<String,AgeRangeVO >  mapp=VO.getMap();

	 	    int cellnum=5;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =   mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 		 cell7.setCellStyle(style1);
	 	 		 
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getAgeRangePerc());
	 	 		 cell8.setCellStyle(style1);

	 	   }
	 	   rownum++;
	   }
}
public void generateExcelsForPanchayatReport2(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
{
	 // Report 2 
	 DecimalFormat df = new DecimalFormat("##.##");
	
	    Font font = workbook.createFont();
	    font.setFontName("Calibri");
	    font.setFontHeightInPoints((short)12);
	    CellStyle style = workbook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    
   Font font1 = workbook.createFont();
   font1.setFontName("Calibri");
   font1.setFontHeightInPoints((short)11);
   CellStyle style1 = workbook.createCellStyle();
   style1.setFont(font1);
   
   
   Font font2 = workbook.createFont();
   font2.setFontName("Calibri");
   font2.setFontHeightInPoints((short)15);
   CellStyle style2 = workbook.createCellStyle();
   style2.setFont(font2);
   
   
   
   HSSFRow  rowHead= sheet.createRow((short) 0);
   Cell cell = rowHead.createCell(1);
   cell.setCellValue("3. Age wise (Gender) Information - Panchayath");
   cell.setCellStyle(style2);
   sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
  
   int rownum=1;
   HSSFRow  rowForReport2= sheet.createRow((short) rownum);
   Cell cellForReport2 = rowForReport2.createCell(0);
   cellForReport2.setCellValue("Mandal");
   cellForReport2.setCellStyle(style);
   sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
   
   Cell cellForReport21 = rowForReport2.createCell(1);
   cellForReport21.setCellValue("Panchayat");
   cellForReport21.setCellStyle(style);
   sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
   
  // int countForReport2 = 0;
	int colCountForReport2 = 2;
	Cell cell12 = rowForReport2.createCell(colCountForReport2);
	cell12.setCellValue("Total Voters");
	cell12.setCellStyle(style);
   int spanCount = colCountForReport2+2;
   sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
   //countForReport2++;
   colCountForReport2 = spanCount;
   colCountForReport2++;
   
   //System.out.println(colCountForReport2);
   
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport2.createCell(colCountForReport2);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount = colCountForReport2+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
		cell123.setCellStyle(style);
	    //countForReport2++;
	    colCountForReport2 = spanCount;
	    colCountForReport2++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport2 = sheet.createRow(rownum++);
	
	 Cell cell143= row2ForReport2.createCell(2);
	 cell143.setCellValue("Votes");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(3);
	 cell143.setCellValue("Male");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(4);
	 cell143.setCellValue("Female");
	 cell143.setCellStyle(style);
	int colSpanForReport2 = 5;
	for (String name : ageRanges)
   {
	   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
  }
	
	for(AgeRangeVO VO : list)
   {
   	HSSFRow  row4= sheet.createRow((short) rownum);
	    Cell cell2 = row4.createCell(0);
	    cell2.setCellValue(VO.getTehsilName());
	    cell2.setCellStyle(style1);

	    Cell cell21 = row4.createCell(1);
	    cell21.setCellValue(VO.getPanchayatName());
	    cell21.setCellStyle(style1);

       Map<String,AgeRangeVO >  mapp=VO.getMap();
       
       Cell cell73 = row4.createCell(2);
       cell73.setCellValue(VO.getTotalCount());
	    cell73.setCellStyle(style1);

		 
		 Cell cell83 = row4.createCell(3);
		 cell83.setCellValue(VO.getTotalMaleCount());
 	     cell83.setCellStyle(style1);

		 Cell cell93 = row4.createCell(4);
		 cell93.setCellValue(VO.getTotalFemaleCount());
 	     cell93.setCellStyle(style1);

	    int cellnum=5;
	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	    {    
	 	     AgeRangeVO VO1 = mappp.getValue();
	 		 Cell cell7 = row4.createCell(cellnum++);
	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount() != null ? VO1.getParticularAgeVotersCount() : 0);
	 	     cell7.setCellStyle(style1);

	         Cell cell8 = row4.createCell(cellnum++);
	 		 cell8.setCellValue(VO1.getMaleCount() != null ? VO1.getMaleCount() : 0);
	 	     cell8.setCellStyle(style1);

	 		 Cell cell9 = row4.createCell(cellnum++);
	 		 cell9.setCellValue(VO1.getFemaleCount() != null ? VO1.getFemaleCount() : 0);
	 	     cell9.setCellStyle(style1);

	   }
	  
	   rownum++;
   }


		
}
public void generateExcelsForPanchayatReport3(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
{
	  // Report 3
	
		 DecimalFormat df = new DecimalFormat("##.##");
			
		 Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		   /* style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		    
	 //for data.	    
	 Font font1 = workbook.createFont();
	 font1.setFontName("Calibri");
	 font1.setFontHeightInPoints((short)11);
	 CellStyle style1 = workbook.createCellStyle();
	 style1.setFont(font1);
	 
	 //for heading.
	 Font font2 = workbook.createFont();
	 font2.setFontName("Calibri");
	 font2.setFontHeightInPoints((short)15);
	 CellStyle style2 = workbook.createCellStyle();
	 style2.setFont(font2);
	 
		   
		    HSSFRow  rowHead= sheet.createRow((short) 0);
		    Cell cell = rowHead.createCell(1);
		    cell.setCellValue("3. Age wise (Gender%) Information - Panchayath");
		    cell.setCellStyle(style2);
		    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
		
		  
	    int rownum=1;
	    HSSFRow  rowForReport3= sheet.createRow((short) rownum);
	    Cell cellForReport3 = rowForReport3.createCell(0);
	    cellForReport3.setCellValue("Mandal");
	    cellForReport3.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
	    
	    Cell cellForReport33 = rowForReport3.createCell(1);
	    cellForReport33.setCellValue("Panchayat");
	    cellForReport33.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
	    
	   // int countForReport2 = 0;
		int colCountForReport3 = 2;
		Cell cell122 = rowForReport3.createCell(colCountForReport3);
		cell122.setCellValue("Total Voters");
		cell122.setCellStyle(style);
	    int spanCount3 = colCountForReport3+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
	    cell122.setCellStyle(style);
	    //countForReport2++;
	    colCountForReport3 = spanCount3;
	    colCountForReport3++;
	    
	    //System.out.println(colCountForReport2);
	    
		for (String name : ageRanges)
		{
			Cell cell123 = rowForReport3.createCell(colCountForReport3);
		    cell123.setCellValue(name);
		    cell123.setCellStyle(style);
		    spanCount3 = colCountForReport3+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
		    cell123.setCellStyle(style);
		    //countForReport2++;
		    colCountForReport3 = spanCount3;
		    colCountForReport3++;
		   // System.out.println(colCountForReport2);
		}
		rownum++;
		Row row2ForReport3 = sheet.createRow(rownum++);
		
		 Cell cell13= row2ForReport3.createCell(2);
		 cell13.setCellValue("Votes");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(3);
		 cell13.setCellValue("Male");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(4);
		 cell13.setCellValue("Female");
		 cell13.setCellStyle(style);
		int colSpanForReport3 = 5;
		for (String name : ageRanges)
	    {
		   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Votes");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Male");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Female");
		   cell134.setCellStyle(style);
	   }
		
		for(AgeRangeVO VO : list)
	    {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	 	    cell2.setCellStyle(style1);

	        Map<String,AgeRangeVO >  mapp=VO.getMap();
	        
	        Cell cell17 = row4.createCell(1);
	        cell17.setCellValue(VO.getPanchayatName());
	        cell17.setCellStyle(style1);
	        
	        Cell cell73 = row4.createCell(2);
	        cell73.setCellValue(VO.getTotalCount());
	        cell73.setCellStyle(style1);
	 
	 		 Cell cell83 = row4.createCell(3);
	 	 	 cell83.setCellValue(VO.getTotalMaleCount() != null ? Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount())):0.00);
	         cell83.setCellStyle(style1);

	 		 Long totlCountForAPanchayat = VO.getTotalCount();
	 		 Cell cell93 = row4.createCell(4);
	 	 	 cell93.setCellValue(VO.getTotalFemaleCount() != null ? Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount())):0.00);
	         cell93.setCellStyle(style1);

	 	 	 int cellnum=5;
	 	   
			 
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	         cell7.setCellStyle(style1);

	 	 		 
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) );
	 	         cell8.setCellStyle(style1);

	 	 		 
	 	 		 Cell cell9 = row4.createCell(cellnum++);
	 	 		 cell9.setCellValue(Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) );
	 	         cell9.setCellStyle(style1);

	 	   }
	 	   rownum++;
	   }

		
}		 

	/**
	 * This service is used for generating the excel for Mandal wise
	 * @param list
	 * @param sheet
	 * @param workbook
	 * @param ageRanges
	 */
	public void generateExcelsForMandalReport1(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{      
		  DecimalFormat df = new DecimalFormat("##.##");
			
		  Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		    //for data.
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)11);
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)15);
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setFont(font2);

		    HSSFRow  rowHead= sheet.createRow((short) 0);
		    Cell cell = rowHead.createCell(1);
		    cell.setCellValue("2. Age wise Information - Mandal Wise");
		    cell.setCellStyle(style2);
		    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
		    
		    Row row1 = sheet.createRow(1);
		    Cell cell1 = row1.createCell(0);
		    cell1.setCellValue("Mandal");
		    cell1.setCellStyle(style);
		    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
		    
		    cell1 = row1.createCell(1);
		    cell1.setCellValue("Total Voters");
		    cell1.setCellStyle(style);
		    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

		    cell1 = row1.createCell(2);
		    cell1.setCellValue("% in Constituency");
		    cell1.setCellStyle(style);
		    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
		    cell1.setCellStyle(style);
		    
		    int count = 0;
			int colCount = 3;
			for (String name : ageRanges)
			{
				    cell1 = row1.createCell(colCount);
				    cell1.setCellValue(name);
				    cell1.setCellStyle(style);
				    int spanCount = colCount+1;
				    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
				    count++;
				    colCount = spanCount;
				    colCount++;
			}
		    	
			Row row2 = sheet.createRow(2);
			int colSpan = 3;
			for (String name : ageRanges)
		    {
			    cell1 = row2.createCell(colSpan++);
			    cell1.setCellValue("Votes");
			    cell1.setCellStyle(style);
			    
			    
			    cell1 = row2.createCell(colSpan++);
			    cell1.setCellValue("%");
			    cell1.setCellStyle(style);
			  //  colSpan++;
		   }
	   
			int rownum = 3;
			Long totalCountForAConstituency = 0l;
			for(AgeRangeVO VO : list)
			{
				totalCountForAConstituency = totalCountForAConstituency + VO.getTotalCount();
			}
	        for(AgeRangeVO VO : list)
	        {  
		    	HSSFRow  row4= sheet.createRow((short) rownum);
		 	    Cell cell2 = row4.createCell(0);
		 	    cell2.setCellValue(VO.getTehsilName());
		 	    cell2.setCellStyle(style1);
		 	    Cell cell3 = row4.createCell(1);
		 	    cell3.setCellValue(VO.getTotalCount());
		 	    cell3.setCellStyle(style1);
		 	    Cell cell6= row4.createCell(2);
		 	    cell6.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalCount()*100)/totalCountForAConstituency)));
		 	    cell6.setCellStyle(style1);

		        Map<String,AgeRangeVO >  mapp=VO.getMap();
	            int cellnum=3;
		 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
		 	    {    
		 	 	     AgeRangeVO VO1     =    mappp.getValue();
		 	 		 Cell cell7 = row4.createCell(cellnum++);
		 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	         	 	 cell7.setCellStyle(style1);
	         	 	 Cell cell8 = row4.createCell(cellnum++);
		 	 		 cell8.setCellValue(VO1.getAgeRangePerc());
		 	 	     cell8.setCellStyle(style1);
		 	 	 }
		 	   rownum++;
	       }
	        rownum++;
	        rownum++;
	        rownum++;
	        rownum++;
	      // Report 2 
	        
	       
	        HSSFRow  rowForReport2= sheet.createRow((short) rownum);
	        Cell cellForReport2 = rowForReport2.createCell(0);
	        cellForReport2.setCellValue("Mandal");
	        cellForReport2.setCellStyle(style);
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
		    
		   // int countForReport2 = 0;
			int colCountForReport2 = 1;
			Cell cell12 = rowForReport2.createCell(colCountForReport2);
			cell12.setCellValue("Total Voters");
			cell12.setCellStyle(style);
		    int spanCount = colCountForReport2+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
		    //countForReport2++;
		    colCountForReport2 = spanCount;
		    colCountForReport2++;
		    
		    //System.out.println(colCountForReport2);
		    
			for (String name : ageRanges)
			{
				Cell cell123 = rowForReport2.createCell(colCountForReport2);
			    cell123.setCellValue(name);
			    cell123.setCellStyle(style);
			    spanCount = colCountForReport2+2;
			    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
		 	    //countForReport2++;
			    colCountForReport2 = spanCount;
			    colCountForReport2++;
			   // System.out.println(colCountForReport2);
			}
			rownum++;
			Row row2ForReport2 = sheet.createRow(rownum++);
			
			 Cell cell143= row2ForReport2.createCell(1);
			 cell143.setCellValue("Votes");
			 cell143.setCellStyle(style);
			 cell143 = row2ForReport2.createCell(2);
			 cell143.setCellValue("Male");
			 cell143.setCellStyle(style);
			 cell143 = row2ForReport2.createCell(3);
			 cell143.setCellValue("Female");
			 cell143.setCellStyle(style);
			int colSpanForReport2 = 4;
			for (String name : ageRanges)
		    {
			   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
			   cell134.setCellValue("Votes");
			   cell134.setCellStyle(style);
			    
			   cell134 = row2ForReport2.createCell(colSpanForReport2++);
			   cell134.setCellValue("Male");
			   cell134.setCellStyle(style);
			    
			   cell134 = row2ForReport2.createCell(colSpanForReport2++);
			   cell134.setCellValue("Female");
			   cell134.setCellStyle(style);
		   }
			
			
			for(AgeRangeVO VO : list)
	        {
				Long maleCountForAMandal=0l;
				Long femaleCountForAMandal=0l;
		    	HSSFRow  row4= sheet.createRow((short) rownum);
		 	    Cell cell2 = row4.createCell(0);
		 	    cell2.setCellValue(VO.getTehsilName());
		        cell2.setCellStyle(style1);
		        Map<String,AgeRangeVO >  mapp=VO.getMap();
		        
		        Cell cell73 = row4.createCell(1);
		        cell73.setCellValue(VO.getTotalCount());
		        cell73.setCellStyle(style1); 
		 		 Cell cell83 = row4.createCell(2);
		 		cell83.setCellValue(VO.getTotalMaleCount());
		 		cell83.setCellStyle(style1);
		 		Cell cell93 = row4.createCell(3);
		 		cell93.setCellValue(VO.getTotalFemaleCount());
		 		cell93.setCellStyle(style1);
		 	    int cellnum=4;
		 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
		 	    {    
		 	 	     AgeRangeVO VO1     =    mappp.getValue();
		 	 		 Cell cell7 = row4.createCell(cellnum++);
		 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
		 	 		cell7.setCellStyle(style1);
		 	 		 Cell cell8 = row4.createCell(cellnum++);
		 	 		 cell8.setCellValue(VO1.getMaleCount());
		 	 		 cell8.setCellStyle(style1);
		 	 		 Cell cell9 = row4.createCell(cellnum++);
		 	 		 cell9.setCellValue(VO1.getFemaleCount());
		 	 		 cell9.setCellStyle(style1);
		 	   }
		       rownum++;
	       }
			
			
			rownum++;
	        rownum++;
	        rownum++;
	        rownum++;
	      // Report 3
	        
	       
	        HSSFRow  rowForReport3= sheet.createRow((short) rownum);
	        Cell cellForReport3 = rowForReport3.createCell(0);
	        cellForReport3.setCellValue("Mandal");
	        cellForReport3.setCellStyle(style);
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
		    
		   // int countForReport2 = 0;
			int colCountForReport3 = 1;
			Cell cell122 = rowForReport3.createCell(colCountForReport3);
			cell122.setCellValue("Total Voters");
			cell122.setCellStyle(style);
		    int spanCount3 = colCountForReport3+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
		    //countForReport2++;
		    colCountForReport3 = spanCount3;
		    colCountForReport3++;
		    
		    //System.out.println(colCountForReport2);
		    
			for (String name : ageRanges)
			{
				Cell cell123 = rowForReport3.createCell(colCountForReport3);
			    cell123.setCellValue(name);
			    cell123.setCellStyle(style);
			    spanCount3 = colCountForReport3+2;
			    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
			    //countForReport2++;
			    colCountForReport3 = spanCount3;
			    colCountForReport3++;
			   // System.out.println(colCountForReport2);
			}
			rownum++;
			Row row2ForReport3 = sheet.createRow(rownum++);
			
			 Cell cell13= row2ForReport3.createCell(1);
			 cell13.setCellValue("Votes");
			 cell13.setCellStyle(style);
			 cell13 = row2ForReport3.createCell(2);
			 cell13.setCellValue("Male");
			 cell13.setCellStyle(style);
			 cell13 = row2ForReport3.createCell(3);
			 cell13.setCellValue("Female");
			 cell13.setCellStyle(style);
			int colSpanForReport3 = 4;
			for (String name : ageRanges)
		    {
			   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
			   cell134.setCellValue("Votes");
			   cell134.setCellStyle(style);
			   cell134 = row2ForReport3.createCell(colSpanForReport3++);
			   cell134.setCellValue("Male");
			   cell134.setCellStyle(style);
			   cell134 = row2ForReport3.createCell(colSpanForReport3++);
			   cell134.setCellValue("Female");
			   cell134.setCellStyle(style);
		   }
			
			for(AgeRangeVO VO : list)
	        {   
				Long maleCountForAMandal=0l;
				Long femaleCountForAMandal=0l;
				
		    	HSSFRow  row4= sheet.createRow((short) rownum);
		 	    Cell cell2 = row4.createCell(0);
		 	    cell2.setCellValue(VO.getTehsilName());
		 	    cell2.setCellStyle(style1);
		        Map<String,AgeRangeVO >  mapp=VO.getMap();
		        
		        Cell cell73 = row4.createCell(1);
		        cell73.setCellValue(VO.getTotalCount());
		        cell73.setCellStyle(style1);
		 		 
		 		 Cell cell83 = row4.createCell(2);
		 		cell83.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount() )));
		 		cell83.setCellStyle(style1);
		 		 Cell cell93 = row4.createCell(3);
		 		cell93.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount() )));
		 		cell93.setCellStyle(style1);
		 		int cellnum=4;
		 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
		 	    {    
		 	 	     AgeRangeVO VO1     =    mappp.getValue();
		 	 		 Cell cell7 = row4.createCell(cellnum++);
		 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
		 	 		 cell7.setCellStyle(style1);
		 	 		 Cell cell8 = row4.createCell(cellnum++);
		 	 		 cell8.setCellValue(Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)));
		 	 		 cell8.setCellStyle(style1);
		 	 		 
		 	 		 Cell cell9 = row4.createCell(cellnum++);
		 	 		 cell9.setCellValue(Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)));
		 	 	     cell9.setCellStyle(style1);
		 	   }
		 	
		 	   rownum++;
	       }
}

	/**
	 * This service is used for generating the excel sheet for constituency level
	 * @param VO
	 * @param sheet
	 * @param workbook
	 * @param ageRanges
	 */
	public void generateExcelsForConstituency(AgeRangeVO  VO ,HSSFSheet sheet,HSSFWorkbook workbook,List<String> ageRanges)
	{
	
	    DecimalFormat df = new DecimalFormat("##.##");
	    Font font = workbook.createFont();
	    font.setFontName("Calibri");
	    font.setFontHeightInPoints((short)12);
	    CellStyle style = workbook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    //for data.
	    Font font1 = workbook.createFont();
	    font1.setFontName("Calibri");
	    font1.setFontHeightInPoints((short)11);
	    CellStyle style1 = workbook.createCellStyle();
	    style1.setFont(font1);
	    //for heading.
	    Font font2 = workbook.createFont();
	    font2.setFontName("Calibri");
	    font2.setFontHeightInPoints((short)15);
	    CellStyle style2 = workbook.createCellStyle();
	    style2.setFont(font2);
    
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("1. Age wise Information - "+ VO.getTehsilName() +" Constituency");
	    cell.setCellStyle(style2);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Constituency");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,2 ));


		int colCount = 3;
		for (String name : ageRanges)
		{
			  cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+1;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    colCount = spanCount;
			    colCount++;
		}
	    	
		Row row2 = sheet.createRow(2);
		int colSpan = 3;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
	   }
      // int rownum = 3;

	   HSSFRow  row4= sheet.createRow((short) 3);
	   Cell cell2 = row4.createCell(0);
	   cell2.setCellValue(VO.getTehsilName());//cname.
	   cell2.setCellStyle(style1);
	   Cell cell6= row4.createCell(1);
	   cell6.setCellValue(VO.getTotalCount());
	   cell6.setCellStyle(style1);
	   sheet.addMergedRegion(new CellRangeAddress(3,3,1,2));

       Map<String,AgeRangeVO >  mapp=VO.getMap();
   
	   int cellnum=3;
	   for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	   {    
		   	 AgeRangeVO VO1     =    mappp.getValue();
	 		 
		   	 Cell cell7 = row4.createCell(cellnum++);
	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 		 cell7.setCellStyle(style1); 
	 		 Cell cell8 = row4.createCell(cellnum++);
	 		 cell8.setCellValue(VO1.getAgeRangePerc());
	 		 cell8.setCellStyle(style1);
	   }
	 
	   HSSFRow  row453= sheet.createRow((short) 4);
	   	Cell cell713 = row453.createCell(1);
	   	cell713.setCellValue("Male");
	   	cell713.setCellStyle(style); 
		 Cell cell813 = row453.createCell(2);
		 cell813.setCellStyle(style);
		 cell813.setCellValue("Female");
		 int colCount23 =3;
		 for (String name : ageRanges)
	     {
				Cell cell73 = row453.createCell(colCount23++);
				cell73.setCellValue("Male");
				cell73.setCellStyle(style); 
				 Cell cell34 = row453.createCell(colCount23++);
				 cell34.setCellValue("Female");
				 cell34.setCellStyle(style);
	     }
		 
	   HSSFRow  row45= sheet.createRow((short) 5);
	     Cell cell71 = row45.createCell(1);
		 cell71.setCellValue(VO.getTotalMaleCount());
		 cell71.setCellStyle(style1);
		 Cell cell81 = row45.createCell(2);
		 cell81.setCellValue(VO.getTotalFemaleCount());
		 cell81.setCellStyle(style1);
	   int cellnum3=3;
	   for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	   {    
		   	 AgeRangeVO VO1 = mappp.getValue();
	 		 
		   	 Cell cell7 = row45.createCell(cellnum3++);
	 		 cell7.setCellValue(VO1.getMaleCount());
	 		 cell7.setCellStyle(style1);
	 		 Cell cell8 = row45.createCell(cellnum3++);
	 		 cell8.setCellValue(VO1.getFemaleCount());
	 		 cell8.setCellStyle(style1);
	   }
	   
	  
	   HSSFRow  row5= sheet.createRow((short)6);
	     Cell cell19 = row5.createCell(1);
	     Double malePerc = VO.getTotalMaleCount()/VO.getTotalCount().doubleValue() *100;
	     cell19.setCellValue(Double.parseDouble(df.format(malePerc)));
	     cell19.setCellStyle(style1);
		 Cell cell18 = row5.createCell(2);
		 Double femalePerc = VO.getTotalFemaleCount()/VO.getTotalCount().doubleValue() *100;
		 cell18.setCellValue(Double.parseDouble(df.format(femalePerc)));
		 cell18.setCellStyle(style1);
	     int cellnum78 = 3;
	     for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	     {    
		    AgeRangeVO VO1 = mappp.getValue();
	 		 Long totalCountAgeRangeWise = VO1.getMaleCount() + VO1.getFemaleCount();
		   	 Cell cell7 = row5.createCell(cellnum78++);
	 		 cell7.setCellValue(Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue() * 100)));
	 		 cell7.setCellStyle(style1);
	 		 Cell cell8 = row5.createCell(cellnum78++);
	 		 cell8.setCellValue(Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue() *100)));
	 		 cell8.setCellStyle(style1);
	     }
}
	
	
	/**
	 * This service is used for creating excel sheet for Important familes
	 * @param list
	 * @param sheet
	 * @param workbook
	 */
	public void generateExcelsForImportaneFamiles(List<VoterHouseInfoVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook,String type)
	{
		    Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    CellStyle style = workbook.createCellStyle();
		    style.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		   /* style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);*/
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);

		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)11);
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);

		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)15);
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setFont(font2);
		    
	    int i = 0;
	    Row row1 = sheet.createRow(0);
	    Cell cell1 = row1.createCell(i);
	    cell1.setCellValue("S.NO");
	    cell1.setCellStyle(style);
	    i++;
	    if(type.equalsIgnoreCase("RURAL"))
	    {
	    	cell1 = row1.createCell(i++);
		    cell1.setCellValue("Mandal/Muncipality");
		    cell1.setCellStyle(style);
		   
		    cell1 = row1.createCell(i++);
		    cell1.setCellValue("Panchayat");
		    cell1.setCellStyle(style);
		    
		    cell1 = row1.createCell(i++);
		    cell1.setCellValue("Hamlet");
		    cell1.setCellStyle(style);
	    }
	    else if(type.equalsIgnoreCase("URBAN"))
	    {
	    	cell1 = row1.createCell(i++);
		    cell1.setCellValue("Muncipality/Corporation");
		    cell1.setCellStyle(style);
		   
		    cell1 = row1.createCell(i++);
		    cell1.setCellValue("Ward");
		    cell1.setCellStyle(style);

	    }
	    else
	    {
	    	cell1 = row1.createCell(i++);
		    cell1.setCellValue("Mandal/Muncipality");
		    cell1.setCellStyle(style);
		   
		    cell1 = row1.createCell(i++);
		    cell1.setCellValue("Panchayat");
		    cell1.setCellStyle(style);
		    
		    cell1 = row1.createCell(i++);
		    cell1.setCellValue("Hamlet");
		    cell1.setCellStyle(style);
		    
		    cell1 = row1.createCell(i++);
		    cell1.setCellValue("Ward");
		    cell1.setCellStyle(style);
	    }
	    
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Booth");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Caste");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("H.NO");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Count");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Elder Voter Id");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Elder Name");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Elder Gender");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Elder Age");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Younger VoterId");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Younger Name");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Younger Gender");
	    cell1.setCellStyle(style);
	    
	    cell1 = row1.createCell(i++);
	    cell1.setCellValue("Younger Age");
	    cell1.setCellStyle(style);
	    
	    Map<Long,List<VoterHouseInfoVO>> map = new HashMap<Long, List<VoterHouseInfoVO>>();
		  for (VoterHouseInfoVO voterHouseInfoVO : list)
		  {
			  List<VoterHouseInfoVO> values =  map.get(Long.valueOf(voterHouseInfoVO.getPartNo()));
			  if(values == null)
			  {
				  values = new ArrayList<VoterHouseInfoVO>();
				 
			  }
			  values.add(voterHouseInfoVO);
			  map.put(Long.valueOf(voterHouseInfoVO.getPartNo()), values);
			  
		  }
		  Set<Long> boothIds = map.keySet();
		  
		  if(boothIds != null && boothIds.size() > 0)
		  {
			  int count3 = 1;
			  int count = 1;
			  for (Long boothId : boothIds)
			  {
				  List<VoterHouseInfoVO>  list1 = map.get(boothId);
				  Collections.sort(list1,sort);
				  for (VoterHouseInfoVO voterHouseInfoVO : list1)
				  {
					  int j = 0;
					  Row row2 = sheet.createRow(count3);
					    Cell cell31 = row2.createCell(j++);
					    cell31.setCellValue(count);
					   // cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getAddress() != null ? voterHouseInfoVO.getAddress().toString() : "");
					    
					   if(type.equalsIgnoreCase("RURAL"))
					   {
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getTehsilName() != null ? voterHouseInfoVO.getTehsilName().toString() : "");
						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getPanchayatName() != null ? voterHouseInfoVO.getPanchayatName().toString() : "");

						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getHamletName() != null ? voterHouseInfoVO.getHamletName().toString() : "");
						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getPartNo() != null ? voterHouseInfoVO.getPartNo().toString() : "");
						   // cell31.setCellStyle(style);

					   }
					   else if(type.equalsIgnoreCase("URBAN"))
					   {
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getTehsilName() != null ? voterHouseInfoVO.getTehsilName().toString() : "");
						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getWardName() != null ? voterHouseInfoVO.getWardName().toString() : "");

						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getPartNo() != null ? voterHouseInfoVO.getPartNo().toString() : "");
						   // cell31.setCellStyle(style);

					   }
					   else
					   {
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getTehsilName() != null ? voterHouseInfoVO.getTehsilName().toString() : "");
						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getPanchayatName() != null ? voterHouseInfoVO.getPanchayatName().toString() : "");

						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getHamletName() != null ? voterHouseInfoVO.getHamletName().toString() : "");
						    
						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getWardName() != null ? voterHouseInfoVO.getWardName().toString() : "");

						    cell31 = row2.createCell(j++);
						    cell31.setCellValue(voterHouseInfoVO.getPartNo() != null ? voterHouseInfoVO.getPartNo().toString() : "");
						   // cell31.setCellStyle(style);
					   }
					    
					    
					   
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getElderCaste() != null ? voterHouseInfoVO.getElderCaste().toString() : "");
					   // cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getHouseNo() != null ? voterHouseInfoVO.getHouseNo().toString() : "");
					   // cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getCount() != null ? voterHouseInfoVO.getCount().toString() : "");
					    //cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getVoterIdCardNo() != null ? voterHouseInfoVO.getVoterIdCardNo().toString() : "");
					   // cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getElder() != null ? voterHouseInfoVO.getElder().toString() : "");
					   // cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getElderGender() != null ? voterHouseInfoVO.getElderGender().toString() : "");
					    //cell31.setCellStyle(style);
					    
					   
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getElderAge() != null ? voterHouseInfoVO.getElderAge().toString() : "");
					    //cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getVoterGroup() != null ? voterHouseInfoVO.getVoterGroup().toString() : "");
					    //cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getYounger() != null ? voterHouseInfoVO.getYounger().toString() : "");
					   // cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getYoungerGender() != null ? voterHouseInfoVO.getYoungerGender().toString() : "");
					   // cell31.setCellStyle(style);
					    
					    cell31 = row2.createCell(j++);
					    cell31.setCellValue(voterHouseInfoVO.getYoungerAge() != null ? voterHouseInfoVO.getYoungerAge().toString() : "");
					   // cell31.setCellStyle(style);
					    count3++;
					    count++;
				  }
			  }
			  
		  }
}

	
	 public static Comparator<VoterHouseInfoVO> sort = new Comparator<VoterHouseInfoVO>()
	 {
			  
		  public int compare(VoterHouseInfoVO loc1, VoterHouseInfoVO loc2)
			{
			   return (loc2.getCount().compareTo(loc1.getCount()));
			}
	 };
}
