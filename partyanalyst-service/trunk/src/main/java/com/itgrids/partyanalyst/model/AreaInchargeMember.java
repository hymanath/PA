package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "area_incharge_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AreaInchargeMember extends BaseModel implements Serializable{
	
	private Long areaInchargeMemberId;
	private Long areaInchargeLocationId;
	private AreaInchargeLocation areaInchargeLocation;
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
	private String isActive;
	private String isDeleted;
	private Date fromDate;
	private Date toDate;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_incharge_member_id", unique = true, nullable = false)
	public Long getAreaInchargeMemberId() {
		return areaInchargeMemberId;
	}
	public void setAreaInchargeMemberId(Long areaInchargeMemberId) {
		this.areaInchargeMemberId = areaInchargeMemberId;
	}
	@Column(name="area_incharge_location_id")
	public Long getAreaInchargeLocationId() {
		return areaInchargeLocationId;
	}
	public void setAreaInchargeLocationId(Long areaInchargeLocationId) {
		this.areaInchargeLocationId = areaInchargeLocationId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "area_incharge_location_id",insertable = false,updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AreaInchargeLocation getAreaInchargeLocation() {
		return areaInchargeLocation;
	}
	public void setAreaInchargeLocation(AreaInchargeLocation areaInchargeLocation) {
		this.areaInchargeLocation = areaInchargeLocation;
	}
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "tdp_cadre_id",insertable = false,updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
}
