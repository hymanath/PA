package com.itgrids.partyanalyst.dto;

public class OrderOfPriorityVO {
  private Long panchayatId;
  private String name;
  private Long totalVoters;
  private Long targetedVoters;
  private Long previousVoters =0l;
  private Long prevElectionVotes=0l;
  private Long opportunity;
  private Double opportunityPerc;
  private Double prpWeight = 0d;
  private Double youngWeight = 0d;
  private Double ageWeight = 0d;
  private Double casteWeight = 0d;
  private Double prevTrnzWeight = 0d;
  private Double totalWeight = 0d;
  private String type;
  
public Long getPanchayatId() {
	return panchayatId;
}
public void setPanchayatId(Long panchayatId) {
	this.panchayatId = panchayatId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getTotalVoters() {
	return totalVoters;
}
public void setTotalVoters(Long totalVoters) {
	this.totalVoters = totalVoters;
}
public Long getTargetedVoters() {
	return targetedVoters;
}
public void setTargetedVoters(Long targetedVoters) {
	this.targetedVoters = targetedVoters;
}
public Long getPreviousVoters() {
	return previousVoters;
}
public void setPreviousVoters(Long previousVoters) {
	this.previousVoters = previousVoters;
}
public Long getOpportunity() {
	return opportunity;
}
public void setOpportunity(Long opportunity) {
	this.opportunity = opportunity;
}
public Double getOpportunityPerc() {
	return opportunityPerc;
}
public void setOpportunityPerc(Double opportunityPerc) {
	this.opportunityPerc = opportunityPerc;
}
public Double getPrpWeight() {
	return prpWeight;
}
public void setPrpWeight(Double prpWeight) {
	this.prpWeight = prpWeight;
}
public Double getYoungWeight() {
	return youngWeight;
}
public void setYoungWeight(Double youngWeight) {
	this.youngWeight = youngWeight;
}
public Double getAgeWeight() {
	return ageWeight;
}
public void setAgeWeight(Double ageWeight) {
	this.ageWeight = ageWeight;
}
public Double getCasteWeight() {
	return casteWeight;
}
public void setCasteWeight(Double casteWeight) {
	this.casteWeight = casteWeight;
}
public Double getPrevTrnzWeight() {
	return prevTrnzWeight;
}
public void setPrevTrnzWeight(Double prevTrnzWeight) {
	this.prevTrnzWeight = prevTrnzWeight;
}
public Double getTotalWeight() {
	return totalWeight;
}
public void setTotalWeight(Double totalWeight) {
	this.totalWeight = totalWeight;
}
public Long getPrevElectionVotes() {
	return prevElectionVotes;
}
public void setPrevElectionVotes(Long prevElectionVotes) {
	this.prevElectionVotes = prevElectionVotes;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
  
  
}
