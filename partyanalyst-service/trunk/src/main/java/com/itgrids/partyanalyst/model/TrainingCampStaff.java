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


/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "training_camp_staff")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampStaff extends BaseModel implements Serializable{
	
	private Long trainingCampStaffId;
	private Long trainingCampId;
	private Long trainingCampProgramId;
	private String tdpCadreId;
	private String desc;
	
	private TrainingCamp trainingCamp;
	private TrainingCampProgram trainingCampProgram;
	private TdpCadre tdpCadre;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_staff_id", unique=true, nullable=false)
	public Long getTrainingCampStaffId() {
		return trainingCampStaffId;
	}
	public void setTrainingCampStaffId(Long trainingCampStaffId) {
		this.trainingCampStaffId = trainingCampStaffId;
	}
	
	
	@Column(name="training_camp_id")
	public Long getTrainingCampId() {
		return trainingCampId;
	}
	public void setTrainingCampId(Long trainingCampId) {
		this.trainingCampId = trainingCampId;
	}
	
	
	@Column(name="training_camp_program_id")
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	
	@Column(name="tdp_cadre_id")
	public String getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(String tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCamp getTrainingCamp() {
		return trainingCamp;
	}
	public void setTrainingCamp(TrainingCamp trainingCamp) {
		this.trainingCamp = trainingCamp;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_program_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampProgram getTrainingCampProgram() {
		return trainingCampProgram;
	}
	public void setTrainingCampProgram(TrainingCampProgram trainingCampProgram) {
		this.trainingCampProgram = trainingCampProgram;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
}
