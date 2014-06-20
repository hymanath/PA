package com.itgrids.partyanalyst.utils;

public class MatchedTypeVo {

	
	private Long memberId;
	private boolean ageMatched;
	private boolean relativeNameMatch;
	private boolean unMatched;
	private boolean genderMatch;
	
	public MatchedTypeVo() {
		super();
		
	}
	public MatchedTypeVo(Long memberId) {
		super();
		this.memberId = memberId;
	}

	public boolean isGenderMatch() {
		return genderMatch;
	}
	public void setGenderMatch(boolean genderMatch) {
		this.genderMatch = genderMatch;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public boolean isAgeMatched() {
		return ageMatched;
	}
	public void setAgeMatched(boolean ageMatched) {
		this.ageMatched = ageMatched;
	}
	public boolean isRelativeNameMatch() {
		return relativeNameMatch;
	}
	public void setRelativeNameMatch(boolean relativeNameMatch) {
		this.relativeNameMatch = relativeNameMatch;
	}
	public boolean isUnMatched() {
		return unMatched;
	}
	public void setUnMatched(boolean unMatched) {
		this.unMatched = unMatched;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((memberId == null) ? 0 : memberId.hashCode());
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
		MatchedTypeVo other = (MatchedTypeVo) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}

	
	
}
