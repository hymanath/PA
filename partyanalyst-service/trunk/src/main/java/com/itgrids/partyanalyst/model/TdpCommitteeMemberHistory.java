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
@Table(name = "tdp_committee_member_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeMemberHistory {
	private Long tdpCommitteeMemberHistoryId;
	private Long tdpCommitteeMemberId;
	private Long tdpCommitteeRoleId;
	private Long tdpCadreId;
	private Date startDate;
	private Date endDate;
	private String isActive;
	private Long tdpCommitteeEnrollmentId;	
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private Date historyInsertedTime; // moved into history table
	private Long userId; // moved userId to history table
	
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	@Column(name = "history_inserted_time")
	public Date getHistoryInsertedTime() {
		return historyInsertedTime;
	}

	public void setHistoryInsertedTime(Date historyInsertedTime) {
		this.historyInsertedTime = historyInsertedTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_member_history_id", unique = true, nullable = false)
	public Long getTdpCommitteeMemberHistoryId() {
		return tdpCommitteeMemberHistoryId;
	}

	public void setTdpCommitteeMemberHistoryId(Long tdpCommitteeMemberHistoryId) {
		this.tdpCommitteeMemberHistoryId = tdpCommitteeMemberHistoryId;
	}

	@Column(name = "tdp_committee_member_id")
	public Long getTdpCommitteeMemberId() {
		return tdpCommitteeMemberId;
	}

	public void setTdpCommitteeMemberId(Long tdpCommitteeMemberId) {
		this.tdpCommitteeMemberId = tdpCommitteeMemberId;
	}

	@Column(name = "tdp_committee_role_id")
	public Long getTdpCommitteeRoleId() {
		return tdpCommitteeRoleId;
	}
	
	public void setTdpCommitteeRoleId(Long tdpCommitteeRoleId) {
		this.tdpCommitteeRoleId = tdpCommitteeRoleId;
	}
	
		
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Column(name = "tdp_committee_enrollment_id")
	public Long getTdpCommitteeEnrollmentId() {
		return tdpCommitteeEnrollmentId;
	}
	
	public void setTdpCommitteeEnrollmentId(Long tdpCommitteeEnrollmentId) {
		this.tdpCommitteeEnrollmentId = tdpCommitteeEnrollmentId;
	}
		
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}

	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}
