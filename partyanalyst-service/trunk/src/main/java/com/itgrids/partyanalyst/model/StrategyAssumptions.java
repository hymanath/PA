package com.itgrids.partyanalyst.model;

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
@Table(name = "strategy_assumptions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StrategyAssumptions  extends BaseModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185307548949257913L;

	private Long strategyAssumptionsId;
	private Constituency constituency;
	private Long voterBasePerc;
	private Long exceptedPollingPerc;
	private Long targetedVotesPerc;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "strategy_assumptions_id", unique = true, nullable = false)
	public Long getStrategyAssumptionsId() {
		return strategyAssumptionsId;
	}
	
	public void setStrategyAssumptionsId(Long strategyAssumptionsId) {
		this.strategyAssumptionsId = strategyAssumptionsId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@Column(name = "voter_base_perc", length = 30)
	public Long getVoterBasePerc() {
		return voterBasePerc;
	}
	
	public void setVoterBasePerc(Long voterBasePerc) {
		this.voterBasePerc = voterBasePerc;
	}
	
	@Column(name = "excepted_polling_perc", length = 30)
	public Long getExceptedPollingPerc() {
		return exceptedPollingPerc;
	}
	
	public void setExceptedPollingPerc(Long exceptedPollingPerc) {
		this.exceptedPollingPerc = exceptedPollingPerc;
	}
	
	@Column(name = "targeted_votes_perc", length = 30)
	public Long getTargetedVotesPerc() {
		return targetedVotesPerc;
	}
	
	public void setTargetedVotesPerc(Long targetedVotesPerc) {
		this.targetedVotesPerc = targetedVotesPerc;
	}
    
	
}
