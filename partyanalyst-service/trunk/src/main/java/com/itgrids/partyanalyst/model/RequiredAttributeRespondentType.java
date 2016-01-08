package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
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
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "required_attribute_respondent_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RequiredAttributeRespondentType {
	
	private Long requiredAttributeRespondentTypeId;
	private Long requiredAttributeId;
	private Long respondentTypeId;
	private RequiredAttribute requiredAttribute;
	private RespondentType respondentType;
	
	public RequiredAttributeRespondentType(){
		
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "required_attribute_respondent_type_id", unique = true, nullable = false)
	public Long getRequiredAttributeRespondentTypeId() {
		return requiredAttributeRespondentTypeId;
	}
	public void setRequiredAttributeRespondentTypeId(
			Long requiredAttributeRespondentTypeId) {
		this.requiredAttributeRespondentTypeId = requiredAttributeRespondentTypeId;
	}
	
	@Column(name = "required_attribute_id")
	public Long getRequiredAttributeId() {
		return requiredAttributeId;
	}
	public void setRequiredAttributeId(Long requiredAttributeId) {
		this.requiredAttributeId = requiredAttributeId;
	}
	
	@Column(name = "respondent_type_id")
	public Long getRespondentTypeId() {
		return respondentTypeId;
	}
	public void setRespondentTypeId(Long respondentTypeId) {
		this.respondentTypeId = respondentTypeId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "required_attribute_id",insertable = false,updatable = false)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RequiredAttribute getRequiredAttribute() {
		return requiredAttribute;
	}
	public void setRequiredAttribute(RequiredAttribute requiredAttribute) {
		this.requiredAttribute = requiredAttribute;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "respondent_type_id",insertable = false,updatable = false)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RespondentType getRespondentType() {
		return respondentType;
	}
	public void setRespondentType(RespondentType respondentType) {
		this.respondentType = respondentType;
	}

}
