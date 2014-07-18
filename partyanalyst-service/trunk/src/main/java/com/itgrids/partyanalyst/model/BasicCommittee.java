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
@Table(name = "basic_committee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BasicCommittee {
	private Long basicCommitteeId;
	private String name;
	private CommitteeType committeeType;
	
	
	public BasicCommittee()
	{
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="basic_committee_id", unique=true, nullable=false)
	public Long getBasicCommitteeId() {
		return basicCommitteeId;
	}


	public void setBasicCommitteeId(Long basicCommitteeId) {
		this.basicCommitteeId = basicCommitteeId;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="committee_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommitteeType getCommitteeType() {
		return committeeType;
	}


	public void setCommitteeType(CommitteeType committeeType) {
		this.committeeType = committeeType;
	}
	
	

}
