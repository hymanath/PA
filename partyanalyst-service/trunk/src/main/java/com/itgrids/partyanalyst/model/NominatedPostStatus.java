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
@Table(name = "nominated_post_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostStatus extends BaseModel implements Serializable{

	private Long nominatedPostStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_status_id", unique = true, nullable = false)
	public Long getNominatedPostStatusId() {
		return nominatedPostStatusId;
	}
	public void setNominatedPostStatusId(Long nominatedPostStatusId) {
		this.nominatedPostStatusId = nominatedPostStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
