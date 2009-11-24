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

	private List<BoothResultExcelColumnMapper> excelRows;
	private List<BoothResultValueObject> booths;
	private List<CandidateBoothWiseResult> candidates;
	private List<ConstituencyBoothBlock> constituenciesBlocks;
	
	public ExcelBoothResultReader(){
	}

	public List<CandidateBoothWiseResult> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateBoothWiseResult> candidates) {
		this.candidates = candidates;
	}

	public List<BoothResultExcelColumnMapper> getExcelRows() {
		return excelRows;
	}

	public void setExcelRows(List<BoothResultExcelColumnMapper> excelRows) {
		this.excelRows = excelRows;
	}

	public List<BoothResultValueObject> getBooths() {
		return booths;
	}

	public void setBooths(List<BoothResultValueObject> booths) {
		this.booths = booths;
	}
	
	public List<ConstituencyBoothBlock> getConstituenciesBlocsks() {
		return constituenciesBlocks;
	}

	public void setConstituenciesBlocsks(
			List<ConstituencyBoothBlock> constituenciesBlocsks) {
		this.constituenciesBlocks = constituenciesBlocsks;
	}

	public void readExcel(String filePath, boolean isParliament) throws CsvException{
		
		try{
			System.out.println("In Reader "+ filePath);
			String headerInf[] = new String[50];
			File exceFile= new File(filePath);
			excelRows= new ArrayList<BoothResultExcelColumnMapper>();
			Workbook workbook=Workbook.getWorkbook(exceFile);	
			Sheet[] sheets = workbook.getSheets();
			constituenciesBlocks = new ArrayList<ConstituencyBoothBlock>();
			for(Sheet sheet:sheets){
				System.out.println("######### Sheet Start ##########");
				ConstituencyBoothBlock assemblyConstituencyBlock = new ConstituencyBoothBlock();
				String name = sheet.getName().trim();
				System.out.println("Sheet Name::"+name);
				String[] constiNameAndDistrictId = StringUtils.split(name.trim(), "_");
				System.out.println("Size:"+constiNameAndDistrictId.length);
				if(isParliament == true){
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
				}
				if(isParliament == false){
					System.out.println("In False");
					String constituencyName = constiNameAndDistrictId[0];
					Long districtId = new Long(constiNameAndDistrictId[1]);
					assemblyConstituencyBlock.setConstituencyName(constituencyName);
					assemblyConstituencyBlock.setDistrictId(districtId);
				}
				int rows = sheet.getRows();
				int columns = sheet.getColumns();
				fillHeaderInfo(sheet,sheet.getColumns(),headerInf);
				System.out.println("Rows::"+rows);
				excelRows = getExcelRecords(sheet, rows, columns);
				identifyRowAndBindObject(excelRows, headerInf, columns);
				assemblyConstituencyBlock.setBoothResults(booths);
				assemblyConstituencyBlock.setCandidateResults(candidates);
				constituenciesBlocks.add(assemblyConstituencyBlock);
			}
			System.out.println("Total Sheets"+sheets.length);
			System.out.println("Total Constituencies:"+constituenciesBlocks.size());
		}catch(IOException ioe){
			System.out.println("ioe =="+ioe.getMessage());
		}catch(BiffException ioe){
			System.out.println("ioe =="+ioe.getMessage());
		}catch(Exception excep){
			System.out.println("ioe =="+excep.getMessage());
		}
	}

	public void identifyRowAndBindObject(List<BoothResultExcelColumnMapper> boothResults,String headerInfo[],int columnsOfExcel) throws Exception{
		candidates= new ArrayList<CandidateBoothWiseResult>();
		booths= new ArrayList<BoothResultValueObject>();
		BoothResultValueObject boothResultValueObject=null;
		System.out.println("Total Columns::"+columnsOfExcel);
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
								boothResultExcelVO.setVotesEarned(checkForComma(longVar));
							}
							if(method.getName().equals("getColumn2")){
								boothResultExcelVO.setPartNo(longVar);
								boothResultValueObject.setPartNumber(longVar);
							}
							if(i==2){
								if(method.getName().equals("getColumn"+String.valueOf(tempInt))){
									boothResultValueObject.setTotalNoOfValidVotes(checkForComma(longVar));								
								}
								if(method.getName().equals("getColumn"+String.valueOf(tempInt+1))){
									boothResultValueObject.setRejectedVotes(checkForComma(longVar));
								}
								if(method.getName().equals("getColumn"+String.valueOf(tempInt+2))){
									boothResultValueObject.setTenderedVotes(checkForComma(longVar));
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
		}catch (IllegalArgumentException argumentExeception) {
			throw new CsvException(argumentExeception.getMessage());
		}catch(InvocationTargetException invocationEx){
			throw new CsvException(invocationEx.getMessage());
		}catch(IllegalAccessException iace){
			throw new CsvException(iace.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		excelBoothResultReader.readExcel("C:/Documents and Settings/USER/Desktop/New Folder/forTest/2004_assembly_test.xls", false);
		excelBoothResultReader.testConstituencyBlock();
	}

	private void fillHeaderInfo(Sheet sheet,int noOfColumns,String headerInformation[]){
		for(int columnPos=0; columnPos<noOfColumns; columnPos++){
			String cellData=checkCellData(sheet.getCell(columnPos,0).getContents());
			headerInformation[columnPos]=cellData;
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
	
	public Long checkForComma(String value){
			Long longVal = new Long(0);
			if(!StringUtils.isEmpty(value)){
				if(StringUtils.contains(value, ","))
					longVal = new Long(StringUtils.replaceChars(value.trim(), ",", ""));
				else
					longVal = new Long(value);
			}
			return longVal;
	}

	public List<BoothResultExcelColumnMapper> getExcelRecords(Sheet sheet,int rows, int columns){
		List<BoothResultExcelColumnMapper> totalRecords = new ArrayList<BoothResultExcelColumnMapper>();
		for(int i=1; i< rows; i++){
			BoothResultExcelColumnMapper record = new BoothResultExcelColumnMapper(sheet, i, columns);
			if(StringUtils.isEmpty(record.getColumn1()))
				break;
			totalRecords.add(record);
		}
		return totalRecords;
	}

	public void testConstituencyBlock(){
		List<ConstituencyBoothBlock> list = getConstituenciesBlocsks();
		for(ConstituencyBoothBlock assemblyConstituencyBlock:list){
			System.out.println("###########################################################################################");
			System.out.println("Parliament Name::"+assemblyConstituencyBlock.getParliamentConstituencyName());
			System.out.println("State Id::"+assemblyConstituencyBlock.getStateId());
			System.out.println("@@@@@constituency Name : "+ assemblyConstituencyBlock.getConstituencyName());
			System.out.println("District Id"+assemblyConstituencyBlock.getDistrictId());
			List<CandidateBoothWiseResult> canList = assemblyConstituencyBlock.getCandidateResults();
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
			}
			System.out.println("###########################################################################################");
		}
	}
	
}
