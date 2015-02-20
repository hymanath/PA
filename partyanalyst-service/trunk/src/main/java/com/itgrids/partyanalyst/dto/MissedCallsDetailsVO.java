package com.itgrids.partyanalyst.dto;

public class MissedCallsDetailsVO {

	
	public Long totalCount = 0L;
	public Long mismatchedCnt= 0L;
	public Long singleMemberRegCnt= 0L;
	public Long multiMemberRegCnt= 0L;
	public Long districtId;
	public Long districtCount= 0L;
	public String name;
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getMismatchedCnt() {
		return mismatchedCnt;
	}
	public void setMismatchedCnt(Long mismatchedCnt) {
		this.mismatchedCnt = mismatchedCnt;
	}
	public Long getSingleMemberRegCnt() {
		return singleMemberRegCnt;
	}
	public void setSingleMemberRegCnt(Long singleMemberRegCnt) {
		this.singleMemberRegCnt = singleMemberRegCnt;
	}
	public Long getMultiMemberRegCnt() {
		return multiMemberRegCnt;
	}
	public void setMultiMemberRegCnt(Long multiMemberRegCnt) {
		this.multiMemberRegCnt = multiMemberRegCnt;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
