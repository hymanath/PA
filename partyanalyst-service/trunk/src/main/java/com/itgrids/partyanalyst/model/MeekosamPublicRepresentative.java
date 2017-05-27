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
@Table(name = "meekosam_public_representative")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamPublicRepresentative extends BaseModel implements Serializable {
	private Long meekosamPublicRepresentativeId;
	private String name;
	private String type;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_public_representative_id", unique = true, nullable = false)
	public Long getMeekosamPublicRepresentativeId() {
		return meekosamPublicRepresentativeId;
	}
	public void setMeekosamPublicRepresentativeId(
			Long meekosamPublicRepresentativeId) {
		this.meekosamPublicRepresentativeId = meekosamPublicRepresentativeId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
