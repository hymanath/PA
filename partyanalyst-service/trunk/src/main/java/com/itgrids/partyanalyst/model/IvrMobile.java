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
@Table(name = "ivr_mobile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrMobile extends BaseModel implements Serializable{

	private Long ivrMobileId ;
	private District district;
	private Constituency constituency;
	private Panchayat panchayat;
	private Tehsil tehsil;
	private Long mobileNo;
	
	public IvrMobile()
	{
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_mobile_id ", unique = true, nullable = false)
	public Long getIvrMobileId() {
		return ivrMobileId;
	}

	public void setIvrMobileId(Long ivrMobileId) {
		this.ivrMobileId = ivrMobileId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	@Column(name="mobile_no")
	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	
}
