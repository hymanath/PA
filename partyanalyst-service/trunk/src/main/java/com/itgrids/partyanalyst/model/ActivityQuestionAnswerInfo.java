package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "activity_question_answer_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityQuestionAnswerInfo {
	
	private Long activityQuestionAnswerInfoId;
	private Long activityScopeId;
	private Long activityQuestionnaireId;
	private Long regionScopesId;
	private Long constituencyId;
	private UserAddress  address;
	private Long count;
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_question_answer_info_id", unique = true, nullable = false)
	public Long getActivityQuestionAnswerInfoId() {
		return activityQuestionAnswerInfoId;
	}

	public void setActivityQuestionAnswerInfoId(Long activityQuestionAnswerInfoId) {
		this.activityQuestionAnswerInfoId = activityQuestionAnswerInfoId;
	}

	@Column(name="activity_scope_id")
	public Long getActivityScopeId() {
		return activityScopeId;
	}

	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}

	@Column(name="activity_questionnaire_id")
	public Long getActivityQuestionnaireId() {
		return activityQuestionnaireId;
	}

	public void setActivityQuestionnaireId(Long activityQuestionnaireId) {
		this.activityQuestionnaireId = activityQuestionnaireId;
	}

	@Column(name="region_scopes_id")
	public Long getRegionScopesId() {
		return regionScopesId;
	}

	public void setRegionScopesId(Long regionScopesId) {
		this.regionScopesId = regionScopesId;
	}

	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}

	@Column(name="count")
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
