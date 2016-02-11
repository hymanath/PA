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
@Table(name="ivr_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrOption extends BaseModel implements Serializable{
	
	private Long ivrOptionId;
	private String option;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_option_id", unique = true, nullable = false)
	public Long getIvrOptionId() {
		return ivrOptionId;
	}
	public void setIvrOptionId(Long ivrOptionId) {
		this.ivrOptionId = ivrOptionId;
	}
	@Column(name = "option")
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	
}
