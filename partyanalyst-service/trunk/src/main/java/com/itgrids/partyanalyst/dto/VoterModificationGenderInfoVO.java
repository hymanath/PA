package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class VoterModificationGenderInfoVO implements Serializable{

	private static final long serialVersionUID = -2275881848012595870L;
	
	private Long addedTotal = 0L;
	private Long addedMale = 0L;
	private Long addedFemale = 0L;
	private Long deletedTotal = 0L;
	private Long deletedMale = 0L;
	private Long deletedFemale = 0L;
	
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
