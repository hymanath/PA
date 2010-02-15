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
@Table(name = "problem_and_problem_source")
public class ProblemAndProblemSource extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768383074738249375L;
	private Long problemAndProblemSourceId;
	private String postedBy;
	private Registration user;
	private Problem problem;
	private ProblemSource problemSource;
	private ProblemExternalSource problemExternalSource;
	private Set<ProblemLocation> problemLocations = new HashSet<ProblemLocation>(0);
	
	public ProblemAndProblemSource(){
		
	}

	public ProblemAndProblemSource(Long problemAndProblemSourceId){
		this.problemAndProblemSourceId = problemAndProblemSourceId;
	}
	
	public ProblemAndProblemSource(String postedBy,
			Registration user, Problem problem,ProblemExternalSource problemExternalSource, 
			Set<ProblemLocation> problemLocations, ProblemSource problemSource) {
		this.postedBy = postedBy;
		this.user = user;
		this.problem = problem;
		this.problemExternalSource = problemExternalSource;
		this.problemLocations = problemLocations;
		this.problemSource = problemSource;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_and_problem_source_id", unique = true, nullable = false)
	public Long getProblemAndProblemSourceId() {
		return problemAndProblemSourceId;
	}

	public void setProblemAndProblemSourceId(Long problemAndProblemSourceId) {
		this.problemAndProblemSourceId = problemAndProblemSourceId;
	}

	@Column(name = "posted_by", length = 50)
	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_external_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemExternalSource getProblemExternalSource() {
		return problemExternalSource;
	}

	public void setProblemExternalSource(ProblemExternalSource problemExternalSource) {
		this.problemExternalSource = problemExternalSource;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemAndProblemSource")
	public Set<ProblemLocation> getProblemLocations() {
		return problemLocations;
	}

	public void setProblemLocations(Set<ProblemLocation> problemLocations) {
		this.problemLocations = problemLocations;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemSource getProblemSource() {
		return problemSource;
	}

	public void setProblemSource(ProblemSource problemSource) {
		this.problemSource = problemSource;
	}

	
}
