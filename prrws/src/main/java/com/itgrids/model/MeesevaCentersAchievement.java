package com.itgrids.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meeseva_centers_achievement")
public class MeesevaCentersAchievement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long meesevaCentersAchievementId;
	private String monthName;
	private Date fromDate;
	private Date toDate;
	private Long target;
	private Long achievement;
	private String isDeleted;
	
	@Id
	@Column(name="meeseva_centers_achievement_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMeesevaCentersAchievementId() {
		return meesevaCentersAchievementId;
	}
	public void setMeesevaCentersAchievementId(Long meesevaCentersAchievementId) {
		this.meesevaCentersAchievementId = meesevaCentersAchievementId;
	}
	
	@Column(name="month_name")
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Column(name="target")
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	
	@Column(name="achievement")
	public Long getAchievement() {
		return achievement;
	}
	public void setAchievement(Long achievement) {
		this.achievement = achievement;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
