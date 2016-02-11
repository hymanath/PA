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
@Table(name = "ivr_respondent_cadre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrRespondentCadre {

	
	private Long ivrRespondentCadreId;
	private Long ivrRespondentId;
	private Long voterId;
    private Long isVerified;
	private String isMultiple;
	
	private Voter voter;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_respondent_cadre_id", unique = true, nullable = false)
	public Long getIvrRespondentCadreId() {
		return ivrRespondentCadreId;
	}
	public void setIvrRespondentCadreId(Long ivrRespondentCadreId) {
		this.ivrRespondentCadreId = ivrRespondentCadreId;
	}
	@Column(name = "ivr_respondent_id")
	public Long getIvrRespondentId() {
		return ivrRespondentId;
	}
	public void setIvrRespondentId(Long ivrRespondentId) {
		this.ivrRespondentId = ivrRespondentId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "voter_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	@Column(name = "is_verified")
	public Long getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Long isVerified) {
		this.isVerified = isVerified;
	}
	@Column(name = "is_multiple")
	public String getIsMultiple() {
		return isMultiple;
	}
	public void setIsMultiple(String isMultiple) {
		this.isMultiple = isMultiple;
	}
}
