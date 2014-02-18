package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="hh_option_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHOptionType extends BaseModel implements Serializable {
	 private Long optionTypeId;
	 private String optionType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_type_id", unique = true, nullable = false)
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	
	@Column(name = "option_type", length = 100)
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	 
	 
}
