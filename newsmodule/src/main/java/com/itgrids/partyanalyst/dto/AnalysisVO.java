package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class AnalysisVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055862184125103854L;

	private Date fromDate;
	private Date toDate;
	private Long sourcePartyId;
	private Long sourceCandidateId;
	private Long sourceBenifitId;
	private Long destiPartyId;
	private Long destiCandidateId;
	private Long destiBenifitId;
	private boolean byKeyword;
	private boolean byCategory;
	private String ids;
	private String sourceIds;
	private String locationValues;
	private boolean sourceCandPartyWise;
	private boolean destCandPartyWise;
	private boolean locationPresent;
	private boolean sourcePresent;
	private Long locationLvl;
	private boolean sourceCand;
	private boolean destiCand;
	private boolean sourceParty;
	private boolean destiParty;
	private boolean bySourceCand;
	private boolean byDestiCand;
	private String gallaryKeyWordIds;
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getSourcePartyId() {
		return sourcePartyId;
	}
	public void setSourcePartyId(Long sourcePartyId) {
		this.sourcePartyId = sourcePartyId;
	}
	public Long getSourceCandidateId() {
		return sourceCandidateId;
	}
	public void setSourceCandidateId(Long sourceCandidateId) {
		this.sourceCandidateId = sourceCandidateId;
	}
	public Long getSourceBenifitId() {
		return sourceBenifitId;
	}
	public void setSourceBenifitId(Long sourceBenifitId) {
		this.sourceBenifitId = sourceBenifitId;
	}
	public Long getDestiPartyId() {
		return destiPartyId;
	}
	public void setDestiPartyId(Long destiPartyId) {
		this.destiPartyId = destiPartyId;
	}
	public Long getDestiCandidateId() {
		return destiCandidateId;
	}
	public void setDestiCandidateId(Long destiCandidateId) {
		this.destiCandidateId = destiCandidateId;
	}
	public Long getDestiBenifitId() {
		return destiBenifitId;
	}
	public void setDestiBenifitId(Long destiBenifitId) {
		this.destiBenifitId = destiBenifitId;
	}
	public boolean isByKeyword() {
		return byKeyword;
	}
	public void setByKeyword(boolean byKeyword) {
		this.byKeyword = byKeyword;
	}
	public boolean isByCategory() {
		return byCategory;
	}
	public void setByCategory(boolean byCategory) {
		this.byCategory = byCategory;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getSourceIds() {
		return sourceIds;
	}
	public void setSourceIds(String sourceIds) {
		this.sourceIds = sourceIds;
	}
	public String getLocationValues() {
		return locationValues;
	}
	public void setLocationValues(String locationValues) {
		this.locationValues = locationValues;
	}
	public boolean isSourceCandPartyWise() {
		return sourceCandPartyWise;
	}
	public void setSourceCandPartyWise(boolean sourceCandPartyWise) {
		this.sourceCandPartyWise = sourceCandPartyWise;
	}
	public boolean isDestCandPartyWise() {
		return destCandPartyWise;
	}
	public void setDestCandPartyWise(boolean destCandPartyWise) {
		this.destCandPartyWise = destCandPartyWise;
	}
	public boolean isLocationPresent() {
		return locationPresent;
	}
	public void setLocationPresent(boolean locationPresent) {
		this.locationPresent = locationPresent;
	}
	public boolean isSourcePresent() {
		return sourcePresent;
	}
	public void setSourcePresent(boolean sourcePresent) {
		this.sourcePresent = sourcePresent;
	}
	public Long getLocationLvl() {
		return locationLvl;
	}
	public void setLocationLvl(Long locationLvl) {
		this.locationLvl = locationLvl;
	}
	public boolean isSourceCand() {
		return sourceCand;
	}
	public void setSourceCand(boolean sourceCand) {
		this.sourceCand = sourceCand;
	}
	public boolean isDestiCand() {
		return destiCand;
	}
	public void setDestiCand(boolean destiCand) {
		this.destiCand = destiCand;
	}
	public boolean isSourceParty() {
		return sourceParty;
	}
	public void setSourceParty(boolean sourceParty) {
		this.sourceParty = sourceParty;
	}
	public boolean isDestiParty() {
		return destiParty;
	}
	public void setDestiParty(boolean destiParty) {
		this.destiParty = destiParty;
	}
	public boolean isBySourceCand() {
		return bySourceCand;
	}
	public void setBySourceCand(boolean bySourceCand) {
		this.bySourceCand = bySourceCand;
	}
	public boolean isByDestiCand() {
		return byDestiCand;
	}
	public void setByDestiCand(boolean byDestiCand) {
		this.byDestiCand = byDestiCand;
	}
	public String getGallaryKeyWordIds() {
		return gallaryKeyWordIds;
	}
	public void setGallaryKeyWordIds(String gallaryKeyWordIds) {
		this.gallaryKeyWordIds = gallaryKeyWordIds;
	}
	
	
}
