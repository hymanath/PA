package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * constituency_urban_percentage entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */
@Entity
@Table(name = "constituency_urban_percentage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConstituencyUrbanPercentage extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -6425960375507229537L;
	
	private Long constituencyUrbanPercentageId;
	private Constituency constituency;
	private CensusYear censusYear;
	private Double urbanPercentage;
	
	public ConstituencyUrbanPercentage()
	{}
	
	public ConstituencyUrbanPercentage(Constituency constituency,CensusYear censusYear,Double urbanPercentage)
	{
		this.constituency = constituency;
		this.censusYear = censusYear;
		this.urbanPercentage = urbanPercentage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "constituency_urban_percentage_id", unique = true, nullable = false)
	public Long getConstituencyUrbanPercentageId() {
		return constituencyUrbanPercentageId;
	}

	public void setConstituencyUrbanPercentageId(Long constituencyUrbanPercentageId) {
		this.constituencyUrbanPercentageId = constituencyUrbanPercentageId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="census_year_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CensusYear getCensusYear() {
		return censusYear;
	}

	public void setCensusYear(CensusYear censusYear) {
		this.censusYear = censusYear;
	}

	@Column(name = "urban_percentage", precision = 5, scale = 2)
	public Double getUrbanPercentage() {
		return urbanPercentage;
	}

	public void setUrbanPercentage(Double urbanPercentage) {
		this.urbanPercentage = urbanPercentage;
	}
	
}
