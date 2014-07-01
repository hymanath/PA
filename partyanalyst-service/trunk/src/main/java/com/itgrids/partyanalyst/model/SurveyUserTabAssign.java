package com.itgrids.partyanalyst.model;

import java.util.Date;

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

/**
 * 
 * @author Prasad Thiragabathina
 *
 *This Model Describes About Survey user tab details
 */

@Entity
@Table(name = "survey_user_tab_assign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyUserTabAssign
{
	private Long surveyUserTabAssignId;
	private SurveyUser surveyUser;
	private String tabNo;
	private String activeStatus;
	private Date date;
	private String remarks;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_user_tab_assign_id", unique = true, nullable = false)
	public Long getSurveyUserTabAssignId() {
		return surveyUserTabAssignId;
	}
	public void setSurveyUserTabAssignId(Long surveyUserTabAssignId) {
		this.surveyUserTabAssignId = surveyUserTabAssignId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	
	@Column(name="tab_no")
	public String getTabNo() {
		return tabNo;
	}
	public void setTabNo(String tabNo) {
		this.tabNo = tabNo;
	}
	
	@Column(name="status")
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
}
