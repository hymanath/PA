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

@Entity
@Table(name = "committee_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommitteeMember {

	
	private Long committeeMemberId;
	private CommitteeRole committeeRole;
	private Cadre cadre;
	private Date fromDate;
	private Date toDate;
	private String isActive;
	
	
	
	public CommitteeMember()
	{
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="committee_member_id", unique=true, nullable=false)
	
	public Long getCommitteeMemberId() {
		return committeeMemberId;
	}
	public void setCommitteeMemberId(Long committeeMemberId) {
		this.committeeMemberId = committeeMemberId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="committee_role_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommitteeRole getCommitteeRole() {
		return committeeRole;
	}
	public void setCommitteeRole(CommitteeRole committeeRole) {
		this.committeeRole = committeeRole;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Cadre getCadre() {
		return cadre;
	}
	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "from_date")
	public Date getFromDate() {
		return fromDate;
	}	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
	
	
}

