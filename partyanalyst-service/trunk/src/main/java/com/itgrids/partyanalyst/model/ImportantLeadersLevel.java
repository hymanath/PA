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
@Table(name = "important_leaders_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImportantLeadersLevel extends BaseModel implements Serializable{

	private Long importantLeadersLevelId;
	private String scope;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "important_leaders_level_id", unique = true, nullable = false)
	public Long getImportantLeadersLevelId() {
		return importantLeadersLevelId;
	}
	public void setImportantLeadersLevelId(Long importantLeadersLevelId) {
		this.importantLeadersLevelId = importantLeadersLevelId;
	}
	
	@Column(name="scope")
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
