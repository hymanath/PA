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
@Table(name = "problem_location")
public class ProblemLocation extends BaseModel implements Serializable{

	private Long problemLocationId;
	private Hamlet hamlet;
	private Ward ward;
	private Township township;
	private ProblemSource problemSource;
	private ProblemClassification problemClassification;
	private Set<ProblemHistory> problemHistories = new HashSet<ProblemHistory>(0); 
	
	public ProblemLocation(){
		
	}
	
	public ProblemLocation(Long problemLocationId){
		this.problemLocationId = problemLocationId;
	}

	

	public ProblemLocation(Hamlet hamlet,
			Ward ward, Township township,
			ProblemClassification problemClassification,
			ProblemSource problemSource,
			Set<ProblemHistory> problemHistories) {
		this.hamlet = hamlet;
		this.ward = ward;
		this.township = township;
		this.problemClassification = problemClassification;
		this.problemSource = problemSource;
		this.problemHistories = problemHistories;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_location_id", unique = true, nullable = false)
	public Long getProblemLocationId() {
		return problemLocationId;
	}

	public void setProblemLocationId(Long problemLocationId) {
		this.problemLocationId = problemLocationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_classification_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemClassification getProblemClassification() {
		return problemClassification;
	}

	public void setProblemClassification(ProblemClassification problemClassification) {
		this.problemClassification = problemClassification;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemLocation")
	public Set<ProblemHistory> getProblemHistories() {
		return problemHistories;
	}

	public void setProblemHistories(Set<ProblemHistory> problemHistories) {
		this.problemHistories = problemHistories;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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
