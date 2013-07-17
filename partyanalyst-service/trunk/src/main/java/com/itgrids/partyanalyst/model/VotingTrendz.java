package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

/**
 * opinion_poll entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="voting_trendz")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VotingTrendz extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2428337071105507640L;
	
	private Long votingTrendzId;
	private Constituency constituency;
	private VoterReportLevel voterReportLevel;
	private Long reportLevelValue;
	private ElectionType electionType;
	private Long year;
	private Long totalBooths;
	private Long totalVotes;
	private Long votesPolled;
	private Integer orderNo;
	
	public VotingTrendz(){}
	
	public VotingTrendz(Constituency constituency,VoterReportLevel voterReportLevel,Long reportLevelValue,ElectionType electionType,
	 Long year,Long totalBooths,Long totalVotes,Long votesPolled,Integer orderNo)
	{
		this.constituency = constituency;
		this.voterReportLevel = voterReportLevel;
		this.reportLevelValue = reportLevelValue;
		this.electionType = electionType;
		this.year = year;
		this.totalBooths = totalBooths;
		this.totalVotes = totalVotes;
		this.votesPolled = votesPolled;
		this.orderNo = orderNo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="voting_trendz_id", unique=true, nullable=false)
	public Long getVotingTrendzId() {
		return votingTrendzId;
	}

	public void setVotingTrendzId(Long votingTrendzId) {
		this.votingTrendzId = votingTrendzId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="report_level_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterReportLevel getVoterReportLevel() {
		return voterReportLevel;
	}

	public void setVoterReportLevel(VoterReportLevel voterReportLevel) {
		this.voterReportLevel = voterReportLevel;
	}

	@Column(name="report_level_value")
	public Long getReportLevelValue() {
		return reportLevelValue;
	}

	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="election_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionType getElectionType() {
		return electionType;
	}

	public void setElectionType(ElectionType electionType) {
		this.electionType = electionType;
	}

	@Column(name="year")
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	@Column(name="total_booths")
	public Long getTotalBooths() {
		return totalBooths;
	}

	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}

	@Column(name="total_votes")
	public Long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	@Column(name="votes_polled")
	public Long getVotesPolled() {
		return votesPolled;
	}

	public void setVotesPolled(Long votesPolled) {
		this.votesPolled = votesPolled;
	}

	@Column(name="order_no")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
}
