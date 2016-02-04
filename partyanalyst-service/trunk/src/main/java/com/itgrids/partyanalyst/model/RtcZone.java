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
@Table(name="rtc_zone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RtcZone extends BaseModel implements Serializable{
	
	private Long rtcZoneId;
	private String zoneName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rtc_zone_id", unique=true, nullable=false)
	public Long getRtcZoneId() {
		return rtcZoneId;
	}
	public void setRtcZoneId(Long rtcZoneId) {
		this.rtcZoneId = rtcZoneId;
	}
	
	@Column(name="zone_name")
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
}
