package com.itgrids.partyanalyst.excel.booth;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.booth.BoothDataExcelColumnMapper;

public class BoothDataExcelReader {
	
	private List<BoothInfo> boothsInfo;
	private List<BoothDataExcelColumnMapper> boothDataExcelRows;
	
	public BoothDataExcelReader(){
	}	

	public List<BoothInfo> getBoothsInfo() {
		return boothsInfo;
	}

	public void setBoothsInfo(List<BoothInfo> boothsInfo) {
		this.boothsInfo = boothsInfo;
	}

	public List<BoothDataExcelColumnMapper> getBoothDataExcelRows() {
		return boothDataExcelRows;
	}

	public void setBoothDataExcelRows(List<BoothDataExcelColumnMapper> boothDataExcelRows) {
		this.boothDataExcelRows = boothDataExcelRows;
	}

	public void readExcelFile(String filePath) throws CsvException{
		BoothDataExcelColumnMapper boothDataExcelColumnMapper;
		try{
			File exceFile = new File(filePath);
			boothDataExcelRows = new ArrayList<BoothDataExcelColumnMapper>();
			Workbook workbook=Workbook.getWorkbook(exceFile);	
			Sheet sheet = workbook.getSheet(0);		
			System.out.println("Rows::"+sheet.getRows());
			System.out.println("Columns::"+sheet.getColumns());
			for (int row = 1; row < sheet.getRows(); row++) {
				boothDataExcelColumnMapper = new BoothDataExcelColumnMapper(sheet,row,sheet.getColumns());
				boothDataExcelRows.add(boothDataExcelColumnMapper);
			}
			System.out.println("Total No.Of Rows Read From Excel::"+boothDataExcelRows.size());
			identifyRowAndBindObject(boothDataExcelRows);
		} catch(IOException ioe){
			throw new CsvException(ioe.getMessage());
		}catch(BiffException ioe){
			throw new CsvException(ioe.getMessage());
		}catch(Exception excep){

		}
	}
	
	public void identifyRowAndBindObject(List<BoothDataExcelColumnMapper> boothDataExcelRows) throws CsvException{
		System.out.println("Total Rows In the Booth#"+boothDataExcelRows.size());
		boothsInfo = new ArrayList<BoothInfo>();
		try{
			for(BoothDataExcelColumnMapper boothDataExcelRow:boothDataExcelRows){
				BoothInfo boothRecord = new BoothInfo();
				Method []methods=BoothDataExcelColumnMapper.class.getDeclaredMethods();
				for(Method method:methods){
					if(method.getName().startsWith("get")){
						String stringVar=(String)method.invoke(boothDataExcelRow, null);
						System.out.println("Siva  "+stringVar+"Method Name:"+method.getName());
						if(method.getName().equals("getColumn1")){
							System.out.println("DistrictName "+stringVar);
							boothRecord.setDistrictName(stringVar);
						}
						else
						if(method.getName().equals("getColumn2")){
							boothRecord.setDistrictId(checkAndAssignLong(stringVar));
							System.out.println("DistrictId "+stringVar);
						}
						else
						if(method.getName().equals("getColumn3")){
							boothRecord.setConstituencyNo(checkAndAssignLong(stringVar));
							System.out.println("ConstituencyNo "+stringVar);
						}
						else
						if(method.getName().equals("getColumn4")){
							System.out.println("ConstituencyName "+stringVar);
							boothRecord.setConstituencyName(stringVar);
						}
						else
						if(method.getName().equals("getColumn6")){
							boothRecord.setMandalName(stringVar);
							System.out.println("MandalName "+stringVar);
						}
						else
						if(method.getName().equals("getColumn7")){
							boothRecord.setPartNo(stringVar);
							System.out.println("PartNo "+stringVar);
						}
						else
						if(method.getName().equals("getColumn8")){
							boothRecord.setPartName(stringVar);
							System.out.println("PartName "+stringVar);
						}
						else
						if(method.getName().equals("getColumn9")){
							boothRecord.setLocation(stringVar);
							System.out.println("Location "+stringVar);
						}
						else
						if(method.getName().equals("getColumn10")){
							boothRecord.setVillagesCovered(stringVar);
							System.out.println("VillagesCovered "+stringVar);
						}
						else
						if(method.getName().equals("getColumn11")){
							boothRecord.setCensusCode(stringVar);
							System.out.println("CensusCode "+stringVar);
						}
						else
						if(method.getName().equals("getColumn12")){
							boothRecord.setMaleVoters(checkAndAssignLong(stringVar));
							System.out.println("MaleVoters "+stringVar);
						}
						else
						if(method.getName().equals("getColumn13")){
							boothRecord.setFemaleVoters(checkAndAssignLong(stringVar));
							System.out.println("FemaleVoters "+stringVar);
						}
						else
						if(method.getName().equals("getColumn14")){
							boothRecord.setTotalVoters(checkAndAssignLong(stringVar));
							System.out.println("TotalVoters "+stringVar);
						}
					}
				}
				boothsInfo.add(boothRecord);
			}
		}catch (IllegalArgumentException argumentExeception) {
			throw new CsvException(argumentExeception.getMessage());
		}catch(InvocationTargetException invocationEx){
			throw new CsvException(invocationEx.getMessage());
		}catch(IllegalAccessException iace){
			throw new CsvException(iace.getMessage());
		}
		
	}
	
	public Long checkAndAssignLong(String value){
		System.out.println();
		Long longVal = new Long(0);
		if(!StringUtils.isEmpty(value))
			if(StringUtils.isNumeric(value.trim()))
				longVal = new Long(value);
		return longVal;
	}
	
	public static void main(String[] args)throws Exception {
		BoothDataExcelReader reader = new BoothDataExcelReader();
		reader.readExcelFile("C:/Documents and Settings/USER/Desktop/booth/kavali_booth_data_2004.xls");
	}
	
}