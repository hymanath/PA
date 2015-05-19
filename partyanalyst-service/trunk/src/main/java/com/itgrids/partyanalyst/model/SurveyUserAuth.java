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
@Table(name="survey_user_auth" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyUserAuth {
	 
		private Long surveyUserAuthId;
		private EventSurveyUser	eventSurveyUser;
		private Long eventSurveyUserId;
		private String imeiNo;
		private String imeiNo2;
		private Date insertedTime;
		private String status;
		private String isDeleted;
		private User updatedBy;
		private Long updatedById;
		private String cause;
		private String userName;
		private String password;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="survey_user_auth_id")
		public Long getSurveyUserAuthId() {
			return surveyUserAuthId;
		}

		public void setSurveyUserAuthId(Long surveyUserAuthId) {
			this.surveyUserAuthId = surveyUserAuthId;
		}

		@ManyToOne(fetch = FetchType.LAZY )
		@JoinColumn(name = "event_survey_user_id" , insertable = false, updatable = false)
		@LazyToOne(LazyToOneOption.NO_PROXY)
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public EventSurveyUser getEventSurveyUser() {
			return eventSurveyUser;
		}

		public void setEventSurveyUser(EventSurveyUser eventSurveyUser) {
			this.eventSurveyUser = eventSurveyUser;
		}

		@Column(name="event_survey_user_id")
		public Long getEventSurveyUserId() {
			return eventSurveyUserId;
		}

		public void setEventSurveyUserId(Long eventSurveyUserId) {
			this.eventSurveyUserId = eventSurveyUserId;
		}
		
		@Column(name="imei_no")
		public String getImeiNo() {
			return imeiNo;
		}
		
		public void setImeiNo(String imeiNo) {
			this.imeiNo = imeiNo;
		}
		
		@Column(name="is_deleted")
		public String getIsDeleted() {
			return isDeleted;
		}
		
		public void setIsDeleted(String isDeleted) {
			this.isDeleted = isDeleted;
		}

		@Column(name="imei_no2")
		public String getImeiNo2() {
			return imeiNo2;
		}

		public void setImeiNo2(String imeiNo2) {
			this.imeiNo2 = imeiNo2;
		}

		@Column(name="inserted_time")
		public Date getInsertedTime() {
			return insertedTime;
		}

		public void setInsertedTime(Date insertedTime) {
			this.insertedTime = insertedTime;
		}

		@Column(name="status")
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@ManyToOne(fetch = FetchType.LAZY )
		@JoinColumn(name = "updatedby_id", insertable = false, updatable = false)
		@LazyToOne(LazyToOneOption.NO_PROXY)
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public User getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(User updatedBy) {
			this.updatedBy = updatedBy;
		}

		@Column(name="updatedby_id")
		public Long getUpdatedById() {
			return updatedById;
		}

		public void setUpdatedById(Long updatedById) {
			this.updatedById = updatedById;
		}

		@Column(name="cause")
		public String getCause() {
			return cause;
		}

		public void setCause(String cause) {
			this.cause = cause;
		}

		@Column(name="user_name")
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		@Column(name="password")
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		
}
