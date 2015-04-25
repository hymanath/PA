package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "representative_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RepresentativeLevel implements java.io.Serializable{
	
	private Long representativeLevelId;
	private String representativeLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "representative_level_id", unique = true, nullable = false)
	public Long getRepresentativeLevelId() {
		return representativeLevelId;
	}
	public void setRepresentativeLevelId(Long representativeLevelId) {
		this.representativeLevelId = representativeLevelId;
	}
	
	@Column(name="representative_level")
	public String getRepresentativeLevel() {
		return representativeLevel;
	}
	public void setRepresentativeLevel(String representativeLevel) {
		this.representativeLevel = representativeLevel;
	}
	
	

}
