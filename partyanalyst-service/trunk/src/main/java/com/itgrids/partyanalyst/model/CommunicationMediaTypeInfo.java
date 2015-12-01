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
@Table(name = "communication_media_type_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommunicationMediaTypeInfo extends BaseModel implements java.io.Serializable{
	
	private Long communicationMediaTypeInfoId;
	private Long communicationMediaTypeId;
	private String name;
	private String description;
	
	private CommunicationMediaType communicationMediaType;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "communication_media_type_info_id", unique = true, nullable = false)
	public Long getCommunicationMediaTypeInfoId() {
		return communicationMediaTypeInfoId;
	}
	public void setCommunicationMediaTypeInfoId(Long communicationMediaTypeInfoId) {
		this.communicationMediaTypeInfoId = communicationMediaTypeInfoId;
	}
	
	@Column(name = "communication_media_type_id")
	public Long getCommunicationMediaTypeId() {
		return communicationMediaTypeId;
	}
	public void setCommunicationMediaTypeId(Long communicationMediaTypeId) {
		this.communicationMediaTypeId = communicationMediaTypeId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="communication_media_type_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommunicationMediaType getCommunicationMediaType() {
		return communicationMediaType;
	}
	public void setCommunicationMediaType(
			CommunicationMediaType communicationMediaType) {
		this.communicationMediaType = communicationMediaType;
	}
	
}
