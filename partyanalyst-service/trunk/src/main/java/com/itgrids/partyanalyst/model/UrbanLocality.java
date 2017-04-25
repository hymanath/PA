package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "urban_locality")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UrbanLocality extends BaseModel implements Serializable{

	private Long urbanLocalityId;
	private String areaName;
	private Long localElectionBodyId;
	private LocalElectionBody localElectionBody;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "urban_locality_id", unique = true, nullable = false)
	public Long getUrbanLocalityId() {
		return urbanLocalityId;
	}
	public void setUrbanLocalityId(Long urbanLocalityId) {
		this.urbanLocalityId = urbanLocalityId;
	}
	
	@Column(name = "area_name")
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Column(name = "local_election_body_id")
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
}
