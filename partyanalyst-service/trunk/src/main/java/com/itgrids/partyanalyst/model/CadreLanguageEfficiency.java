/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 25, 2010
 */
package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * CadreLanguageEfficiency entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "cadre_language_efficiency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreLanguageEfficiency implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cadreLanguageEfficiencyId;
	private Cadre cadre;
	private Language language;
	private String isAbleToSpeak;
	private String isAbleToRead;
	private String isAbleToWrite;
	
	public CadreLanguageEfficiency() {
		super();
		
	}

	public CadreLanguageEfficiency(Long cadreLanguageEfficiencyId, Cadre cadre,
			Language language, String isAbleToSpeak, String isAbleToRead,
			String isAbleToWrite) {
		super();
		this.cadreLanguageEfficiencyId = cadreLanguageEfficiencyId;
		this.cadre = cadre;
		this.language = language;
		this.isAbleToSpeak = isAbleToSpeak;
		this.isAbleToRead = isAbleToRead;
		this.isAbleToWrite = isAbleToWrite;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_language_efficiency_id", nullable = false, unique = true)
	public Long getCadreLanguageEfficiencyId() {
		return cadreLanguageEfficiencyId;
	}

	public void setCadreLanguageEfficiencyId(Long cadreLanguageEfficiencyId) {
		this.cadreLanguageEfficiencyId = cadreLanguageEfficiencyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="language_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Column(name = "is_able_to_speak", length = 25)
	public String getIsAbleToSpeak() {
		return isAbleToSpeak;
	}

	public void setIsAbleToSpeak(String isAbleToSpeak) {
		this.isAbleToSpeak = isAbleToSpeak;
	}

	@Column(name = "is_able_to_read", length = 25)
	public String getIsAbleToRead() {
		return isAbleToRead;
	}

	public void setIsAbleToRead(String isAbleToRead) {
		this.isAbleToRead = isAbleToRead;
	}

	@Column(name = "is_able_to_write", length = 25)
	public String getIsAbleToWrite() {
		return isAbleToWrite;
	}

	public void setIsAbleToWrite(String isAbleToWrite) {
		this.isAbleToWrite = isAbleToWrite;
	}
	
	
	
	
	
	
	
}
