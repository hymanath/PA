package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "field_vendor_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FieldVendorLocation extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 75700620764929264L;
	
	
    private Long fieldVendorLocationId;
    private Long fieldVendorId;
    private Long constituencyId;
    private String isDeleted;
    
    private FieldVendor fieldVendor;
    private Constituency constituency;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "field_vendor_location_id", unique = true, nullable = false)
	public Long getFieldVendorLocationId() {
		return fieldVendorLocationId;
	}
	public void setFieldVendorLocationId(Long fieldVendorLocationId) {
		this.fieldVendorLocationId = fieldVendorLocationId;
	}
	
	@Column(name="field_vendor_id")
	public Long getFieldVendorId() {
		return fieldVendorId;
	}
	public void setFieldVendorId(Long fieldVendorId) {
		this.fieldVendorId = fieldVendorId;
	}
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="field_vendor_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FieldVendor getFieldVendor() {
		return fieldVendor;
	}
	public void setFieldVendor(FieldVendor fieldVendor) {
		this.fieldVendor = fieldVendor;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
    
    
}
