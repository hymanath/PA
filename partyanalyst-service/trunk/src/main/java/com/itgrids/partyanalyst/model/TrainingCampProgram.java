package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "training_camp_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampProgram extends BaseModel implements Serializable{

	private Long trainingCampProgramId;
	private String programName;
	private String description;
	private Long durationInDays;
	private String isDeleted;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_program_id", unique=true, nullable=false)
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	@Column(name="program_name")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="duration_in_days")
	public Long getDurationInDays() {
		return durationInDays;
	}
	public void setDurationInDays(Long durationInDays) {
		this.durationInDays = durationInDays;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
