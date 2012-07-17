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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * problem entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="problem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Problem extends BaseModel implements Serializable{

	private static final long serialVersionUID = 5322436995777648316L;
	
	private Long problemId;
	private String title;
	private String description;
	private String referenceNo;
	private ProblemType problemType;
	private RegionScopes regionScopes;
	private Long impactLevelValue;
	private ProblemCompleteLocation problemCompleteLocation;
	private InformationSource informationSource;
	private ProblemExternalSource externalSource;
	private Date identifiedOn;
	private Date existingFrom;
	private Date insertedTime;
	private Date updatedTime;
	private String isApproved;
	private String isDelete;
	private ProblemStatus problemStatus;
	
	private Set<CadreProblems> cadreProblems = new HashSet<CadreProblems>(0);
	private Set<ProblemFiles> problemFiles = new HashSet<ProblemFiles>(0);
	private Set<ProblemComments> problemComments = new HashSet<ProblemComments>(0);
	private Set<ProblemLikes> problemLikes = new HashSet<ProblemLikes>(0);
	private Set<UserProblem> userProblems = new HashSet<UserProblem>(0);
	private Set<ProblemRating> problemRatings = new HashSet<ProblemRating>(0);
	
	public Problem()
	{}
	
	public Problem(String title,String description,String referenceNo,ProblemType problemType,
			RegionScopes regionScopes, Long impactLevelValue,ProblemCompleteLocation problemCompleteLocation,
			InformationSource informationSource,ProblemExternalSource externalSource,Date identifiedOn,
			Date existingFrom,Date insertedTime,Date updatedTime,String isApproved,String isDelete)
	{
		this.title = title;
		this.description = description;
		this.referenceNo = referenceNo;
		this.problemType = problemType;
		this.regionScopes = regionScopes;
		this.impactLevelValue = impactLevelValue;
		this.problemCompleteLocation = problemCompleteLocation;
		this.informationSource = informationSource;
		this.externalSource = externalSource;
		this.identifiedOn = identifiedOn;
		this.existingFrom = existingFrom;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
		this.isApproved = isApproved;
		this.isDelete = isDelete;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_id", unique=true, nullable=false)
	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "reference_no", length = 10)
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemType getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_scopes_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	@Column(name = "impact_level_value")
	public Long getImpactLevelValue() {
		return impactLevelValue;
	}

	public void setImpactLevelValue(Long impactLevelValue) {
		this.impactLevelValue = impactLevelValue;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_complete_location_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemCompleteLocation getProblemCompleteLocation() {
		return problemCompleteLocation;
	}

	public void setProblemCompleteLocation(
			ProblemCompleteLocation problemCompleteLocation) {
		this.problemCompleteLocation = problemCompleteLocation;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="information_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public InformationSource getInformationSource() {
		return informationSource;
	}

	public void setInformationSource(InformationSource informationSource) {
		this.informationSource = informationSource;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="external_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemExternalSource getExternalSource() {
		return externalSource;
	}

	public void setExternalSource(ProblemExternalSource externalSource) {
		this.externalSource = externalSource;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "identified_on", length = 10)
	public Date getIdentifiedOn() {
		return identifiedOn;
	}

	public void setIdentifiedOn(Date identifiedOn) {
		this.identifiedOn = identifiedOn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "existing_from", length = 10)
	public Date getExistingFrom() {
		return existingFrom;
	}

	public void setExistingFrom(Date existingFrom) {
		this.existingFrom = existingFrom;
	}

	@Column(name = "inserted_time", length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "updated_time", length = 10)
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "is_approved", length = 10)
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	@Column(name = "is_delete", length = 10)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreProblems> getCadreProblems() {
		return cadreProblems;
	}

	public void setCadreProblems(Set<CadreProblems> cadreProblems) {
		this.cadreProblems = cadreProblems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemFiles> getProblemFiles() {
		return problemFiles;
	}

	public void setProblemFiles(Set<ProblemFiles> problemFiles) {
		this.problemFiles = problemFiles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemComments> getProblemComments() {
		return problemComments;
	}

	public void setProblemComments(Set<ProblemComments> problemComments) {
		this.problemComments = problemComments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemLikes> getProblemLikes() {
		return problemLikes;
	}

	public void setProblemLikes(Set<ProblemLikes> problemLikes) {
		this.problemLikes = problemLikes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserProblem> getUserProblems() {
		return userProblems;
	}

	public void setUserProblems(Set<UserProblem> userProblems) {
		this.userProblems = userProblems;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_status_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemStatus getProblemStatus() {
		return problemStatus;
	}

	public void setProblemStatus(ProblemStatus problemStatus) {
		this.problemStatus = problemStatus;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemRating> getProblemRatings() {
		return problemRatings;
	}

	public void setProblemRatings(Set<ProblemRating> problemRatings) {
		this.problemRatings = problemRatings;
	}
	
	
}
