package com.itgrids.partyanalyst.excel.booth;

import jxl.Sheet;

import org.apache.commons.lang.StringUtils;

public class BoothDataExcelColumnMapper {

	private String column1;
	private String column2;
	private String column3;
	private String column4;
	private String column5;
	private String column6;
	private String column7;
	private String column8;
	private String column9;
	private String column10;
	
	public BoothDataExcelColumnMapper(Sheet sheet,int rowNo,int noOfColumns){
		for(int columnPos=0; columnPos<noOfColumns; columnPos++){
			String cellData=checkCellData((sheet.getCell(columnPos,rowNo)).getContents());
			switch (columnPos) {
				case 0:setColumn1(cellData);break;
				case 1:setColumn2(cellData);break;
				case 2:setColumn3(cellData);break;
				case 3:setColumn4(cellData);break;
				case 4:setColumn5(cellData);break;
				case 5:setColumn6(cellData);break;
				case 6:setColumn7(cellData);break;
				case 7:setColumn8(cellData);break;
				case 8:setColumn9(cellData);break;
				case 9:setColumn10(cellData);break;
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

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}

	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
	}

}
