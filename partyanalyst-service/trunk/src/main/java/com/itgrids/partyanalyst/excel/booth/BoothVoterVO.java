package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;

public class BoothVoterVO implements Serializable{

	private static final long serialVersionUID = 2633153379374752094L;
	
	private Long boothId;
	private Long voterId;
	private Long serialNo;
	
	public BoothVoterVO(Long voterId, Long serailNo)
	{
		this.voterId = voterId;
		this.serialNo = serailNo;
	}
	
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

}
