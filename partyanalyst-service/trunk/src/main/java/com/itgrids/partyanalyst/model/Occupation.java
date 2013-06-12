/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 25, 2010
 */
package com.itgrids.partyanalyst.model;

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
/**
 * Occupation entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "occupation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Occupation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long occupationId;
	private String occupation;
	private Set<Cadre> cadres = new HashSet<Cadre>(0);
	private Set<SurveyorProfile> surveyorProfile = new HashSet<SurveyorProfile>(0);
	
	public Occupation() {
		super();
	
	}

	public Occupation(Long occupationId, String occupation) {
		super();
		this.occupationId = occupationId;
		this.occupation = occupation;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "occupation_id", nullable = false, unique = true)
	public Long getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}

	@Column(name = "occupation", length = 100 )
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "occupation")
	public Set<Cadre> getCadres() {
		return cadres;
	}

	public void setCadres(Set<Cadre> cadres) {
		this.cadres = cadres;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="occupation")
	public Set<SurveyorProfile> getSurveyorProfile() {
		return surveyorProfile;
	}

	public void setSurveyorProfile(Set<SurveyorProfile> surveyorProfile) {
		this.surveyorProfile = surveyorProfile;
	}
	
	
	
}
