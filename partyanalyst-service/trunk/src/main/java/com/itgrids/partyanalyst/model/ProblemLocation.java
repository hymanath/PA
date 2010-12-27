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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "problem_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemLocation extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8098244953899201518L;
	private Long problemLocationId;
	private Hamlet hamlet;
	private Ward ward;
	private Township township;
	private RegionScopes problemImpactLevel;
	private Long problemImpactLevelValue;
	private ProblemAndProblemSource problemAndProblemSource;
	private ProblemClassification problemClassification;
	private Set<ProblemHistory> problemHistories = new HashSet<ProblemHistory>(0); 
	
	private Date updatedDate;
	
	public ProblemLocation(){
		
	}
	
	public ProblemLocation(Long problemLocationId){
		this.problemLocationId = problemLocationId;
	}

	

	public ProblemLocation(Hamlet hamlet,
			Ward ward, Township township,
			ProblemClassification problemClassification,
			ProblemAndProblemSource problemAndProblemSource,
			Set<ProblemHistory> problemHistories,
			RegionScopes problemImpactLevel,
			Long problemImpactLevelValue,Date updatedDate) {
		this.hamlet = hamlet;
		this.ward = ward;
		this.township = township;
		this.problemClassification = problemClassification;
		this.problemAndProblemSource = problemAndProblemSource;
		this.problemHistories = problemHistories;
		this.problemImpactLevel = problemImpactLevel;
		this.problemImpactLevelValue = problemImpactLevelValue;
		this.updatedDate = updatedDate;
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

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
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

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_and_problem_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemAndProblemSource getProblemAndProblemSource() {
		return problemAndProblemSource;
	}

	public void setProblemAndProblemSource(ProblemAndProblemSource problemAndProblemSource) {
		this.problemAndProblemSource = problemAndProblemSource;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "region_scopes_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getProblemImpactLevel() {
		return problemImpactLevel;
	}

	public void setProblemImpactLevel(RegionScopes problemImpactLevel) {
		this.problemImpactLevel = problemImpactLevel;
	}

	@Column(name = "problem_impact_level_value", length = 15)
	public Long getProblemImpactLevelValue() {
		return problemImpactLevelValue;
	}

	public void setProblemImpactLevelValue(Long problemImpactLevelValue) {
		this.problemImpactLevelValue = problemImpactLevelValue;
	}

	@Column(name = "updated_date", length = 15)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
}
