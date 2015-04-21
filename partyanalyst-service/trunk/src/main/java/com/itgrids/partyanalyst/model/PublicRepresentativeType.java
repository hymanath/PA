package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 
 * @author Srishailam Pittala
 *
 */


@Entity
@Table(name = "public_representative_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicRepresentativeType implements java.io.Serializable{

	private Long publicRepresentativeTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "public_representative_type_id", unique = true, nullable = false)
	public Long getPublicRepresentativeTypeId() {
		return publicRepresentativeTypeId;
	}
	public void setPublicRepresentativeTypeId(Long publicRepresentativeTypeId) {
		this.publicRepresentativeTypeId = publicRepresentativeTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
