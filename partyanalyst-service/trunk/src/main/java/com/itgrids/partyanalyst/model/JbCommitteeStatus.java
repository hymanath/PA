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
@Table(name = "jb_committee_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeStatus {

	private Long jbCommitteeStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_status_id",unique = true,nullable = false)
	public Long getJbCommitteeStatusId() {
		return jbCommitteeStatusId;
	}

	public void setJbCommitteeStatusId(Long jbCommitteeStatusId) {
		this.jbCommitteeStatusId = jbCommitteeStatusId;
	}
	
    @Column(name = "status")
	public String getStatus() {
		return status;
	}
    public void setStatus(String status) {
		this.status = status;
	}
    
	
}
