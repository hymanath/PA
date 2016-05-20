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
@Table(name = "acceptance_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AcceptanceStatus extends BaseModel implements Serializable{

	private Long acceptanceStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acceptance_status_id", unique = true, nullable = false)
	public Long getAcceptanceStatusId() {
		return acceptanceStatusId;
	}
	public void setAcceptanceStatusId(Long acceptanceStatusId) {
		this.acceptanceStatusId = acceptanceStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
