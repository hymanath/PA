package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "tdp_cadre_age_info_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreAgeInfoTemp extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -5266246292559597102L;
	
	private Long tdpCadreAgeInfoTempId;
	private Long ageRangeId;
	private Long locationScopeId;
	private Long locationValue;
	private Long cadre2014;
	private Long cadre2016;
	private Long newCadre;
	private String newCadrePercent;
	private Long renewalCadre;
	private String renewalCadrePercent;
	private Date insertedTime;
	private Long constituencyId;
    
    @Id
  	@GeneratedValue(strategy = GenerationType.AUTO)
  	@Column(name = "tdp_cadre_age_info_temp_id", unique = true, nullable = false)
	public Long getTdpCadreAgeInfoTempId() {
		return tdpCadreAgeInfoTempId;
	}
	public void setTdpCadreAgeInfoTempId(Long tdpCadreAgeInfoTempId) {
		this.tdpCadreAgeInfoTempId = tdpCadreAgeInfoTempId;
	}
	
	@Column(name = "age_range_id")
	public Long getAgeRangeId() {
		return ageRangeId;
	}
	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name = "cadre_2014")
	public Long getCadre2014() {
		return cadre2014;
	}
	public void setCadre2014(Long cadre2014) {
		this.cadre2014 = cadre2014;
	}
	
	@Column(name = "cadre_2016")
	public Long getCadre2016() {
		return cadre2016;
	}
	public void setCadre2016(Long cadre2016) {
		this.cadre2016 = cadre2016;
	}
	
	@Column(name = "new_cadre")
	public Long getNewCadre() {
		return newCadre;
	}
	public void setNewCadre(Long newCadre) {
		this.newCadre = newCadre;
	}
	
	@Column(name = "new_cadre_percent")
	public String getNewCadrePercent() {
		return newCadrePercent;
	}
	public void setNewCadrePercent(String newCadrePercent) {
		this.newCadrePercent = newCadrePercent;
	}
	
	@Column(name = "renewal_cadre")
	public Long getRenewalCadre() {
		return renewalCadre;
	}
	public void setRenewalCadre(Long renewalCadre) {
		this.renewalCadre = renewalCadre;
	}
	
	@Column(name = "renewal_cadre_percent")
	public String getRenewalCadrePercent() {
		return renewalCadrePercent;
	}
	public void setRenewalCadrePercent(String renewalCadrePercent) {
		this.renewalCadrePercent = renewalCadrePercent;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
}
