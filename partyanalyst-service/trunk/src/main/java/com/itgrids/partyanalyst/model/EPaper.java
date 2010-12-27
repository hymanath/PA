/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "epaper")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EPaper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long epaperId;
	private String name;
	private String description;
	private String classification;
	private String stateUrl;
	private String countryUrl;
	private Long stateId;
	private Long countryId;
	private String language;
	private String image;
	private Set<EPaperUrl> epaperUrl = new HashSet<EPaperUrl>(0);
	
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
	public EPaper(String name,String description,String classification,String stateUrl,String countryUrl,
			Long stateId,Long countryId,String language,String image,Set<EPaperUrl> epaperUrl){
		this.name = name;
		this.description = description;
		this.classification = classification;
		this.stateUrl = stateUrl;
		this.countryUrl = countryUrl;
		this.stateId = stateId;
		this.countryId = countryId;
		this.language = language;
		this.image = image;
		this.epaperUrl = epaperUrl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "epaper_id", unique = true, nullable = false)
	public Long getEpaperId() {
		return epaperId;
	}
	
	@Column(name = "name", length=200)
	public String getName() {
		return name;
	}
	
	@Column(name = "description", length=200)
	public String getDescription() {
		return description;
	}

	@Column(name = "classification", length=200)
	public String getClassification() {
		return classification;
	}

	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}

	@Column(name = "country_id")
	public Long getCountryId() {
		return countryId;
	}

	@Column(name = "state_url", length=200)
	public String getStateUrl() {
		return stateUrl;
	}

	@Column(name = "country_url", length=200)
	public String getCountryUrl() {
		return countryUrl;
	}
	
	@Column(name = "language", length=200)
	public String getLanguage() {
		return language;
	}
	
	@Column(name = "image", length=200)
	public String getImage() {
		return image;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "epaper")
	public Set<EPaperUrl> getEpaperUrl() {
		return epaperUrl;
	}

	public void setEpaperUrl(Set<EPaperUrl> epaperUrl) {
		this.epaperUrl = epaperUrl;
	}

	public void setEpaperId(Long epaperId) {
		this.epaperId = epaperId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public void setStateUrl(String stateUrl) {
		this.stateUrl = stateUrl;
	}

	public void setCountryUrl(String countryUrl) {
		this.countryUrl = countryUrl;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
}
