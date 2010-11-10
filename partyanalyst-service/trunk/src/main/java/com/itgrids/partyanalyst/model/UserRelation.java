/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 03, 2010
 */
package com.itgrids.partyanalyst.model;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * UserRelation entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">kamalakar</a>
 */
@Entity
@Table(name = "user_relation")


public class UserRelation implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	private Long userRelationId;
	private String relationship;
	private Set<CadreFamilyMemberInfo> cadreFamilyMemberInfo = new HashSet<CadreFamilyMemberInfo>();
	public UserRelation() {
		super();
	}
	
	public UserRelation(String relationship) {
		super();
		this.relationship = relationship;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_relation_id", nullable = false, unique = true)
	public Long getUserRelationId() {
		return userRelationId;
	}

	public void setUserRelationId(Long userRelationId) {
		this.userRelationId = userRelationId;
	}

	@Column(name = "relationship", length = 25)
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userRelation")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreFamilyMemberInfo> getCadreFamilyMemberInfo() {
		return cadreFamilyMemberInfo;
	}

	public void setCadreFamilyMemberInfo(
			Set<CadreFamilyMemberInfo> cadreFamilyMemberInfo) {
		this.cadreFamilyMemberInfo = cadreFamilyMemberInfo;
	}
}
