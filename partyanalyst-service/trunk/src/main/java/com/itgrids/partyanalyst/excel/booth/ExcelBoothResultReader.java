package com.itgrids.partyanalyst.excel.booth;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;

import com.itgrids.partyanalyst.excel.CsvException;

public class ExcelBoothResultReader {

	public List<ConstituencyBoothBlock> readExcel(File filePath, boolean isParliament) throws CsvException{
		List<ConstituencyBoothBlock> constituenciesBlocks = new ArrayList<ConstituencyBoothBlock>();
		try{
			//System.out.println("In Reader "+ filePath);
			String headerInf[] = new String[100];
			Workbook workbook=Workbook.getWorkbook(filePath);	
			Sheet[] sheets = workbook.getSheets();
			
			for(Sheet sheet:sheets){
				//System.out.println("######### Sheet Start ##########");
				ConstituencyBoothBlock assemblyConstituencyBlock = new ConstituencyBoothBlock();
				String name = sheet.getCell(0,0).getContents().trim();
				//System.out.println("Sheet Name::"+name);
				String[] constiNameAndDistrictId = StringUtils.split(name.trim(), "_");
			//	System.out.println("Size:"+constiNameAndDistrictId.length);
				if(isParliament){
					System.out.println("In true");
					String parliamentName = constiNameAndDistrictId[0].trim();
					System.out.println(parliamentName);
					Long stateId = new Long(constiNameAndDistrictId[1].trim());
					System.out.println(stateId);
					String constituencyName = constiNameAndDistrictId[2].trim();
					System.out.println(constituencyName);
					Long districtId = new Long(constiNameAndDistrictId[3].trim());
					System.out.println(districtId);
					assemblyConstituencyBlock.setParliamentConstituencyName(parliamentName);
					assemblyConstituencyBlock.setStateId(stateId);
					assemblyConstituencyBlock.setConstituencyName(constituencyName);
					assemblyConstituencyBlock.setDistrictId(districtId);
				}else{
				//	System.out.println("In False");
					String constituencyName = constiNameAndDistrictId[0];
					Long districtId = new Long(constiNameAndDistrictId[1]);
					assemblyConstituencyBlock.setConstituencyName(constituencyName);
					assemblyConstituencyBlock.setDistrictId(districtId);
				}
				
				int rows = sheet.getRows();
				int columns = sheet.getColumns();
				fillHeaderInfo(sheet,sheet.getColumns(),headerInf);
				List<CandidateBoothWiseResult> candidates = new ArrayList<CandidateBoothWiseResult>();
				List<BoothResultValueObject> booths = new ArrayList<BoothResultValueObject>();
				identifyRowAndBindObject(candidates, booths, getExcelRecords(sheet, rows, columns), headerInf, columns);
				assemblyConstituencyBlock.setBoothResults(booths);
				assemblyConstituencyBlock.setCandidateResults(candidates);
				constituenciesBlocks.add(assemblyConstituencyBlock);
			}

		}catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("ioe1 =="+ioe.getMessage());
		}catch(BiffException ioe){
			ioe.printStackTrace();
			System.out.println("ioe2 =="+ioe.getMessage());
		}catch(Exception excep){
			excep.printStackTrace();
			System.out.println("ioe3 =="+excep.getMessage());
		}
		
		return constituenciesBlocks;
	}

	public void identifyRowAndBindObject(List<CandidateBoothWiseResult> candidates, 
			List<BoothResultValueObject> booths, List<BoothResultExcelColumnMapper> boothResults,
			String headerInfo[],int columnsOfExcel){
		BoothResultValueObject boothResultValueObject=null;
		//System.out.println("Total Columns::"+columnsOfExcel);
		int tempInt=columnsOfExcel-2;
		try{
			for(int i=2;i<columnsOfExcel-3;i++){
				CandidateBoothWiseResult candidateBoothWiseResult = new CandidateBoothWiseResult();
				candidateBoothWiseResult.setCandidateName(headerInfo[i]);
				List<BoothResultExcelVO> boothResultVOs = new ArrayList<BoothResultExcelVO>();
				for (BoothResultExcelColumnMapper boothResultExcelColumnMapper : boothResults) {
					boothResultValueObject = new BoothResultValueObject();
					BoothResultExcelVO boothResultExcelVO = new BoothResultExcelVO();
					java.lang.reflect.Method []methods=BoothResultExcelColumnMapper.class.getDeclaredMethods();
					for (Method method : methods) {
						if(method.getName().startsWith("get")){
							String longVar=(String)method.invoke(boothResultExcelColumnMapper, null);
							if(method.getName().equals("getColumn"+String.valueOf(i+1))){
								boothResultExcelVO.setVotesEarned(StringUtils.replaceChars(longVar.trim(), ",", ""));
							}
							if(method.getName().equals("getColumn2")){
								boothResultExcelVO.setPartNo(longVar);
								boothResultValueObject.setPartNumber(longVar);
							}
							if(i==2){
								if(method.getName().equals("getColumn"+String.valueOf(tempInt))){
									boothResultValueObject.setTotalNoOfValidVotes(StringUtils.replaceChars(longVar.trim(), ",", ""));								
								}
								if(method.getName().equals("getColumn"+String.valueOf(tempInt+1))){
									boothResultValueObject.setRejectedVotes(StringUtils.replaceChars(longVar.trim(), ",", ""));
								}
								if(method.getName().equals("getColumn"+String.valueOf(tempInt+2))){
									boothResultValueObject.setTenderedVotes(StringUtils.replaceChars(longVar.trim(), ",", ""));
								}
							}
						}
					}

					boothResultVOs.add(boothResultExcelVO);
					if(i==2)
						booths.add(boothResultValueObject);
				}
				candidateBoothWiseResult.setBoothresults(boothResultVOs);
				candidates.add(candidateBoothWiseResult);

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		File exceFile= new File("C:/Documents and Settings/ITGrids/Desktop/290710_New/TempBoothAcResults2010.xls");
		excelBoothResultReader.readExcel(exceFile, false);
	}

	private void fillHeaderInfo(Sheet sheet,int noOfColumns,String headerInformation[]){
		for(int columnPos=0; columnPos<noOfColumns; columnPos++){
			try{
				String cellData=checkCellData(sheet.getCell(columnPos,1).getContents());
				headerInformation[columnPos]=cellData;	
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	public String checkCellData(String cellData){
		String cellContect="";
		if(cellData!=null){
			if(cellData.length()>0){
				cellContect=StringUtils.trim(cellData.trim());
			}
		}
		return cellContect;
	}

	public List<BoothResultExcelColumnMapper> getExcelRecords(Sheet sheet,int rows, int columns){
		List<BoothResultExcelColumnMapper> totalRecords = new ArrayList<BoothResultExcelColumnMapper>();
		for(int i=2; i< rows; i++){
			BoothResultExcelColumnMapper record = new BoothResultExcelColumnMapper(sheet, i, columns);
			if(StringUtils.isEmpty(record.getColumn1()))
				break;
			totalRecords.add(record);
		}
		return totalRecords;
	}

	public void testConstituencyBlock(List<ConstituencyBoothBlock> list){
		System.out.println("Total Constituencies Read From EXCEL::"+list.size());
		for(ConstituencyBoothBlock assemblyConstituencyBlock:list){
			System.out.println("###########################################################################################");
			System.out.println("Parliament Name::"+assemblyConstituencyBlock.getParliamentConstituencyName());
			System.out.println("State Id::"+assemblyConstituencyBlock.getStateId());
			System.out.println("@@@@@constituency Name : "+ assemblyConstituencyBlock.getConstituencyName());
			System.out.println("District Id"+assemblyConstituencyBlock.getDistrictId()+" Total Booths:"+assemblyConstituencyBlock.getBoothResults().size());
			/*List<CandidateBoothWiseResult> canList = assemblyConstituencyBlock.getCandidateResults();
			for(CandidateBoothWiseResult candidateBoothWiseResult:canList){
				System.out.println("######Candidate Name::"+candidateBoothWiseResult.getCandidateName());
				List<BoothResultExcelVO> brList = candidateBoothWiseResult.getBoothresults();
				for(BoothResultExcelVO boothResultExcelVO:brList){
					System.out.println("Part No:"+boothResultExcelVO.getPartNo());
					System.out.println("Votes Earned:"+boothResultExcelVO.getVotesEarned());
				}
			}
			List<BoothResultValueObject> boothList = assemblyConstituencyBlock.getBoothResults();
			for(BoothResultValueObject boothResultValueObject:boothList){
				System.out.println("Part No:"+boothResultValueObject.getPartNumber());
				System.out.println("Total Valid Votes:"+boothResultValueObject.getTotalNoOfValidVotes());
				System.out.println("Rejected Votes:"+boothResultValueObject.getRejectedVotes());
				System.out.println("Tendered Votes:"+boothResultValueObject.getTenderedVotes());
				System.out.println("Total votes:"+boothResultValueObject.getTotalVotes());
			}*/
			System.out.println("###########################################################################################");
		}
	}
	
}
