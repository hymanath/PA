package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "voter_age_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterAgeRange extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 2225466394145089166L;
	private Long voterAgeRangeId ;
	private Long ageRange;
	
	public VoterAgeRange(){}
	public VoterAgeRange(Long ageRange)
	{
		this.ageRange = ageRange;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_age_range_id" , unique = true, nullable = false)
	public Long getVoterAgeRangeId() {
		return voterAgeRangeId;
	}
	public void setVoterAgeRangeId(Long voterAgeRangeId) {
		this.voterAgeRangeId = voterAgeRangeId;
	}
	
	@Column(name = "age_range", length = 15)
	public Long getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(Long ageRange) {
		this.ageRange = ageRange;
	}
	
	
	
}
