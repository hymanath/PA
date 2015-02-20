package com.itgrids.partyanalyst.dto;

public class MissedCallsDetailsVO {

	
	public Long totalCount = 0L;
	public Long mismatchedCnt= 0L;
	public Long singleMemberRegCnt= 0L;
	public Long multiMemberRegCnt= 0L;
	public Long districtId;
	public Long districtCount= 0L;
	public String name;
	public Long constituencyId;
	public String constituencyName;
	public Long printedCount;
	public Long missedCallsCount;
	
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
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getPrintedCount() {
		return printedCount;
	}
	public void setPrintedCount(Long printedCount) {
		this.printedCount = printedCount;
	}
	public Long getMissedCallsCount() {
		return missedCallsCount;
	}
	public void setMissedCallsCount(Long missedCallsCount) {
		this.missedCallsCount = missedCallsCount;
	}
     
} 
