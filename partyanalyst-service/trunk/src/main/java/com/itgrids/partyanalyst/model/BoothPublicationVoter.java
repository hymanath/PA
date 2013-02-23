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
@Entity
@Table(name="booth_publication_voter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothPublicationVoter implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 private Long BoothPublicationVoterId;	
	 private Booth booth;	
	 private Voter voter;
	 private Long boothId;
	 private Long voterId;
	 
	 public BoothPublicationVoter()
	 {}
	 
	 public BoothPublicationVoter(Booth booth ,Voter voter){
		 this.booth = booth;
		 this.voter = voter;
	 }
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="booth_publication_voter_id", unique=true, nullable=false)
	 public Long getBoothPublicationVoterId() {
			return BoothPublicationVoterId;
	}
	public void setBoothPublicationVoterId(Long boothPublicationVoterId) {
		BoothPublicationVoterId = boothPublicationVoterId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	@Column(name = "booth_id", length = 10)
	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	@Column(name = "voter_id", length = 10)
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	 
	 
	 

}
