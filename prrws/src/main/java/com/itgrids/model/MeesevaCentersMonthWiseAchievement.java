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
	private Long ruralAchievement;
	private Long urbanAchievement;
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
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="rural_achievement")
	public Long getRuralAchievement() {
		return ruralAchievement;
	}
	public void setRuralAchievement(Long ruralAchievement) {
		this.ruralAchievement = ruralAchievement;
	}
	
	@Column(name="urban_achievement")
	public Long getUrbanAchievement() {
		return urbanAchievement;
	}
	public void setUrbanAchievement(Long urbanAchievement) {
		this.urbanAchievement = urbanAchievement;
	}
}
