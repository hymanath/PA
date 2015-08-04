package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "training_camp_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampUser {
	private Long trainingCampUserId;
	private Long userId;
	private Long trainingCampUserTypeId;
	private TrainingCampUserType trainingCampUserType;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_camp_user_id", unique = true, nullable = false)
	public Long getTrainingCampUserId() {
		return trainingCampUserId;
	}
	public void setTrainingCampUserId(Long trainingCampUserId) {
		this.trainingCampUserId = trainingCampUserId;
	}
    @Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "training_camp_user_type_id")
	public Long getTrainingCampUserTypeId() {
		return trainingCampUserTypeId;
	}
	public void setTrainingCampUserTypeId(Long trainingCampUserTypeId) {
		this.trainingCampUserTypeId = trainingCampUserTypeId;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "training_camp_user_type_id",insertable =false,updatable = false)
	public TrainingCampUserType getTrainingCampUserType() {
		return trainingCampUserType;
	}
	public void setTrainingCampUserType(TrainingCampUserType trainingCampUserType) {
		this.trainingCampUserType = trainingCampUserType;
	}
	
	
	

}
