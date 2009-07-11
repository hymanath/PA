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
 * Tehsil entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "tehsil")
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
			Set<Township> townships) {
		this.tehsilId = tehsilId;
		this.district = district;
		this.tehsilName = tehsilName;
		this.tehsilCode = tehsilCode;
		this.startDate = startDate;
		this.deformDate = deformDate;
		this.townships = townships;
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

	@ManyToOne(fetch = FetchType.LAZY)
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append(
				"tehsilId", tehsilId).append("district", district).append(
				"tehsilName", tehsilName).append("tehsilCode", tehsilCode)
				.append("startDate", startDate)
				.append("deformDate", deformDate)
				.append("townships", townships).toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Tehsil))
			return false;
		Tehsil castOther = (Tehsil) other;
		return new EqualsBuilder().append(tehsilId, castOther.tehsilId).append(
				district, castOther.district).append(tehsilName,
				castOther.tehsilName).append(tehsilCode, castOther.tehsilCode)
				.append(startDate, castOther.startDate).append(deformDate,
						castOther.deformDate).append(townships,
						castOther.townships).isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(tehsilId).append(district)
				.append(tehsilName).append(tehsilCode).append(startDate)
				.append(deformDate).append(townships).toHashCode();
	}

}