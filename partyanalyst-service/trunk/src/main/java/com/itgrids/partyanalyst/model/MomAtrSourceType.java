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
@Table(name = "mom_atr_source_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MomAtrSourceType extends BaseModel implements Serializable {

	private Long momAtrSourceTypeId;
	private String sourceType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mom_atr_source_type_id", unique = true, nullable = false)
	public Long getMomAtrSourceTypeId() {
		return momAtrSourceTypeId;
	}
	public void setMomAtrSourceTypeId(Long momAtrSourceTypeId) {
		this.momAtrSourceTypeId = momAtrSourceTypeId;
	}
	@Column(name="source_type")
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}	
}
