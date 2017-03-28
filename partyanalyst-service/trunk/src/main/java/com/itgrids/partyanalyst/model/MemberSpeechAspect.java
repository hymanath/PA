package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "member_speech_aspect")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberSpeechAspect extends BaseModel implements Serializable{
	
	private Long memberSpeechAspectId;
	private Long adminHouseSessionDayId;
	private Long adminHouseMemberId;
	private Long speechAspectId;
	private Long score;
	private String isDeleted;
	
	private AdminHouseSessionDay adminHouseSessionDay;
	private AdminHouseMember adminHouseMember;
	private SpeechAspect speechAspect;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_speech_aspect_id", unique = true, nullable = false)
	public Long getMemberSpeechAspectId() {
		return memberSpeechAspectId;
	}
	public void setMemberSpeechAspectId(Long memberSpeechAspectId) {
		this.memberSpeechAspectId = memberSpeechAspectId;
	}
	
	@Column(name = "admin_house_session_day_id")
	public Long getAdminHouseSessionDayId() {
		return adminHouseSessionDayId;
	}
	public void setAdminHouseSessionDayId(Long adminHouseSessionDayId) {
		this.adminHouseSessionDayId = adminHouseSessionDayId;
	}
	
	@Column(name = "admin_house_member_id")
	public Long getAdminHouseMemberId() {
		return adminHouseMemberId;
	}
	public void setAdminHouseMemberId(Long adminHouseMemberId) {
		this.adminHouseMemberId = adminHouseMemberId;
	}
	
	@Column(name = "speech_aspect_id")
	public Long getSpeechAspectId() {
		return speechAspectId;
	}
	public void setSpeechAspectId(Long speechAspectId) {
		this.speechAspectId = speechAspectId;
	}
	
	@Column(name = "score")
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_house_session_day_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AdminHouseSessionDay getAdminHouseSessionDay() {
		return adminHouseSessionDay;
	}
	public void setAdminHouseSessionDay(AdminHouseSessionDay adminHouseSessionDay) {
		this.adminHouseSessionDay = adminHouseSessionDay;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_house_member_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AdminHouseMember getAdminHouseMember() {
		return adminHouseMember;
	}
	public void setAdminHouseMember(AdminHouseMember adminHouseMember) {
		this.adminHouseMember = adminHouseMember;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "speech_aspect_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SpeechAspect getSpeechAspect() {
		return speechAspect;
	}
	public void setSpeechAspect(SpeechAspect speechAspect) {
		this.speechAspect = speechAspect;
	}
	
	
	

}
