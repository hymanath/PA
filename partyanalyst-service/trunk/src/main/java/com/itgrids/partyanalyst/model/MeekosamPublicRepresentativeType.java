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
@Table(name = "meekosam_public_representative_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamPublicRepresentativeType extends BaseModel implements Serializable {
	private Long meekosamPublicRepresentativeTypeId;
	private String type;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_public_representative_type_id", unique = true, nullable = false)
	public Long getMeekosamPublicRepresentativeTypeId() {
		return meekosamPublicRepresentativeTypeId;
	}
	public void setMeekosamPublicRepresentativeTypeId(
			Long meekosamPublicRepresentativeTypeId) {
		this.meekosamPublicRepresentativeTypeId = meekosamPublicRepresentativeTypeId;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
