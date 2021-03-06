package com.itgrids.model;

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

@Entity
@Table(name = "pm_representee_designation")
public class PmRepresenteeDesignation {
	
		private Long PmRepresenteeDesignationId;
		private Long pmRepresenteeId;
		private Long pmDesignationId;
		private String isActive;
		private String isDeleted;
		private Date startDate;
		private Date endDate;
		
		private PmRepresentee pmRepresentee;
		private PmDesignation pmDesignation;
		
		private User insertedUser;
		private User updatedUser;
		private Date insertedTime;
		private Date updatedTime;
		
		@Id
		@Column(name="pm_representee_designation_id")
		@GeneratedValue(strategy= GenerationType.AUTO)
		public Long getPmRepresenteeDesignationId() {
			return PmRepresenteeDesignationId;
		}
		public void setPmRepresenteeId(Long pmRepresenteeId) {
			this.pmRepresenteeId = pmRepresenteeId;
		}
		@Column(name="pm_representee_id")
		public Long getPmRepresenteeId() {
			return pmRepresenteeId;
		}
		public void setPmRepresenteeDesignationId(Long pmRepresenteeDesignationId) {
			PmRepresenteeDesignationId = pmRepresenteeDesignationId;
		}
		@Column(name="pm_designation_id")
		public Long getPmDesignationId() {
			return pmDesignationId;
		}
		public void setPmDesignationId(Long pmDesignationId) {
			this.pmDesignationId = pmDesignationId;
		}
		@Column(name="is_active")
		public String getIsActive() {
			return isActive;
		}
		public void setIsActive(String isActive) {
			this.isActive = isActive;
		}
		@Column(name="start_date")
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		@Column(name="end_Date")
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name = "pm_representee_id", insertable = false, updatable = false)
		public PmRepresentee getPmRepresentee() {
			return pmRepresentee;
		}
		public void setPmRepresentee(PmRepresentee pmRepresentee) {
			this.pmRepresentee = pmRepresentee;
		}
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name = "pm_designation_id", insertable = false, updatable = false)
		public PmDesignation getPmDesignation() {
			return pmDesignation;
		}
		public void setPmDesignation(PmDesignation pmDesignation) {
			this.pmDesignation = pmDesignation;
		}
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
		public User getInsertedUser() {
			return insertedUser;
		}
		public void setInsertedUser(User insertedUser) {
			this.insertedUser = insertedUser;
		}
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
		public User getUpdatedUser() {
			return updatedUser;
		}
		public void setUpdatedUser(User updatedUser) {
			this.updatedUser = updatedUser;
		}
		@Column(name="inserted_time")
		public Date getInsertedTime() {
			return insertedTime;
		}
		public void setInsertedTime(Date insertedTime) {
			this.insertedTime = insertedTime;
		}
		@Column(name="updated_time")
		public Date getUpdatedTime() {
			return updatedTime;
		}
		public void setUpdatedTime(Date updatedTime) {
			this.updatedTime = updatedTime;
		}
		
		@Column(name="is_deleted")
		public String getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(String isDeleted) {
			this.isDeleted = isDeleted;
		}
		
}
