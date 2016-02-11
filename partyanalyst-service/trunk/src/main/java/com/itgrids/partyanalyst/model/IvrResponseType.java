package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "ivr_response_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrResponseType extends BaseModel implements java.io.Serializable {
	
	private Long ivrResponseTypeId;
	private String responseType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_response_type_id", unique = true, nullable = false)
	public Long getIvrResponseTypeId() {
		return ivrResponseTypeId;
	}
	public void setIvrResponseTypeId(Long ivrResponseTypeId) {
		this.ivrResponseTypeId = ivrResponseTypeId;
	}
	
	@Column(name = "response_type")
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
}
