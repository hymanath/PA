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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "communication_media_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommunicationMediaQuestion extends BaseModel implements java.io.Serializable{
    
	private Long communicationMediaQuestionId;
	private String question;
	private Long mediaOptionTypeId;
	private Long communicationMediaTypeInfoId;
	private Long orderNo;
	private Long levelId;
	private Long scopeId;
	private Long scopeValue;
	private String isDeleted;
	
	private MediaOptionType mediaOptionType; 
	private CommunicationMediaTypeInfo communicationMediaTypeInfo ;
	private ActivityLevel activityLevel;
	private RegionScopes regionScopes;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "communication_media_question_id", unique = true, nullable = false)
	public Long getCommunicationMediaQuestionId() {
		return communicationMediaQuestionId;
	}
	public void setCommunicationMediaQuestionId(Long communicationMediaQuestionId) {
		this.communicationMediaQuestionId = communicationMediaQuestionId;
	}
	
	@Column(name = "question")
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Column(name = "media_option_type_id")
	public Long getMediaOptionTypeId() {
		return mediaOptionTypeId;
	}
	public void setMediaOptionTypeId(Long mediaOptionTypeId) {
		this.mediaOptionTypeId = mediaOptionTypeId;
	}
	
	@Column(name = "communication_media_type_info_id")
	public Long getCommunicationMediaTypeInfoId() {
		return communicationMediaTypeInfoId;
	}
	public void setCommunicationMediaTypeInfoId(Long communicationMediaTypeInfoId) {
		this.communicationMediaTypeInfoId = communicationMediaTypeInfoId;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "level_id")
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="media_option_type_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MediaOptionType getMediaOptionType() {
		return mediaOptionType;
	}
	public void setMediaOptionType(MediaOptionType mediaOptionType) {
		this.mediaOptionType = mediaOptionType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="communication_media_type_info_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommunicationMediaTypeInfo getCommunicationMediaTypeInfo() {
		return communicationMediaTypeInfo;
	}
	public void setCommunicationMediaTypeInfo(
			CommunicationMediaTypeInfo communicationMediaTypeInfo) {
		this.communicationMediaTypeInfo = communicationMediaTypeInfo;
	}
	
	@Column(name = "scope_id")
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="scope_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLevel getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(ActivityLevel activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	@Column(name = "scope_value")
	public Long getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}
	
}
