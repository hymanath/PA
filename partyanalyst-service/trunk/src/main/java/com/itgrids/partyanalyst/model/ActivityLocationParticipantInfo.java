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
@Table(name="activity_location_participant_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityLocationParticipantInfo {
	
	private Long activityLocationParticipantInfoId;
	private ActivityScope activityScope;
	private Long locationLevel;
	private Long locationValue;
	private Long totalMembers;
	private Long totalCovered;
	private Double totalCoveredPerc;
	private Long todayCovered;
	private Double todayCoveredPerc;
	private Long totalRegistration;
	private Long todayRegistration;
	private Long totalLoanAapplied;
	private Long todayLoanApplied;
	private Date insertTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_location_participant_info_id", unique = true, nullable = false)
	public Long getActivityLocationParticipantInfoId() {
		return activityLocationParticipantInfoId;
	}
	public void setActivityLocationParticipantInfoId(
			Long activityLocationParticipantInfoId) {
		this.activityLocationParticipantInfoId = activityLocationParticipantInfoId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "activity_scope_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityScope getActivityScope() {
		return activityScope;
	}
	public void setActivityScope(ActivityScope activityScope) {
		this.activityScope = activityScope;
	}
	
	@Column(name="location_level")
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	
	@Column(name = "location_value" )
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name="total_members")
	public Long getTotalMembers() {
		return totalMembers;
	}
	
	public void setTotalMembers(Long totalMembers) {
		this.totalMembers = totalMembers;
	}
	
	@Column(name="total_covered")
	public Long getTotalCovered() {
		return totalCovered;
	}
	public void setTotalCovered(Long totalCovered) {
		this.totalCovered = totalCovered;
	}
	
	@Column(name="total_covered_perc")
	public Double getTotalCoveredPerc() {
		return totalCoveredPerc;
	}
	public void setTotalCoveredPerc(Double totalCoveredPerc) {
		this.totalCoveredPerc = totalCoveredPerc;
	}
	
	@Column(name="today_covered")
	public Long getTodayCovered() {
		return todayCovered;
	}
	public void setTodayCovered(Long todayCovered) {
		this.todayCovered = todayCovered;
	}
	
	@Column(name="today_covered_perc")
	public Double getTodayCoveredPerc() {
		return todayCoveredPerc;
	}
	public void setTodayCoveredPerc(Double todayCoveredPerc) {
		this.todayCoveredPerc = todayCoveredPerc;
	}
	
	@Column(name="total_registration")
	public Long getTotalRegistration() {
		return totalRegistration;
	}
	public void setTotalRegistration(Long totalRegistration) {
		this.totalRegistration = totalRegistration;
	}
	
	@Column(name="today_registration")
	public Long getTodayRegistration() {
		return todayRegistration;
	}
	public void setTodayRegistration(Long todayRegistration) {
		this.todayRegistration = todayRegistration;
	}
	
	@Column(name="total_loan_applied")
	public Long getTotalLoanAapplied() {
		return totalLoanAapplied;
	}
	public void setTotalLoanAapplied(Long totalLoanAapplied) {
		this.totalLoanAapplied = totalLoanAapplied;
	}
	
	@Column(name="today_loan_applied")
	public Long getTodayLoanApplied() {
		return todayLoanApplied;
	}
	public void setTodayLoanApplied(Long todayLoanApplied) {
		this.todayLoanApplied = todayLoanApplied;
	}
	
	@Column(name="insert_time")
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	

}
