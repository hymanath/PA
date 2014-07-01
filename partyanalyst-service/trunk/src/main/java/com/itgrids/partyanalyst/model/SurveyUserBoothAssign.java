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
 * This Model Describes About Survey User Booth Details Means In Wich Booth He Is Collecting The Voter Details
 *
 */

@Entity
@Table(name = "survey_user_booth_assign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyUserBoothAssign 
{

	private Long surveyUserBoothAssignId;
	private SurveyUser surveyUser;
	private Constituency constituency;
	private Panchayat Panchayat;
	private Booth booth;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_user_booth_assign_id", unique = true, nullable = false)
	public Long getSurveyUserBoothAssignId() {
		return surveyUserBoothAssignId;
	}
	public void setSurveyUserBoothAssignId(Long surveyUserBoothAssignId) {
		this.surveyUserBoothAssignId = surveyUserBoothAssignId;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return Panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		Panchayat = panchayat;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
}
