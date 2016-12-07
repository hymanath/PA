package com.itgrids.cardprint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "election_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionScope  extends BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = 4621970188219643414L;
	
	private Long electionScopeId;
	private Long electionTypeId;
	private Long stateId;
	private Long countryId;
	
	private ElectionType electionType;
	private State state ;
	private Country country;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_scope_id", unique = true, nullable = false)
	public Long getElectionScopeId() {
		return electionScopeId;
	}
	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}
	
	@Column(name = "election_type_id")
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	
	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	@Column(name = "country_id")
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "election_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionType getElectionType() {
		return electionType;
	}
	public void setElectionType(ElectionType electionType) {
		this.electionType = electionType;
	}
	

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "state_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "country_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}
