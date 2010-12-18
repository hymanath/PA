package com.itgrids.partyanalyst.excel;

import jxl.Sheet;

import org.apache.commons.lang.StringUtils;

public class ExcelRowExtracter {
	String excelColumn1;
	String excelColumn2;
	String excelColumn3;
	String excelColumn4;
	String excelColumn5;
	String excelColumn6;
	String excelColumn7;
	String excelColumn8;
	String excelColumn9;

	boolean isCandidateDetails=false;
	public ExcelRowExtracter(){
		excelColumn1="";
		excelColumn2="";
		excelColumn3="";
		excelColumn4="";
		excelColumn5="";
		excelColumn6="";
	}
	public ExcelRowExtracter(Sheet sheet,int rowNo,int noOfColumns){
		for(int columnPos=0;columnPos<noOfColumns;columnPos++){
			String cellData=checkCellData((sheet.getCell(columnPos,rowNo)).getContents());
			switch (columnPos) {
				case 0:setExcelColumn1(cellData);break;
				case 1:setExcelColumn2(cellData);break;
				case 2:setExcelColumn3(cellData);break;
				case 3:setExcelColumn4(cellData);break;
				case 4:setExcelColumn5(cellData);break;
				case 5:setExcelColumn6(cellData);break;
				case 6:setExcelColumn7(cellData);break;
				case 7:setExcelColumn8(cellData);break;
				case 8:setExcelColumn9(cellData);break;
				default:break;
			}
		}
		if(StringUtils.isNotEmpty(getExcelColumn2()) && StringUtils.isNotEmpty(getExcelColumn7()) && StringUtils.isNotEmpty(getExcelColumn6()) && (StringUtils.isNotEmpty(getExcelColumn8()) && StringUtils.isNumeric(StringUtils.replace(getExcelColumn8(), ",", ""))) && StringUtils.isNotEmpty(getExcelColumn9())){
			isCandidateDetails=true;
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
	public boolean isCandidateDetails() {
		return isCandidateDetails;
	}
	public String getExcelColumn1() {
		return excelColumn1;
	}
	public void setExcelColumn1(String excelColumn1) {
		this.excelColumn1 = excelColumn1;
	}
	public String getExcelColumn2() {
		return excelColumn2;
	}
	public void setExcelColumn2(String excelColumn2) {
		this.excelColumn2 = excelColumn2;
	}
	public String getExcelColumn3() {
		return excelColumn3;
	}
	public void setExcelColumn3(String excelColumn3) {
		this.excelColumn3 = excelColumn3;
	}
	public String getExcelColumn4() {
		return excelColumn4;
	}
	public void setExcelColumn4(String excelColumn4) {
		this.excelColumn4 = excelColumn4;
	}
	public String getExcelColumn5() {
		return excelColumn5;
	}
	public void setExcelColumn5(String excelColumn5) {
		this.excelColumn5 = excelColumn5;
	}
	public String getExcelColumn6() {
		return excelColumn6;
	}
	public void setExcelColumn6(String excelColumn6) {
		this.excelColumn6 = excelColumn6;
	}
	public String getExcelColumn7() {
		return excelColumn7;
	}
	public void setExcelColumn7(String excelColumn7) {
		this.excelColumn7 = excelColumn7;
	}
	public String getExcelColumn8() {
		return excelColumn8;
	}
	public void setExcelColumn8(String excelColumn8) {
		this.excelColumn8 = excelColumn8;
	}
	public String getExcelColumn9() {
		return excelColumn9;
	}
	public void setExcelColumn9(String excelColumn9) {
		this.excelColumn9 = excelColumn9;
	}
	public void setCandidateDetails(boolean isCandidateDetails) {
		this.isCandidateDetails = isCandidateDetails;
	}
}
