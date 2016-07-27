package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "user_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLevel extends BaseModel{
	
	private Long   userLevelId;
	private String level;
	
	public UserLevel() {}
	
	public UserLevel(Long userLevelId, String level) {
		this.userLevelId = userLevelId;
		this.level = level;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_level_id", unique = true, nullable = false)
	public Long getUserLevelId() {
		return userLevelId;
	}
	public void setUserLevelId(Long userLevelId) {
		this.userLevelId = userLevelId;
	}
	
	@Column(name = "level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
