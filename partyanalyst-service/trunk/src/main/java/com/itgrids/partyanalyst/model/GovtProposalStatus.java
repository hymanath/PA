package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "govt_proposal_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtProposalStatus extends BaseModel implements Serializable {
	
	private Long govtProposalStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_proposal_status_id", unique = true, nullable = false)
	public Long getGovtProposalStatusId() {
		return govtProposalStatusId;
	}
	public void setGovtProposalStatusId(Long govtProposalStatusId) {
		this.govtProposalStatusId = govtProposalStatusId;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
