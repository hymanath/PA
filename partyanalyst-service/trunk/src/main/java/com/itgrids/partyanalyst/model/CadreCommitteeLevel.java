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
@Table(name = "cadre_committee_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreCommitteeLevel implements Serializable{

	
	private static final long serialVersionUID = -1560141681788248417L;
	private Long   cadreCommitteeLevel;
	private String level;
	private String isDeleted;
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_committee_level_id", unique = true, nullable = false)
	public Long getCadreCommitteeLevel() {
		return cadreCommitteeLevel;
	}
	public void setCadreCommitteeLevel(Long cadreCommitteeLevel) {
		this.cadreCommitteeLevel = cadreCommitteeLevel;
	}
	
	@Column(name="level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
	
}
