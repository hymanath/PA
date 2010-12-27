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
@Table(name = "problem_classification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemClassification extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3804134197097303226L;
	private Long problemClassificationId;
	private String classification;
	private String description;
	
	public ProblemClassification(){
		
	}
	
	public ProblemClassification(Long problemClassificationId){
		this.problemClassificationId = problemClassificationId;
	}
	
	public ProblemClassification(String classification, String description) {
		this.classification = classification;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_classification_id", unique = true, nullable = false)
	public Long getProblemClassificationId() {
		return problemClassificationId;
	}

	public void setProblemClassificationId(Long problemClassificationId) {
		this.problemClassificationId = problemClassificationId;
	}

	@Column(name = "classification", length = 25)
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Column(name = "description", length = 25)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
