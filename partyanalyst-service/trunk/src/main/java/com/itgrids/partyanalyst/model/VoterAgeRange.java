package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "voter_age_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterAgeRange extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 2225466394145089166L;
	private Long voterAgeRangeId ;
	private String ageRange;
	private Set<VoterAgeInfo> voterAgeInfos = new HashSet<VoterAgeInfo>(0);
	private Long minValue;
	private Long maxValue;
	
	public VoterAgeRange(){}
	public VoterAgeRange(String ageRange,Long minValue,Long maxValue)
	{
		this.ageRange = ageRange;
		this.minValue = minValue;
		this.maxValue = maxValue;
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
	
	@Column(name = "age_range", length = 30)
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterAgeRange")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterAgeInfo> getVoterAgeInfos() {
		return voterAgeInfos;
	}
	public void setVoterAgeInfos(Set<VoterAgeInfo> voterAgeInfos) {
		this.voterAgeInfos = voterAgeInfos;
	}
	
	@Column(name = "min_value",length = 3)
	public Long getMinValue() {
		return minValue;
	}
	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}
	
	@Column(name = "max_value",length = 3)
	public Long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}
	
	
	
}
