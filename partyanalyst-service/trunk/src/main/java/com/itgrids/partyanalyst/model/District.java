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
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * District entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "district")
public class District implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 6608505852511099145L;
	
	// Fields
	
	private Long districtId;
	private State state;
	private String districtName;
	private String districtCapital;
	private Double area;
	private Double population;
	private Long districtCode;
	private Date startDate;
	private Date deformDate;
	private Set<Constituency> constituencies = new HashSet<Constituency>(0);
	private Set<Tehsil> tehsils = new HashSet<Tehsil>(0);

	// Constructors

	/** default constructor */
	public District() {
	}

	/** minimal constructor */
	public District(Long districtId) {
		this.districtId = districtId;
	}

	/** full constructor */
	public District(Long districtId, State state, String districtName,
			String districtCapital, Double area, Double population,
			Long districtCode, Date startDate, Date deformDate,
			Set<Constituency> constituencies, Set<Tehsil> tehsils) {
		this.districtId = districtId;
		this.state = state;
		this.districtName = districtName;
		this.districtCapital = districtCapital;
		this.area = area;
		this.population = population;
		this.districtCode = districtCode;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.constituencies = constituencies;
		this.tehsils = tehsils;
	}

	// Property accessors
	@Id
	@Column(name = "district_id", unique = true, nullable = false)
	public Long getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "district_name", length = 100)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Column(name = "district_capital", length = 100)
	public String getDistrictCapital() {
		return this.districtCapital;
	}

	public void setDistrictCapital(String districtCapital) {
		this.districtCapital = districtCapital;
	}

	@Column(name = "area", precision = 15, scale = 5)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "population", precision = 10, scale = 0)
	public Double getPopulation() {
		return this.population;
	}

	public void setPopulation(Double population) {
		this.population = population;
	}

	@Column(name = "district_code")
	public Long getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deform_date", length = 10)
	public Date getDeformDate() {
		return this.deformDate;
	}

	public void setDeformDate(Date deformDate) {
		this.deformDate = deformDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Constituency> getConstituencies() {
		return this.constituencies;
	}

	public void setConstituencies(Set<Constituency> constituencies) {
		this.constituencies = constituencies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Tehsil> getTehsils() {
		return this.tehsils;
	}

	public void setTehsils(Set<Tehsil> tehsils) {
		this.tehsils = tehsils;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append(
				"districtId", districtId).append("state", state).append(
				"districtName", districtName).append("districtCapital",
				districtCapital).append("area", area).append("population",
				population).append("districtCode", districtCode).append(
				"startDate", startDate).append("deformDate", deformDate)
				.append("constituencies", constituencies).append("tehsils",
						tehsils).toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof District))
			return false;
		District castOther = (District) other;
		return new EqualsBuilder().append(districtId, castOther.districtId)
				.append(state, castOther.state).append(districtName,
						castOther.districtName).append(districtCapital,
						castOther.districtCapital).append(area, castOther.area)
				.append(population, castOther.population).append(districtCode,
						castOther.districtCode).append(startDate,
						castOther.startDate).append(deformDate,
						castOther.deformDate).append(constituencies,
						castOther.constituencies).append(tehsils,
						castOther.tehsils).isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(districtId).append(state)
				.append(districtName).append(districtCapital).append(area)
				.append(population).append(districtCode).append(startDate)
				.append(deformDate).append(constituencies).append(tehsils)
				.toHashCode();
	}

}