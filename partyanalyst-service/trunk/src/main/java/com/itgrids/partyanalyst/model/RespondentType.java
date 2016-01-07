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
@Table(name = "respondent_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RespondentType extends BaseModel implements Serializable{
	
	private Long 		respondentTypeId;
	private String 		respondentType;
	private String		isActive;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "respondent_type_id", unique = true, nullable = false)
	public Long getRespondentTypeId() {
		return respondentTypeId;
	}
	public void setRespondentTypeId(Long respondentTypeId) {
		this.respondentTypeId = respondentTypeId;
	}
	
	@Column(name="respondent_type")
	public String getRespondentType() {
		return respondentType;
	}
	public void setRespondentType(String respondentType) {
		this.respondentType = respondentType;
	}
	
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
