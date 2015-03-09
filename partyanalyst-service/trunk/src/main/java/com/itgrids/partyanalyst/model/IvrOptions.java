package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;





@Entity
@Table(name = "ivr_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrOptions {
	
	
	private Long ivrOptionsId;
	private String optionName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_options_id", unique = true, nullable = false)
	public Long getIvrOptionsId() {
		return ivrOptionsId;
	}
	public void setIvrOptionsId(Long ivrOptionsId) {
		this.ivrOptionsId = ivrOptionsId;
	}
	
	@Column(name="option_name")
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
	
	

}
