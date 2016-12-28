package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "admin_house_member_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdminHouseMemberQuestion extends BaseModel implements Serializable{
	
	private Long adminHouseMemberQuestionId;
	private Long adminHouseMemberId;
	private Long adminHouseSessionDayId;
	private String question;
	private String remarks;
	private String isDeleted;
	
	private AdminHouseMember adminHouseMember;
	private AdminHouseSessionDay adminHouseSessionDay;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_house_member_question_id", unique = true, nullable = false)
	public Long getAdminHouseMemberQuestionId() {
		return adminHouseMemberQuestionId;
	}
	public void setAdminHouseMemberQuestionId(Long adminHouseMemberQuestionId) {
		this.adminHouseMemberQuestionId = adminHouseMemberQuestionId;
	}
	@Column(name = "admin_house_member_id")
	public Long getAdminHouseMemberId() {
		return adminHouseMemberId;
	}
	public void setAdminHouseMemberId(Long adminHouseMemberId) {
		this.adminHouseMemberId = adminHouseMemberId;
	}
	@Column(name = "admin_house_session_day_id")
	public Long getAdminHouseSessionDayId() {
		return adminHouseSessionDayId;
	}
	public void setAdminHouseSessionDayId(Long adminHouseSessionDayId) {
		this.adminHouseSessionDayId = adminHouseSessionDayId;
	}
	@Column(name = "question")
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "admin_house_member_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouseMember getAdminHouseMember() {
		return adminHouseMember;
	}
	public void setAdminHouseMember(AdminHouseMember adminHouseMember) {
		this.adminHouseMember = adminHouseMember;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "admin_house_session_day_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouseSessionDay getAdminHouseSessionDay() {
		return adminHouseSessionDay;
	}
	public void setAdminHouseSessionDay(AdminHouseSessionDay adminHouseSessionDay) {
		this.adminHouseSessionDay = adminHouseSessionDay;
	}
}
