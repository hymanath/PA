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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "rws_ivr_type")
public class RwsIvrType {

	private Long rwsIvrTypeId;
	private String rwsIvrName;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rws_ivr_type_id",unique = true,nullable = false)
	public Long getRwsIvrTypeId() {
		return rwsIvrTypeId;
	}
	public void setRwsIvrTypeId(Long rwsIvrTypeId) {
		this.rwsIvrTypeId = rwsIvrTypeId;
	}
	@Column(name = "rws_ivr_name")
	public String getRwsIvrName() {
		return rwsIvrName;
	}
	public void setRwsIvrName(String rwsIvrName) {
		this.rwsIvrName = rwsIvrName;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
