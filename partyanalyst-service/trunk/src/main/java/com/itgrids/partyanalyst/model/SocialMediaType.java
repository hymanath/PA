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
@Table(name = "social_media_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SocialMediaType implements java.io.Serializable {

	private Long socialMediaTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "social_media_type_id", unique = true, nullable = false)
	
	public Long getSocialMediaTypeId() {
		return socialMediaTypeId;
	}
	public void setSocialMediaTypeId(Long socialMediaTypeId) {
		this.socialMediaTypeId = socialMediaTypeId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
