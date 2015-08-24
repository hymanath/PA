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
@Table(name = "tdp_cadre_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreCandidate extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 5434458773890660518L;
	
	private Long    tdpCadreCandidateId;
	private TdpCadre tdpCadre;
	private Candidate candidate;
	private Long tdpCadreId;
	private Long candidateId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_candidate_id", unique = true, nullable = false)
	public Long getTdpCadreCandidateId() {
		return tdpCadreCandidateId;
	}
	public void setTdpCadreCandidateId(Long tdpCadreCandidateId) {
		this.tdpCadreCandidateId = tdpCadreCandidateId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "candidate_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	@Column(name = "tdp_cadre_id" )
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "candidate_id" )
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	
}
