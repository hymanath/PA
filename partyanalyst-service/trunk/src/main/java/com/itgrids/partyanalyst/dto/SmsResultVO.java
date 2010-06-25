package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class SmsResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long totalSmsSent;
	private Long remainingSmsCount;
	private Long status; //			Failure =  1   			Success	=	0
	
	
	public Long getRemainingSmsCount() {
		return remainingSmsCount;
	}
	public void setRemainingSmsCount(Long remainingSmsCount) {
		this.remainingSmsCount = remainingSmsCount;
	}
	public Long getTotalSmsSent() {
		return totalSmsSent;
	}
	public void setTotalSmsSent(Long totalSmsSent) {
		this.totalSmsSent = totalSmsSent;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	
}
