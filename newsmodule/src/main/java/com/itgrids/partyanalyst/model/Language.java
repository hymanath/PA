/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 25, 2010
 */
package com.itgrids.partyanalyst.model;

import java.util.Set;
import com.itgrids.partyanalyst.model.BaseModel;


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

/**
 * Language entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "language")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Language implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long languageId;
	private String language;
	//private Set<CadreLanguageEfficiency> languageEfficiency;
	public Language() {
		super();		
	}

	public Language(Long languageId, String language) {
		super();
		this.languageId = languageId;
		this.language = language;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "language_id", nullable = false, unique = true)
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	
	@Column(name = "language", length = 50 )
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "language")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreLanguageEfficiency> getLanguageEfficiency() {
		return languageEfficiency;
	}

	public void setLanguageEfficiency(
			Set<CadreLanguageEfficiency> languageEfficiency) {
		this.languageEfficiency = languageEfficiency;
	}
	*/
	
	
}
