package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

@Entity
@Table(name = "problem_source")
public class ProblemSource extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768383074738249375L;
	private Long problemSourceId;
	private String problemSource;
	private String description;
	private Set<ProblemAndProblemSource> problemAndProblemSources = new HashSet<ProblemAndProblemSource>(0); 
	
	public ProblemSource(){
		
	}

	public ProblemSource(Long problemSourceId){
		this.problemSourceId = problemSourceId;
	}
	
	public ProblemSource(String problemSource,
			String description, Set<ProblemAndProblemSource> problemAndProblemSources) {
		this.problemSource = problemSource;
		this.description = description;
		this.problemAndProblemSources = problemAndProblemSources;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_source_id", unique = true, nullable = false)
	public Long getProblemSourceId() {
		return problemSourceId;
	}

	public void setProblemSourceId(Long problemSourceId) {
		this.problemSourceId = problemSourceId;
	}
	
	@Column(name = "problem_source", length = 50)
	public String getProblemSource() {
		return problemSource;
	}

	
	public void setProblemSource(String problemSource) {
		this.problemSource = problemSource;
	}

	@Column(name = "description", length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemSource")
	public Set<ProblemAndProblemSource> getProblemAndProblemSources() {
		return problemAndProblemSources;
	}

	public void setProblemAndProblemSources(
			Set<ProblemAndProblemSource> problemAndProblemSources) {
		this.problemAndProblemSources = problemAndProblemSources;
	}
	
	
}
