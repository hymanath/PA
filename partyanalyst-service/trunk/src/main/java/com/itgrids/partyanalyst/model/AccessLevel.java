package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="access_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccessLevel extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long accessLevelId;
	private String accessLevel;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="access_level_id", unique=true, nullable=false)
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
