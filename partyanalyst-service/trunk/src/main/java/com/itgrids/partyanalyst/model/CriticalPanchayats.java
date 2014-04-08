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


@Entity
@Table(name = "critical_panchayats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CriticalPanchayats extends BaseModel{
	
	private Long criticalPanchayatsId;
	private Constituency constituency;
	private Long year;
	private Long locationId;
	private String type;
	private Long priority;
	private String scope;
	
	
	public CriticalPanchayats()
	{
		
	}
	
	public CriticalPanchayats(Long criticalPanchayatsId,Constituency constituency,Long year,
			Long locationId,String type,Long priority,String scope)
	{
		this.criticalPanchayatsId = criticalPanchayatsId;
		this.constituency = constituency;
		this.year = year;
		this.locationId = locationId;
		this.type = type;
		this.priority = priority;
		this.scope =scope;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "critical_panchayats_id", unique = true, nullable = false)
	public Long getCriticalPanchayatsId() {
		return criticalPanchayatsId;
	}

	public void setCriticalPanchayatsId(Long criticalPanchayatsId) {
		this.criticalPanchayatsId = criticalPanchayatsId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@Column(name = "year")
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}
	@Column(name = "location_id")
	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "priority")
	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}
	@Column(name = "scope")
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
