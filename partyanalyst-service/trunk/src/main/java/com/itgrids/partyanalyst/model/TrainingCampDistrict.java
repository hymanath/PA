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
@Entity
@Table(name = "training_camp_district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampDistrict extends BaseModel implements Serializable{
	private Long trainingCampDistrictId;
	private Long trainingCampId;
	private Long districtId;
	private TrainingCamp trainingCamp;
	private District district;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_district_id", unique=true, nullable=false)
	public Long getTrainingCampDistrictId() {
		return trainingCampDistrictId;
	}
	public void setTrainingCampDistrictId(Long trainingCampDistrictId) {
		this.trainingCampDistrictId = trainingCampDistrictId;
	}
	@Column(name="training_camp_id")
	public Long getTrainingCampId() {
		return trainingCampId;
	}
	public void setTrainingCampId(Long trainingCampId) {
		this.trainingCampId = trainingCampId;
	}
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "training_camp_id",insertable =false,updatable = false)
	public TrainingCamp getTrainingCamp() {
		return trainingCamp;
	}
	public void setTrainingCamp(TrainingCamp trainingCamp) {
		this.trainingCamp = trainingCamp;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id",insertable =false,updatable = false)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	

}
