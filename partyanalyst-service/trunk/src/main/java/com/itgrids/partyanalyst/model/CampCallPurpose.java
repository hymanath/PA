package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "camp_call_purpose")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CampCallPurpose {
	private Long campCallPurpose;
	private String purpose;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "camp_call_purpose", unique = true, nullable = false)
	public Long getCampCallPurpose() {
		return campCallPurpose;
	}
	public void setCampCallPurpose(Long campCallPurpose) {
		this.campCallPurpose = campCallPurpose;
	}
	@Column(name = "purpose")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	

}
