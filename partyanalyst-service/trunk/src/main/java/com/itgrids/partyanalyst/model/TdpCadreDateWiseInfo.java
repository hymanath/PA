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
@Table(name = "tdp_cadre_date_wise_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreDateWiseInfo extends BaseModel implements Serializable {
	
	private Long tdpCadreDateWiseInfoId;
	private Date surveyDate;
	private Long locationScopeId;
	private Long locationValue;
	private Long cadre2016;
	private String cadre2016Percent;
	private Long newCadre;
	private String newCadrePercent;
	private Long renewalCadre;
	private String renewalCadrePercent;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_date_wise_info_id", unique = true, nullable = false)
	public Long getTdpCadreDateWiseInfoId() {
		return tdpCadreDateWiseInfoId;
	}
	public void setTdpCadreDateWiseInfoId(Long tdpCadreDateWiseInfoId) {
		this.tdpCadreDateWiseInfoId = tdpCadreDateWiseInfoId;
	}
	
	@Column(name = "survey_date")
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
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
	@Column(name = "cadre_2016")
	public Long getCadre2016() {
		return cadre2016;
	}
	public void setCadre2016(Long cadre2016) {
		this.cadre2016 = cadre2016;
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
	

}
