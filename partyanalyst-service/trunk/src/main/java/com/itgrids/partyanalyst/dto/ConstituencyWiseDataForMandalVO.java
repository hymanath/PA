package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;

public class ConstituencyWiseDataForMandalVO {
	private Long constituencyId;
	private String constituencyName;
	private Long commonVotersInMandalAndConstituency;
	private String percentage;
	private List<PartyGenderWiseVotesVO> partyVotes;
	
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
	
	public Long getCommonVotersInMandalAndConstituency() {
		return commonVotersInMandalAndConstituency;
	}
	
	public void setCommonVotersInMandalAndConstituency(
			Long commonVotersInMandalAndConstituency) {
		this.commonVotersInMandalAndConstituency = commonVotersInMandalAndConstituency;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	
	public List<PartyGenderWiseVotesVO> getPartyVotes() {
		return partyVotes;
	}

	public void setPartyVotes(List<PartyGenderWiseVotesVO> partyVotes) {
		this.partyVotes = partyVotes;
	}

	@Override
	public boolean equals(Object obj) {
		ConstituencyWiseDataForMandalVO voObj = (ConstituencyWiseDataForMandalVO)obj;
		return this.constituencyId.equals(voObj.getConstituencyId());
	}
	
	@Override
	public int hashCode() {
		return this.constituencyId.hashCode();
	}
	
}
