/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
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
@Table(name = "problem_external_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemExternalSource extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7857926512574677326L;
	private Long problemExternalSourceId;
	private String name;
	private String mobile;
	private String email;
	private String address;
	private String telePhone;
	private Set<ProblemAndProblemSource> problemAndProblemSources = new HashSet<ProblemAndProblemSource>(0);
	
	//default constructor
	public ProblemExternalSource(){
		
	}
	
	//parameterized constructor
    public ProblemExternalSource(Long problemExternalSourceId){
		this.problemExternalSourceId = problemExternalSourceId;
	}
	
    //parameterized constructor
    public ProblemExternalSource(String name,String mobile,String email,String address, Set<ProblemAndProblemSource> problemAndProblemSources){
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.problemAndProblemSources = problemAndProblemSources;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_external_source_id", unique = true, nullable = false)
	public Long getProblemExternalSourceId() {
		return problemExternalSourceId;
	}

	public void setProblemExternalSourceId(Long problemExternalSourceId) {
		this.problemExternalSourceId = problemExternalSourceId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "mobile", length = 12)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemExternalSource")
	public Set<ProblemAndProblemSource> getProblemAndProblemSources() {
		return problemAndProblemSources;
	}

	public void setProblemAndProblemSources(Set<ProblemAndProblemSource> problemAndProblemSources) {
		this.problemAndProblemSources = problemAndProblemSources;
	}
	@Column(name = "telephone", length = 12)
	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	
    
}
