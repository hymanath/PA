package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "problem")
public class Problem extends BaseModel implements Serializable{
	
	private Long problemId;
	private String description;
	private String intensity;
	private Date identifiedOn;
	private Set<ProblemSource> problemSources = new HashSet<ProblemSource>(0);
	
	public Problem(){
		
	}

	public Problem(Long problemId){
		this.problemId = problemId;
	}
	
	
	public Problem(String description, String intensity,
			Date identifiedOn, Set<ProblemSource> problemSources) {
		this.description = description;
		this.intensity = intensity;
		this.identifiedOn = identifiedOn;
		this.problemSources = problemSources;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_id", unique = true, nullable = false)
	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "intensity", length = 25)
	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "identified_on", length = 10)
	public Date getIdentifiedOn() {
		return identifiedOn;
	}

	public void setIdentifiedOn(Date identifiedOn) {
		this.identifiedOn = identifiedOn;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problem")
	public Set<ProblemSource> getProblemSources() {
		return problemSources;
	}

	public void setProblemSources(Set<ProblemSource> problemSources) {
		this.problemSources = problemSources;
	}
	
}
