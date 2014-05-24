package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "cadre_party_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadrePartyDesignation extends BaseModel implements Serializable {
	
	private Long cadrePartyDesignationId;
	private Cadre cadre;
	private PartyDesignation partyDesignation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_party_designation_id", unique = true, nullable = false)
	public Long getCadrePartyDesignationId() {
		return cadrePartyDesignationId;
	}
	public void setCadrePartyDesignationId(Long cadrePartyDesignationId) {
		this.cadrePartyDesignationId = cadrePartyDesignationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Cadre getCadre() {
		return cadre;
	}
	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "party_designation_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyDesignation getPartyDesignation() {
		return partyDesignation;
	}
	public void setPartyDesignation(PartyDesignation partyDesignation) {
		this.partyDesignation = partyDesignation;
	}


}
