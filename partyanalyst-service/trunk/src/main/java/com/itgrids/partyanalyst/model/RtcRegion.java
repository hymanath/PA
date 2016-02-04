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
@Table(name="rtc_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RtcRegion extends BaseModel implements Serializable{

	private Long rtcRegionId;
	private String regionName;
	private Long rtcZoneId;
	
	private RtcZone rtcZone;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rtc_region_id", unique=true, nullable=false)
	public Long getRtcRegionId() {
		return rtcRegionId;
	}
	public void setRtcRegionId(Long rtcRegionId) {
		this.rtcRegionId = rtcRegionId;
	}
	
	@Column(name="region_name")
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@Column(name="rtc_zone_id")
	public Long getRtcZoneId() {
		return rtcZoneId;
	}
	public void setRtcZoneId(Long rtcZoneId) {
		this.rtcZoneId = rtcZoneId;
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
}
