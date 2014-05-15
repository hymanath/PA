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
@Table(name="state_sub_region_district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StateSubRegionDistrict extends BaseModel {

	private Long stateSubRegionDistrictId;
	private StateSubRegion stateSubRegion;
	private District district;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="state_sub_region_district_id", unique=true, nullable=false)
	public Long getStateSubRegionDistrictId() {
		return stateSubRegionDistrictId;
	}
	public void setStateSubRegionDistrictId(Long stateSubRegionDistrictId) {
		this.stateSubRegionDistrictId = stateSubRegionDistrictId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_sub_region_id")
	public StateSubRegion getStateSubRegion() {
		return stateSubRegion;
	}
	public void setStateSubRegion(StateSubRegion stateSubRegion) {
		this.stateSubRegion = stateSubRegion;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	
}
