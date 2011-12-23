/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * Tehsil entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "tehsil")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tehsil implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -5349665789702584805L;
	
	// Fields
	
	private Long tehsilId;
	private District district;
	private String tehsilName;
	private Long tehsilCode;
	private Date startDate;
	private String deformDate;
	private Set<Township> townships = new HashSet<Township>(0);
	private Set<Booth> booths = new HashSet<Booth>(0);
	private Set<Constituency> constituencies = new HashSet<Constituency>(0);
	private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	private Set<Panchayat> pachayats = new HashSet<Panchayat>(0);

	// Constructors

	/** default constructor */
	public Tehsil() {
	}

	/** minimal constructor */
	public Tehsil(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	/** full constructor */
	public Tehsil(Long tehsilId, District district, String tehsilName,
			Long tehsilCode, Date startDate, String deformDate,
			Set<Township> townships, Set<Booth> booths,Set<LocalGroupRegion> localGroupRegion) {
		this.tehsilId = tehsilId;
		this.district = district;
		this.tehsilName = tehsilName;
		this.tehsilCode = tehsilCode;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.townships = townships;
		this.booths = booths;
		this.localGroupRegion = localGroupRegion;
	}

	// Property accessors
	@Id
	@Column(name = "tehsil_id", unique = true, nullable = false)
	public Long getTehsilId() {
		return this.tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id")
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Column(name = "tehsil_name", length = 100)
	public String getTehsilName() {
		return this.tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	@Column(name = "tehsil_code")
	public Long getTehsilCode() {
		return this.tehsilCode;
	}

	public void setTehsilCode(Long tehsilCode) {
		this.tehsilCode = tehsilCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "deform_date", length = 25)
	public String getDeformDate() {
		return this.deformDate;
	}

	public void setDeformDate(String deformDate) {
		this.deformDate = deformDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tehsil")
	public Set<Township> getTownships() {
		return this.townships;
	}

	public void setTownships(Set<Township> townships) {
		this.townships = townships;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tehsil")
	public Set<Booth> getBooths() {
		return booths;
	}

	public void setBooths(Set<Booth> booths) {
		this.booths = booths;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tehsil")
	public Set<Constituency> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(Set<Constituency> constituencies) {
		this.constituencies = constituencies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tehsil")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tehsil")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Panchayat> getPachayats() {
		return pachayats;
	}

	public void setPachayats(Set<Panchayat> pachayats) {
		this.pachayats = pachayats;
	}	

}