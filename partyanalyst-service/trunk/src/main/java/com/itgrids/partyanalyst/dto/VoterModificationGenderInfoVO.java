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
	
}
