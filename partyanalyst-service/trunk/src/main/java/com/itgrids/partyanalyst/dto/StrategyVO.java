package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StrategyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1372373331324042566L;

	private Long constituencyId;
	private Long partyId;
	private List<Long> electionIds;
	private Long effectPartyId;
	private Long effectElectionId;
	private Double prevTrnzWt;
	private Double youngWt;
	private Double prpWt;
	private Double agedWt;
	private Double totalCastWt;
	private Long publicationId;
	private Map<Long,Float> castePercents;
	private Map<Long,Set<Long>> mergeCasteMap;
	private Long base;
	private Long assured;
	private Long tdpPerc;
	
	private Double worstMin;
	private Double worstMax;
	private Double veryPoorMin;
	private Double veryPoorMax;
	private Double poorMin;
	private Double poorMax;
	private Double okMin;
	private Double okMax;
	private Double strongMin;
	private Double strongMax;
	private Double veryStrongMin;
	private Double veryStrongMax;
	private boolean considerRange;
	private boolean autoCalculate;
	private boolean autoStrategy;
	private boolean goalDataPresent;
	private List<Long> excludePanchys;
	private boolean onlyPriority;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public List<Long> getElectionIds() {
		return electionIds;
	}
	public void setElectionIds(List<Long> electionIds) {
		this.electionIds = electionIds;
	}
	public Long getEffectPartyId() {
		return effectPartyId;
	}
	public void setEffectPartyId(Long effectPartyId) {
		this.effectPartyId = effectPartyId;
	}
	public Long getEffectElectionId() {
		return effectElectionId;
	}
	public void setEffectElectionId(Long effectElectionId) {
		this.effectElectionId = effectElectionId;
	}
	public Double getPrevTrnzWt() {
		return prevTrnzWt;
	}
	public void setPrevTrnzWt(Double prevTrnzWt) {
		this.prevTrnzWt = prevTrnzWt;
	}
	public Double getYoungWt() {
		return youngWt;
	}
	public void setYoungWt(Double youngWt) {
		this.youngWt = youngWt;
	}
	public Double getPrpWt() {
		return prpWt;
	}
	public void setPrpWt(Double prpWt) {
		this.prpWt = prpWt;
	}
	public Double getAgedWt() {
		return agedWt;
	}
	public void setAgedWt(Double agedWt) {
		this.agedWt = agedWt;
	}
	public Double getTotalCastWt() {
		return totalCastWt;
	}
	public void setTotalCastWt(Double totalCastWt) {
		this.totalCastWt = totalCastWt;
	}
	public Long getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}
	public Map<Long, Float> getCastePercents() {
		return castePercents;
	}
	public void setCastePercents(Map<Long, Float> castePercents) {
		this.castePercents = castePercents;
	}
	public Map<Long, Set<Long>> getMergeCasteMap() {
		return mergeCasteMap;
	}
	public void setMergeCasteMap(Map<Long, Set<Long>> mergeCasteMap) {
		this.mergeCasteMap = mergeCasteMap;
	}
	public Long getBase() {
		return base;
	}
	public void setBase(Long base) {
		this.base = base;
	}
	public Long getAssured() {
		return assured;
	}
	public void setAssured(Long assured) {
		this.assured = assured;
	}
	public Long getTdpPerc() {
		return tdpPerc;
	}
	public void setTdpPerc(Long tdpPerc) {
		this.tdpPerc = tdpPerc;
	}
	public Double getWorstMin() {
		return worstMin;
	}
	public void setWorstMin(Double worstMin) {
		this.worstMin = worstMin;
	}
	public Double getWorstMax() {
		return worstMax;
	}
	public void setWorstMax(Double worstMax) {
		this.worstMax = worstMax;
	}
	public Double getVeryPoorMin() {
		return veryPoorMin;
	}
	public void setVeryPoorMin(Double veryPoorMin) {
		this.veryPoorMin = veryPoorMin;
	}
	public Double getVeryPoorMax() {
		return veryPoorMax;
	}
	public void setVeryPoorMax(Double veryPoorMax) {
		this.veryPoorMax = veryPoorMax;
	}
	public Double getPoorMin() {
		return poorMin;
	}
	public void setPoorMin(Double poorMin) {
		this.poorMin = poorMin;
	}
	public Double getPoorMax() {
		return poorMax;
	}
	public void setPoorMax(Double poorMax) {
		this.poorMax = poorMax;
	}
	public Double getOkMin() {
		return okMin;
	}
	public void setOkMin(Double okMin) {
		this.okMin = okMin;
	}
	public Double getOkMax() {
		return okMax;
	}
	public void setOkMax(Double okMax) {
		this.okMax = okMax;
	}
	public Double getStrongMin() {
		return strongMin;
	}
	public void setStrongMin(Double strongMin) {
		this.strongMin = strongMin;
	}
	public Double getStrongMax() {
		return strongMax;
	}
	public void setStrongMax(Double strongMax) {
		this.strongMax = strongMax;
	}
	public Double getVeryStrongMin() {
		return veryStrongMin;
	}
	public void setVeryStrongMin(Double veryStrongMin) {
		this.veryStrongMin = veryStrongMin;
	}
	public Double getVeryStrongMax() {
		return veryStrongMax;
	}
	public void setVeryStrongMax(Double veryStrongMax) {
		this.veryStrongMax = veryStrongMax;
	}
	public boolean isConsiderRange() {
		return considerRange;
	}
	public void setConsiderRange(boolean considerRange) {
		this.considerRange = considerRange;
	}
	public boolean isAutoCalculate() {
		return autoCalculate;
	}
	public void setAutoCalculate(boolean autoCalculate) {
		this.autoCalculate = autoCalculate;
	}
	public boolean isAutoStrategy() {
		return autoStrategy;
	}
	public void setAutoStrategy(boolean autoStrategy) {
		this.autoStrategy = autoStrategy;
	}
	public boolean isGoalDataPresent() {
		return goalDataPresent;
	}
	public void setGoalDataPresent(boolean goalDataPresent) {
		this.goalDataPresent = goalDataPresent;
	}
	public List<Long> getExcludePanchys() {
		return excludePanchys;
	}
	public void setExcludePanchys(List<Long> excludePanchys) {
		this.excludePanchys = excludePanchys;
	}
	public boolean isOnlyPriority() {
		return onlyPriority;
	}
	public void setOnlyPriority(boolean onlyPriority) {
		this.onlyPriority = onlyPriority;
	}
	
	
}
