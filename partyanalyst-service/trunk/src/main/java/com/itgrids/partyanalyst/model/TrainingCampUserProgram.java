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
@Table(name = "training_camp_user_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampUserProgram extends BaseModel implements Serializable{
    
	private Long trainingCampUserProgramId;
	private User user;
	private Long userId;
	private TrainingCampProgram trainingCampProgram;
	private Long trainingCampProgramId;
	private TrainingCamp trainingCamp;
	private Long trainingCampId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_schedule_id", unique=true, nullable=false)
	public Long getTrainingCampUserProgramId() {
		return trainingCampUserProgramId;
	}
	public void setTrainingCampUserProgramId(Long trainingCampUserProgramId) {
		this.trainingCampUserProgramId = trainingCampUserProgramId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="user_id") 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_program_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampProgram getTrainingCampProgram() {
		return trainingCampProgram;
	}
	public void setTrainingCampProgram(TrainingCampProgram trainingCampProgram) {
		this.trainingCampProgram = trainingCampProgram;
	}
	@Column(name="training_camp_program_id") 
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCamp getTrainingCamp() {
		return trainingCamp;
	}
	public void setTrainingCamp(TrainingCamp trainingCamp) {
		this.trainingCamp = trainingCamp;
	}
	@Column(name="training_camp_id") 
	public Long getTrainingCampId() {
		return trainingCampId;
	}
	public void setTrainingCampId(Long trainingCampId) {
		this.trainingCampId = trainingCampId;
	}
	
}
