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
	boolean isCandidateDetails=false;
	public CsvColumnMapper(){
		csvColumn1="";
		csvColumn2="";
		csvColumn3="";
		csvColumn4="";
		csvColumn5="";
		csvColumn6="";
		
	}
	public CsvColumnMapper(Sheet sheet,int rowNo,int noOfColumns){
		for(int columnPos=0;columnPos<noOfColumns;columnPos++){
			String cellData=checkCellData((sheet.getCell(columnPos,rowNo)).getContents());
			switch (columnPos) {
			case 0:setCsvColumn1(cellData);break;
			case 1:setCsvColumn2(cellData);break;
			case 2:setCsvColumn3(cellData);break;
			case 3:setCsvColumn4(cellData);
			case 4:setCsvColumn5("");
			case 5:setCsvColumn6("");
			default:
				if(noOfColumns>4){
					setCsvColumn5(cellData);
					setCsvColumn6(cellData);
				}
				break;
			}
		}
		if(StringUtils.isNotEmpty(csvColumn2.trim()) && StringUtils.isNotEmpty(csvColumn3.trim()) && (StringUtils.isNotEmpty(csvColumn4.trim()) && StringUtils.isNumeric(StringUtils.replace(csvColumn4, ",", "")))){
			isCandidateDetails=true;
		}
	}

	public String checkCellData(String cellData){
		String cellContect="";
		if(cellData!=null){
			if(cellData.length()>0){
				cellContect=cellData.trim();
			}
		}
		return cellContect;
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
										castOther.csvColumn6).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(csvColumn1).append(csvColumn2)
		.append(csvColumn3).append(csvColumn4).append(csvColumn5)
		.append(csvColumn6).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(csvColumn1)
		.append(csvColumn2).append(csvColumn3).append(csvColumn4).append(
				csvColumn5).append(csvColumn6).toString();
	}

	public boolean isCandidateDetails() {
		return isCandidateDetails;
	}
}
