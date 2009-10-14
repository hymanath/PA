package com.itgrids.partyanalyst.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.itgrids.partyanalyst.excel.BoothInfo;

public class ExcelReader {

	private BoothInfo boothRecord = null;
	
	public ExcelReader(){
	}
	
	public List<BoothInfo> readExcelFile(String filePath){
		FileInputStream fs = null;
		Workbook workbook = null;
		Sheet s = null;
		try {
			fs = new FileInputStream(new File(filePath));
			workbook = Workbook.getWorkbook(fs);
			s = workbook.getSheet(0);		
			System.out.println("Total Columns::"+s.getColumns());
			return getBoothsList(s);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		finally{
			workbook.close();
		}
		return null;
	}
	
	public List<BoothInfo> getBoothsList(Sheet s){
		
		System.out.println("Sheet::"+s);
		List<BoothInfo> boothRecords = new ArrayList<BoothInfo>();
		int rows = s.getRows();
		int columns = s.getColumns();
		System.out.println("Rows :" + rows +"columns :" + columns);
		for (int i = 0; i < rows; i++){
			Cell[] rowData = s.getRow(i);
			boothRecord = new BoothInfo();
			for(int j=0 ; j < columns ; j++){
				switch(j){
				case 0:
					boothRecord.setDistrictName(checkCellContent(rowData[j].getContents()));
					break;
				case 2:
					boothRecord.setConstituencyNo(checkCellContent(rowData[j].getContents()));
					break;
				case 3:
					boothRecord.setConstituencyName(checkCellContent(rowData[j].getContents()));
					break;
				case 5:
					boothRecord.setMandalName(checkCellContent(rowData[j].getContents()));
					break;
				case 6:
					boothRecord.setPartNo(checkCellContent(rowData[j].getContents()));
					break;
				case 7:
					boothRecord.setPartName(checkCellContent(rowData[j].getContents()));
					break;
				case 8:
					boothRecord.setLocation(checkCellContent(rowData[j].getContents()));
					break;
				case 9:
					boothRecord.setVillagesCovered(checkCellContent(rowData[j].getContents()));
					break;
				case 10:
					boothRecord.setCensusCode(checkCellContent(rowData[j].getContents()));
					break;		
				case 11:
					boothRecord.setMaleVoters(checkCellContent(rowData[j].getContents()));
					break;
				case 12:
					boothRecord.setFemaleVoters(checkCellContent(rowData[j].getContents()));
					break;
				case 13:
					boothRecord.setTotalVoters(checkCellContent(rowData[j].getContents()));
					break;
				}
				
			}
			boothRecords.add(boothRecord);
		}	
		return boothRecords;
	}
	
	private String checkCellContent(String data){
		String cellInfo = "";
		if(!StringUtils.isBlank(data)){
			cellInfo = data.trim();
		}
		return cellInfo;
	}
	
}

