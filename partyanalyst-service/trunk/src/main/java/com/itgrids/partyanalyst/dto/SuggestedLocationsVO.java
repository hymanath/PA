package com.itgrids.partyanalyst.dto;

public class SuggestedLocationsVO {
   
	private Long id;
	private String type;
	private Double castePoint = 0d;
	private Double casteWeight = 0d;
	private Double prevTrendPoint = 0d;
	private Double prevTrendWeight = 0d;
	private Double agedVotrPoint = 0d;
	private Double agedVotrWeight = 0d;
	private Double youngVotrPoint = 0d;
	private Double youngVotrWeight = 0d;
	private Double regainablePoint = 0d;
	private Double regainableWeight = 0d;
	private Double totalWeight = 0d;
	private Long currentVoters = 0l;
	private Long target = 0l;
	private Long partySecured = 0l;
	private Long difference = 0l;
	private Double differencePerc = 0d;
	private Double partyPercentage = 0.0d;
	private Double totalTargetPerc = 0.0d;
	private Double partyDiffForCaste = 0.0d;
	private Double diffWithOutNegVal = 0.0d;
	private Double prpEffect = 0.0d;
	private String name;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Double getCastePoint() {
		return castePoint;
	}
	
	public void setCastePoint(Double castePoint) {
		this.castePoint = castePoint;
	}
	
	public Double getCasteWeight() {
		return casteWeight;
	}
	
	public void setCasteWeight(Double casteWeight) {
		this.casteWeight = casteWeight;
	}
	
	public Double getPrevTrendPoint() {
		return prevTrendPoint;
	}
	
	public void setPrevTrendPoint(Double prevTrendPoint) {
		this.prevTrendPoint = prevTrendPoint;
	}
	
	public Double getPrevTrendWeight() {
		return prevTrendWeight;
	}
	
	public void setPrevTrendWeight(Double prevTrendWeight) {
		this.prevTrendWeight = prevTrendWeight;
	}
	
	public Double getAgedVotrPoint() {
		return agedVotrPoint;
	}
	
	public void setAgedVotrPoint(Double agedVotrPoint) {
		this.agedVotrPoint = agedVotrPoint;
	}
	
	public Double getAgedVotrWeight() {
		return agedVotrWeight;
	}
	
	public void setAgedVotrWeight(Double agedVotrWeight) {
		this.agedVotrWeight = agedVotrWeight;
	}
	
	public Double getYoungVotrPoint() {
		return youngVotrPoint;
	}
	
	public void setYoungVotrPoint(Double youngVotrPoint) {
		this.youngVotrPoint = youngVotrPoint;
	}
	
	public Double getYoungVotrWeight() {
		return youngVotrWeight;
	}
	
	public void setYoungVotrWeight(Double youngVotrWeight) {
		this.youngVotrWeight = youngVotrWeight;
	}
	
	public Double getRegainablePoint() {
		return regainablePoint;
	}
	
	public void setRegainablePoint(Double regainablePoint) {
		this.regainablePoint = regainablePoint;
	}
	
	public Double getRegainableWeight() {
		return regainableWeight;
	}
	
	public void setRegainableWeight(Double regainableWeight) {
		this.regainableWeight = regainableWeight;
	}
	
	public Double getTotalWeight() {
		return totalWeight;
	}
	
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}
	
	public Long getCurrentVoters() {
		return currentVoters;
	}
	
	public void setCurrentVoters(Long currentVoters) {
		this.currentVoters = currentVoters;
	}
	
	public Long getTarget() {
		return target;
	}
	
	public void setTarget(Long target) {
		this.target = target;
	}
	
	public Long getPartySecured() {
		return partySecured;
	}
	
	public void setPartySecured(Long partySecured) {
		this.partySecured = partySecured;
	}
	
	public Long getDifference() {
		return difference;
	}
	
	public void setDifference(Long difference) {
		this.difference = difference;
	}
	
	public Double getDifferencePerc() {
		return differencePerc;
	}
	
	public void setDifferencePerc(Double differencePerc) {
		this.differencePerc = differencePerc;
	}

	public Double getPartyPercentage() {
		return partyPercentage;
	}

	public void setPartyPercentage(Double partyPercentage) {
		this.partyPercentage = partyPercentage;
	}

	public Double getPartyDiffForCaste() {
		return partyDiffForCaste;
	}

	public void setPartyDiffForCaste(Double partyDiffForCaste) {
		this.partyDiffForCaste = partyDiffForCaste;
	}

	public Double getDiffWithOutNegVal() {
		return diffWithOutNegVal;
	}

	public void setDiffWithOutNegVal(Double diffWithOutNegVal) {
		this.diffWithOutNegVal = diffWithOutNegVal;
	}

	public Double getTotalTargetPerc() {
		return totalTargetPerc;
	}

	public void setTotalTargetPerc(Double totalTargetPerc) {
		this.totalTargetPerc = totalTargetPerc;
	}

	public Double getPrpEffect() {
		return prpEffect;
	}

	public void setPrpEffect(Double prpEffect) {
		this.prpEffect = prpEffect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
