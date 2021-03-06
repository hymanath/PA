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
@Table(name = "voter_family_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterFamilyRange extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2948051165678102790L;
	private Long voterFamilyRangeId;
	private String familyRange;
	private Set<VoterFamilyInfo> voterFamilyInfos = new HashSet<VoterFamilyInfo>(0);
	
	public VoterFamilyRange(){}
	
	public VoterFamilyRange(String familyRange)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterFamilyRange")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterFamilyInfo> getVoterFamilyInfos() {
		return voterFamilyInfos;
	}

	public void setVoterFamilyInfos(Set<VoterFamilyInfo> voterFamilyInfos) {
		this.voterFamilyInfos = voterFamilyInfos;
	}
	
	@Column(name = "family_range", length = 15)
	public String getFamilyRange() {
		return familyRange;
	}

	public void setFamilyRange(String familyRange) {
		this.familyRange = familyRange;
	}
	
	
}
