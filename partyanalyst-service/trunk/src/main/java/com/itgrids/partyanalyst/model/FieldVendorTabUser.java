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
@Table(name = "field_vendor_tab_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FieldVendorTabUser extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -3664248889329797650L;
	
	private Long fieldVendorTabUserId;
	private Long fieldVendorId;
	private Long cadreSurveyUserId;
	private String isDeleted;
	
	private FieldVendor fieldVendor;
	private CadreSurveyUser cadreSurveyUser;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "field_vendor_tab_user_id", unique = true, nullable = false)
	public Long getFieldVendorTabUserId() {
		return fieldVendorTabUserId;
	}
	public void setFieldVendorTabUserId(Long fieldVendorTabUserId) {
		this.fieldVendorTabUserId = fieldVendorTabUserId;
	}
	
	@Column(name="field_vendor_id")
	public Long getFieldVendorId() {
		return fieldVendorId;
	}
	public void setFieldVendorId(Long fieldVendorId) {
		this.fieldVendorId = fieldVendorId;
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
	@JoinColumn(name="field_vendor_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FieldVendor getFieldVendor() {
		return fieldVendor;
	}
	public void setFieldVendor(FieldVendor fieldVendor) {
		this.fieldVendor = fieldVendor;
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
