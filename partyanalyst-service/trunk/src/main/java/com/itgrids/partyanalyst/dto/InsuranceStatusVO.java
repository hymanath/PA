package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class InsuranceStatusVO implements Serializable {
	private Long statusId;
	private String status;
	private Long insuredMemberCount = 0L;
	private Long insuredAmount = 0L;
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getInsuredMemberCount() {
		return insuredMemberCount;
	}
	public void setInsuredMemberCount(Long insuredMemberCount) {
		this.insuredMemberCount = insuredMemberCount;
	}
	public Long getInsuredAmount() {
		return insuredAmount;
	}
	public void setInsuredAmount(Long insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	
	
}
