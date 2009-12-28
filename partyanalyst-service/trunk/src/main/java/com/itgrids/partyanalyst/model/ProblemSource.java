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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "problem_source")
public class ProblemSource extends BaseModel implements Serializable{

	private Long problemSourceId;
	private String postedBy;
	private String source;
	private Registration user;
	private Problem problem;
	private Set<ProblemLocation> problemLocations = new HashSet<ProblemLocation>(0);
	
	public ProblemSource(){
		
	}

	public ProblemSource(Long problemSourceId){
		this.problemSourceId = problemSourceId;
	}
	
	public ProblemSource(String postedBy, String source,
			Registration user, Problem problem, Set<ProblemLocation> problemLocations) {
		this.postedBy = postedBy;
		this.source = source;
		this.user = user;
		this.problem = problem;
		this.problemLocations = problemLocations;
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

	@Column(name = "posted_by", length = 50)
	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	@Column(name = "source", length = 50)	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemSource")
	public Set<ProblemLocation> getProblemLocations() {
		return problemLocations;
	}

	public void setProblemLocations(Set<ProblemLocation> problemLocations) {
		this.problemLocations = problemLocations;
	}

	
}
