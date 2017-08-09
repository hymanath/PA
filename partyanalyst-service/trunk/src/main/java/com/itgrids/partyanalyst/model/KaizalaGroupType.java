package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "kaizala_group_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaGroupType {

	private Long kaizalaGroupTypeId;
	private String groupType;
	
	@Id
	@Column(name="kaizala_group_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaGroupTypeId() {
		return kaizalaGroupTypeId;
	}
	public void setKaizalaGroupTypeId(Long kaizalaGroupTypeId) {
		this.kaizalaGroupTypeId = kaizalaGroupTypeId;
	}
	
	@Column(name="group_type")
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
}
