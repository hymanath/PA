package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CallTrackingVO implements Serializable {
	
	private Long problemId;
	private String problemPurpose;
	private String referenceNo;
	private Long callTrackingDetailId;
	private String name;
	private String mobile;
	private String problemAddedDate;
	private String villageOrTown;
	private Long count;
	private Long status;
	private Long totalCount;
	private List<CallTrackingVO> callTrackingVO;
	
	
	public Long getProblemId() {
		return problemId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	public String getProblemPurpose() {
		return problemPurpose;
	}
	public void setProblemPurpose(String problemPurpose) {
		this.problemPurpose = problemPurpose;
	}
	public Long getCallTrackingDetailId() {
		return callTrackingDetailId;
	}
	public void setCallTrackingDetailId(Long callTrackingDetailId) {
		this.callTrackingDetailId = callTrackingDetailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProblemAddedDate() {
		return problemAddedDate;
	}
	public void setProblemAddedDate(String problemAddedDate) {
		this.problemAddedDate = problemAddedDate;
	}
	public String getVillageOrTown() {
		return villageOrTown;
	}
	public void setVillageOrTown(String villageOrTown) {
		this.villageOrTown = villageOrTown;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public List<CallTrackingVO> getCallTrackingVO() {
		return callTrackingVO;
	}
	public void setCallTrackingVO(List<CallTrackingVO> callTrackingVO) {
		this.callTrackingVO = callTrackingVO;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	
			
}
