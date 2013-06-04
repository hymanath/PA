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


/**
 * visibility entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="visibility")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Visibility extends BaseModel implements Serializable{

	private static final long serialVersionUID = 6758118713227378986L;
	
	private Long visibilityId;
	private String type;
	
	public Visibility()
	{}
	
	public Visibility(String type)
	{
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="visibility_id", unique=true, nullable=false)
	public Long getVisibilityId() {
		return visibilityId;
	}

	public void setVisibilityId(Long visibilityId) {
		this.visibilityId = visibilityId;
	}

	@Column(name="type",length=10)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
