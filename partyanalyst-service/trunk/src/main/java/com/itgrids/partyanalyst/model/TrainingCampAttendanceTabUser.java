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
@Table(name="training_camp_attendance_tab_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampAttendanceTabUser extends BaseModel implements Serializable{

	private static final long serialVersionUID = -4491422122207225653L;
	
	private Long trainingCampAttendanceTabUserId;
	private TrainingCampSchedule trainingCampSchedule;
	private AttendanceTabUser attendanceTabUser;
	private TrainingCampBatch trainingCampBatch;
	private User insertedBy;
	private Date insertedTime;
	
	private Long trainingCampScheduleId;
	private Long attendanceTabUserId;
	private Long insertedById;
	private Long trainingCampBatchId;
	
	public TrainingCampAttendanceTabUser(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_attendance_tab_user_id", unique=true, nullable=false)
	public Long getTrainingCampAttendanceTabUserId() {
		return trainingCampAttendanceTabUserId;
	}

	public void setTrainingCampAttendanceTabUserId(
			Long trainingCampAttendanceTabUserId) {
		this.trainingCampAttendanceTabUserId = trainingCampAttendanceTabUserId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_schedule_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampSchedule getTrainingCampSchedule() {
		return trainingCampSchedule;
	}

	public void setTrainingCampSchedule(TrainingCampSchedule trainingCampSchedule) {
		this.trainingCampSchedule = trainingCampSchedule;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="attendance_tab_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AttendanceTabUser getAttendanceTabUser() {
		return attendanceTabUser;
	}

	public void setAttendanceTabUser(AttendanceTabUser attendanceTabUser) {
		this.attendanceTabUser = attendanceTabUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="training_camp_schedule_id")
	public Long getTrainingCampScheduleId() {
		return trainingCampScheduleId;
	}

	public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
		this.trainingCampScheduleId = trainingCampScheduleId;
	}

	@Column(name="attendance_tab_user_id")
	public Long getAttendanceTabUserId() {
		return attendanceTabUserId;
	}

	public void setAttendanceTabUserId(Long attendanceTabUserId) {
		this.attendanceTabUserId = attendanceTabUserId;
	}

	@Column(name="inserted_by")
	public Long getInsertedById() {
		return insertedById;
	}

	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_batch_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampBatch getTrainingCampBatch() {
		return trainingCampBatch;
	}

	public void setTrainingCampBatch(TrainingCampBatch trainingCampBatch) {
		this.trainingCampBatch = trainingCampBatch;
	}

	@Column(name="training_camp_batch_id")
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}

	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}
}
