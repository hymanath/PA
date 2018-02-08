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
@Table(name = "training_camp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCamp extends BaseModel implements Serializable{
	private Long trainingCampId;
	private String campName;
	private String description;
	private String location;
	private String address;
	private String trainingFor;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_id", unique=true, nullable=false)
	public Long getTrainingCampId() {
		return trainingCampId;
	}
	public void setTrainingCampId(Long trainingCampId) {
		this.trainingCampId = trainingCampId;
	}
	@Column(name="camp_name")
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="training_for")
	public String getTrainingFor() {
		return trainingFor;
	}
	public void setTrainingFor(String trainingFor) {
		this.trainingFor = trainingFor;
	}

}
