package com.itgrids.partyanalyst.dto;

import java.util.List;

/**
 * @author sys
 *
 */
public class PanchayatHamletsCountVo {

	private String panchayatName;
	private Long panchayatId;
	private List<PanchayatHamletsCountVo> panchayatsList;
	private List<HamletCountVo> hamletsCountList;
	
	
	
	public List<PanchayatHamletsCountVo> getPanchayatsList() {
		return panchayatsList;
	}
	public void setPanchayatsList(List<PanchayatHamletsCountVo> panchayatsList) {
		this.panchayatsList = panchayatsList;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public List<HamletCountVo> getHamletsCountList() {
		return hamletsCountList;
	}
	public void setHamletsCountList(List<HamletCountVo> hamletsCountList) {
		this.hamletsCountList = hamletsCountList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((panchayatId == null) ? 0 : panchayatId.hashCode());
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
		PanchayatHamletsCountVo other = (PanchayatHamletsCountVo) obj;
		if (panchayatId == null) {
			if (other.panchayatId != null)
				return false;
		} else if (!panchayatId.equals(other.panchayatId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PanchayatHamletsCountVo [panchayatName=" + panchayatName
				+ ", panchayatId=" + panchayatId + ", panchayatsList="
				+ panchayatsList + ", hamletsCountList=" + hamletsCountList
				+ "]";
	}
	
	
	
	
}
