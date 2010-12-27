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
 * delimitation_ward entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="delimitation_ward")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DelimitationWard extends BaseModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -1625298018307537476L;
	private Long delimitationWardId;
	private DelimitationConstituencyTown delimitationConstituencyTown;
	private Ward ward;
	private Long isPartial;
	private String description;
	private Date updatedDate;
	private Set<DelimitationBlock> delimitationBlock = new HashSet<DelimitationBlock>(0);

	public DelimitationWard(){
	}
	
	public DelimitationWard(DelimitationConstituencyTown delimitationConstituencyTown,Ward ward,Long isPartial,String description,Date updatedDate){
		
		this.delimitationConstituencyTown = delimitationConstituencyTown;
		this.ward = ward;
		this.isPartial = isPartial;
		this.description = description;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="delimitation_ward_id", unique=true, nullable=false)
	public Long getDelimitationWardId() {
		return delimitationWardId;
	}

	public void setDelimitationWardId(Long delimitationWardId) {
		this.delimitationWardId = delimitationWardId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="delimitation_constituency_town_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DelimitationConstituencyTown getDelimitationConstituencyTown() {
		return delimitationConstituencyTown;
	}

	public void setDelimitationConstituencyTown(
			DelimitationConstituencyTown delimitationConstituencyTown) {
		this.delimitationConstituencyTown = delimitationConstituencyTown;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delimitationWard")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationBlock> getDelimitationBlock() {
		return delimitationBlock;
	}

	public void setDelimitationBlock(Set<DelimitationBlock> delimitationBlock) {
		this.delimitationBlock = delimitationBlock;
	}
	
	
}
