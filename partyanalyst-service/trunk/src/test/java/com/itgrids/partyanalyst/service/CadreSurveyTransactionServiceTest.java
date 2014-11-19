package com.itgrids.partyanalyst.service;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreSurveyTransactionServiceTest  extends BaseDaoTestCase {

	private ICadreTxnDetailsDAO cadreTxnDetailsDAO;
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	private ICadreOtpDetailsDAO cadreOtpDetailsDAO;
	private ITdpCadreReportService tdpCadreReportService;
	
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}
	
	public void setCadreTxnDetailsDAO(ICadreTxnDetailsDAO cadreTxnDetailsDAO) {
		this.cadreTxnDetailsDAO = cadreTxnDetailsDAO;
	}
	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}
	public void setCadreOtpDetailsDAO(ICadreOtpDetailsDAO cadreOtpDetailsDAO) {
		this.cadreOtpDetailsDAO = cadreOtpDetailsDAO;
	}
	
	@Test
	public void testGenerateReports()
	{
		
		try {
			List<Long> constituencyIds = new ArrayList<Long>();
			constituencyIds.add(232L);

			
			TdpCadreLocationWiseReportVO constituencyReportVO1 = tdpCadreReportService.generateExcelReportForTdpCadre(constituencyIds);
			HSSFWorkbook workbook = new HSSFWorkbook(); 
			
			DecimalFormat df = new DecimalFormat("##.##");
		    Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    
		    CellStyle style5 = workbook.createCellStyle();
		    style5.setFont(font);
		    style5.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style5.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style5.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style5.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style5.setAlignment(CellStyle.ALIGN_CENTER);
		    
		    
		    CellStyle style = workbook.createCellStyle();
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		    
		    //for data.
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)10);
		    
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    style1.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style1.setAlignment(CellStyle.ALIGN_CENTER);
		    
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)10);
		    
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style2.setAlignment(CellStyle.ALIGN_CENTER);
		    style2.setFont(font2);

		    String constituencyName = constituencyReportVO1.getName();
			
			HSSFSheet constituencyWiseSheet  ;
			int randomNumber = -100;
			while(randomNumber < 0)
			{
				randomNumber = new Random().nextInt()*10000000;
			}
		
			String filename="D:/apache-tomcat-6.0.37/webapps/PartyAnalyst/Reports"+"/"+constituencyName+"_"+randomNumber+".xls";
			FileOutputStream 	out=new FileOutputStream(filename);
		    List<TdpCadreLocationWiseReportVO> constituencywiseReportList = new ArrayList<TdpCadreLocationWiseReportVO>();
		    
		    constituencyWiseSheet = workbook.createSheet(IConstants.CONSTITUENCY);
		   	    
				constituencyWiseSheet.addMergedRegion(new CellRangeAddress(1,1,1,5 )); // TOTAL OVERVIEW  HEADING
				constituencyWiseSheet.addMergedRegion(new CellRangeAddress(5,5,1,4 )); //GENDER OVERVIEW HEADING
				constituencyWiseSheet.addMergedRegion(new CellRangeAddress(10,10,1,4 )); // AGE OVER VIEW HEADING
				
				HSSFRow row1 = constituencyWiseSheet.createRow((short)1);							
			    Cell cell1 = row1.createCell((short)1);
			    cell1.setCellValue(" CONSTITUENCY WISE OVER VIEW  ");
			    cell1.setCellStyle(style);
			    
			    HSSFRow row2 = constituencyWiseSheet.createRow((short)2);
			    Cell cell2 = row2.createCell((short)1);
			    cell2.setCellValue(IConstants.CONSTITUENCY);
			    cell2.setCellStyle(style1);   
				    
			    cell2 = row2.createCell((short)2);
			    cell2.setCellValue(" TOTAL VOTERS ");
			    cell2.setCellStyle(style1);
			   
			    cell2 = row2.createCell((short)3);
			    cell2.setCellValue(" TARGETED CADRE ");
			    cell2.setCellStyle(style1);
			    
			    cell2 = row2.createCell((short)4);
			    cell2.setCellValue(" REGISTRIED CADRE ");
			    cell2.setCellStyle(style1);
			    
			    cell2 = row2.createCell((short)5);
			    cell2.setCellValue(" REGISTERED CADRE PERCENTAGE ");
			    cell2.setCellStyle(style1);
			    
			    HSSFRow row3 = constituencyWiseSheet.createRow((short)3);
			    Cell cell3 = row3.createCell((short)1);
			    cell3.setCellValue(constituencyReportVO1.getName() != null ? constituencyReportVO1.getName() : "");
			    cell3.setCellStyle(style2);
			    
			    cell3 = row3.createCell((short)2);
			    cell3.setCellValue(constituencyReportVO1.getTotalVoters() != null ?constituencyReportVO1.getTotalVoters():0L);
			    cell3.setCellStyle(style2);
			    
			    cell3 = row3.createCell((short)3);
			    cell3.setCellValue(constituencyReportVO1.getTargetedCadre() != null ? constituencyReportVO1.getTargetedCadre() :0L);
			    cell3.setCellStyle(style2);
			    			    
			    cell3 = row3.createCell((short)4);
			    cell3.setCellValue(constituencyReportVO1.getRegisteredCadre() != null ? constituencyReportVO1.getRegisteredCadre() :0L);
			    cell3.setCellStyle(style2);
			    
			    cell3 = row3.createCell((short)5);
			    cell3.setCellValue(constituencyReportVO1.getPercentage() != null ? constituencyReportVO1.getPercentage().toString() :"");
			    cell3.setCellStyle(style2);
			    
			    HSSFRow row5 = constituencyWiseSheet.createRow(5);							
			    Cell cell5 = row5.createCell((short)1);
			    cell5.setCellValue(" GENDER WISE OVER VIEW  ");
			    cell5.setCellStyle(style);
			    
			    HSSFRow row6 = constituencyWiseSheet.createRow((short)6);
			    Cell cell6 = row6.createCell((short)1);
			    cell6.setCellValue(" GENDER ");
			    cell6.setCellStyle(style1);   
				    
			    cell6 = row6.createCell((short)2);
			    cell6.setCellValue(" TOTAL ");
			    cell6.setCellStyle(style1); 
			    
			    cell6 = row6.createCell((short)3);
			    cell6.setCellValue(" REGISTERED ");
			    cell6.setCellStyle(style1); 
			    
			    cell6 = row6.createCell((short)4);
			    cell6.setCellValue(" REGISTERED PERCENTAGE ");
			    cell6.setCellStyle(style1); 
			    
			    HSSFRow row7 = constituencyWiseSheet.createRow(7);
			    Cell cell7 = row7.createCell((short)1);
			    cell7.setCellValue(" MALE ");
			    cell7.setCellStyle(style1);
			   
			    cell7 = row7.createCell((short)2);
			    cell7.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() :0L);
			    cell7.setCellStyle(style2);
			    
			    cell7 = row7.createCell((short)3);
			    cell7.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount():0L);
			    cell7.setCellStyle(style2);
			    
			    cell7 = row7.createCell(4);
			    cell7.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc():0.0d);
			    cell7.setCellStyle(style2);
			    
			    HSSFRow row8 = constituencyWiseSheet.createRow(8);
			    Cell cell8 = row8.createCell((short)1);
			    cell8.setCellValue(" FEMALE ");
			    cell8.setCellStyle(style1);
			    
			    cell8 = row8.createCell((short)2);
			    cell8.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() :0L);
			    cell8.setCellStyle(style2);
			    
			    cell8 = row8.createCell((short)3);
			    cell8.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() :0L);
			    cell8.setCellStyle(style2);
			    
			    cell8 = row8.createCell((short)4);
			    cell8.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() :0.0d);
			    cell8.setCellStyle(style2);
			    
			    
			    
			    
			    
			    
			    
			    HSSFRow row10 = constituencyWiseSheet.createRow(10);
			    Cell cell10 = row10.createCell((short)1);
			    cell10.setCellValue(" AGE WISE OVER VIEW  ");
			    cell10.setCellStyle(style);
			    
			    HSSFRow row11 = constituencyWiseSheet.createRow(11);
			    
			    Cell cell11 = row11.createCell((short)1);
			    cell11.setCellValue(" AGE RANGE ");
			    cell11.setCellStyle(style1);
			    
			    cell11 = row11.createCell((short)2);
			    cell11.setCellValue(" Total ");
			    cell11.setCellStyle(style1);
			    
			    cell11 = row11.createCell((short)3);
			    cell11.setCellValue(" Registered ");
			    cell11.setCellStyle(style1);
			    
			    cell11 = row11.createCell((short)4);
			    cell11.setCellValue(" Percentage ");
			    cell11.setCellStyle(style1);
			    
			    constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList();
			    HSSFRow row12 = constituencyWiseSheet.createRow(12);
			    
			    Cell cell12 = row12.createCell((short)1);
			    cell12.setCellValue(" 18-25 ");
			    cell12.setCellStyle(style1);
			    
			    cell11 = row12.createCell((short)2);
			    cell11.setCellValue( constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getTotalVoters() != null ?  constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getTotalVoters():0L);
			    cell11.setCellStyle(style2);
			    
			    HSSFRow row13 = constituencyWiseSheet.createRow(13);
			    
			    cell11 = row13.createCell((short)2);//64402
			    cell11.setCellValue( constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getTotalVoters() != null ?  constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getTotalVoters():0L);
			    cell11.setCellStyle(style2);
			    
			    HSSFRow row14 = constituencyWiseSheet.createRow(14);
			    
			    cell11 = row14.createCell((short)2);//48797
			    cell11.setCellValue( constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getTotalVoters() != null ?  constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getTotalVoters():0L);
			    cell11.setCellStyle(style2);
			    
			    HSSFRow row15 = constituencyWiseSheet.createRow(15);
			    
			    cell11 = row15.createCell((short)2);
			    cell11.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getTotalVoters() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getTotalVoters():0L);
			    cell11.setCellStyle(style2);
			    
			    HSSFRow row16 = constituencyWiseSheet.createRow(16);
			    
			    cell11 = row16.createCell((short)2);
			    cell11.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getTotalVoters() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getTotalVoters():0L);
			    cell11.setCellStyle(style2);
			    
			    
			    
			    
			    Cell cell13 = row13.createCell((short)1);
			    cell13.setCellValue(" 26-35 ");
			    cell13.setCellStyle(style1);
			    
			    cell13 = row12.createCell((short)3);
			    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getCadresInAge():0L);
			    cell13.setCellStyle(style2);
			    
			    cell13 = row13.createCell((short)3);
			    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getCadresInAge():0L);
			    cell13.setCellStyle(style2);
			    
			    cell13 = row14.createCell((short)3);
			    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getCadresInAge():0L);
			    cell13.setCellStyle(style2);
			    
			    cell13 = row15.createCell((short)3);
			    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getCadresInAge():0L);
			    cell13.setCellStyle(style2);
			    
			    cell13 = row16.createCell((short)3);
			    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getCadresInAge():0L);
			    cell13.setCellStyle(style2);
			    
			    
			    
			    Cell cell14 = row14.createCell((short)1);
			    cell14.setCellValue(" 36-45 ");
			    cell14.setCellStyle(style1);
			    
			    cell14 = row12.createCell((short)4);
			    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getAgePerc():0L);
			    cell14.setCellStyle(style2);
			    
			    cell14 = row13.createCell((short)4);
			    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getAgePerc():0L);
			    cell14.setCellStyle(style2);
			    
			    cell14 = row14.createCell((short)4);
			    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getAgePerc():0L);
			    cell14.setCellStyle(style2);
			    
			    cell14 = row15.createCell((short)4);
			    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getAgePerc():0L);
			    cell14.setCellStyle(style2);
			    
			    cell14 = row16.createCell((short)4);
			    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getAgePerc():0L);
			    cell14.setCellStyle(style2);
			    
			    
			    
			    Cell cell15 = row15.createCell((short)1);
			    cell15.setCellValue(" 46-60 ");
			    cell15.setCellStyle(style1);
			    
			   
			    
			    Cell cell16 = row16.createCell((short)1);
			    cell16.setCellValue(" Above 60 ");
			    cell16.setCellStyle(style1);
			    
			    
			    
			    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(17,17,1,6 )); // MANDAL WISE OVER VIEW 
			    
			    HSSFRow row17 = constituencyWiseSheet.createRow((short)17);
			    Cell cell17 = row17.createCell((short)1);
			    cell17.setCellValue(" MANDAL WISE OVER VIEW  ");
			    cell17.setCellStyle(style);
			    
			    HSSFRow row18 = constituencyWiseSheet.createRow((short)18);
			    Cell cell18 = row16.createCell((short)1);
			    cell18.setCellValue(IConstants.TEHSIL);
			    cell18.setCellStyle(style1);   
				    
			    cell18 = row18.createCell((short)2);
			    cell18.setCellValue(" TOTAL VOTERS ");
			    cell18.setCellStyle(style1);
			   
			    cell18 = row18.createCell((short)3);
			    cell18.setCellValue(" TARGETED CADRE ");
			    cell18.setCellStyle(style1);
			    
			    cell18 = row18.createCell((short)4);
			    cell18.setCellValue(" REGISTRIED CADRE ");
			    cell18.setCellStyle(style1);
			    
			    cell18 = row18.createCell((short)5);
			    cell18.setCellValue(" REGISTRIED CADRE PERCENTAGE ");
			    cell18.setCellStyle(style1);
			    List<TdpCadreLocationWiseReportVO> mandalsList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getTehsilWiseList();
			    short count = 19;
			    if(mandalsList != null && mandalsList.size()>0)
			    {
			    	for (TdpCadreLocationWiseReportVO mandalsVo : mandalsList) 
			    	{
			    		HSSFRow tehsilRow = constituencyWiseSheet.createRow((short)count);
			    		 Cell tehsilCell = tehsilRow.createCell((short)1);
			    		 tehsilCell.setCellValue(mandalsVo.getName() != null ? mandalsVo.getName() : "");
			    		 tehsilCell.setCellStyle(style2);
			    		 
			    		 tehsilCell = tehsilRow.createCell((short)2);
			    		 tehsilCell.setCellValue(mandalsVo.getTotalVoters() != null ?mandalsVo.getTotalVoters().toString():" -- ");
			    		 tehsilCell.setCellStyle(style2);
					    
			    		 tehsilCell = tehsilRow.createCell((short)3);
			    		 tehsilCell.setCellValue(mandalsVo.getTargetedCadre() != null ? mandalsVo.getTargetedCadre().toString() :" -- ");
			    		 tehsilCell.setCellStyle(style2);
					    			    
			    		 tehsilCell = tehsilRow.createCell((short)4);
			    		 tehsilCell.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString() :" -- ");
			    		 tehsilCell.setCellStyle(style2);
					    
			    		 tehsilCell = tehsilRow.createCell((short)5);//mandal wise percentage
					     tehsilCell.setCellValue(mandalsVo.getPercentage() != null ? mandalsVo.getPercentage().toString() :" -- ");
					     tehsilCell.setCellStyle(style2);
						 
			    		 
			    		 /*tehsilCell = tehsilRow.createCell((short)5);//mandal wise percentage
					     tehsilCell.setCellValue(mandalsVo.getRegisteredCadre()*100/constituencyReportVO1.getRegisteredCadre());
					     tehsilCell.setCellStyle(style2);*/
						 
					     count = (short) (count+1);
					}
			    }
			   
			    count = (short) (count+1);
			    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(count,count,1,6 )); // PANCHAYAT WISE OVER VIEW 
			    HSSFRow row = constituencyWiseSheet.createRow((short)count);
			    Cell cell = row.createCell((short)1);
			    cell.setCellValue(" PANCHAYAT WISE OVER VIEW  ");
			    cell.setCellStyle(style);
			    
			    count = (short) (count+1);
			    
			    HSSFRow textRow = constituencyWiseSheet.createRow((short)count);
			    Cell textCell = textRow.createCell((short)1);
			    textCell.setCellValue(IConstants.PANCHAYAT);
			    textCell.setCellStyle(style1);   
				    
			    textCell = textRow.createCell((short)2);
			    textCell.setCellValue(" TOTAL VOTERS ");
			    textCell.setCellStyle(style1);
			   
			    textCell = textRow.createCell((short)3);
			    textCell.setCellValue(" TARGETED CADRE ");
			    textCell.setCellStyle(style1);
			    
			    textCell = textRow.createCell((short)4);
			    textCell.setCellValue(" REGISTRIED CADRE ");
			    textCell.setCellStyle(style1);
			    
			    textCell = textRow.createCell((short)5);
			    textCell.setCellValue(" REGISTRIED CADRE PERCENTAGE ");
			    textCell.setCellStyle(style1);
			    
			    count = (short) (count+1);
			    List<TdpCadreLocationWiseReportVO> panchayatList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getPanchayatWiseList();
			    
			    if(panchayatList != null && panchayatList.size()>0)
			    {
			    	for (TdpCadreLocationWiseReportVO mandalsVo : panchayatList) 
			    	{
			    		HSSFRow panchaytrow = constituencyWiseSheet.createRow((short)count);
			    		 Cell panchaytCell = panchaytrow.createCell((short)1);
			    		 panchaytCell.setCellValue(mandalsVo.getName() != null ? mandalsVo.getName() : "");
			    		 panchaytCell.setCellStyle(style2);
			    		 
			    		 panchaytCell = panchaytrow.createCell((short)2);
			    		 panchaytCell.setCellValue(mandalsVo.getTotalVoters() != null ?mandalsVo.getTotalVoters().toString():" -- ");
			    		 panchaytCell.setCellStyle(style2);
					    
			    		 panchaytCell = panchaytrow.createCell((short)3);
			    		 panchaytCell.setCellValue(mandalsVo.getTargetedCadre() != null ? mandalsVo.getTargetedCadre().toString() :" -- ");
			    		 panchaytCell.setCellStyle(style2);
					    			    
			    		 panchaytCell = panchaytrow.createCell((short)4);
			    		 panchaytCell.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString() :" -- ");
			    		 panchaytCell.setCellStyle(style2);
					    
			    		 panchaytCell = panchaytrow.createCell((short)5);
			    		 panchaytCell.setCellValue(mandalsVo.getPercentage() != null ? mandalsVo.getPercentage().toString() :" -- ");
			    		 panchaytCell.setCellStyle(style2);
						 
					     count = (short) (count+1);
					}
			    }
			    
		    workbook.write(out);
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	/*
	@Test
	public void testDetails()
	{
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			
			cal.add(Calendar.DATE, -1);
			Date yesterDay = cal.getTime();
			
			Long totalTeamCount = cadreSurveyUserDAO.getTotalCadreSurveyDetails();
			List<Long> teaminfo = cadreTxnDetailsDAO.getTotalCadreSurveyTxnTeamSize(yesterDay);
			Long workingTeamSize = 0L;
			returnVO.setTeamSize(totalTeamCount);
			returnVO.setIdleTeamSize( totalTeamCount - workingTeamSize );
			
			List<Object[]> yesterTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(null,yesterDay);
			
			if(yesterTransactions != null && yesterTransactions.size()>0)
			{
				for (Object[] transaction : yesterTransactions)
				{
					Long paidAmount = transaction[0] != null ?  Long.valueOf(transaction[0].toString()):0L;
					Long pendingAmount = transaction[1] != null ? Long.valueOf(transaction[1].toString()) :0L;
					Long totalAmount = transaction[2] != null ? Long.valueOf(transaction[2].toString()) :0L;
				
					returnVO.setSubmittedCount(paidAmount / 100 );
					returnVO.setNotSubmittedCount(pendingAmount / 100 );					
					returnVO.setYesterDayCount(totalAmount / 100);
					
					returnVO.setDepositedAmount(paidAmount);
					returnVO.setRemainingAmount(pendingAmount);
					returnVO.setActualAmount(totalAmount);
				}
			}
			
			cal.add(Calendar.DATE, -7); 
			Date oneWeekBackDate = cal.getTime();
			
			List<Object[]> thisWeekTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(today,oneWeekBackDate);
			
			if(thisWeekTransactions != null && thisWeekTransactions.size()>0)
			{
				for (Object[] transactions : thisWeekTransactions )
				{
					Long totalWeekAmount = transactions[2] != null ? Long.valueOf(transactions[2].toString()) :0L;
					returnVO.setWeekCount(totalWeekAmount / 100);
				}
			}
			
			List<Object[]> totalTransactions = cadreTxnDetailsDAO.getTransactionDetailsByDates(today,null);
			
			if(totalTransactions != null && totalTransactions.size()>0)
			{
				for (Object[] totalTransactins : totalTransactions )
				{
					Long totalTransactionsCount = totalTransactins[2] != null ? Long.valueOf(totalTransactins[2].toString()) :0L;
					returnVO.setRecordsCount( totalTransactionsCount / 100 );
				}
			}
			
			
			List<Object[]> yesterDayOtpInfo = null;//cadreOtpDetailsDAO.getOtpDetailsForDates(null,yesterDay);
			Map<String,Long> transactionOtpMap = new TreeMap<String,Long>();
			transactionOtpMap.put("Y", 0L);
			transactionOtpMap.put("N", 0L);
			
			if(yesterDayOtpInfo != null && yesterDayOtpInfo.size()>0)
			{
				for (Object[] otp : yesterDayOtpInfo )
				{
					if(transactionOtpMap.get(otp[0] != null ? otp[0].toString().trim():"") != null)
					{
						Long count = transactionOtpMap.get(otp[0] != null ? otp[0].toString().trim():"");
						count = count + ((otp[1] != null ? Long.valueOf(otp[1].toString().trim()):0L) + (otp[2] != null ? Long.valueOf(otp[2].toString().trim()):0L) ) ;
						
						transactionOtpMap.put(otp[0] != null ? otp[0].toString().trim():"", count != 0L ? count / 100:0L );
					}
				}
			}
			
			if(transactionOtpMap != null && transactionOtpMap.size()>0)
			{
				for( String status : transactionOtpMap.keySet()) 
				{
					if(status != null && status.trim().length()>0)
					{
						if(status.trim().equalsIgnoreCase("Y"))
						{
							returnVO.setOtpConfirmCount(transactionOtpMap.get(status.trim()));
						}
						if(status.trim().equalsIgnoreCase("N"))
						{
							returnVO.setRemainingOTPCount(transactionOtpMap.get(status.trim()));
						}
					}
				}
			}
			
			Long totalOTPGeneratedCount = returnVO.getOtpConfirmCount() + returnVO.getRemainingOTPCount();
			returnVO.setOtpRequestCount(totalOTPGeneratedCount);
			
			List<Object[]> otpNotRequestedTxnsDetailsList = cadreOtpDetailsDAO.getOfflineTxnDetailsIdsForDates(null,yesterDay);
			
			Map<String,Long> transactionNONOtpMap = new TreeMap<String,Long>();
			transactionNONOtpMap.put("Y", 0L);
			transactionNONOtpMap.put("N", 0L);
			
			if(otpNotRequestedTxnsDetailsList != null && otpNotRequestedTxnsDetailsList.size()>0)
			{
				Long totalRecords = 0L;
				for (Object[] txnInfo : otpNotRequestedTxnsDetailsList)
				{
					if(transactionNONOtpMap.get(txnInfo[0] != null ? txnInfo[0].toString().trim():"") != null)
					{
						Long count = transactionNONOtpMap.get(txnInfo[0] != null ? txnInfo[0].toString().trim():"");
						count = count+((txnInfo[1] != null ? Long.valueOf(txnInfo[1].toString().trim()):0L));
						totalRecords = totalRecords + count;
						
						transactionNONOtpMap.put(txnInfo[0] != null ? txnInfo[0].toString().trim():"",count != 0L ? count / 100:0L );
					}
				}
				
				returnVO.setOtpNonRequestCount(totalRecords != 0L ? totalRecords:0L );
			}
			
			if(transactionNONOtpMap != null && transactionNONOtpMap.size()>0)
			{
				for( String txnStatus : transactionNONOtpMap.keySet()) 
				{
					if(txnStatus != null && txnStatus.trim().length()>0)
					{
						if(txnStatus.trim().equalsIgnoreCase("Y"))
						{
							returnVO.setNonOTPConfirmCount(transactionOtpMap.get(txnStatus.trim()));
						}
						if(txnStatus.trim().equalsIgnoreCase("N"))
						{
							returnVO.setRemainingNonOTPCount(transactionOtpMap.get(txnStatus.trim()));
						}
					}
				}
			}
			
			Long notPaidRecords = returnVO.getYesterDayCount() - (returnVO.getOtpConfirmCount() + returnVO.getNonOTPConfirmCount());
			
			returnVO.setPendingCount(notPaidRecords);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
