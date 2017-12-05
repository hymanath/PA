package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_designation")
public class PetitionDesignation {

	
	private Long petitionDesignationId;
	private String designationName;
	private String isDeleted;
	
	@Id
	@Column(name="petition_designation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionDesignationId() {
		return petitionDesignationId;
	}
	public void setPetitionDesignationId(Long petitionDesignationId) {
		this.petitionDesignationId = petitionDesignationId;
	}
	@Column(name="designation_name")
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}	
	
}
