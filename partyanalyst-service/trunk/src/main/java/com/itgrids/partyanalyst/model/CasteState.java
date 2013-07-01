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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="caste_state")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CasteState extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long casteStateId;
	private State state;
	private Caste caste;
	private CasteCategoryGroup casteCategoryGroup;
	private Set<CandidateCaste> candidateCaste=new HashSet<CandidateCaste>();
	private Set<SurveyorProfile> surveyorProfile=new HashSet<SurveyorProfile>();
	private String isGlobal;
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="caste_category_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteCategoryGroup getCasteCategoryGroup() {
		return casteCategoryGroup;
	}

	public void setCasteCategoryGroup(CasteCategoryGroup casteCategoryGroup) {
		this.casteCategoryGroup = casteCategoryGroup;
	}

	
	

	
	public CasteState(){
		
	}
	
	public CasteState(Long casteStateId,State state,Caste caste){
		this.casteStateId=casteStateId;
		this.state=state;
		this.caste=caste;

	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="caste_state_id",unique = true,nullable= false )
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="caste_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Caste getCaste() {
		return caste;
	}
	public void setCaste(Caste caste) {
		this.caste = caste;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "casteState")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<CandidateCaste> getCandidateCaste() {
		return candidateCaste;
	}

	public void setCandidateCaste(Set<CandidateCaste> candidateCaste) {
		this.candidateCaste = candidateCaste;
	}

	@Column(name = "is_global")
	public String getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "casteState")
	public Set<SurveyorProfile> getSurveyorProfile() {
		return surveyorProfile;
	}

	public void setSurveyorProfile(Set<SurveyorProfile> surveyorProfile) {
		this.surveyorProfile = surveyorProfile;
	}
	
	
}
