package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "committee_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommitteeType {
	
	private Long committeeTypeId;
	private String committeeType;
	public CommitteeType()
	{
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="committee_type_id", unique=true, nullable=false)
	public Long getCommitteeTypeId() {
		return committeeTypeId;
	}
	
	public void setCommitteeTypeId(Long committeeTypeId) {
		this.committeeTypeId = committeeTypeId;
	}
	@Column(name="committee_type")
	public String getCommitteeType() {
		return committeeType;
	}
	public void setCommitteeType(String committeeType) {
		this.committeeType = committeeType;
	}
	
	

}
