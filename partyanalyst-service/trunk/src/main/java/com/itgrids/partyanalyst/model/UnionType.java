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
@Table(name="union_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnionType extends BaseModel implements Serializable{

	private Long unionTypeId;
	private String unionType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="union_type_id", unique=true, nullable=false)
	public Long getUnionTypeId() {
		return unionTypeId;
	}
	public void setUnionTypeId(Long unionTypeId) {
		this.unionTypeId = unionTypeId;
	}
	
	@Column(name="union_type")
	public String getUnionType() {
		return unionType;
	}
	public void setUnionType(String unionType) {
		this.unionType = unionType;
	} 
}
