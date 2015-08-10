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
@Table(name="training_camp_attendance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampAttendance extends BaseModel implements Serializable{

	private static final long serialVersionUID = 6382859791460676310L;
	private Long trainingCampAttendanceId;
	private Attendance attendance;
	private TrainingCampSchedule trainingCampSchedule;
	private TrainingCampBatch trainingCampBatch;
	private Date insertedTime;
	private Long attendanceId;
	private Long trainingCampScheduleId;
	private Long trainingCampBatchId;
	
	public TrainingCampAttendance(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_attendance_id", unique=true, nullable=false)
	public Long getTrainingCampAttendanceId() {
		return trainingCampAttendanceId;
	}

	public void setTrainingCampAttendanceId(Long trainingCampAttendanceId) {
		this.trainingCampAttendanceId = trainingCampAttendanceId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="attendance_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
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
	@JoinColumn(name="training_camp_batch_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampBatch getTrainingCampBatch() {
		return trainingCampBatch;
	}

	public void setTrainingCampBatch(TrainingCampBatch trainingCampBatch) {
		this.trainingCampBatch = trainingCampBatch;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="attendance_id")
	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	@Column(name="training_camp_schedule_id")
	public Long getTrainingCampScheduleId() {
		return trainingCampScheduleId;
	}

	public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
		this.trainingCampScheduleId = trainingCampScheduleId;
	}

	@Column(name="training_camp_batch_id")
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}

	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}
}
