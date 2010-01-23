/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

@Entity
@Table(name = "problem_external_source")
public class ProblemExternalSource extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7857926512574677326L;
	private Long problemExternalSourceId;
	private String name;
	private String mobile;
	private String telePhone;
	private String email;
	private String address;
	private Set<ProblemSource> problemSource;
	
	//default constructor
	public ProblemExternalSource(){
		
	}
	
	//parameterized constructor
    public ProblemExternalSource(Long problemExternalSourceId){
		this.problemExternalSourceId = problemExternalSourceId;
	}
	
    //parameterized constructor
    public ProblemExternalSource(String name,String mobile,String email,String address, Set<ProblemSource> problemSource){
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.problemSource = problemSource;
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

	@Column(name = "name", length = 25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "mobile", length = 25)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 25)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 25)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemExternalSource")
	public Set<ProblemSource> getProblemSource() {
		return problemSource;
	}

	public void setProblemSource(Set<ProblemSource> problemSource) {
		this.problemSource = problemSource;
	}
	@Column(name = "telephone", length = 25)
	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	
    
    
    
}
