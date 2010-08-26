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
 * Occupation entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "occupation")
public class Occupation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long occupationId;
	private String occupation;
	
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
	
}
