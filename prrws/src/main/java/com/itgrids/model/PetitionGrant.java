package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_grant")
public class PetitionGrant {

	private Long petitionGrantId;
	private String grantUnder;
	
	@Id
	@Column(name="petition_grant_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionGrantId() {
		return petitionGrantId;
	}
	public void setPetitionGrantId(Long petitionGrantId) {
		this.petitionGrantId = petitionGrantId;
	}
	@Column(name="grant_under")
	public String getGrantUnder() {
		return grantUnder;
	}
	public void setGrantUnder(String grantUnder) {
		this.grantUnder = grantUnder;
	}
	
	
}
