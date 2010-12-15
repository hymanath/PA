/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13, 2010
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * delimitation_year entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="delimitation_year")
public class DelimitationYear extends BaseModel implements java.io.Serializable{
	
	 private static final long serialVersionUID = 1429540901539571873L;
	 private Long delimitationYearId;
	 private String year;
	 private Country country;
	 private String description;
	 private Date updatedDate;
	 private Set<DelimitationConstituency> delimitationConstituency = new HashSet<DelimitationConstituency>(0);
	 
	 public DelimitationYear(){
	 }
	 
	 public DelimitationYear(Country country,String year,String description,Date updatedDate){
		 this.country = country;
		 this.year = year;
		 this.description = description;
		 this.updatedDate = updatedDate;
	 }

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="delimitation_year_id", unique=true, nullable=false)
	public Long getDelimitationYearId() {
		return delimitationYearId;
	}

	public void setDelimitationYearId(Long delimitationYearId) {
		this.delimitationYearId = delimitationYearId;
	}

	@Column(name="year",length=25, nullable=false)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="country_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name="description",length=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delimitationYear")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationConstituency> getDelimitationConstituency() {
		return delimitationConstituency;
	}

	public void setDelimitationConstituency(
			Set<DelimitationConstituency> delimitationConstituency) {
		this.delimitationConstituency = delimitationConstituency;
	}
}
