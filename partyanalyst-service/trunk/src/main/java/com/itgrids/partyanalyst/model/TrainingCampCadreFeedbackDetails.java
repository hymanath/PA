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
@Table(name = "training_camp_cadre_feedback_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampCadreFeedbackDetails extends BaseModel implements Serializable{
	
	private Long trainingCampCadreFeedbackDetailsId;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private CadreLeadershipLevel cadreLeadershipLevel;
	private Long cadreLeadershipLevelId;
	private CadreComminicationSkillsStatus cadreComminicationSkillsStatus;
	private Long cadreComminicationSkillsStatusId;
	private CadreLeadershipSkillsStatus cadreLeadershipSkillsStatus;
	private Long cadreLeadershipSkillsStatusId;
	private CadreHealthStatus cadreHealthStatus;
	private Long cadreHealthStatusId;
	private String remarks;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private TrainingCampBatch trainingCampBatch;
	private Long trainingCampBatchId;
	
	private String smartPhoneExist;
	private String watsappUsing;
	private String watsappShare;
	private String facebookUsing;
	private String healthCardAttachment;
	private Long trainingCampProgramId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_cadre_feedback_details_id", unique=true, nullable=false)
	public Long getTrainingCampCadreFeedbackDetailsId() {
		return trainingCampCadreFeedbackDetailsId;
	}
	public void setTrainingCampCadreFeedbackDetailsId(
			Long trainingCampCadreFeedbackDetailsId) {
		this.trainingCampCadreFeedbackDetailsId = trainingCampCadreFeedbackDetailsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "tdp_cadre_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cadre_leadership_level_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreLeadershipLevel getCadreLeadershipLevel() {
		return cadreLeadershipLevel;
	}
	public void setCadreLeadershipLevel(CadreLeadershipLevel cadreLeadershipLevel) {
		this.cadreLeadershipLevel = cadreLeadershipLevel;
	}
	
	@Column(name = "cadre_leadership_level_id")
	public Long getCadreLeadershipLevelId() {
		return cadreLeadershipLevelId;
	}
	public void setCadreLeadershipLevelId(Long cadreLeadershipLevelId) {
		this.cadreLeadershipLevelId = cadreLeadershipLevelId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cadre_comminication_skills_status_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreComminicationSkillsStatus getCadreComminicationSkillsStatus() {
		return cadreComminicationSkillsStatus;
	}
	public void setCadreComminicationSkillsStatus(
			CadreComminicationSkillsStatus cadreComminicationSkillsStatus) {
		this.cadreComminicationSkillsStatus = cadreComminicationSkillsStatus;
	}
	
	@Column(name = "cadre_comminication_skills_status_id")
	public Long getCadreComminicationSkillsStatusId() {
		return cadreComminicationSkillsStatusId;
	}
	public void setCadreComminicationSkillsStatusId(
			Long cadreComminicationSkillsStatusId) {
		this.cadreComminicationSkillsStatusId = cadreComminicationSkillsStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cadre_leadership_skills_status_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreLeadershipSkillsStatus getCadreLeadershipSkillsStatus() {
		return cadreLeadershipSkillsStatus;
	}
	public void setCadreLeadershipSkillsStatus(
			CadreLeadershipSkillsStatus cadreLeadershipSkillsStatus) {
		this.cadreLeadershipSkillsStatus = cadreLeadershipSkillsStatus;
	}
	
	@Column(name = "cadre_leadership_skills_status_id")
	public Long getCadreLeadershipSkillsStatusId() {
		return cadreLeadershipSkillsStatusId;
	}
	public void setCadreLeadershipSkillsStatusId(Long cadreLeadershipSkillsStatusId) {
		this.cadreLeadershipSkillsStatusId = cadreLeadershipSkillsStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cadre_health_status_id", insertable = false, updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreHealthStatus getCadreHealthStatus() {
		return cadreHealthStatus;
	}
	public void setCadreHealthStatus(CadreHealthStatus cadreHealthStatus) {
		this.cadreHealthStatus = cadreHealthStatus;
	}
	
	@Column(name = "cadre_health_status_id")
	public Long getCadreHealthStatusId() {
		return cadreHealthStatusId;
	}
	public void setCadreHealthStatusId(Long cadreHealthStatusId) {
		this.cadreHealthStatusId = cadreHealthStatusId;
	}
	
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "training_camp_batch_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampBatch getTrainingCampBatch() {
		return trainingCampBatch;
	}
	public void setTrainingCampBatch(TrainingCampBatch trainingCampBatch) {
		this.trainingCampBatch = trainingCampBatch;
	}
	@Column(name = "training_camp_batch_id")
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}
	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}
	
	
	@Column(name = "smart_phone_exist")
	public String getSmartPhoneExist() {
		return smartPhoneExist;
	}
	public void setSmartPhoneExist(String smartPhoneExist) {
		this.smartPhoneExist = smartPhoneExist;
	}
	
	@Column(name = "watsapp_using")
	public String getWatsappUsing() {
		return watsappUsing;
	}
	public void setWatsappUsing(String watsappUsing) {
		this.watsappUsing = watsappUsing;
	}
	
	@Column(name="watsapp_share")
	public String getWatsappShare() {
		return watsappShare;
	}
	public void setWatsappShare(String watsappShare) {
		this.watsappShare = watsappShare;
	}
	
	@Column(name="facebook_using")
	public String getFacebookUsing() {
		return facebookUsing;
	}
	public void setFacebookUsing(String facebookUsing) {
		this.facebookUsing = facebookUsing;
	}
	@Column(name="health_card_attachment")
	public String getHealthCardAttachment() {
		return healthCardAttachment;
	}
	public void setHealthCardAttachment(String healthCardAttachment) {
		this.healthCardAttachment = healthCardAttachment;
	}
	
	@Column(name="training_camp_program_id")
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	
	
	
}
