package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "scheme_phase")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Schemephase extends BaseModel implements Serializable{
	
	private Long schemePhaseId;
	private String schemePhase;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "scheme_phase_id", unique = true, nullable = false)
	public Long getSchemePhaseId() {
		return schemePhaseId;
	}
	public void setSchemePhaseId(Long schemePhaseId) {
		this.schemePhaseId = schemePhaseId;
	}
	
	@Column(name = "scheme_phase")
	public String getSchemePhase() {
		return schemePhase;
	}
	public void setSchemePhase(String schemePhase) {
		this.schemePhase = schemePhase;
	}
	
	

}
