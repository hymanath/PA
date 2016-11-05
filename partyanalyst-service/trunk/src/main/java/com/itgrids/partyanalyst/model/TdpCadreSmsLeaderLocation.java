package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "tdp_cadre_sms_leader_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreSmsLeaderLocation  extends BaseModel implements Serializable{

	private Long tdpCadreSmsLeaderLocationId;
	private Long tdpCadreSmsLeaderId;
	private Long locationScopeId;
	private Long locationValue;
	private Long isDeleted;
	private String locationName;
	
	private TdpCadreSmsLeader tdpCadreSmsLeader;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_sms_leader_location_id", unique = true, nullable = false)
	public Long getTdpCadreSmsLeaderLocationId() {
		return tdpCadreSmsLeaderLocationId;
	}
	public void setTdpCadreSmsLeaderLocationId(Long tdpCadreSmsLeaderLocationId) {
		this.tdpCadreSmsLeaderLocationId = tdpCadreSmsLeaderLocationId;
	}
	
	@Column(name = "tdp_cadre_sms_leader_id")
	public Long getTdpCadreSmsLeaderId() {
		return tdpCadreSmsLeaderId;
	}
	public void setTdpCadreSmsLeaderId(Long tdpCadreSmsLeaderId) {
		this.tdpCadreSmsLeaderId = tdpCadreSmsLeaderId;
	}
	
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "is_deleted")
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_sms_leader_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public TdpCadreSmsLeader getTdpCadreSmsLeader() {
		return tdpCadreSmsLeader;
	}
	public void setTdpCadreSmsLeader(TdpCadreSmsLeader tdpCadreSmsLeader){
		this.tdpCadreSmsLeader = tdpCadreSmsLeader;
	}
	@Column(name = "location_name")
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
}
