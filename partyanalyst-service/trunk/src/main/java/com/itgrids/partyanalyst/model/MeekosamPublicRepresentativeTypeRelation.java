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
@Table(name = "meekosam_public_representative_type_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamPublicRepresentativeTypeRelation extends BaseModel implements Serializable {
	private Long meekosamPublicRepresentativeTypeRelationId;
	private Long meekosamPublicRepresentativeId;
	private Long meekosamPublicRepresentativeTypeId;
	private Long locationLevelId;
	private Long locationValue;
	private Long userAddressId;
	private String isDeleted;
	
	private MeekosamPublicRepresentativeType meekosamPublicRepresentativeType;
	private UserAddress userAddress;
	private MeekosamPublicRepresentative meekosamPublicRepresentative;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_public_representative_type_relation_id", unique = true, nullable = false)
	public Long getMeekosamPublicRepresentativeTypeRelationId() {
		return meekosamPublicRepresentativeTypeRelationId;
	}
	public void setMeekosamPublicRepresentativeTypeRelationId(
			Long meekosamPublicRepresentativeTypeRelationId) {
		this.meekosamPublicRepresentativeTypeRelationId = meekosamPublicRepresentativeTypeRelationId;
	}
	@Column(name = "meekosam_public_representative_type_id")
	public Long getMeekosamPublicRepresentativeTypeId() {
		return meekosamPublicRepresentativeTypeId;
	}
	public void setMeekosamPublicRepresentativeTypeId(
			Long meekosamPublicRepresentativeTypeId) {
		this.meekosamPublicRepresentativeTypeId = meekosamPublicRepresentativeTypeId;
	}
	@Column(name = "location_level_id")
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "user_address_id")
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_public_representative_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamPublicRepresentativeType getMeekosamPublicRepresentativeType() {
		return meekosamPublicRepresentativeType;
	}
	public void setMeekosamPublicRepresentativeType(
			MeekosamPublicRepresentativeType meekosamPublicRepresentativeType) {
		this.meekosamPublicRepresentativeType = meekosamPublicRepresentativeType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@Column(name = "meekosam_public_representative_id")
	public Long getMeekosamPublicRepresentativeId() {
		return meekosamPublicRepresentativeId;
	}
	public void setMeekosamPublicRepresentativeId(
			Long meekosamPublicRepresentativeId) {
		this.meekosamPublicRepresentativeId = meekosamPublicRepresentativeId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_public_representative_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamPublicRepresentative getMeekosamPublicRepresentative() {
		return meekosamPublicRepresentative;
	}
	public void setMeekosamPublicRepresentative(
			MeekosamPublicRepresentative meekosamPublicRepresentative) {
		this.meekosamPublicRepresentative = meekosamPublicRepresentative;
	}
}
