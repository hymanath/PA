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

import com.itgrids.partyanalyst.model.ActivityLocationPublicAttendance;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;
import com.itgrids.partyanalyst.model.BaseModel;

@Entity
@Table(name="activity_tab_user_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityTabUserAnswer extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long activityTabUserAnswerId;
	private Long activityLocationPublicAttendanceId;
	private Long activityQuestionAnswerId;
	
	private ActivityLocationPublicAttendance activityLocationPublicAttendance;
	private ActivityQuestionAnswer avtivivityQuestionAnswer;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_tab_user_answer_id", unique = true, nullable = false)
	public Long getActivityTabUserAnswerId() {
		return activityTabUserAnswerId;
	}
	public void setActivityTabUserAnswerId(Long activityTabUserAnswerId) {
		this.activityTabUserAnswerId = activityTabUserAnswerId;
	}
	
	@Column(name = "activity_location_public_attendance_id")
	public Long getActivityLocationPublicAttendanceId() {
		return activityLocationPublicAttendanceId;
	}
	public void setActivityLocationPublicAttendanceId(
			Long activityLocationPublicAttendanceId) {
		this.activityLocationPublicAttendanceId = activityLocationPublicAttendanceId;
	}
	
	@Column(name = "activity_question_answer_id")
	public Long getActivityQuestionAnswerId() {
		return activityQuestionAnswerId;
	}
	public void setActivityQuestionAnswerId(Long activityQuestionAnswerId) {
		this.activityQuestionAnswerId = activityQuestionAnswerId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_location_public_attendance_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationPublicAttendance getActivityLocationPublicAttendance() {
		return activityLocationPublicAttendance;
	}
	public void setActivityLocationPublicAttendance(
			ActivityLocationPublicAttendance activityLocationPublicAttendance) {
		this.activityLocationPublicAttendance = activityLocationPublicAttendance;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_question_answer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityQuestionAnswer getAvtivivityQuestionAnswer() {
		return avtivivityQuestionAnswer;
	}
	public void setAvtivivityQuestionAnswer(
			ActivityQuestionAnswer avtivivityQuestionAnswer) {
		this.avtivivityQuestionAnswer = avtivivityQuestionAnswer;
	}
	
	
	
}
