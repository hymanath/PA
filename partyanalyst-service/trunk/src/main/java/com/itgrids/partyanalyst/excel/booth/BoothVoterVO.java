package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;

public class BoothVoterVO implements Serializable{

	private static final long serialVersionUID = 2633153379374752094L;
	
	private Long boothId;
	private Long voterId;
	private Long serialNo;
	private String partNo;
	private Long constituencyId;
	private String voterCardNo;
	private Long sno;
	
	public Long getSno() {
		return sno;
	}

	public void setSno(Long sno) {
		this.sno = sno;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getVoterCardNo() {
		return voterCardNo;
	}

	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}

	public BoothVoterVO(Long voterId, Long serailNo)
	{
		this.voterId = voterId;
		this.serialNo = serailNo;
	}
	
	public BoothVoterVO() {}

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
