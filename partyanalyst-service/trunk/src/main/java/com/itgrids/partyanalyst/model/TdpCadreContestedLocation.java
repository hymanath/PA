package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "tdp_cadre_contested_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreContestedLocation extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long tdpCadreContestedLocationId;
	private TdpCadreCandidate tdpCadreCandidate;
	private Long tdpCadreCandidateId;
	private ConstituencyElection constituencyElection;
	private Long constituencyElectionId;
	private Date fromDate;
	private Date toDate;
	private String isActive;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_contested_location_id", unique = true, nullable = false)
	public Long getTdpCadreContestedLocationId() {
		return tdpCadreContestedLocationId;
	}
	public void setTdpCadreContestedLocationId(Long tdpCadreContestedLocationId) {
		this.tdpCadreContestedLocationId = tdpCadreContestedLocationId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_candidate_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadreCandidate getTdpCadreCandidate() {
		return tdpCadreCandidate;
	}
	public void setTdpCadreCandidate(TdpCadreCandidate tdpCadreCandidate) {
		this.tdpCadreCandidate = tdpCadreCandidate;
	}
	
	@Column(name = "tdp_cadre_candidate_id" )
	public Long getTdpCadreCandidateId() {
		return tdpCadreCandidateId;
	}
	public void setTdpCadreCandidateId(Long tdpCadreCandidateId) {
		this.tdpCadreCandidateId = tdpCadreCandidateId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_election_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ConstituencyElection getConstituencyElection() {
		return constituencyElection;
	}
	public void setConstituencyElection(ConstituencyElection constituencyElection) {
		this.constituencyElection = constituencyElection;
	}
	
	@Column(name = "constituency_election_id" )
	public Long getConstituencyElectionId() {
		return constituencyElectionId;
	}
	public void setConstituencyElectionId(Long constituencyElectionId) {
		this.constituencyElectionId = constituencyElectionId;
	}
	
	@Column(name = "from_date" )
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name = "to_date" )
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Column(name = "is_active" )
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
