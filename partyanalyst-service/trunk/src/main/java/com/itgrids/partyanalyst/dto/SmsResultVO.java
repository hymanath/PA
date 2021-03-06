package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SmsResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long totalSmsSent;
	private Long remainingSmsCount;
	private Long status; //			Failure =  1   			Success	=	0
	private ResultStatus resultStatus;
	private List<CadreInfo> smsSentCadreInfo;
	
	
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
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<CadreInfo> getSmsSentCadreInfo() {
		return smsSentCadreInfo;
	}
	public void setSmsSentCadreInfo(List<CadreInfo> smsSentCadreInfo) {
		this.smsSentCadreInfo = smsSentCadreInfo;
	}
	
	
}
