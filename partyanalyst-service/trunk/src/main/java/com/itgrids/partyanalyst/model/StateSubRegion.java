package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name="state_sub_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StateSubRegion extends BaseModel  {
	
	private Long stateSubRegionId;
	private String name;
	private StateRegion stateRegion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="state_sub_region_id", unique=true, nullable=false)
	public Long getStateSubRegionId() {
		return stateSubRegionId;
	}
	public void setStateSubRegionId(Long stateSubRegionId) {
		this.stateSubRegionId = stateSubRegionId;
	}
	
	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_region_id")
	public StateRegion getStateRegion() {
		return stateRegion;
	}
	public void setStateRegion(StateRegion stateRegion) {
		this.stateRegion = stateRegion;
	}
	
}
