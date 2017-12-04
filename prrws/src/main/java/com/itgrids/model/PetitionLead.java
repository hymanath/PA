package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_lead")
public class PetitionLead {

	private Long petitionLeadId;
	private String lead ;
	
	@Id
	@Column(name="petition_lead_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionLeadId() {
		return petitionLeadId;
	}
	public void setPetitionLeadId(Long petitionLeadId) {
		this.petitionLeadId = petitionLeadId;
	}
	@Column(name="lead")
	public String getLead() {
		return lead;
	}
	public void setLead(String lead) {
		this.lead = lead;
	}
	
	

}
