package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "access_level")
public class AccessLevel extends BaseModel implements Serializable{
	
	private Long accessLevelId;
	private String accessLevel;
	
	@Id
	@Column(name="access_level_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getAccessLevelId() {
		return accessLevelId;
	}
	public void setAccessLevelId(Long accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
	
	@Column(name="access_level")
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
}
