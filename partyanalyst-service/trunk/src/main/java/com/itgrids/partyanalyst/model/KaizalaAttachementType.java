package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.Date;
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
@Table(name = "kaizala_attachement_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaAttachementType extends BaseModel implements Serializable{
	
	private Long kaizalaAttachementTypeId;
	private String attachmentType;
	
	@Id
	@Column(name="kaizala_attachement_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaAttachementTypeId() {
		return kaizalaAttachementTypeId;
	}
	public void setKaizalaAttachementTypeId(Long kaizalaAttachementTypeId) {
		this.kaizalaAttachementTypeId = kaizalaAttachementTypeId;
	}
	@Column(name="attachment_type")
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	
}
