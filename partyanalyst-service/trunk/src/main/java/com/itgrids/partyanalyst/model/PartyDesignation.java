package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "party_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyDesignation extends BaseModel implements Serializable {
	
	private Long partyDesignationId;
	private String partyDesignationName;
	 private Set<CadrePartyDesignation> cadrePartyDesignation = new HashSet<CadrePartyDesignation>(0);
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_designation_id", unique = true, nullable = false)
	public Long getPartyDesignationId() {
		return partyDesignationId;
	}
	public void setPartyDesignationId(Long partyDesignationId) {
		this.partyDesignationId = partyDesignationId;
	}
	@Column(name = "party_designation_name")
	public String getPartyDesignationName() {
		return partyDesignationName;
	}
	public void setPartyDesignationName(String partyDesignationName) {
		this.partyDesignationName = partyDesignationName;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "partyDesignation")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadrePartyDesignation> getCadrePartyDesignation() {
		return cadrePartyDesignation;
	}
	public void setCadrePartyDesignation(
			Set<CadrePartyDesignation> cadrePartyDesignation) {
		this.cadrePartyDesignation = cadrePartyDesignation;
	}
	
	
	

}
