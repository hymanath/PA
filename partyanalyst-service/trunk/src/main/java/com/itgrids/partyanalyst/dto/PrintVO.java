package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class PrintVO implements Serializable{
	
	private Long tdpCadreCardPrintId;
	private Date printTime;
	private String serialNumber;
	private String printStatus;
	private String printCode;
	private String printDesc;
	private String printerSerialNumber;
	private String boxNo;
	private String pcNo;
	private String outerBoxNo;
	
	public Long getTdpCadreCardPrintId() {
		return tdpCadreCardPrintId;
	}
	public void setTdpCadreCardPrintId(Long tdpCadreCardPrintId) {
		this.tdpCadreCardPrintId = tdpCadreCardPrintId;
	}
	public Date getPrintTime() {
		return printTime;
	}
	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public String getPrintCode() {
		return printCode;
	}
	public void setPrintCode(String printCode) {
		this.printCode = printCode;
	}
	public String getPrintDesc() {
		return printDesc;
	}
	public void setPrintDesc(String printDesc) {
		this.printDesc = printDesc;
	}
	public String getPrinterSerialNumber() {
		return printerSerialNumber;
	}
	public void setPrinterSerialNumber(String printerSerialNumber) {
		this.printerSerialNumber = printerSerialNumber;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	public String getPcNo() {
		return pcNo;
	}
	public void setPcNo(String pcNo) {
		this.pcNo = pcNo;
	}
	public String getOuterBoxNo() {
		return outerBoxNo;
	}
	public void setOuterBoxNo(String outerBoxNo) {
		this.outerBoxNo = outerBoxNo;
	}
	
}
