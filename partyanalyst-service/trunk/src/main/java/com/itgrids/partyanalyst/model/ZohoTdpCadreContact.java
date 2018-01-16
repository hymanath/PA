package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "zoho_tdp_cadre_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZohoTdpCadreContact {
	
	private Long zohoTdpCadreContactId;
	private Long tdpCadreId;
	private String zohoContactId;
	private String isValid;
	
	private TdpCadre tdpCadre;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "zoho_tdp_cadre_contact_id", unique = true, nullable = false)
	public Long getZohoTdpCadreContactId() {
		return zohoTdpCadreContactId;
	}
	public void setZohoTdpCadreContactId(Long zohoTdpCadreContactId) {
		this.zohoTdpCadreContactId = zohoTdpCadreContactId;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "zoho_contact_id")
	public String getZohoContactId() {
		return zohoContactId;
	}
	public void setZohoContactId(String zohoContactId) {
		this.zohoContactId = zohoContactId;
	}
	
	@Column(name = "is_valid")
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	
	
	
	
	
	
	
	
	
}
