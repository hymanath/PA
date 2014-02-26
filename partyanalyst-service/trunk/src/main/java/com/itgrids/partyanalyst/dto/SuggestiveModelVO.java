package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class SuggestiveModelVO {
	
	
	List<YouthLeaderSelectionVO> casteData;
	List<PanchayatVO>  youngOldVoters;
	Map<String ,Map<String,PartyImpactVO>> prpImpact;
	List<PartyPositionVO> orderOfPriority;
	List<SuggestedLocationsVO> suggestedLocations;
	
	public List<YouthLeaderSelectionVO> getCasteData() {
		return casteData;
	}
	
	public void setCasteData(List<YouthLeaderSelectionVO> casteData) {
		this.casteData = casteData;
	}
	
	public List<PanchayatVO> getYoungOldVoters() {
		return youngOldVoters;
	}
	
	public void setYoungOldVoters(List<PanchayatVO> youngOldVoters) {
		this.youngOldVoters = youngOldVoters;
	}
	
	public Map<String, Map<String, PartyImpactVO>> getPrpImpact() {
		return prpImpact;
	}
	
	public void setPrpImpact(Map<String, Map<String, PartyImpactVO>> prpImpact) {
		this.prpImpact = prpImpact;
	}
	
	public List<PartyPositionVO> getOrderOfPriority() {
		return orderOfPriority;
	}
	
	public void setOrderOfPriority(List<PartyPositionVO> orderOfPriority) {
		this.orderOfPriority = orderOfPriority;
	}
	
	public List<SuggestedLocationsVO> getSuggestedLocations() {
		return suggestedLocations;
	}
	
	public void setSuggestedLocations(List<SuggestedLocationsVO> suggestedLocations) {
		this.suggestedLocations = suggestedLocations;
	}
    
	
}
