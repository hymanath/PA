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
	
	
}
