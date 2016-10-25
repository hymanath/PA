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
@Table(name = "cadre_reg_user_tab_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegUserTabUser extends BaseModel implements Serializable{

	private Long cadreRegUserTabUserId;
	private Long cadreRegUserId;
	private Long cadreSurveyUserId;
	private String isDeleted;
	
	private CadreRegUser cadreRegUser;
	private CadreSurveyUser cadreSurveyUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_user_tab_user_id", unique = true, nullable = false)
	public Long getCadreRegUserTabUserId() {
		return cadreRegUserTabUserId;
	}
	public void setCadreRegUserTabUserId(Long cadreRegUserTabUserId) {
		this.cadreRegUserTabUserId = cadreRegUserTabUserId;
	}
	
	@Column(name="cadre_reg_user_id")
	public Long getCadreRegUserId() {
		return cadreRegUserId;
	}
	public void setCadreRegUserId(Long cadreRegUserId) {
		this.cadreRegUserId = cadreRegUserId;
	}
	
	@Column(name="cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_reg_user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegUser getCadreRegUser() {
		return cadreRegUser;
	}
	public void setCadreRegUser(CadreRegUser cadreRegUser) {
		this.cadreRegUser = cadreRegUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_survey_user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}
	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}
}
