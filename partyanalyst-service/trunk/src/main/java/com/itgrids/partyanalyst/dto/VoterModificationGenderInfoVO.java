package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class VoterModificationGenderInfoVO implements Serializable{

	private static final long serialVersionUID = -2275881848012595870L;
	
	private String previousPublicationName;
	private String publicationName;
	private Long previousPublicationId;
	private Long publicationId;
	private Long addedTotal = 0L;
	private Long addedMale = 0L;
	private Long addedFemale = 0L;
	private Long deletedTotal = 0L;
	private Long deletedMale = 0L;
	private Long deletedFemale = 0L;
	private boolean dataPresent;	
	private Long reportLevelValue;
	
	private Long movedTotal = 0L;
	private Long movedMale = 0L;
	private Long movedFemale = 0L;
	private Long relocatedTotal = 0L;
	private Long relocatedMale = 0L;
	private Long relocatedFemale = 0L;
	
	public String getPreviousPublicationName() {
		return previousPublicationName;
	}
	public void setPreviousPublicationName(String previousPublicationName) {
		this.previousPublicationName = previousPublicationName;
	}
	public String getPublicationName() {
		return publicationName;
	}
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}
	
	public Long getPreviousPublicationId() {
		return previousPublicationId;
	}
	public void setPreviousPublicationId(Long previousPublicationId) {
		this.previousPublicationId = previousPublicationId;
	}
	public Long getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}
	public Long getAddedTotal() {
		return addedTotal;
	}
	public void setAddedTotal(Long addedTotal) {
		this.addedTotal = addedTotal;
	}
	public Long getAddedMale() {
		return addedMale;
	}
	public void setAddedMale(Long addedMale) {
		this.addedMale = addedMale;
	}
	public Long getAddedFemale() {
		return addedFemale;
	}
	public void setAddedFemale(Long addedFemale) {
		this.addedFemale = addedFemale;
	}
	public Long getDeletedTotal() {
		return deletedTotal;
	}
	public void setDeletedTotal(Long deletedTotal) {
		this.deletedTotal = deletedTotal;
	}
	public Long getDeletedMale() {
		return deletedMale;
	}
	public void setDeletedMale(Long deletedMale) {
		this.deletedMale = deletedMale;
	}
	public Long getDeletedFemale() {
		return deletedFemale;
	}
	public void setDeletedFemale(Long deletedFemale) {
		this.deletedFemale = deletedFemale;
	}
	public boolean isDataPresent() {
		return dataPresent;
	}
	public void setDataPresent(boolean dataPresent) {
		this.dataPresent = dataPresent;
	}
	
	public Long getReportLevelValue() {
		return reportLevelValue;
	}
	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}
	public Long getMovedTotal() {
		return movedTotal;
	}
	public void setMovedTotal(Long movedTotal) {
		this.movedTotal = movedTotal;
	}
	public Long getMovedMale() {
		return movedMale;
	}
	public void setMovedMale(Long movedMale) {
		this.movedMale = movedMale;
	}
	public Long getMovedFemale() {
		return movedFemale;
	}
	public void setMovedFemale(Long movedFemale) {
		this.movedFemale = movedFemale;
	}
	public Long getRelocatedTotal() {
		return relocatedTotal;
	}
	public void setRelocatedTotal(Long relocatedTotal) {
		this.relocatedTotal = relocatedTotal;
	}
	public Long getRelocatedMale() {
		return relocatedMale;
	}
	public void setRelocatedMale(Long relocatedMale) {
		this.relocatedMale = relocatedMale;
	}
	public Long getRelocatedFemale() {
		return relocatedFemale;
	}
	public void setRelocatedFemale(Long relocatedFemale) {
		this.relocatedFemale = relocatedFemale;
	}
	
}
