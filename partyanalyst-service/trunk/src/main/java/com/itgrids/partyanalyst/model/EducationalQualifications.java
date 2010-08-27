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
/**
 * EducationalQualifications entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "educational_qualifications" )

public class EducationalQualifications implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long eduQualificationId;
	private String qualification;
	private Set<Cadre> cadres = new HashSet<Cadre>(0);
	
	public EducationalQualifications() {
		super();
		
	}

	public EducationalQualifications(Long eduQualificationId,
			String qualification) {
		
		this.eduQualificationId = eduQualificationId;
		this.qualification = qualification;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "educational_qualification_id", nullable = false, unique = true)
	public Long getEducational_qualification_id() {
		return eduQualificationId;
	}

	public void setEducational_qualification_id(Long eduQualificationId) {
		this.eduQualificationId = eduQualificationId;
	}

	@Column(name="qualification", length=75)
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "education")
	public Set<Cadre> getCadres() {
		return cadres;
	}

	public void setCadres(Set<Cadre> cadres) {
		this.cadres = cadres;
	}
	
	
	
}
