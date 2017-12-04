package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_brief_lead")
public class PetitionBriefLead {

	private Long petitionBriefLeadId;
	private String breifLead ;
	
	@Id
	@Column(name="petition_brief_lead_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionBriefLeadId() {
		return petitionBriefLeadId;
	}
	public void setPetitionBriefLeadId(Long petitionBriefLeadId) {
		this.petitionBriefLeadId = petitionBriefLeadId;
	}
	@Column(name="breif_lead")
	public String getBreifLead() {
		return breifLead;
	}
	public void setBreifLead(String breifLead) {
		this.breifLead = breifLead;
	}
	
	
	
}
