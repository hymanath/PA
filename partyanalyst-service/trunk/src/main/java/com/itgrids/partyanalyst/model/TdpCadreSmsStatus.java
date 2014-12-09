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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "tdp_cadre_sms_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreSmsStatus {
	private Long tdpCadreSmsStatusId;
	private String trNumber;
	private String mobileNo;
	private String smsStatus;
	private TdpCadre tdpCadre;
	private Long  tdpCadreId;
	public TdpCadreSmsStatus()
	{
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_sms_status_id", unique = true, nullable = false)
	public Long getTdpCadreSmsStatusId() {
		return tdpCadreSmsStatusId;
	}

	public void setTdpCadreSmsStatusId(Long tdpCadreSmsStatusId) {
		this.tdpCadreSmsStatusId = tdpCadreSmsStatusId;
	}
	@Column(name="tr_number")
	public String getTrNumber() {
		return trNumber;
	}

	public void setTrNumber(String trNumber) {
		this.trNumber = trNumber;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="sms_status")
	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}

	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	
}
