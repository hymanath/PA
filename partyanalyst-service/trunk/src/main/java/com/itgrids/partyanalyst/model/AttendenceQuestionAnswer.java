package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "attendence_question_answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttendenceQuestionAnswer extends BaseModel implements Serializable{
	
	private Long attendenceQuestionAnswerId;
	private Long activityQuestionAnswerId;
	private Long attendanceId;
	private Date insertedTime;
	
	private ActivityQuestionAnswer activityQuestionAnswer;
	private Attendance attendance;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attendence_question_answer_id", unique = true, nullable = false)
	public Long getAttendenceQuestionAnswerId() {
		return attendenceQuestionAnswerId;
	}
	public void setAttendenceQuestionAnswerId(Long attendenceQuestionAnswerId) {
		this.attendenceQuestionAnswerId = attendenceQuestionAnswerId;
	}
	
	@Column(name="activity_question_answer_id")
	public Long getActivityQuestionAnswerId() {
		return activityQuestionAnswerId;
	}
	public void setActivityQuestionAnswerId(Long activityQuestionAnswerId) {
		this.activityQuestionAnswerId = activityQuestionAnswerId;
	}
	
	@Column(name="attendance_id")
	public Long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_question_answer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityQuestionAnswer getActivityQuestionAnswer() {
		return activityQuestionAnswer;
	}
	public void setActivityQuestionAnswer(
			ActivityQuestionAnswer activityQuestionAnswer) {
		this.activityQuestionAnswer = activityQuestionAnswer;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="attendance_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
}
