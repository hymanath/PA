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

/**
 * blood_group entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="blood_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodGroup extends BaseModel implements Serializable{

	private static final long serialVersionUID = -6332147788473403394L;
	
	private Long bloodGroupId;
	private String bloodGroup;
	
	public BloodGroup()
	{}
	
	public BloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="blood_group_id", unique=true, nullable=false)
	public Long getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	@Column(name="blood_group",length=300)
	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

}
