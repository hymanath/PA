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
@Table(name = "cadre_txn_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class CadreTxnUser extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long cadreTxnUserId;	
	private CadreSurveyUser cadreSurveyUser;
	private Long cadreSurveyUserId;
	private String mobileNo;
	
	public CadreTxnUser(){
		
	}
	
	public CadreTxnUser(Long cadreTxnUserId,CadreSurveyUser cadreSurveyUser, String mobileNo,Long cadreSurveyUserId){
		super();
		this.cadreTxnUserId = cadreTxnUserId;
		this.cadreSurveyUser = cadreSurveyUser;
		this.cadreSurveyUserId = cadreSurveyUserId;
		this.mobileNo = mobileNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_txn_user_id", nullable = false, unique = true)
	public Long getCadreTxnUserId() {
		return cadreTxnUserId;
	}

	public void setCadreTxnUserId(Long cadreTxnUserId) {
		this.cadreTxnUserId = cadreTxnUserId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_survey_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}

	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}

	@Column(name = "cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}

	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}

	@Column(name = "mobile_no", length = 15)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
