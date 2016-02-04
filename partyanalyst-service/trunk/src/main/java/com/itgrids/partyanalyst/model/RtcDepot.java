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
@Table(name="rtc_depot")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RtcDepot extends BaseModel implements Serializable{

	private Long rtcDepotId;
	private String depotName;
	private Long rtcRegionId;
	
	private RtcRegion rtcRegion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rtc_depot_id", unique=true, nullable=false)
	public Long getRtcDepotId() {
		return rtcDepotId;
	}
	public void setRtcDepotId(Long rtcDepotId) {
		this.rtcDepotId = rtcDepotId;
	}
	
	@Column(name="depot_name")
	public String getDepotName() {
		return depotName;
	}
	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}
	
	@Column(name="rtc_region_id")
	public Long getRtcRegionId() {
		return rtcRegionId;
	}
	public void setRtcRegionId(Long rtcRegionId) {
		this.rtcRegionId = rtcRegionId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="rtc_region_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RtcRegion getRtcRegion() {
		return rtcRegion;
	}
	public void setRtcRegion(RtcRegion rtcRegion) {
		this.rtcRegion = rtcRegion;
	}
}
