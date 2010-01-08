/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.excel.problem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jxl.Sheet;
import jxl.Workbook;

import com.itgrids.partyanalyst.excel.CsvException;

public class ProblemDataExcelReader {

	public List<ProblemDataUploadVO> readExcelData(File filePath) throws CsvException{
	 
		List<ProblemDataUploadVO> problemDataUploadVO = new ArrayList<ProblemDataUploadVO>();
		
		try{
			Workbook workbook=Workbook.getWorkbook(filePath);	
			Sheet[] sheets = workbook.getSheets();
			ProblemDataUploadVO problemDataVO = null;
			for(Sheet sheet:sheets){
				List<ProblemDataExcelColumnMapper> problemExcelData = new ArrayList<ProblemDataExcelColumnMapper>();
				problemDataVO = new ProblemDataUploadVO();
				String districtId[] = sheet.getName().split("_");
				if(districtId.length > 1)
					problemDataVO.setDistrictId(Long.valueOf(districtId[1].trim()));
				for(int row = 0; row < sheet.getRows(); row++ ){
					ProblemDataExcelColumnMapper problemDataExcelColumnMapper = new ProblemDataExcelColumnMapper(sheet, row, sheet.getColumns());
					problemExcelData.add(problemDataExcelColumnMapper);
				}
				problemDataVO.setPollingBoothPartVO(readExcelDataAndBindObject(problemExcelData,problemDataVO));
				problemDataVO.setFileName(sheet.getName());
				problemDataUploadVO.add(problemDataVO);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return problemDataUploadVO;
	}
	
	public List<PollingBoothPartVO> readExcelDataAndBindObject(List<ProblemDataExcelColumnMapper> problemExcelData,ProblemDataUploadVO problemDataVO) throws Exception{
		
		List<PollingBoothPartVO> pollingBoothPartVOs = new ArrayList<PollingBoothPartVO>();
		List<HamletProblemUploadVO> hamletProblemUploadVO = null;
		List<HamletProblemVO> hamletProblemsList = null;
		HamletProblemUploadVO hamletProblemUpload = null;
		PollingBoothPartVO partVO = null;
				
		for(ProblemDataExcelColumnMapper problemDataExcelColumnMapper:problemExcelData){
			String firstColumnData = problemDataExcelColumnMapper.getColumn1();
			String secondColumnData = problemDataExcelColumnMapper.getColumn2();
			String thirdColumnData = problemDataExcelColumnMapper.getColumn3();
			String fourthColumnData = problemDataExcelColumnMapper.getColumn4();
			String fifthColumnData = problemDataExcelColumnMapper.getColumn5();
						
			if(firstColumnData.contains("_") && problemDataExcelColumnMapper.getColumn3().length() == 0 && problemDataExcelColumnMapper.getColumn4().length() == 0 && problemDataExcelColumnMapper.getColumn5().length() == 0){
				if(ProblemExcelDataColumnNames.MANDAL_NAME.getValue().equals(firstColumnData)){
					problemDataVO.setMandalName(secondColumnData);
					continue;
				}
				if(ProblemExcelDataColumnNames.TOWNSHIP_NAME.getValue().equals(firstColumnData)){
					problemDataVO.setTownshipName(secondColumnData);
					continue;
				}
				if(ProblemExcelDataColumnNames.CONSTITUENCY_NAME.getValue().equals(firstColumnData)){
					problemDataVO.setConstituencyName(secondColumnData);
					continue;
				}
				if(ProblemExcelDataColumnNames.AREATYPE_DETAILS.getValue().equals(firstColumnData)){
					problemDataVO.setAreaType(secondColumnData);
					continue;
				}
				if(ProblemExcelDataColumnNames.DATE.getValue().equals(firstColumnData)){
					problemDataVO.setProblemDate(secondColumnData);
					continue;
				}
					
			}
			else if(ProblemExcelDataColumnNames.POLLINGBOOTHPARTNO.getValue().equals(firstColumnData))
				continue;
			else{
				
				if(firstColumnData.length() > 0 && secondColumnData.length() > 0 && thirdColumnData.length() > 0){
					if(hamletProblemUpload != null){
						hamletProblemUpload.setHamletProblems(hamletProblemsList);
						hamletProblemUploadVO.add(hamletProblemUpload);
					}
					if(partVO != null && hamletProblemUploadVO != null){
						partVO.setHamletProblemUploadVO(hamletProblemUploadVO);
						pollingBoothPartVOs.add(partVO);
					}
					partVO= new PollingBoothPartVO();
					hamletProblemUploadVO = new ArrayList<HamletProblemUploadVO>();
					hamletProblemUpload = new HamletProblemUploadVO();
					hamletProblemsList = new ArrayList<HamletProblemVO>();
					partVO.setPartNo(firstColumnData);
					hamletProblemUpload.setHamletName(secondColumnData);
					hamletProblemUpload.setPanchayatName(thirdColumnData);
				}
				if(firstColumnData.length() == 0 && secondColumnData.length() > 0 && thirdColumnData.length() > 0){
					if(hamletProblemUpload != null){
						hamletProblemUpload.setHamletProblems(hamletProblemsList);
						hamletProblemUploadVO.add(hamletProblemUpload);
					}
					hamletProblemUpload = new HamletProblemUploadVO();
					hamletProblemUpload.setHamletName(secondColumnData);
					hamletProblemUpload.setPanchayatName(thirdColumnData);
					hamletProblemsList = new ArrayList<HamletProblemVO>();
				}
				if(problemDataExcelColumnMapper.getColumn5().length() > 0){
					HamletProblemVO hamletProblem = new HamletProblemVO();
					hamletProblem.setProblemDesc(problemDataExcelColumnMapper.getColumn5());
					//hamletProblem.setProblemClassification(problemDataExcelColumnMapper.getColumn4());
					if(hamletProblemsList != null)
					hamletProblemsList.add(hamletProblem);
					continue;
				}
				if(firstColumnData.length() == 0 && secondColumnData.length() == 0 && thirdColumnData.length() == 0 && fourthColumnData.length() == 0 && fifthColumnData.length() == 0)
					continue;
							
				if(ProblemExcelDataColumnNames.END.getValue().equals(firstColumnData)){	
				    if(hamletProblemUpload != null){
						hamletProblemUpload.setHamletProblems(hamletProblemsList);
						hamletProblemUploadVO.add(hamletProblemUpload);
					}
					if(partVO != null && hamletProblemUploadVO != null){
						partVO.setHamletProblemUploadVO(hamletProblemUploadVO);
						pollingBoothPartVOs.add(partVO);
					}
					break;
				}
				
			}
			
		}
		
	return pollingBoothPartVOs;
	}
	
	public Long checkForLong(String value)throws Exception{
		Long longVal = new Long(0);
		if(!StringUtils.isEmpty(value)){
			if(StringUtils.isNumeric(value.trim()))
				longVal = new Long(value);
		}
		return longVal;
	}

	
	public static void main(String args[])throws Exception{
		
		ProblemDataExcelReader problemDataExcelReader = new ProblemDataExcelReader();
		File file = new File("D:/allurmandalProblems.xls");
		
		List<ProblemDataUploadVO> problemData = problemDataExcelReader.readExcelData(file);
		System.out.println("Total Townships -- >" + problemData.size());
		for(ProblemDataUploadVO problemDataUpload:problemData){
		 System.out.println("Input File Name " + problemDataUpload.getFileName());
		 System.out.println("District Id :: " + problemDataUpload.getDistrictId());
		 
		 System.out.println("Mandal :: " + problemDataUpload.getMandalName());
		 System.out.println("Constituency :: " + problemDataUpload.getConstituencyName());
		 System.out.println("Township :: " + problemDataUpload.getTownshipName());
		 System.out.println("AreaType :: " + problemDataUpload.getAreaType());
		 System.out.println("Date :: " + problemDataUpload.getProblemDate());
		 
		 System.out.println("Total No Of Parts ::" + problemDataUpload.getPollingBoothPartVO().size());
		  for(PollingBoothPartVO boothWiseData:problemDataUpload.getPollingBoothPartVO()){
			  
			  System.out.println("PartNO :: " + boothWiseData.getPartNo());
			  for(HamletProblemUploadVO hamletProblems:boothWiseData.getHamletProblemUploadVO()){
				  System.out.println("Hamlet Name ::" + hamletProblems.getHamletName());
				  for(HamletProblemVO hamletPblm:hamletProblems.getHamletProblems()){
					  System.out.print("" + hamletPblm.getProblemDesc().trim());
					  System.out.print("" + hamletPblm.getProblemClassification());
					  System.out.println("");
				  }
				  System.out.println("");
			  }
		  }
		}
	}
	
}
