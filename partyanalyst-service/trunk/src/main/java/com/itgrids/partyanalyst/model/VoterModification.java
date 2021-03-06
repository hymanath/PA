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
 * voter_modification entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */


@Entity
@Table(name="voter_modification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterModification extends BaseModel implements Serializable{

	private static final long serialVersionUID = -211727195268950291L;
	
	private Long voterModificationId;
	private Voter voter;
	private String status;
	private Long partNo;
	private PublicationDate publicationDate;
	private Constituency constituency;
	private Long voterId;
	private Long publicationDateId;
	private Long constituencyId;
	private VoterStatus voterStatus;
	private Long voterStatusId;
	
	public VoterModification()
	{}
	
	public VoterModification(Voter voter,String status, Booth booth,PublicationDate publicationDate)
	{
		this.voter = voter;
		this.status = status;
		this.publicationDate = publicationDate;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="voter_modification_id", unique=true, nullable=false)
	public Long getVoterModificationId() {
		return voterModificationId;
	}
	public void setVoterModificationId(Long voterModificationId) {
		this.voterModificationId = voterModificationId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@Column(name="status",length=10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="publication_date_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	@Column(name = "publication_date_id")
	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Column(name="part_no")
	public Long getPartNo() {
		return partNo;
	}

	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_status_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterStatus getVoterStatus() {
		return voterStatus;
	}

	public void setVoterStatus(VoterStatus voterStatus) {
		this.voterStatus = voterStatus;
	}

	@Column(name = "voter_status_id", length = 10)
	public Long getVoterStatusId() {
		return voterStatusId;
	}

	public void setVoterStatusId(Long voterStatusId) {
		this.voterStatusId = voterStatusId;
	}

	
}
