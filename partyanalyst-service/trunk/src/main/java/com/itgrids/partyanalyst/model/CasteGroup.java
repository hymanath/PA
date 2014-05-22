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
@Table(name = "caste_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CasteGroup extends BaseModel implements Serializable {
	
	private Long casteGroupId;
	private String casteGroupName;
	private Set<ConstiCasteGroupPerc> constiCasteGroupPerc = new HashSet<ConstiCasteGroupPerc>(0);
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "caste_group_id", unique = true, nullable = false)
	public Long getCasteGroupId() {
		return casteGroupId;
	}
	public void setCasteGroupId(Long casteGroupId) {
		this.casteGroupId = casteGroupId;
	}
	@Column(name = "caste_group_name")
	public String getCasteGroupName() {
		return casteGroupName;
	}
	public void setCasteGroupName(String casteGroupName) {
		this.casteGroupName = casteGroupName;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "casteGroup")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ConstiCasteGroupPerc> getConstiCasteGroupPerc() {
		return constiCasteGroupPerc;
	}
	public void setConstiCasteGroupPerc(
			Set<ConstiCasteGroupPerc> constiCasteGroupPerc) {
		this.constiCasteGroupPerc = constiCasteGroupPerc;
	}
	
	

}
