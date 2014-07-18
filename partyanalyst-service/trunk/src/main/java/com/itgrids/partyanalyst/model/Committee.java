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
@Table(name = "committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Committee {
	private Long committeeId;
	private String name;
	private BasicCommittee basicCommittee;
	private CommitteeLevel committeeLevel;
	private Long commiiteeLevelValue;
	
	public Committee()
	{
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="committee_id", unique=true, nullable=false)
	public Long getCommitteeId() {
		return committeeId;
	}

	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="basic_committee_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BasicCommittee getBasicCommittee() {
		return basicCommittee;
	}

	public void setBasicCommittee(BasicCommittee basicCommittee) {
		this.basicCommittee = basicCommittee;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="commiitee_level_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommitteeLevel getCommitteeLevel() {
		return committeeLevel;
	}

	public void setCommitteeLevel(CommitteeLevel committeeLevel) {
		this.committeeLevel = committeeLevel;
	}
	@Column(name="commiitee_level_value")
	public Long getCommiiteeLevelValue() {
		return commiiteeLevelValue;
	}

	public void setCommiiteeLevelValue(Long commiiteeLevelValue) {
		this.commiiteeLevelValue = commiiteeLevelValue;
	}
	
	
	
}
