package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
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
@Table(name = "predicted_voter_caste")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PredictedVoterCaste  extends BaseModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -3249300603701987127L;
	private Long predictedVoterCasteId;
	private Voter voter;
	
	private CasteState casteState;
	public PredictedVoterCaste()
	{
		
	}
	public PredictedVoterCaste( Long predictedVoterCasteId,Voter voter,CasteState casteState)
	{
		this.predictedVoterCasteId =predictedVoterCasteId;
		this.voter =voter;
		this.casteState = casteState;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="predicted_voter_caste_id", unique = true, nullable = false)
	public Long getPredictedVoterCasteId() {
		return predictedVoterCasteId;
	}

	public void setPredictedVoterCasteId(Long predictedVoterCasteId) {
		this.predictedVoterCasteId = predictedVoterCasteId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="caste_state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}

	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}
	
	
	

}
