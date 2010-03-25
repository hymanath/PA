package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConstituencyWiseDataForMandalVO {
	private Long constituencyId;
	private String constituencyName;
	
	private Long commonMaleVotersInMandalAndConstituency;
	private Long commonFemaleVotersInMandalAndConstituency;
	private Long commonMaleOrFemaleVoters;
	private Long commonTotalVotersInMandalAndConstituency;
	
	private Long malePolledVotes;
	private Long femalePolledVotes;
	private Long maleOrFemaleValidVotes;
	private Long totalPolledVotes;
	
	private String percentage;
	private List<SelectOptionVO> revenueVillages;
	private List<PartyGenderWiseVotesVO> partyVotes;
	private List<MandalLocalElectionPartyResultsVO> localElectionsVOs;
	
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
	
	public List<MandalLocalElectionPartyResultsVO> getLocalElectionsVOs() {
		return localElectionsVOs;
	}

	public void setLocalElectionsVOs(
			List<MandalLocalElectionPartyResultsVO> localElectionsVOs) {
		this.localElectionsVOs = localElectionsVOs;
	}

	public Long getCommonMaleVotersInMandalAndConstituency() {
		return commonMaleVotersInMandalAndConstituency;
	}

	public void setCommonMaleVotersInMandalAndConstituency(
			Long commonMaleVotersInMandalAndConstituency) {
		this.commonMaleVotersInMandalAndConstituency = commonMaleVotersInMandalAndConstituency;
	}

	public Long getCommonFemaleVotersInMandalAndConstituency() {
		return commonFemaleVotersInMandalAndConstituency;
	}

	public void setCommonFemaleVotersInMandalAndConstituency(
			Long commonFemaleVotersInMandalAndConstituency) {
		this.commonFemaleVotersInMandalAndConstituency = commonFemaleVotersInMandalAndConstituency;
	}

	public Long getCommonTotalVotersInMandalAndConstituency() {
		return commonTotalVotersInMandalAndConstituency;
	}

	public void setCommonTotalVotersInMandalAndConstituency(
			Long commonTotalVotersInMandalAndConstituency) {
		this.commonTotalVotersInMandalAndConstituency = commonTotalVotersInMandalAndConstituency;
	}

	public Long getMalePolledVotes() {
		return malePolledVotes;
	}

	public void setMalePolledVotes(Long malePolledVotes) {
		this.malePolledVotes = malePolledVotes;
	}

	public Long getFemalePolledVotes() {
		return femalePolledVotes;
	}

	public void setFemalePolledVotes(Long femalePolledVotes) {
		this.femalePolledVotes = femalePolledVotes;
	}

	public Long getTotalPolledVotes() {
		return totalPolledVotes;
	}

	public void setTotalPolledVotes(Long totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}

	public Long getMaleOrFemaleValidVotes() {
		return maleOrFemaleValidVotes;
	}

	public void setMaleOrFemaleValidVotes(Long maleOrFemaleValidVotes) {
		this.maleOrFemaleValidVotes = maleOrFemaleValidVotes;
	}

	public Long getCommonMaleOrFemaleVoters() {
		return commonMaleOrFemaleVoters;
	}

	public void setCommonMaleOrFemaleVoters(Long commonMaleOrFemaleVoters) {
		this.commonMaleOrFemaleVoters = commonMaleOrFemaleVoters;
	}

	public List<SelectOptionVO> getRevenueVillages() {
		return revenueVillages;
	}

	public void setRevenueVillages(List<SelectOptionVO> revenueVillages) {
		this.revenueVillages = revenueVillages;
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
