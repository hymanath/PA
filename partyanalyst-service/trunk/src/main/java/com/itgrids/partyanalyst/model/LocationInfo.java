package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

@Entity
@Table(name = "location_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocationInfo extends BaseModel implements Serializable{

	private Long locationInfoId;
	private Long levelId;
	private Long constituencyId;
	private Long scopeId;
	private Long scopeValue;
	private Long count;
	private Long publicationDateId;
	
	private Constituency constituency;
	private PublicationDate publicationDate;
	private RegionScopes regionScopes;
	private RegionScopes regionScopes2;

	//location_info_id,level_id,scope_id,scope_value,count
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "location_info_id", unique = true, nullable = false)
	public Long getLocationInfoId() {
		return locationInfoId;
	}
	public void setLocationInfoId(Long locationInfoId) {
		this.locationInfoId = locationInfoId;
	}
	
	@Column(name = "level_id")
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	@Column(name = "scope_id")
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	
	@Column(name = "scope_value")
	public Long getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}
	@Column(name = "count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes2() {
		return regionScopes2;
	}
	public void setRegionScopes2(RegionScopes regionScopes2) {
		this.regionScopes2 = regionScopes2;
	}
	
	@Column(name = "publication_date_id")
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="publication_date_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
	
}
