package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "urban_block")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UrbanBlock extends BaseModel implements Serializable{

	private Long urbanBlockId;
	private String blockName;
	private Long urbanLocalityId;
	private UrbanLocality urbanLocality;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "urban_block_id", unique = true, nullable = false)
	public Long getUrbanBlockId() {
		return urbanBlockId;
	}
	public void setUrbanBlockId(Long urbanBlockId) {
		this.urbanBlockId = urbanBlockId;
	}
	
	@Column(name = "block_name")
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	
	@Column(name = "urban_locality_id")
	public Long getUrbanLocalityId() {
		return urbanLocalityId;
	}
	public void setUrbanLocalityId(Long urbanLocalityId) {
		this.urbanLocalityId = urbanLocalityId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "urban_locality_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public UrbanLocality getUrbanLocality() {
		return urbanLocality;
	}
	public void setUrbanLocality(UrbanLocality urbanLocality) {
		this.urbanLocality = urbanLocality;
	}
}
