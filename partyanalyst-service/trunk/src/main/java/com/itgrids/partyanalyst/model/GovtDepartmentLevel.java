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
@Table(name = "govt_department_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentLevel extends BaseModel implements Serializable{

	private Long govtDepartmentLevelId;
	private String levelName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_level_id", unique = true, nullable = false)
	public Long getGovtDepartmentLevelId() {
		return govtDepartmentLevelId;
	}
	public void setGovtDepartmentLevelId(Long govtDepartmentLevelId) {
		this.govtDepartmentLevelId = govtDepartmentLevelId;
	}
	
	@Column(name = "level_name")
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
