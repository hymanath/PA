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
@Table(name="area_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AreaType extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long areaTypeId ;
	private String type;
	private Set<CustomVoterGroup> customVoterGroups = new HashSet<CustomVoterGroup>(0);

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_type_id", unique = true, nullable = false)
	public Long getAreaTypeId() {
		return areaTypeId;
	}
	public void setAreaTypeId(Long areaTypeId) {
		this.areaTypeId = areaTypeId;
	}
	
	@Column(name="type" ,length=20)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "areaType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CustomVoterGroup> getCustomVoterGroups() {
		return customVoterGroups;
	}
	public void setCustomVoterGroups(Set<CustomVoterGroup> customVoterGroups) {
		this.customVoterGroups = customVoterGroups;
	}

}
