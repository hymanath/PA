/* 
 * Copyright (c) 2009 IT Grids Ltd.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * ProblemSourceScope Entity
 * @author<a href="r.sivakumar@itgrids.com" >Sivakumar</a>
 *
 */
@Entity
@Table(name = "problem_source_scope")
public class ProblemSourceScope extends BaseModel implements Serializable{

	private Long problemSourceScopeId;
	private String scope;
	private UserCategory userCategory;
	private Set<ProblemHistory> problemHistories = new HashSet<ProblemHistory>(0);
	
	public ProblemSourceScope(){
		
	}
	
	public ProblemSourceScope(Long problemSourceScopeId){
		this.problemSourceScopeId = problemSourceScopeId;
	}

	

	public ProblemSourceScope(String scope, UserCategory userCategory,
			Set<ProblemHistory> problemHistories) {
		this.scope = scope;
		this.userCategory = userCategory;
		this.problemHistories = problemHistories;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_source_scope_id", unique = true, nullable = false)
	public Long getProblemSourceScopeId() {
		return problemSourceScopeId;
	}

	public void setProblemSourceScopeId(Long problemSourceScopeId) {
		this.problemSourceScopeId = problemSourceScopeId;
	}

	@Column(name = "scope", length = 50)
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemSourceScope")
	public Set<ProblemHistory> getProblemHistories() {
		return problemHistories;
	}

	public void setProblemHistories(Set<ProblemHistory> problemHistories) {
		this.problemHistories = problemHistories;
	}
}
