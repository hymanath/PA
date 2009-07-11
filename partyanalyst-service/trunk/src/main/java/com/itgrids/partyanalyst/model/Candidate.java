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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Candidate entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "candidate")
public class Candidate implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 1164868684649963786L;
	
	// Fields
	
	private Long candidateId;
	private String firstname;
	private String middlename;
	private String lastname;
	private Date dateofbirth;
	private String emailAddress;
	private String phone;
	private String mobile;
	private String address;
	private String education;
	private String gender;
	private Set<Nomination> nominations = new HashSet<Nomination>(0);

	// Constructors

	/** default constructor */
	public Candidate() {
	}

	/** minimal constructor */
	public Candidate(Long candidateId) {
		this.candidateId = candidateId;
	}

	/** full constructor */
	public Candidate(Long candidateId, String firstname, String middlename,
			String lastname, Date dateofbirth, String emailAddress,
			String phone, String mobile, String address, String education,
			String gender, Set<Nomination> nominations) {
		this.candidateId = candidateId;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
		this.emailAddress = emailAddress;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.education = education;
		this.gender = gender;
		this.nominations = nominations;
	}

	// Property accessors
	@Id
	@Column(name = "candidate_id", unique = true, nullable = false)
	public Long getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	@Column(name = "firstname", length = 40)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "middlename", length = 40)
	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	@Column(name = "lastname", length = 40)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofbirth", length = 10)
	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Column(name = "email_address", length = 60)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "phone", length = 25)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "mobile", length = 25)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "address", length = 25)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "education", length = 25)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "gender", length = 25)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidate")
	public Set<Nomination> getNominations() {
		return this.nominations;
	}

	public void setNominations(Set<Nomination> nominations) {
		this.nominations = nominations;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("candidateId", candidateId)
				.append("firstname", firstname)
				.append("middlename", middlename).append("lastname", lastname)
				.append("dateofbirth", dateofbirth).append("emailAddress",
						emailAddress).append("phone", phone).append("mobile",
						mobile).append("address", address).append("education",
						education).append("gender", gender).append(
						"nominations", nominations).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Candidate))
			return false;
		Candidate castOther = (Candidate) other;
		return new EqualsBuilder().append(candidateId, castOther.candidateId)
				.append(firstname, castOther.firstname).append(middlename,
						castOther.middlename).append(lastname,
						castOther.lastname).append(dateofbirth,
						castOther.dateofbirth).append(emailAddress,
						castOther.emailAddress).append(phone, castOther.phone)
				.append(mobile, castOther.mobile).append(address,
						castOther.address).append(education,
						castOther.education).append(gender, castOther.gender)
				.append(nominations, castOther.nominations).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(candidateId).append(firstname)
				.append(middlename).append(lastname).append(dateofbirth)
				.append(emailAddress).append(phone).append(mobile).append(
						address).append(education).append(gender).append(
						nominations).toHashCode();
	}

}