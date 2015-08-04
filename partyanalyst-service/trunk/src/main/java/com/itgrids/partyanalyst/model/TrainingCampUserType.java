package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "training_camp_user_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampUserType {
	private Long trainingCampUserTypeId;
	private String type;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_camp_user_type_id", unique = true, nullable = false)
	public Long getTrainingCampUserTypeId() {
		return trainingCampUserTypeId;
	}
	public void setTrainingCampUserTypeId(Long trainingCampUserTypeId) {
		this.trainingCampUserTypeId = trainingCampUserTypeId;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
