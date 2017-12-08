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
@Table(name = "Pm_representee_designation")
public class PmRepresenteeDesignation {
	
		private Long PmRepresenteeDesignationId;
		private Long pmRepresenteeId;
		private Long pmDesignationId;
		private String isActive;
		private Date startDate;
		private Date endDate;
		
		private PmRepresentee pmRepresentee;
		private PmDesignation pmDesignation;
		
		@Id
		@Column(name="Pm_representee_designation_id")
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
}
