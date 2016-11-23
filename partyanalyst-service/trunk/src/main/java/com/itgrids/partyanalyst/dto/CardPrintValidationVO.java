package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CardPrintValidationVO implements Serializable{
	
	private static final long serialVersionUID = -6763384019254385475L;
	
	private Long   cardPrintVendorId;
	private Long   tdpCadreId;
	private String memberShipId;
	private String printStatus;
	private List<Long> rejectReasonIds;
	private String  rejectReason;
	private Long    cardPrintValidationUserId;
	private String  boxNo;
	
	
	public Long getCardPrintVendorId() {
		return cardPrintVendorId;
	}
	public void setCardPrintVendorId(Long cardPrintVendorId) {
		this.cardPrintVendorId = cardPrintVendorId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public List<Long> getRejectReasonIds() {
		return rejectReasonIds;
	}
	public void setRejectReasonIds(List<Long> rejectReasonIds) {
		this.rejectReasonIds = rejectReasonIds;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Long getCardPrintValidationUserId() {
		return cardPrintValidationUserId;
	}
	public void setCardPrintValidationUserId(Long cardPrintValidationUserId) {
		this.cardPrintValidationUserId = cardPrintValidationUserId;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	
}
