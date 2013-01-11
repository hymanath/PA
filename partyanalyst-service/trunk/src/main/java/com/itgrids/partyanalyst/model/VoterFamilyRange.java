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
@Table(name = "voter_family_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterFamilyRange extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2948051165678102790L;
	private Long voterFamilyRangeId;
	private Long familyRange;
	
	public VoterFamilyRange(){}
	
	public VoterFamilyRange(Long familyRange)
	{
		this.familyRange = familyRange;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_family_range_id", unique = true, nullable = false)
	public Long getVoterFamilyRangeId() {
		return voterFamilyRangeId;
	}

	public void setVoterFamilyRangeId(Long voterFamilyRangeId) {
		this.voterFamilyRangeId = voterFamilyRangeId;
	}

	@Column(name = "family_range", length = 15)
	public Long getFamilyRange() {
		return familyRange;
	}

	public void setFamilyRange(Long familyRange) {
		this.familyRange = familyRange;
	}
	
	
}
