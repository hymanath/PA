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
@Table(name = "communication_media_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommunicationMediaType extends BaseModel implements java.io.Serializable {
    
	
	private Long communicationMediaTypeId;
	private String mediaType;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "communication_media_type_id", unique = true, nullable = false)
	public Long getCommunicationMediaTypeId() {
		return communicationMediaTypeId;
	}
	public void setCommunicationMediaTypeId(Long communicationMediaTypeId) {
		this.communicationMediaTypeId = communicationMediaTypeId;
	}
	
	@Column(name = "media_type")
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	@Column(name = "is_deleted ")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
