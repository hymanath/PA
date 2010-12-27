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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "information_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InformationSource extends BaseModel implements Serializable{

	private static final long serialVersionUID = 3768383074738249375L;
	private Long informationSourceId;
	private String informationSource;
	private String description;
	private Set<ProblemAndProblemSource> problemAndProblemSources = new HashSet<ProblemAndProblemSource>(0); 
	
	public InformationSource(){
		
	}

	public InformationSource(Long informationSourceId){
		this.informationSourceId = informationSourceId;
	}
	
	public InformationSource(String informationSource,
			String description, Set<ProblemAndProblemSource> problemAndProblemSources) {
		this.informationSource = informationSource;
		this.description = description;
		this.problemAndProblemSources = problemAndProblemSources;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "information_source_id", unique = true, nullable = false)
	public Long getInformationSourceId() {
		return informationSourceId;
	}

	public void setInformationSourceId(Long informationSourceId) {
		this.informationSourceId = informationSourceId;
	}
	
	@Column(name = "information_source", length = 50)
	public String getInformationSource() {
		return informationSource;
	}

	
	public void setInformationSource(String informationSource) {
		this.informationSource = informationSource;
	}

	@Column(name = "description", length = 250)
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
