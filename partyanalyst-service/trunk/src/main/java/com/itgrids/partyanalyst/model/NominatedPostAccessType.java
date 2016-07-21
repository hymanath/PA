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
@Table(name="nominated_post_access_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostAccessType implements java.io.Serializable {
	
	private Long nominatedPostAccessTypeId;
	private String accessType;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_access_type_id", unique = true, nullable = false)
	public Long getNominatedPostAccessTypeId() {
		return nominatedPostAccessTypeId;
	}
	public void setNominatedPostAccessTypeId(Long nominatedPostAccessTypeId) {
		this.nominatedPostAccessTypeId = nominatedPostAccessTypeId;
	}
	@Column(name = "access_type")
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
}
