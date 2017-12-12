package com.itgrids.partyanalyst.model;

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
@Table(name = "mom_app_user_access")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MomAppUserAccess {
	
	private Long momAppUserAccessId;
	private Long itdpAppUserId;
	private Long accessLevelId;
	private Long accessValue;
	private String isDeleted;
	private RegionScopes accessLevel;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mom_app_user_access_id", unique=true, nullable=false)
	public Long getMomAppUserAccessId() {
		return momAppUserAccessId;
	}
	public void setMomAppUserAccessId(Long momAppUserAccessId) {
		this.momAppUserAccessId = momAppUserAccessId;
	}
	@Column(name = "itdp_app_user_id")
	public Long getItdpAppUserId() {
		return itdpAppUserId;
	}
	public void setItdpAppUserId(Long itdpAppUserId) {
		this.itdpAppUserId = itdpAppUserId;
	}
	@Column(name = "access_level_id")
	public Long getAccessLevelId() {
		return accessLevelId;
	}
	public void setAccessLevelId(Long accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
	@Column(name = "access_value")
	public Long getAccessValue() {
		return accessValue;
	}
	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="access_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(RegionScopes accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	
}
