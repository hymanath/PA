package com.itgrids.partyanalyst.model;

import java.util.Date;

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


/**
 * 
 * @author Srishailam Pittala
 *
 */


@Entity
@Table(name = "tab_user_login_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabUserLoginDetails implements java.io.Serializable{

	private Long tabUserLoginDetailsId;
	private CadreSurveyUser cadreSurveyUser;
	private Long cadreSurveyUserId;
	private String userName;
	private String mobileNo;
	private String tabIMEINo;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tab_user_login_details_id", unique = true, nullable = false)
	public Long getTabUserLoginDetailsId() {
		return tabUserLoginDetailsId;
	}
	public void setTabUserLoginDetailsId(Long tabUserLoginDetailsId) {
		this.tabUserLoginDetailsId = tabUserLoginDetailsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_survey_userId" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}
	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}
	
	@Column(name="cadre_survey_userId")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name="userName")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="mobileNo")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="tab_imei_no")
	public String getTabIMEINo() {
		return tabIMEINo;
	}
	public void setTabIMEINo(String tabIMEINo) {
		this.tabIMEINo = tabIMEINo;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
}