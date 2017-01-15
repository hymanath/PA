package com.itgrids.cardprint.dto;

import java.io.Serializable;

public class CardPrintingDispatchVO implements Serializable{
	
	private Long id;
	private String name;
	
	private String boxNo;
	private Long mandalId;
	private String mandalName;
	private Long panchayatId;
	private String panchayatName;
	private Long noOfCards;
	private String isQAPassed;
	private String errorPerc;
	private Long validatedCardsCount;
	private String status;
	private Long errorCount;
	
	private Long totalCadre;
	private Long printedCadre;
	private Long unPrintedCadre;
	
	public Long getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Long errorCount) {
		this.errorCount = errorCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getNoOfCards() {
		return noOfCards;
	}
	public void setNoOfCards(Long noOfCards) {
		this.noOfCards = noOfCards;
	}
	public String getIsQAPassed() {
		return isQAPassed;
	}
	public void setIsQAPassed(String isQAPassed) {
		this.isQAPassed = isQAPassed;
	}
	public String getErrorPerc() {
		return errorPerc;
	}
	public void setErrorPerc(String errorPerc) {
		this.errorPerc = errorPerc;
	}
	public Long getValidatedCardsCount() {
		return validatedCardsCount;
	}
	public void setValidatedCardsCount(Long validatedCardsCount) {
		this.validatedCardsCount = validatedCardsCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getTotalCadre() {
		return totalCadre;
	}
	public void setTotalCadre(Long totalCadre) {
		this.totalCadre = totalCadre;
	}
	public Long getPrintedCadre() {
		return printedCadre;
	}
	public void setPrintedCadre(Long printedCadre) {
		this.printedCadre = printedCadre;
	}
	public Long getUnPrintedCadre() {
		return unPrintedCadre;
	}
	public void setUnPrintedCadre(Long unPrintedCadre) {
		this.unPrintedCadre = unPrintedCadre;
	}
}
