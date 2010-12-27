/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
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
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long user_id;
	private String is_candidate;
	private String is_cadre;
	private String is_media;
	private String is_normal;
	
	private Set<CandidateUser> candidateUser = new HashSet<CandidateUser>(0);
	private Set<CadreUser> cadreUser = new HashSet<CadreUser>(0);
	private Set<MediaUser> mediaUser = new HashSet<MediaUser>(0);
	private Set<NormalUser> normalUser = new HashSet<NormalUser>(0);
	private Set<UserGroupRelation> userGroupRelation = new HashSet<UserGroupRelation>(0);
	
	/** Default Constructor */
	public User(){
		super();
	}
	
	/** Parameterized Constructor */
	public User(Long user_id, String is_candidate, String is_cadre,
			String is_media, String is_normal) {
		this.user_id = user_id;
		this.is_candidate = is_candidate;
		this.is_cadre = is_cadre;
		this.is_media = is_media;
		this.is_normal = is_normal;
	}

	/** Parameterized Constructor */
	public User(Long user_id, String is_candidate, String is_cadre,
			String is_media, String is_normal,
			Set<CandidateUser> candidateUser, Set<CadreUser> cadreUser,
			Set<MediaUser> mediaUser, Set<NormalUser> normalUser) {
		super();
		this.user_id = user_id;
		this.is_candidate = is_candidate;
		this.is_cadre = is_cadre;
		this.is_media = is_media;
		this.is_normal = is_normal;
		this.candidateUser = candidateUser;
		this.cadreUser = cadreUser;
		this.mediaUser = mediaUser;
		this.normalUser = normalUser;
	}

	/** Parameterized Constructor */
	public User(Long user_id, String is_candidate, String is_cadre,
			String is_media, String is_normal,
			Set<CandidateUser> candidateUser, Set<CadreUser> cadreUser,
			Set<MediaUser> mediaUser, Set<NormalUser> normalUser,
			Set<UserGroupRelation> userGroupRelation) {
		super();
		this.user_id = user_id;
		this.is_candidate = is_candidate;
		this.is_cadre = is_cadre;
		this.is_media = is_media;
		this.is_normal = is_normal;
		this.candidateUser = candidateUser;
		this.cadreUser = cadreUser;
		this.mediaUser = mediaUser;
		this.normalUser = normalUser;
		this.userGroupRelation = userGroupRelation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Column(name = "is_candidate", length = 25)
	public String getIs_candidate() {
		return is_candidate;
	}

	public void setIs_candidate(String is_candidate) {
		this.is_candidate = is_candidate;
	}

	@Column(name = "is_cadre", length = 25)
	public String getIs_cadre() {
		return is_cadre;
	}

	public void setIs_cadre(String is_cadre) {
		this.is_cadre = is_cadre;
	}

	@Column(name = "is_media", length = 25)
	public String getIs_media() {
		return is_media;
	}

	public void setIs_media(String is_media) {
		this.is_media = is_media;
	}

	@Column(name = "is_normal", length = 25)
	public String getIs_normal() {
		return is_normal;
	}

	public void setIs_normal(String is_normal) {
		this.is_normal = is_normal;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CandidateUser> getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(Set<CandidateUser> candidateUser) {
		this.candidateUser = candidateUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreUser> getCadreUser() {
		return cadreUser;
	}

	public void setCadreUser(Set<CadreUser> cadreUser) {
		this.cadreUser = cadreUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<MediaUser> getMediaUser() {
		return mediaUser;
	}

	public void setMediaUser(Set<MediaUser> mediaUser) {
		this.mediaUser = mediaUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<NormalUser> getNormalUser() {
		return normalUser;
	}

	public void setNormalUser(Set<NormalUser> normalUser) {
		this.normalUser = normalUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupRelation> getUserGroupRelation() {
		return userGroupRelation;
	}

	public void setUserGroupRelation(Set<UserGroupRelation> userGroupRelation) {
		this.userGroupRelation = userGroupRelation;
	}
	
}
