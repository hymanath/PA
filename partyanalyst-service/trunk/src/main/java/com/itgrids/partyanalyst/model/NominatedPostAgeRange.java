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
@Table(name = "nominated_post_age_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostAgeRange extends BaseModel implements Serializable{

	private Long nominatedPostAgeRangeId;
	private String ageRange;
	private Long minValue;
	private Long maxValue;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_age_range_id", unique = true, nullable = false)
	public Long getNominatedPostAgeRangeId() {
		return nominatedPostAgeRangeId;
	}
	public void setNominatedPostAgeRangeId(Long nominatedPostAgeRangeId) {
		this.nominatedPostAgeRangeId = nominatedPostAgeRangeId;
	}
	
	@Column(name="age_range")
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	
	@Column(name="min_value")
	public Long getMinValue() {
		return minValue;
	}
	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}
	
	@Column(name="max_value")
	public Long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}
}
