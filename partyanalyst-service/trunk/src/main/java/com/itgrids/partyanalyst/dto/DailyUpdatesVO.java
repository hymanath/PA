package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DailyUpdatesVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3645474642105854684L;
	boolean isPartySelected;
	boolean isCandidateSelected;
	boolean isSpecialPageSelected;
	boolean all;
	List<Long> partyIdsList;
	List<Long> candidateIdsList;
	List<Long> specialPageIdsList;
	Date from;
	Date to;
	public boolean isPartySelected() {
		return isPartySelected;
	}
	public void setPartySelected(boolean isPartySelected) {
		this.isPartySelected = isPartySelected;
	}
	public boolean isCandidateSelected() {
		return isCandidateSelected;
	}
	public void setCandidateSelected(boolean isCandidateSelected) {
		this.isCandidateSelected = isCandidateSelected;
	}
	public boolean isSpecialPageSelected() {
		return isSpecialPageSelected;
	}
	public void setSpecialPageSelected(boolean isSpecialPageSelected) {
		this.isSpecialPageSelected = isSpecialPageSelected;
	}
	public boolean isAll() {
		return all;
	}
	public void setAll(boolean all) {
		this.all = all;
	}
	public List<Long> getPartyIdsList() {
		return partyIdsList;
	}
	public void setPartyIdsList(List<Long> partyIdsList) {
		this.partyIdsList = partyIdsList;
	}
	public List<Long> getCandidateIdsList() {
		return candidateIdsList;
	}
	public void setCandidateIdsList(List<Long> candidateIdsList) {
		this.candidateIdsList = candidateIdsList;
	}
	public List<Long> getSpecialPageIdsList() {
		return specialPageIdsList;
	}
	public void setSpecialPageIdsList(List<Long> specialPageIdsList) {
		this.specialPageIdsList = specialPageIdsList;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	
	
}
