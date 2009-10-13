/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Ward entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "ward")
public class Ward implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -2036446525734366674L;
	
	// Fields
	
	private Long wardId;
	private Township township;
	private String wardName;
	private Long wardCode;

	// Constructors
	
	/** default constructor */
	public Ward() {
	}

	/** minimal constructor */
	public Ward(Long wardId) {
		this.wardId = wardId;
	}

	/** full constructor */
	public Ward(Long wardId, Township township, String wardName, Long wardCode) {
		this.wardId = wardId;
		this.township = township;
		this.wardName = wardName;
		this.wardCode = wardCode;
	}

	// Property accessors
	@Id
	@Column(name = "ward_id", unique = true, nullable = false)
	public Long getWardId() {
		return this.wardId;
	}

	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	public Township getTownship() {
		return this.township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@Column(name = "ward_name", length = 100)
	public String getWardName() {
		return this.wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	@Column(name = "ward_code")
	public Long getWardCode() {
		return this.wardCode;
	}

	public void setWardCode(Long wardCode) {
		this.wardCode = wardCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append(
				"wardId", wardId).append("township", township).append(
				"wardName", wardName).append("wardCode", wardCode).toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Ward))
			return false;
		Ward castOther = (Ward) other;
		return new EqualsBuilder().append(wardId, castOther.wardId).append(
				township, castOther.township).append(wardName,
				castOther.wardName).append(wardCode, castOther.wardCode)
				.isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(wardId).append(township)
				.append(wardName).append(wardCode).toHashCode();
	}

}