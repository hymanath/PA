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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * delimitation_constituency_town entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="delimitation_constituency_town")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DelimitationConstituencyTown extends BaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = -597911145053457072L;
	private Long delimitationConstituencyTownId;
	private DelimitationConstituency delimitationConstituency;
	private Township township;
	private Long isPartial;
	private String description;
	private Date updatedDate;
	private Set<DelimitationWard> delimitationWard = new HashSet<DelimitationWard>(0);
	
	public DelimitationConstituencyTown(){
	}
	
	public DelimitationConstituencyTown(DelimitationConstituency delimitationConstituency,Township township,Long isPartial,String description, Date updatedDate){
		this.delimitationConstituency = delimitationConstituency;
		this.township = township;
		this.isPartial = isPartial;
		this.description = description;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="delimitation_constituency_town_id", unique=true, nullable=false)
	public Long getDelimitationConstituencyTownId() {
		return delimitationConstituencyTownId;
	}

	public void setDelimitationConstituencyTownId(
			Long delimitationConstituencyTownId) {
		this.delimitationConstituencyTownId = delimitationConstituencyTownId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="delimitation_constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DelimitationConstituency getDelimitationConstituency() {
		return delimitationConstituency;
	}

	public void setDelimitationConstituency(
			DelimitationConstituency delimitationConstituency) {
		this.delimitationConstituency = delimitationConstituency;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="township_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	@Column(name="is_partial",length=10)
	public Long getIsPartial() {
		return isPartial;
	}

	public void setIsPartial(Long isPartial) {
		this.isPartial = isPartial;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delimitationConstituencyTown")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationWard> getDelimitationWard() {
		return delimitationWard;
	}

	public void setDelimitationWard(Set<DelimitationWard> delimitationWard) {
		this.delimitationWard = delimitationWard;
	}

}
