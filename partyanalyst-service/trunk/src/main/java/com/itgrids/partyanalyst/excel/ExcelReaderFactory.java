package com.itgrids.partyanalyst.excel;

import java.util.Map;

public class ExcelReaderFactory {
	private static Map<String, IExcelReader> excelReaders;
	
	public static IExcelReader  selectReader(String pattern){
		IExcelReader optedReader=null;
		if(excelReaders!=null && excelReaders.size()>0){
			System.out.println("Before reader selection Factory size == "+excelReaders.size());
			optedReader= excelReaders.get(pattern);
		}
		
		return optedReader;
	}
	public Map<String, IExcelReader> getExcelReaders() {
		return excelReaders;
	}
	public void setExcelReaders(Map<String, IExcelReader> excelReaders) {
		this.excelReaders = excelReaders;
	}
	
}
