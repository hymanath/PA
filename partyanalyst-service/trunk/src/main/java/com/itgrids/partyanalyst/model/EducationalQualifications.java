/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 25, 2010
 */
package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Long educational_qualification_id;
	private String qualification;
	
	public EducationalQualifications() {
		super();
		
	}

	public EducationalQualifications(Long educational_qualification_id,
			String qualification) {
		
		this.educational_qualification_id = educational_qualification_id;
		this.qualification = qualification;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "educational_qualification_id", nullable = false, unique = true)
	public Long getEducational_qualification_id() {
		return educational_qualification_id;
	}

	public void setEducational_qualification_id(Long educational_qualification_id) {
		this.educational_qualification_id = educational_qualification_id;
	}

	@Column(name="qualification", length=75)
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
}
