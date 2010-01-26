/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
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

@Entity
@Table(name = "epapers")
public class EPaper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long epaperId;
	private String epaperUrl;
	private String mainUrl;
	private String language;
	private String image;
	private District district;
	private State state;
	
	// Constructors

	/** 
	 * Default Constructor 
	 */
	public EPaper(){			
	}
	
	/** 
	 * Minimal Constructor
	 */
	
	public EPaper(Long epaperId){
		this.epaperId = epaperId;
	}

	
	/**
	 *  Full Constructor with all parameter
	 * */
	public EPaper(String epaperUrl,String mainUrl,District district,State state,String language,String image){
		this.epaperUrl = epaperUrl;
		this.mainUrl = mainUrl;
		this.district = district;
		this.state = state;
		this.language = language;
		this.image = image;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "epapers_id", unique = true, nullable = false)
	public Long getEpaperId() {
		return epaperId;
	}

	@Column(name = "epapers_url", length=200)
	public String getEpaperUrl() {
		return epaperUrl;
	}
	@Column(name = "main_url", length=200)
	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
	
	public void setEpaperId(Long epaperId) {
		this.epaperId = epaperId;
	}

	public void setEpaperUrl(String epaperUrl) {
		this.epaperUrl = epaperUrl;
	}

	@Column(name = "lang", length=200)
	public String getLanguage() {
		return language;
	}

	@Column(name = "image", length=200)
	public String getImage() {
		return image;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setImage(String image) {
		this.image = image;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	public District getDistrict() {
		return district;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public void setState(State state) {
		this.state = state;
	}
	

}
