package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tdp_cadre_location_info_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreLocationInfoTemp extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -2782866186314316187L;
	
	private Long tdpCadreLocationInfoTempId;
	private Long locationScopeId;
	private Long locationValue;
	private Long cadre2014;
	private Long cadre2016;
	private String cadre2014Percent;
	private String cadre2016Percent;
	private Long  newCadre;
	private Long renewalCadre;
	private String newCadrePercent;
	private String renewalCadrePercent;
	private String type;
	private Date   insertedTime;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_location_info_temp_id", unique = true, nullable = false)
	public Long getTdpCadreLocationInfoTempId() {
		return tdpCadreLocationInfoTempId;
	}

	public void setTdpCadreLocationInfoTempId(Long tdpCadreLocationInfoTempId) {
		this.tdpCadreLocationInfoTempId = tdpCadreLocationInfoTempId;
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
	@Column(name = "cadre_2014_percent")
	public String getCadre2014Percent() {
		return cadre2014Percent;
	}

	public void setCadre2014Percent(String cadre2014Percent) {
		this.cadre2014Percent = cadre2014Percent;
	}
	@Column(name = "cadre_2016_percent")
	public String getCadre2016Percent() {
		return cadre2016Percent;
	}

	public void setCadre2016Percent(String cadre2016Percent) {
		this.cadre2016Percent = cadre2016Percent;
	}

	@Column(name = "new_cadre")
	public Long getNewCadre() {
		return newCadre;
	}

	public void setNewCadre(Long newCadre) {
		this.newCadre = newCadre;
	}
	@Column(name = "renewal_cadre")
	public Long getRenewalCadre() {
		return renewalCadre;
	}

	public void setRenewalCadre(Long renewalCadre) {
		this.renewalCadre = renewalCadre;
	}
	@Column(name = "new_cadre_percent")
	public String getNewCadrePercent() {
		return newCadrePercent;
	}

	public void setNewCadrePercent(String newCadrePercent) {
		this.newCadrePercent = newCadrePercent;
	}
	@Column(name = "renewal_cadre_percent")
	public String getRenewalCadrePercent() {
		return renewalCadrePercent;
	}

	public void setRenewalCadrePercent(String renewalCadrePercent) {
		this.renewalCadrePercent = renewalCadrePercent;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
}
