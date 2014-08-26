package com.itgrids.partyanalyst.dto;

public class CasteCollectedDetailsVO {
	
	private Long casteCount = 0L;
	private Long mobileNUmbersCount = 0L;
	private Long hamletCount = 0L;
	private Long wardCount = 0L;
	
	private String castePercent ;
	private String mobilePercent;
	private String hamletPercent;
	private String wardPercent;
	
	private Long casteMatchedCount = 0L;
	private Long casteUnMatchedCount = 0L;
	private Long mobileMatchedCount = 0L;
	private Long mobileUnmatchedCount = 0L;
	private Long newlyCollectedCount = 0L;
	private Long notIdentifiedCount = 0L;
	
	private String matchedPercent;
	private String unmatchedPercent;
	private String notIdentifiedPercent;
	private Long emptyCount = 0L;
	private Long totalCount = 0L;

	
	
	public Long getEmptyCount() {
		return emptyCount;
	}
	public void setEmptyCount(Long emptyCount) {
		this.emptyCount = emptyCount;
	}
	public String getMatchedPercent() {
		return matchedPercent;
	}
	public void setMatchedPercent(String matchedPercent) {
		this.matchedPercent = matchedPercent;
	}
	public String getUnmatchedPercent() {
		return unmatchedPercent;
	}
	public void setUnmatchedPercent(String unmatchedPercent) {
		this.unmatchedPercent = unmatchedPercent;
	}
	public String getNotIdentifiedPercent() {
		return notIdentifiedPercent;
	}
	public void setNotIdentifiedPercent(String notIdentifiedPercent) {
		this.notIdentifiedPercent = notIdentifiedPercent;
	}
	
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getNotIdentifiedCount() {
		return notIdentifiedCount;
	}
	public void setNotIdentifiedCount(Long notIdentifiedCount) {
		this.notIdentifiedCount = notIdentifiedCount;
	}
	public Long getNewlyCollectedCount() {
		return newlyCollectedCount;
	}
	public void setNewlyCollectedCount(Long newlyCollectedCount) {
		this.newlyCollectedCount = newlyCollectedCount;
	}
	public Long getCasteMatchedCount() {
		return casteMatchedCount;
	}
	public void setCasteMatchedCount(Long casteMatchedCount) {
		this.casteMatchedCount = casteMatchedCount;
	}
	public Long getCasteUnMatchedCount() {
		return casteUnMatchedCount;
	}
	public void setCasteUnMatchedCount(Long casteUnMatchedCount) {
		this.casteUnMatchedCount = casteUnMatchedCount;
	}
	public Long getMobileMatchedCount() {
		return mobileMatchedCount;
	}
	public void setMobileMatchedCount(Long mobileMatchedCount) {
		this.mobileMatchedCount = mobileMatchedCount;
	}
	public Long getMobileUnmatchedCount() {
		return mobileUnmatchedCount;
	}
	public void setMobileUnmatchedCount(Long mobileUnmatchedCount) {
		this.mobileUnmatchedCount = mobileUnmatchedCount;
	}
	public String getCastePercent() {
		return castePercent;
	}
	public void setCastePercent(String castePercent) {
		this.castePercent = castePercent;
	}
	public String getMobilePercent() {
		return mobilePercent;
	}
	public void setMobilePercent(String mobilePercent) {
		this.mobilePercent = mobilePercent;
	}
	public String getHamletPercent() {
		return hamletPercent;
	}
	public void setHamletPercent(String hamletPercent) {
		this.hamletPercent = hamletPercent;
	}
	public String getWardPercent() {
		return wardPercent;
	}
	public void setWardPercent(String wardPercent) {
		this.wardPercent = wardPercent;
	}
	public Long getCasteCount() {
		return casteCount;
	}
	public void setCasteCount(Long casteCount) {
		this.casteCount = casteCount;
	}
	public Long getMobileNUmbersCount() {
		return mobileNUmbersCount;
	}
	public void setMobileNUmbersCount(Long mobileNUmbersCount) {
		this.mobileNUmbersCount = mobileNUmbersCount;
	}
	public Long getHamletCount() {
		return hamletCount;
	}
	public void setHamletCount(Long hamletCount) {
		this.hamletCount = hamletCount;
	}
	public Long getWardCount() {
		return wardCount;
	}
	public void setWardCount(Long wardCount) {
		this.wardCount = wardCount;
	}

}
