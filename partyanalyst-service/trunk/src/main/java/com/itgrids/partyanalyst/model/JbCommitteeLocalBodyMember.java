package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "jb_committee_local_body_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeLocalBodyMember extends BaseModel implements java.io.Serializable {

	
	private Long jbCommitteeLocalBodyMemberId;
	private Long jbCommitteeMemberId;
	private Long nominationId;
	private Long candidateId;
	private String isActive;
	
	private JbCommitteeMember jbCommitteeMember;
	private Nomination nomination;
	private Candidate candidate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_local_body_member_id", unique = true, nullable = false)
	public Long getJbCommitteeLocalBodyMemberId() {
		return jbCommitteeLocalBodyMemberId;
	}
	public void setJbCommitteeLocalBodyMemberId(Long jbCommitteeLocalBodyMemberId) {
		this.jbCommitteeLocalBodyMemberId = jbCommitteeLocalBodyMemberId;
	}
	@Column(name="jb_committee_member_id")
	public Long getJbCommitteeMemberId() {
		return jbCommitteeMemberId;
	}
	public void setJbCommitteeMemberId(Long jbCommitteeMemberId) {
		this.jbCommitteeMemberId = jbCommitteeMemberId;
	}
	@Column(name="nomination_id")
	public Long getNominationId() {
		return nominationId;
	}
	public void setNominationId(Long nominationId) {
		this.nominationId = nominationId;
	}
	@Column(name="candidate_id")
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "jb_committee_member_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbCommitteeMember getJbCommitteeMember() {
		return jbCommitteeMember;
	}
	public void setJbCommitteeMember(JbCommitteeMember jbCommitteeMember) {
		this.jbCommitteeMember = jbCommitteeMember;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "nomination_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Nomination getNomination() {
		return nomination;
	}
	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
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
	
}
