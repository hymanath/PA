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
@Table(name="training_camp_batch_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampBatchType implements java.io.Serializable{

	private Long trainingCampBatchTypeId;
	private String type;
	private String isDeleted;
	private String description;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_batch_type_id", unique=true, nullable=false)
	public Long getTrainingCampBatchTypeId() {
		return trainingCampBatchTypeId;
	}
	public void setTrainingCampBatchTypeId(Long trainingCampBatchTypeId) {
		this.trainingCampBatchTypeId = trainingCampBatchTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="is_deleted")	
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
