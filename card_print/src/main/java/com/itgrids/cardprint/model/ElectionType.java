package com.itgrids.cardprint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "election_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionType extends BaseModel implements java.io.Serializable {

	
	private static final long serialVersionUID = -3703578759043525036L;
	
	private Long electionTypeId;
	private String electionType;
	private String scope;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_type_id", unique = true, nullable = false)
	public Long getElectionTypeId() {
		return this.electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	@Column(name = "election_type", length = 25)
	public String getElectionType() {
		return this.electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	@Column(name = "scope", length = 25)
	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
