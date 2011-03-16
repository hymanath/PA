package com.itgrids.partyanalyst.excel;

import java.lang.reflect.Method;

import jxl.Sheet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CsvColumnMapper {
	String csvColumn1;
	String csvColumn2;
	String csvColumn3;
	String csvColumn4;
	String csvColumn5;
	String csvColumn6;
	String csvColumn7;
	String csvColumn8;
	String csvColumn9;
	String csvColumn10;
	
	boolean isCandidateDetails=false;
	public CsvColumnMapper(){
		csvColumn1="";
		csvColumn2="";
		csvColumn3="";
		csvColumn4="";
		csvColumn5="";
		csvColumn6="";
		csvColumn7="";
		csvColumn8="";
		csvColumn9="";
		
		
	}
	public CsvColumnMapper(Sheet sheet,int rowNo,int noOfColumns){
		for(int columnPos=0;columnPos<noOfColumns;columnPos++){
			String cellData=checkCellData((sheet.getCell(columnPos,rowNo)).getContents());
			switch (columnPos) {
			case 0:setCsvColumn1(cellData);break;
			case 1:setCsvColumn2(cellData);break;
			case 2:setCsvColumn3(cellData);break;
			case 3:setCsvColumn4(cellData);break;
			case 4:setCsvColumn5(cellData);break;
			case 5:setCsvColumn6(cellData);break;
			case 6:setCsvColumn7(cellData);break;
			case 7:setCsvColumn8(cellData);break;
			case 8:setCsvColumn9(cellData);break;
			
			default:
				if(noOfColumns>4){
					/*setCsvColumn5(cellData);
					setCsvColumn6(cellData);
					setCsvColumn7(cellData);
					setCsvColumn8(cellData);
					setCsvColumn9(cellData);*/
					
				}
				break;
			}
		}
		if(StringUtils.isNotEmpty(csvColumn2.trim()) && StringUtils.isNotEmpty(csvColumn3.trim()) && (StringUtils.isNotEmpty(csvColumn4.trim()) && StringUtils.isNumeric(StringUtils.replace(csvColumn4, ",", "")))){
			isCandidateDetails=true;
		}
	}

	public String checkCellData(String cellData){
		String cellContent="";
		if(cellData!=null){
			if(cellData.length()>0){
				cellContent=StringUtils.trim(cellData.trim());
			}
		}
		return cellContent;
	}


	public String getCsvColumn1() {
		return csvColumn1;
	}
	public void setCsvColumn1(String csvColumn1) {
		this.csvColumn1 = csvColumn1;
	}
	public String getCsvColumn2() {
		return csvColumn2;
	}
	public void setCsvColumn2(String csvColumn2) {
		this.csvColumn2 = csvColumn2;
	}
	public String getCsvColumn3() {
		return csvColumn3;
	}
	public void setCsvColumn3(String csvColumn3) {
		this.csvColumn3 = csvColumn3;
	}
	public String getCsvColumn4() {
		return csvColumn4;
	}
	public void setCsvColumn4(String csvColumn4) {
		this.csvColumn4 = csvColumn4;
	}
	public String getCsvColumn5() {
		return csvColumn5;
	}
	public void setCsvColumn5(String csvColumn5) {
		this.csvColumn5 = csvColumn5;
	}
	public String getCsvColumn6() {
		return csvColumn6;
	}
	public void setCsvColumn6(String csvColumn6) {
		this.csvColumn6 = csvColumn6;
	}
	public String getCsvColumn7() {
		return csvColumn7;
	}
	public void setCsvColumn7(String csvColumn7) {
		this.csvColumn7 = csvColumn7;
	}
	public String getCsvColumn8() {
		return csvColumn8;
	}
	public void setCsvColumn8(String csvColumn8) {
		this.csvColumn8 = csvColumn8;
	}
	public String getCsvColumn9() {
		return csvColumn9;
	}
	public void setCsvColumn9(String csvColumn9) {
		this.csvColumn9 = csvColumn9;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CsvColumnMapper))
			return false;
		CsvColumnMapper castOther = (CsvColumnMapper) other;
		return new EqualsBuilder().append(csvColumn1, castOther.csvColumn1)
		.append(csvColumn2, castOther.csvColumn2).append(csvColumn3,
				castOther.csvColumn3).append(csvColumn4,
						castOther.csvColumn4).append(csvColumn5,
								castOther.csvColumn5).append(csvColumn6,
										castOther.csvColumn6).append(csvColumn7,
												castOther.csvColumn7).append(csvColumn8,
														castOther.csvColumn8).append(csvColumn9,
																castOther.csvColumn9).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(csvColumn1).append(csvColumn2)
		.append(csvColumn3).append(csvColumn4).append(csvColumn5)
		.append(csvColumn6).append(csvColumn7).append(csvColumn8)
		.append(csvColumn9).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(csvColumn1)
		.append(csvColumn2).append(csvColumn3).append(csvColumn4).append(
				csvColumn5).append(csvColumn6).append(csvColumn7).append(csvColumn8)
				.append(csvColumn9).toString();
	}

	public boolean isCandidateDetails() {
		return isCandidateDetails;
	}
}
