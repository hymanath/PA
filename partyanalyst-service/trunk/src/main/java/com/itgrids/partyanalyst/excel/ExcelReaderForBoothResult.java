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

public class ExcelReaderForBoothResult {
	
	public ExcelReaderForBoothResult(){
	}
	
	public List<String> getHeadingFromXlsFile(Sheet sheet) {
		List<String> names = new ArrayList<String>();
		int columnCount = sheet.getColumns();
		System.out.println("Candidate Names:");
		for (int i = 0; i < columnCount; i++) {
			names.add(checkCellContent(sheet.getCell(i, 0).getContents()));
			System.out.println(checkCellContent(sheet.getCell(i, 0).getContents()));
		}
		return names;
	}
	
	public List<CandidateBoothWiseResult> readExcelFile(String filePath){
		FileInputStream fs = null;
		Workbook workbook = null;
		Sheet s = null;
		try {
			fs = new FileInputStream(new File(filePath));
			workbook = Workbook.getWorkbook(fs);
			s = workbook.getSheet(0);		
			System.out.println("Total Columns::"+s.getColumns()+"Rows ::"+s.getRows());
			getHeadingFromXlsFile(s);
			List<String> names = getHeadingFromXlsFile(s);
			Cell[] partNos = s.getColumn(0);
			List<CandidateBoothWiseResult> candidateBoothWiseResults = new ArrayList<CandidateBoothWiseResult>();
			for(int i=2;i<s.getColumns();i++){
				CandidateBoothWiseResult candidateBoothWiseResult = new CandidateBoothWiseResult();
				candidateBoothWiseResult.setCandidateName(names.get(i));
				Cell[] columnData = s.getColumn(i);
				System.out.println("########"+i+"th Column::");
				List<BoothResultExcelVO> boothResultVOs = new ArrayList<BoothResultExcelVO>();
				
				for(int j=1;j<columnData.length;j++){
					BoothResultExcelVO boothResultVO = new BoothResultExcelVO();
					boothResultVO.setPartNo(new Long(checkCellContent(partNos[j].getContents())));
					boothResultVO.setVotesEarned(new Double(checkCellContent(columnData[j].getContents())));
					boothResultVOs.add(boothResultVO);
					System.out.println((j+1)+"--"+columnData[j].getContents());
				}
				candidateBoothWiseResult.setBoothresults(boothResultVOs);
				candidateBoothWiseResults.add(candidateBoothWiseResult);
			}
			return candidateBoothWiseResults;
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
	
	public static void main(String a[])throws Exception{
		ExcelReaderForBoothResult readFile = new ExcelReaderForBoothResult();
		readFile.readExcelFile("C:/Documents and Settings/USER/Desktop/booth/Kavali_booth_results_2004_Assembly.xls");
	}
	
	private String checkCellContent(String data){
		String cellInfo = "";
		if(!StringUtils.isBlank(data)){
			cellInfo = data.trim();
		}
		return cellInfo;
	}
	
}
