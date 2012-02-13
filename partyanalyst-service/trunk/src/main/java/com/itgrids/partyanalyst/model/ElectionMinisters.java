package com.itgrids.partyanalyst.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * election_ministers entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="election_ministers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionMinisters extends BaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 3042206593648529011L;
	
	private Long electionMinistersId;
	private Election election;
	private MinistryScope ministryScope;
	private Candidate candidate;
	private String status;
	private Date fromDate;
	private Date toDate;
	
	public ElectionMinisters()
	{}
	
	public ElectionMinisters(Election election,MinistryScope ministryScope,Candidate candidate,
			String status,Date fromDate,Date toDate)
	{
		this.election = election;
		this.ministryScope = ministryScope;
		this.candidate = candidate;
		this.status = status;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="election_ministers_id", unique=true, nullable=false)
	public Long getElectionMinistersId() {
		return electionMinistersId;
	}

	public void setElectionMinistersId(Long electionMinistersId) {
		this.electionMinistersId = electionMinistersId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ministry_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MinistryScope getMinistryScope() {
		return ministryScope;
	}

	public void setMinistryScope(MinistryScope ministryScope) {
		this.ministryScope = ministryScope;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Column(name="status",length=20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "from_date", length = 10)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "to_date", length = 10)
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
}
