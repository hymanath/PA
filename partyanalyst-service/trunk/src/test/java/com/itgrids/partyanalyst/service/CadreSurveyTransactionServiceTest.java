package com.itgrids.partyanalyst.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
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
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
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
			/*constituencyIds.add(232L);
			constituencyIds.add(354L);
			constituencyIds.add(282L);
			*/
			//constituencyIds.add(241L);
			
			constituencyIds.add(267L);
			constituencyIds.add(270L);
			constituencyIds.add(271L);
			constituencyIds.add(272L);
			constituencyIds.add(273L);
			constituencyIds.add(275L);
			constituencyIds.add(276L);
			constituencyIds.add(277L);
			constituencyIds.add(278L);
			constituencyIds.add(279L);
			constituencyIds.add(297L);
			constituencyIds.add(298L);
			constituencyIds.add(299L);
			constituencyIds.add(300L);













			
			TdpCadreLocationWiseReportVO constituencyReportVO1 = tdpCadreReportService.generateExcelReportForTdpCadre(constituencyIds);
			
			/*HSSFWorkbook workbook = new HSSFWorkbook(); 
			
			DecimalFormat df2 = new DecimalFormat("###.##");
			
			HSSFFont hSSFFont = workbook.createFont();
	        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
	        hSSFFont.setFontHeightInPoints((short) 9);
	        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        hSSFFont.setColor(HSSFColor.BLACK.index);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			
			Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)10);
		    
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)10);
		   
		    
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    style1.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style1.setAlignment(CellStyle.ALIGN_CENTER);
		    style1.setFont(hSSFFont);
		   
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style2.setAlignment(CellStyle.ALIGN_CENTER);
		    style2.setFont(font2);
		    
		    CellStyle style5 = workbook.createCellStyle();
		    style5.setFont(hSSFFont);
		    
		    
		    CellStyle style = workbook.createCellStyle();
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		   
		    String constituencyName = constituencyReportVO1.getName();
		    Long totalRegistered = 0l;
			Long totalVoters = 0l;
			HSSFSheet constituencyWiseSheet;
			
			int randomNumber = -100;
			while(randomNumber < 0)
			{
				randomNumber = new Random().nextInt()*10000000;
			}
			 String path = "D:/apache-tomcat-6.0.37/webapps/PartyAnalyst/Reports";
			 FileOutputStream fileOut = null;
			 String filename = "";
			 FileOutputStream fos = null;
			 
			 fos  = new FileOutputStream(path+"/"+randomNumber+"_Reports" +".zip");
			 ZipOutputStream zos = new ZipOutputStream(fos);
			 filename= path+"/"+"_"+constituencyName+ " "+randomNumber+".xls";
			 String FILE = filename;
			 File file  = new File(FILE);
			 file.createNewFile();
			 fileOut =  new FileOutputStream(FILE);
			 
			constituencyWiseSheet = workbook.createSheet(IConstants.CONSTITUENCY);

			constituencyWiseSheet.addMergedRegion(new CellRangeAddress(2,2,1,5 )); // TOTAL OVERVIEW  HEADING
			
			HSSFRow row2 = constituencyWiseSheet.createRow((short)2);							
		    Cell cell1 = row2.createCell((short)1);
		    cell1.setCellValue(" KRIYASEELA SABHYATVAM 2014 STATUS  - "+" "+constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getConstituencyNo()+" - "+constituencyName);
		    cell1.setCellStyle(style5);
		    
		    HSSFRow row4 = constituencyWiseSheet.createRow((short)4);							
		    Cell cell2 = row4.createCell((short)1);
		    cell2.setCellValue(" 2012 CADRE COUNT ");
		    cell2.setCellStyle(style5);
		    
		    cell2 = row4.createCell((short)2);
		    cell2.setCellValue(constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getCadresCount() != null ? constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getCadresCount().toString():"");
		    
		    cell2 = row4.createCell((short)4);
		    cell2.setCellValue(" 2014 CADRE COUNT ");
		    cell2.setCellStyle(style5);
		    
		    cell2 = row4.createCell((short)5);
		    cell2.setCellValue(constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getRegisteredCadre() != null ? constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getRegisteredCadre().toString() :"");

		    
		    HSSFRow row5 = constituencyWiseSheet.createRow((short)6);
		    Cell cell5 = row5.createCell((short)1);
		    cell5.setCellValue(IConstants.CONSTITUENCY);
		    cell5.setCellStyle(style1);   
			    
		    cell5 = row5.createCell((short)2);
		    cell5.setCellValue(" TOTAL VOTERS ");
		    cell5.setCellStyle(style1);
		   
		    cell5 = row5.createCell((short)3);
		    cell5.setCellValue(" TARGET ");
		    cell5.setCellStyle(style1);
		    
		    cell5 = row5.createCell((short)4);
		    cell5.setCellValue("  ACHIEVED ");
		    cell5.setCellStyle(style1);
		    
		    cell5 = row5.createCell((short)5);
		    cell5.setCellValue(" ACHIEVED %  ");
		    cell5.setCellStyle(style1);
		    
		    HSSFRow row6 = constituencyWiseSheet.createRow((short)7);
		    Cell cell6 = row6.createCell((short)1);
		    cell6.setCellValue(constituencyReportVO1.getName() != null ? constituencyReportVO1.getName() : "");
		    cell6.setCellStyle(style2);
		    
		    cell6 = row6.createCell((short)2);
		    cell6.setCellValue(constituencyReportVO1.getTotalVoters() != null ?constituencyReportVO1.getTotalVoters():0L);
		    cell6.setCellStyle(style2);
		    
		    totalVoters = constituencyReportVO1.getTotalVoters() != null ?constituencyReportVO1.getTotalVoters():0L;
		    
		    cell6 = row6.createCell((short)3);
		    cell6.setCellValue(constituencyReportVO1.getTargetedCadre() != null ? constituencyReportVO1.getTargetedCadre() :0L);
		    cell6.setCellStyle(style2);
		    			    
		    cell6 = row6.createCell((short)4);
		    cell6.setCellValue(constituencyReportVO1.getRegisteredCadre() != null ? constituencyReportVO1.getRegisteredCadre() :0L);
		    cell6.setCellStyle(style2);
		    
		    totalRegistered = constituencyReportVO1.getRegisteredCadre() != null ? constituencyReportVO1.getRegisteredCadre() :0L;
		    Double percentage = constituencyReportVO1.getRegisteredCadre() != null ? (constituencyReportVO1.getRegisteredCadre() * 100.0 / constituencyReportVO1.getTargetedCadre())  :0.00d;
		    cell6 = row6.createCell((short)5);
		    cell6.setCellValue(percentage > 0 ? Double.valueOf(df2.format(percentage)).toString():" -- ");
		    cell6.setCellStyle(style2);
		    
		   constituencyWiseSheet.addMergedRegion(new CellRangeAddress(9,9,1,4 )); //GENDER OVERVIEW HEADING
		    
		    HSSFRow row8 = constituencyWiseSheet.createRow(9);							
		    Cell cell8 = row8.createCell((short)1);
		    cell8.setCellValue(" GENDER WISE OVERVIEW  ");
		    cell8.setCellStyle(style5);
		    
		    HSSFRow row9 = constituencyWiseSheet.createRow((short)10);
		    Cell cell9 = row9.createCell((short)1);
		    cell9.setCellValue(" GENDER ");
		    cell9.setCellStyle(style1);   
			    
		    cell9 = row9.createCell((short)2);
		    cell9.setCellValue(" TOTAL ");
		    cell9.setCellStyle(style1); 
		    
		    cell9 = row9.createCell((short)3);
		    cell9.setCellValue(" ACHIEVED ");
		    cell9.setCellStyle(style1); 
		    
		    cell9 = row9.createCell((short)4);
		    cell9.setCellValue(" ACHIEVED % ");
		    cell9.setCellStyle(style1); 
		    
		    HSSFRow row10 = constituencyWiseSheet.createRow((short)11);
		    Cell cell10 = row10.createCell((short)1);
		    cell10.setCellValue(" MALE ");
		    cell10.setCellStyle(style1);
		   
		    cell10 = row10.createCell((short)2);
		    cell10.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() :0L);
		    cell10.setCellStyle(style2);
		    
		    Long genderCount = constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters();
		    
		    cell10 = row10.createCell((short)3);
		    cell10.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount():0L);
		    cell10.setCellStyle(style2);
		    
		    cell10 = row10.createCell((short)4);
		    cell10.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc():0.0d);
		    cell10.setCellStyle(style2);
		    
		    
		    HSSFRow row11 = constituencyWiseSheet.createRow(12);
		    Cell cell11 = row11.createCell((short)1);
		    cell11.setCellValue(" FEMALE ");
		    cell11.setCellStyle(style1);
		   
		    cell11 = row11.createCell((short)2);
		    cell11.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() :0L);
		    cell11.setCellStyle(style2);
		    
		    genderCount = genderCount + constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters();
		    
		    cell11 = row11.createCell((short)3);
		    cell11.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() :0L);
		    cell11.setCellStyle(style2);
		    
		    cell11 = row11.createCell((short)4);
		    cell11.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() :0.0d);
		    cell11.setCellStyle(style2);
		    
		    
		    HSSFRow row12 = constituencyWiseSheet.createRow(13);
		    Cell cell12 = row12.createCell((short)1);
		    cell12.setCellValue(" OTHERS ");
		    cell12.setCellStyle(style1);
		    
		   /* cell12 = row12.createCell((short)2);
		    cell12.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getTotalVoters() :0L);
		    cell12.setCellStyle(style2);
		    
		    cell12 = row12.createCell((short)2);
		    cell12.setCellValue( totalVoters - genderCount);
		    cell12.setCellStyle(style2);
		    
		    cell12 = row12.createCell((short)3);
		    cell12.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getCadresCount() :0L);
		    cell12.setCellStyle(style2);
		    
		    cell12 = row12.createCell((short)4);
		    cell12.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getGenderPerc() :0.0d);
		    cell12.setCellStyle(style2);
		    
		    
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(15,15,1,4 )); // CASTE OVER VIEW HEADING
		    
		    HSSFRow row13 = constituencyWiseSheet.createRow((short)15);
		    Cell cell13 = row13.createCell((short)1);
		    cell13.setCellValue(" CASTE CATEGORY WISE OVERVIEW  ");
		    cell1.setCellStyle(style5);
		   
		    HSSFRow row14 = constituencyWiseSheet.createRow(16);
		    Cell cell14 = row14.createCell((short)1);
		    cell14.setCellValue(" CASTE  CATEGORY ");
		    cell14.setCellStyle(style1);
		    
		    cell14 = row14.createCell((short)2);
		    cell14.setCellValue("ACHIEVED");
		    cell14.setCellStyle(style1);
		    
		    cell14 = row14.createCell((short)3);
		    cell14.setCellValue("ACHIEVED %");
		    cell14.setCellStyle(style1);
		    
		    Integer rowNo = 17;
		    for( CadreRegisterInfo vo : constituencyReportVO1.getCasteGroupList())
		    {
		    	if(vo != null)
		    	{
		    		HSSFRow row = constituencyWiseSheet.createRow(rowNo);
				    Cell cellForCaste = row.createCell((short)1);
				    cellForCaste.setCellValue(vo.getName() != null ? vo.getName().toString() : "");
				    cellForCaste.setCellStyle(style1);
				    
				    cellForCaste = row.createCell((short)2);
				    cellForCaste.setCellValue(vo.getApCount() != null ? vo.getApCount().toString() : "");
				    cellForCaste.setCellStyle(style2);
				    
				    cellForCaste = row.createCell((short)3);
				    cellForCaste.setCellValue(vo.getPercentStr() != null ? vo.getPercentStr().toString() : "");
				    cellForCaste.setCellStyle(style2);
				    rowNo++;
		    	}
		    	    
		    }
		    
		    rowNo = rowNo + 2;
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,1,4 )); // AGE OVER VIEW HEADING
		    HSSFRow rowForAgeHead = constituencyWiseSheet.createRow(rowNo);
		    Cell cellForAgeHead = rowForAgeHead.createCell((short)1);
		    cellForAgeHead.setCellValue(" AGE WISE OVERVIEW  ");
		    cellForAgeHead.setCellStyle(style);
		    
		    rowNo = rowNo + 1;
		    HSSFRow rowForAge = constituencyWiseSheet.createRow(rowNo);
		    Cell cellHeadForAge = rowForAge.createCell((short)1);
		    cellHeadForAge.setCellValue("  AGE RANGE  ");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)2);
		    cellHeadForAge.setCellValue("TOTAL VOTERS");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)3);
		    cellHeadForAge.setCellValue("TOTAL %");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)4);
		    cellHeadForAge.setCellValue("ACHIEVED");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)5);
		    cellHeadForAge.setCellValue("ACHIEVED %");
		    cellHeadForAge.setCellStyle(style1);
		    
		    rowNo = rowNo + 1;
		    for(TdpCadreLocationWiseReportVO vo : constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList())
		    {
		    	    HSSFRow rowForAgeCell = constituencyWiseSheet.createRow(rowNo);
				    Cell cellForAge = rowForAgeCell.createCell((short)1);
				    cellForAge.setCellValue(vo.getAgeRange()  != null ? vo.getAgeRange().toString() : "");
				    cellForAge.setCellStyle(style1);
				    
				    cellForAge = rowForAgeCell.createCell((short)2);
				    cellForAge.setCellValue(vo.getTotalVoters()  != null ? vo.getTotalVoters().toString() : "");
				    cellForAge.setCellStyle(style2);
				  
				    if(totalVoters > 0)
				    {
				    	cellForAge = rowForAgeCell.createCell((short)3);
					    cellForAge.setCellValue(new BigDecimal((vo.getTotalVoters() .doubleValue() / totalVoters.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString() );
					    cellForAge.setCellStyle(style2);
					    
				    }
				    else
				    {
				    	cellForAge = rowForAgeCell.createCell((short)3);
					    cellForAge.setCellValue("0.00" );
					    cellForAge.setCellStyle(style2);
					    
				    }
				    
				    
				    cellForAge = rowForAgeCell.createCell((short)4);
				    cellForAge.setCellValue(vo.getCadresInAge()  != null ? vo.getCadresInAge().toString() : "");
				    cellForAge.setCellStyle(style2);
				    
				    if(totalRegistered > 0)
				    {
				    	 cellForAge = rowForAgeCell.createCell((short)5);
						 cellForAge.setCellValue(vo.getCadresInAge() != null ? new BigDecimal((vo.getCadresInAge() .doubleValue() / totalRegistered.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString():"") ;
						 cellForAge.setCellStyle(style2);
				    }
				    else
				    {
				    	 cellForAge = rowForAgeCell.createCell((short)5);
						 cellForAge.setCellValue("0.00") ;
						 cellForAge.setCellStyle(style2);
				    }
				    rowNo++;
		    }
		    
		    rowNo = rowNo+2;
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,1,4 )); // AGE OVER VIEW HEADING
		    HSSFRow row17 = constituencyWiseSheet.createRow(rowNo);
		    Cell cell17 = row17.createCell((short)1);
		    cell17.setCellValue(" MANDAL WISE OVERVIEW  ");
		    cell17.setCellStyle(style);
		    
		    rowNo = rowNo+1;
		    HSSFRow row18 = constituencyWiseSheet.createRow(rowNo);
		    Cell cell18 = row18.createCell((short)1);
		    cell18.setCellValue(IConstants.TEHSIL);
		    cell18.setCellStyle(style1);   
			    
		    cell18 = row18.createCell((short)2);
		    cell18.setCellValue(" TOTAL VOTERS ");
		    cell18.setCellStyle(style1);
		   
		    cell18 = row18.createCell((short)3);
		    cell18.setCellValue(" TARGET ");
		    cell18.setCellStyle(style1);
		    
		    cell18 = row18.createCell((short)4);
		    cell18.setCellValue(" ACHIEVED ");
		    cell18.setCellStyle(style1);
		    
		    cell18 = row18.createCell((short)5);
		    cell18.setCellValue(" ACHIEVED % ");
		    cell18.setCellStyle(style1);
		    List<TdpCadreLocationWiseReportVO> mandalsList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getTehsilWiseList();
		    
		    rowNo = rowNo+1;
		    if(mandalsList != null && mandalsList.size()>0)
		    {
		    	for (TdpCadreLocationWiseReportVO mandalsVo : mandalsList) 
		    	{
		    		HSSFRow tehsilRow = constituencyWiseSheet.createRow(rowNo);
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
		    		 tehsilCell.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString():" -- ");
		    		 tehsilCell.setCellStyle(style2);
		    		 Double mandalPerc =  mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre() * 100.0 / mandalsVo.getTargetedCadre() :0.00d;

		    		 tehsilCell = tehsilRow.createCell((short)5);//mandal wise percentage
		    		 tehsilCell.setCellValue(mandalPerc > 0 ? Double.valueOf(df2.format(mandalPerc)).toString():" -- ");
				     tehsilCell.setCellStyle(style2);

					 
				     rowNo = rowNo+1;
				}
		    }
		    
		    rowNo = rowNo+2;
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,1,6 )); // PANCHAYAT WISE OVER VIEW 
		    HSSFRow row = constituencyWiseSheet.createRow(rowNo);
		    Cell cell = row.createCell((short)1);
		    cell.setCellValue(" BOOTH WISE OVERVIEW  ");
		    cell.setCellStyle(style);
		    
		    rowNo = rowNo + 1;
		    
		    HSSFRow textRow = constituencyWiseSheet.createRow(rowNo);
		    Cell textCell = textRow.createCell((short)1);
		    textCell.setCellValue(IConstants.PANCHAYAT);
		    textCell.setCellStyle(style1);   
			    
		    textCell = textRow.createCell((short)2);
		    textCell.setCellValue(" TOTAL VOTERS ");
		    textCell.setCellStyle(style1);
		   
		    textCell = textRow.createCell((short)3);
		    textCell.setCellValue(" TARGET ");
		    textCell.setCellStyle(style1);
		    
		    textCell = textRow.createCell((short)4);
		    textCell.setCellValue(" ACHIEVED ");
		    textCell.setCellStyle(style1);
		    
		    textCell = textRow.createCell((short)5);
		    textCell.setCellValue(" ACHIEVED % ");
		    textCell.setCellStyle(style1);
		    
		    rowNo = rowNo + 1;
		    List<TdpCadreLocationWiseReportVO> panchayatList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getPanchayatWiseList();
		    
		    if(panchayatList != null && panchayatList.size()>0)
		    {
		    	for (TdpCadreLocationWiseReportVO mandalsVo : panchayatList) 
		    	{
		    		HSSFRow panchaytrow = constituencyWiseSheet.createRow(rowNo);
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
		    		 panchaytCell.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString()  :" -- ");
		    		 panchaytCell.setCellStyle(style2);
		    		 
		    		 Double boothPerc =  mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre() * 100.0 / mandalsVo.getTargetedCadre() :0.00d;

		    		 panchaytCell = panchaytrow.createCell((short)5);
		    		 panchaytCell.setCellValue(boothPerc > 0 ? Double.valueOf(df2.format(boothPerc)).toString() :" -- ") ;
		    		 panchaytCell.setCellStyle(style2);
					 
		    		 rowNo = rowNo + 1;
				}
		    }
		    
		    workbook.write(fileOut);
		    
		    addToZipFile(filename, zos);
		    zos.close();
			fos.close(); 
			
*/
	} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	  public  void addToZipFile(String fileName, ZipOutputStream zos)  {

			//System.out.println("Writing '" + fileName + "' to zip file");
			try {
				File file = new File(fileName);
				FileInputStream fis = new FileInputStream(file);
				ZipEntry zipEntry = new ZipEntry(fileName);
				zos.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zos.write(bytes, 0, length);
				}

				zos.closeEntry();
				fis.close();
			} catch (Exception e) {
				// TODO: handle exception
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
