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
@Table(name="edition_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EditionType extends BaseModel implements Serializable{
	
	private Long editionTypeId;
	private String editionType;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "edition_type_id", unique = true, nullable = false)
	public Long getEditionTypeId() {
		return editionTypeId;
	}
	public void setEditionTypeId(Long editionTypeId) {
		this.editionTypeId = editionTypeId;
	}
	
	@Column(name = "edition_type")
	public String getEditionType() {
		return editionType;
	}
	public void setEditionType(String editionType) {
		this.editionType = editionType;
	}

}
