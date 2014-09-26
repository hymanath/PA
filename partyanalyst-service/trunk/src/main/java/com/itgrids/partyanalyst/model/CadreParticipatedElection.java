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


/**
 * 
 * @author Prasad Thiragabathina
 *
 */

@Entity
@Table(name = "cadre_participated_election")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreParticipatedElection {

	private Long 				cadreParticipatedElectionId;
	private Long 				tdpCadreId;
	private Long 				electionId;
	private Long 				constituencyId;
	
	private TdpCadre 			tdpCadre;
	private Election 			election;
	private Constituency 		constituency;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_participated_election_id", unique = true, nullable = false)
	public Long getCadreParticipatedElectionId() {
		return cadreParticipatedElectionId;
	}
	public void setCadreParticipatedElectionId(Long cadreParticipatedElectionId) {
		this.cadreParticipatedElectionId = cadreParticipatedElectionId;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="election_id")
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "election_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
	
	 
}
