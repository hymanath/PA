package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;

@Entity
@Table(name="admin_house")
public class AdminHouse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long adminHouseId;
	private String houseName;
	private Long electionScopeId;
	
	private ElectionScope electionScope;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_house_id", unique = true, nullable = false)
	public Long getAdminHouseId() {
		return adminHouseId;
	}

	public void setAdminHouseId(Long adminHouseId) {
		this.adminHouseId = adminHouseId;
	}
	@Column(name = "house_name")
	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	@Column(name = "election_scope_id")
	public Long getElectionScopeId() {
		return electionScopeId;
	}

	public void setElectionScopeId(Long electionScopeId) {
		this.electionScopeId = electionScopeId;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "election_scope_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionScope getElectionScope() {
		return electionScope;
	}

	public void setElectionScope(ElectionScope electionScope) {
		this.electionScope = electionScope;
	}
	
	

}