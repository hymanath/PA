package com.itgrids.eliteclub.model;

// Generated Apr 11, 2014 7:30:00 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ContactDetails generated by hbm2java
 */
@Entity
@Table(name = "contact_details", catalog = "elite_club")
public class ContactDetails implements java.io.Serializable {

	private Integer contactId;
	private User user;
	private String name;
	private String phoneNumber;
	private Set<ContactAkmt> contactAkmts = new HashSet<ContactAkmt>(0);

	public ContactDetails() {
	}

	public ContactDetails(User user, String name, String phoneNumber,
			Set<ContactAkmt> contactAkmts) {
		this.user = user;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.contactAkmts = contactAkmts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "contact_id", unique = true, nullable = false)
	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name", length = 75)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone_number", length = 45)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contactDetails")
	public Set<ContactAkmt> getContactAkmts() {
		return this.contactAkmts;
	}

	public void setContactAkmts(Set<ContactAkmt> contactAkmts) {
		this.contactAkmts = contactAkmts;
	}

}
