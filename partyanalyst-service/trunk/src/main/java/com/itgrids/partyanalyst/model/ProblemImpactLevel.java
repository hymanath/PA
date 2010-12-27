/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 29, 2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Sai Krishna Basetti
 *
 */
@Entity
@Table(name="problem_impact_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemImpactLevel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long problemImpactLevelId;
	private String problemImpactLevel;
	private String description;
	
	private Set<ProblemLocation> problemHistory = new HashSet<ProblemLocation>(0);
	
	//Default Constructor
	public ProblemImpactLevel(){
		
	}

	//Parameterized Constructor
	public ProblemImpactLevel(String problemImpactLevel, String description,
			Set<ProblemLocation> problemHistory) {
		this.problemImpactLevel = problemImpactLevel;
		this.description = description;
		this.problemHistory = problemHistory;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_impact_level_id", unique=true, nullable=false)
	public Long getProblemImpactLevelId() {
		return problemImpactLevelId;
	}

	public void setProblemImpactLevelId(Long problemImpactLevelId) {
		this.problemImpactLevelId = problemImpactLevelId;
	}

	@Column(name="problem_impact_level",length=25)
	public String getProblemImpactLevel() {
		return problemImpactLevel;
	}

	public void setProblemImpactLevel(String problemImpactLevel) {
		this.problemImpactLevel = problemImpactLevel;
	}

	@Column(name="description",length=25)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemImpactLevel")
	public Set<ProblemLocation> getProblemHistory() {
		return problemHistory;
	}

	public void setProblemHistory(Set<ProblemLocation> problemHistory) {
		this.problemHistory = problemHistory;
	}
*/
}
