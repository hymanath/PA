package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "govt_scheme_phase")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtSchemePhase extends BaseModel implements Serializable {
	
	private Long govtSchemePhaseId;
	private Long govtSchemeId;
	private Long schemePhaseId;
	
	private Schemephase schemephase;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_scheme_phase_id", unique = true, nullable = false)
	public Long getGovtSchemePhaseId() {
		return govtSchemePhaseId;
	}
	public void setGovtSchemePhaseId(Long govtSchemePhaseId) {
		this.govtSchemePhaseId = govtSchemePhaseId;
	}
	

	@Column(name = "govt_scheme_id")
	public Long getGovtSchemeId() {
		return govtSchemeId;
	}
	public void setGovtSchemeId(Long govtSchemeId) {
		this.govtSchemeId = govtSchemeId;
	}

	@Column(name = "scheme_phase_id")
	public Long getSchemePhaseId() {
		return schemePhaseId;
	}
	public void setSchemePhaseId(Long schemePhaseId) {
		this.schemePhaseId = schemePhaseId;
	}
 
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="scheme_phase_id",insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Schemephase getSchemephase() {
		return schemephase;
	}
	public void setSchemephase(Schemephase schemephase) {
		this.schemephase = schemephase;
	}
	
	

}
