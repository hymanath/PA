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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "media_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MediaOptions extends BaseModel implements java.io.Serializable{
     
	private Long mediaOptionsId;
	private String options;
	private Long keyId;
	private Long locationLevel;
	private Long locationValue;
	private String isDeleted;
	
	private RegionScopes regionScopes;
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "media_options_id", unique = true, nullable = false)
	public Long getMediaOptionsId() {
		return mediaOptionsId;
	}
	public void setMediaOptionsId(Long mediaOptionsId) {
		this.mediaOptionsId = mediaOptionsId;
	}
	
	@Column(name = "options")
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}

	@Column(name = "key_id")
	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	
	@Column(name = "location_level")
	public Long getLocationLevel() {
		return locationLevel;
	}

	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}

	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_level",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
}
