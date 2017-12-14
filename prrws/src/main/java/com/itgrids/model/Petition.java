package com.itgrids.model;

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

@Entity
@Table(name = "petition")
public class Petition {
	
	
	private Long petitionId;
	private String endorsmentNo;
	private Date representationDate;
	private Date endorsmentDate;
	private String workName;
	private Double estimationCost;
	private Long noOfWorks;
	private String isPreviousPetition;
	private String grievanceDescription;
	private String isDeleted;
	private Long pmStatusId;
	private String representeeType;
	
    private User insertedUser;
	private User updatedUser;
	private Date insertedTime;
	private Date updatedTime;
	
	private PmStatus pmStatus;

	@Id
	@Column(name="petition_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="endorsment_no")
	public String getEndorsmentNo() {
		return endorsmentNo;
	}

	public void setEndorsmentNo(String endorsmentNo) {
		this.endorsmentNo = endorsmentNo;
	}
	@Column(name="representation_date")
	public Date getRepresentationDate() {
		return representationDate;
	}

	public void setRepresentationDate(Date representationDate) {
		this.representationDate = representationDate;
	}
	@Column(name="endorsment_date")
	public Date getEndorsmentDate() {
		return endorsmentDate;
	}
	public void setEndorsmentDate(Date endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	@Column(name="estimation_cost")
	public Double getEstimationCost() {
		return estimationCost;
	}
	public void setEstimationCost(Double estimationCost) {
		this.estimationCost = estimationCost;
	}
	@Column(name="no_of_works")
	public Long getNoOfWorks() {
		return noOfWorks;
	}
	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}
	@Column(name="is_previous_petition")
	public String getIsPreviousPetition() {
		return isPreviousPetition;
	}
	public void setIsPreviousPetition(String isPreviousPetition) {
		this.isPreviousPetition = isPreviousPetition;
	}
	@Column(name="grievance_description")
	public String getGrievanceDescription() {
		return grievanceDescription;
	}

	public void setGrievanceDescription(String grievanceDescription) {
		this.grievanceDescription = grievanceDescription;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="pm_status_id")
	public Long getPmStatusId() {
		return pmStatusId;
	}
	public void setPmStatusId(Long pmStatusId) {
		this.pmStatusId = pmStatusId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_status_id", insertable = false, updatable = false)
	public PmStatus getPmStatus() {
		return pmStatus;
	}
	public void setPmStatus(PmStatus pmStatus) {
		this.pmStatus = pmStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="representee_type")
	public String getRepresenteeType() {
		return representeeType;
	}
	public void setRepresenteeType(String representeeType) {
		this.representeeType = representeeType;
	}
	

}
