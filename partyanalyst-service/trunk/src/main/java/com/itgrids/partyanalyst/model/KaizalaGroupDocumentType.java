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
@Table(name = "kaizala_group_document_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaGroupDocumentType extends BaseModel implements Serializable{
	
	private Long kaizalaGroupDocumentTypeId;
	private String type;
	
	
	@Id
	@Column(name="kaizala_group_document_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaGroupDocumentTypeId() {
		return kaizalaGroupDocumentTypeId;
	}
	public void setKaizalaGroupDocumentTypeId(Long kaizalaGroupDocumentTypeId) {
		this.kaizalaGroupDocumentTypeId = kaizalaGroupDocumentTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
