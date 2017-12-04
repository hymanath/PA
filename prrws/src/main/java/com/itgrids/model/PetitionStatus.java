package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_status")
public class PetitionStatus {

	private Long petitionStatusId;
	private String description;
	
	@Id
	@Column(name="petition_status_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionStatusId() {
		return petitionStatusId;
	}
	public void setPetitionStatusId(Long petitionStatusId) {
		this.petitionStatusId = petitionStatusId;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
