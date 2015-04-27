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
@Entity
@Table(name = "event_survey_user_login_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventSurveyUserLoginDetails {
	
	private Long eventSurveyUserLoginDetailsId;
	private String imei;
	private String simno;
	private Date loginTime;
	private EventSurveyUser eventSurveyUser;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_survey_user_login_details_id", unique = true, nullable = false)
	public Long getEventSurveyUserLoginDetailsId() {
		return eventSurveyUserLoginDetailsId;
	}
	public void setEventSurveyUserLoginDetailsId(Long eventSurveyUserLoginDetailsId) {
		this.eventSurveyUserLoginDetailsId = eventSurveyUserLoginDetailsId;
	}
	@Column(name = "imei")
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	@Column(name = "simno")
	public String getSimno() {
		return simno;
	}
	public void setSimno(String simno) {
		this.simno = simno;
	}
	@Column(name = "login_time")
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "event_survey_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EventSurveyUser getEventSurveyUser() {
		return eventSurveyUser;
	}
	public void setEventSurveyUser(EventSurveyUser eventSurveyUser) {
		this.eventSurveyUser = eventSurveyUser;
	}
	
	
	

}
