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
@Table(name="tdp_cadre_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreLocation extends BaseModel implements Serializable{

	private Long tdpCadreLocationId;
	private Long rtcZoneId;
	private Long rtcRegionId;
	private Long rtcDepotId;
	
	private RtcZone rtcZone;
	private RtcRegion rtcRegion;
	private RtcDepot rtcDepot;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tdp_cadre_location_id", unique=true, nullable=false)
	public Long getTdpCadreLocationId() {
		return tdpCadreLocationId;
	}
	public void setTdpCadreLocationId(Long tdpCadreLocationId) {
		this.tdpCadreLocationId = tdpCadreLocationId;
	}
	
	@Column(name="rtc_zone_id")
	public Long getRtcZoneId() {
		return rtcZoneId;
	}
	public void setRtcZoneId(Long rtcZoneId) {
		this.rtcZoneId = rtcZoneId;
	}
	
	@Column(name="rtc_region_id")
	public Long getRtcRegionId() {
		return rtcRegionId;
	}
	public void setRtcRegionId(Long rtcRegionId) {
		this.rtcRegionId = rtcRegionId;
	}
	
	@Column(name="rtc_depot_id")
	public Long getRtcDepotId() {
		return rtcDepotId;
	}
	public void setRtcDepotId(Long rtcDepotId) {
		this.rtcDepotId = rtcDepotId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="rtc_zone_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RtcZone getRtcZone() {
		return rtcZone;
	}
	public void setRtcZone(RtcZone rtcZone) {
		this.rtcZone = rtcZone;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="rtc_depot_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RtcDepot getRtcDepot() {
		return rtcDepot;
	}
	public void setRtcDepot(RtcDepot rtcDepot) {
		this.rtcDepot = rtcDepot;
	}
}
