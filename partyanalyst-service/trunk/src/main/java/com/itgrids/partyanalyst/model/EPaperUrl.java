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
@Table(name = "epaper_url")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EPaperUrl implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long epaperUrlId;
	private Long districtId;
	private String districtUrl;
	private EPaper epaper;
	
	// Constructors

	/** 
	 * Default Constructor 
	 */
	public EPaperUrl(){			
	}
	
	/** 
	 * Minimal Constructor
	 */
	
	public EPaperUrl(Long epaperUrlId){
		this.epaperUrlId = epaperUrlId;
	}

	
	/**
	 *  Full Constructor with all parameter
	 * */

	public EPaperUrl(String districtUrl,String stateUrl,String countryUrl,Long districtId,EPaper epaper){	
		this.districtUrl = districtUrl;
		this.districtId = districtId;
		this.epaper = epaper;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "epaperurl_id", unique = true, nullable = false)
	public Long getEpaperUrlId() {
		return epaperUrlId;
	}

	@Column(name = "district_url", length=200)
	public String getDistrictUrl() {
		return districtUrl;
	}

	@Column(name = "district_id", length=200)
	public Long getDistrictId() {
		return districtId;
	}


	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "epaper_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EPaper getEpaper() {
		return epaper;
	}

	public void setEpaperUrlId(Long epaperUrlId) {
		this.epaperUrlId = epaperUrlId;
	}

	public void setDistrictUrl(String districtUrl) {
		this.districtUrl = districtUrl;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public void setEpaper(EPaper epaper) {
		this.epaper = epaper;
	}
	
	
}
