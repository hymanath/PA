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
@Table(name = "tdp_cadre_data_verification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreDataVerification extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = 5418111801563159080L;
	
	private Long tdpCadreDataVerificationId;
	private Long tdpCadreId;
	private Long verifiedBy;
	private Date verifiedTime;
	private Long dataRejectReasonId;
	
	private TdpCadre tdpCadre;
	private CadreRegUser cadreRegUser;
	private DataRejectReason dataRejectReason;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "tdp_cadre_data_verification_id", unique = true, nullable = false)
	public Long getTdpCadreDataVerificationId() {
		return tdpCadreDataVerificationId;
	}
	public void setTdpCadreDataVerificationId(Long tdpCadreDataVerificationId) {
		this.tdpCadreDataVerificationId = tdpCadreDataVerificationId;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="verified_by")
	public Long getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(Long verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	
	@Column(name="verified_time")
	public Date getVerifiedTime() {
		return verifiedTime;
	}
	public void setVerifiedTime(Date verifiedTime) {
		this.verifiedTime = verifiedTime;
	}
	
	@Column(name="data_reject_reason_id")
	public Long getDataRejectReasonId() {
		return dataRejectReasonId;
	}
	public void setDataRejectReasonId(Long dataRejectReasonId) {
		this.dataRejectReasonId = dataRejectReasonId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="verified_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegUser getCadreRegUser() {
		return cadreRegUser;
	}
	public void setCadreRegUser(CadreRegUser cadreRegUser) {
		this.cadreRegUser = cadreRegUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="data_reject_reason_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DataRejectReason getDataRejectReason() {
		return dataRejectReason;
	}
	public void setDataRejectReason(DataRejectReason dataRejectReason) {
		this.dataRejectReason = dataRejectReason;
	}
	
	
}
