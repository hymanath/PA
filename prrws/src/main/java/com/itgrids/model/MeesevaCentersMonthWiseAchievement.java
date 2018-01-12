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
@Table(name = "meeseva_centers_month_wise_achievement")
public class MeesevaCentersMonthWiseAchievement extends BaseModel implements Serializable{
	
	private Long meesevaCentersMonthWiseAchievementId;
	private Date date;
	private Long target;
	private Long achievement;
	private String isDeleted;
	
	@Id
	@Column(name="meeseva_centers_month_wise_achievement_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMeesevaCentersMonthWiseAchievementId() {
		return meesevaCentersMonthWiseAchievementId;
	}
	public void setMeesevaCentersMonthWiseAchievementId(Long meesevaCentersMonthWiseAchievementId) {
		this.meesevaCentersMonthWiseAchievementId = meesevaCentersMonthWiseAchievementId;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
