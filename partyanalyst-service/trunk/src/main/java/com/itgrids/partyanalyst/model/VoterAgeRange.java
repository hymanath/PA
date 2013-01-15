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
	private Long ageRange;
	private Set<VoterAgeInfo> voterAgeInfos = new HashSet<VoterAgeInfo>(0);
	
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterAgeRange")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterAgeInfo> getVoterAgeInfos() {
		return voterAgeInfos;
	}
	public void setVoterAgeInfos(Set<VoterAgeInfo> voterAgeInfos) {
		this.voterAgeInfos = voterAgeInfos;
	}
	
	
	
}
