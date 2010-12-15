/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13, 2010
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * delimitation_village entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="delimitation_village")
public class DelimitationVillage extends BaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = -3777828316217061025L;
	private Long delimitationVillageId;
	private DelimitationConstituencyMandal delimitationConstituencyMandal;
	private Township township;
	private Long isPartial;
	private String description;
	private Date updatedDate;

	public DelimitationVillage()
	{}
	
	public DelimitationVillage(DelimitationConstituencyMandal delimitationConstituencyMandal,Township township,Long isPartial,String description,Date updatedDate)
	{
		this.delimitationConstituencyMandal = delimitationConstituencyMandal;
		this.township = township;
		this.isPartial = isPartial;
		this.description = description;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="delimitation_village_id", unique=true, nullable=false)
	public Long getDelimitationVillageId() {
		return delimitationVillageId;
	}

	public void setDelimitationVillageId(Long delimitationVillageId) {
		this.delimitationVillageId = delimitationVillageId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="delimitation_constituency_mandal_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DelimitationConstituencyMandal getDelimitationConstituencyMandal() {
		return delimitationConstituencyMandal;
	}

	public void setDelimitationConstituencyMandal(
			DelimitationConstituencyMandal delimitationConstituencyMandal) {
		this.delimitationConstituencyMandal = delimitationConstituencyMandal;
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
	
	
}
