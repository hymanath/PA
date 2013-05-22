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
@Table(name="query_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QueryTemp extends BaseModel implements Serializable{

	private static final long serialVersionUID = 375228877168724578L;
	
	private Long id;
	private Long value;
	
	public QueryTemp() {}
	
	public QueryTemp(Long value){
		this.value = value;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="value",length=15)
	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
