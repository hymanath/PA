package com.itgrids.partyanalyst.dto;

public class HamletCountVo {

	
	private String hamletName;
	private Long hamletId;
	private long surveyCount=0;
	
	public long getSurveyCount() {
		return surveyCount;
	}
	public void setSurveyCount(long surveyCount) {
		this.surveyCount = surveyCount;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hamletId == null) ? 0 : hamletId.hashCode());
		return result;
	}
			
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HamletCountVo other = (HamletCountVo) obj;
		if (hamletId == null) {
			if (other.hamletId != null)
				return false;
		} else if (!hamletId.equals(other.hamletId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HamletCountVo [hamletName=" + hamletName + ", hamletId="
				+ hamletId + ", surveyCount=" + surveyCount + "]";
	}
	
	
}
