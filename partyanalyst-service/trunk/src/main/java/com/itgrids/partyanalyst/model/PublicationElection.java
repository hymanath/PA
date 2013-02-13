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
@Table(name="publication_election")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicationElection extends BaseModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5412310508513250224L;
	
	private Long publicationElectionId;
	private PublicationDate publicationDate;
	private Election election;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "publication_election_id", unique = true, nullable = false)
	public Long getPublicationElectionId() {
		return publicationElectionId;
	}

	public void setPublicationElectionId(Long publicationElectionId) {
		this.publicationElectionId = publicationElectionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_date_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return this.election;
	}

	public void setElection(Election election) {
		this.election = election;
	}
}
