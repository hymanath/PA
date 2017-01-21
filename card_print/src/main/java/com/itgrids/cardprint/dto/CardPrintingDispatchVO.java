package com.itgrids.cardprint.dto;

import java.io.Serializable;
import java.util.List;

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
	private Long validatedCardsCount=0l;
	private String status;
	private Long errorCount = 0l;
	private Long approvedCount = 0l;
	
	private Long totalCadre = 0l;
	private Long printedCadre = 0l;
	private Long unPrintedCadre = 0l;
	
	private String constPrintStatus;
	
	private Long totalBoxesCount = 0l;
	private Long verifiedBoxesCount = 0l;
	private Long notVerifiedBoxesCount = 0l;
	
	private Long acceptedBoxesCount = 0l;
	private Long rejectedBoxesCount = 0l;
	
	private List<CardPrintingDispatchVO> subList; 
	
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
	public Long getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(Long approvedCount) {
		this.approvedCount = approvedCount;
	}
	public List<CardPrintingDispatchVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CardPrintingDispatchVO> subList) {
		this.subList = subList;
	}
	public String getConstPrintStatus() {
		return constPrintStatus;
	}
	public void setConstPrintStatus(String constPrintStatus) {
		this.constPrintStatus = constPrintStatus;
	}
	public Long getTotalBoxesCount() {
		return totalBoxesCount;
	}
	public void setTotalBoxesCount(Long totalBoxesCount) {
		this.totalBoxesCount = totalBoxesCount;
	}
	public Long getVerifiedBoxesCount() {
		return verifiedBoxesCount;
	}
	public void setVerifiedBoxesCount(Long verifiedBoxesCount) {
		this.verifiedBoxesCount = verifiedBoxesCount;
	}
	public Long getNotVerifiedBoxesCount() {
		return notVerifiedBoxesCount;
	}
	public void setNotVerifiedBoxesCount(Long notVerifiedBoxesCount) {
		this.notVerifiedBoxesCount = notVerifiedBoxesCount;
	}
	public Long getAcceptedBoxesCount() {
		return acceptedBoxesCount;
	}
	public void setAcceptedBoxesCount(Long acceptedBoxesCount) {
		this.acceptedBoxesCount = acceptedBoxesCount;
	}
	public Long getRejectedBoxesCount() {
		return rejectedBoxesCount;
	}
	public void setRejectedBoxesCount(Long rejectedBoxesCount) {
		this.rejectedBoxesCount = rejectedBoxesCount;
	}
	
}
