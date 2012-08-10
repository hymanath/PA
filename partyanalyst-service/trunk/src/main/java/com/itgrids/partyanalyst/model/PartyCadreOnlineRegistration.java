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
@Table(name="party_cadre_online_registration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyCadreOnlineRegistration extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4997930164496187337L;
	
	private Long partyCadreOnlineRegistrationId;
	private Party party;
	private CadreOnlineRegistration cadreOnlineRegistration;
	
	public PartyCadreOnlineRegistration(){};
	public PartyCadreOnlineRegistration(Party party,CadreOnlineRegistration cadreOnlineRegistration){
		this.party = party;
		this.cadreOnlineRegistration = cadreOnlineRegistration;
	};
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_cadre_online_registration_id",unique=true, nullable=false)
	public Long getPartyCadreOnlineRegistrationId() {
		return partyCadreOnlineRegistrationId;
	}
	public void setPartyCadreOnlineRegistrationId(
			Long partyCadreOnlineRegistrationId) {
		this.partyCadreOnlineRegistrationId = partyCadreOnlineRegistrationId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "cadre_online_registration_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreOnlineRegistration getCadreOnlineRegistration() {
		return cadreOnlineRegistration;
	}
	public void setCadreOnlineRegistration(
			CadreOnlineRegistration cadreOnlineRegistration) {
		this.cadreOnlineRegistration = cadreOnlineRegistration;
	}
	

}
