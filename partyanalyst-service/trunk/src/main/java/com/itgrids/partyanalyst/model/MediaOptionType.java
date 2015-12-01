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
@Table(name = "media_option_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MediaOptionType extends BaseModel implements java.io.Serializable{

	private Long mediaOptionTypeId;
	private String optionType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "media_option_type_id", unique = true, nullable = false)
	public Long getMediaOptionTypeId() {
		return mediaOptionTypeId;
	}
	public void setMediaOptionTypeId(Long mediaOptionTypeId) {
		this.mediaOptionTypeId = mediaOptionTypeId;
	}
	
	@Column(name = "option_type")
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	
}
