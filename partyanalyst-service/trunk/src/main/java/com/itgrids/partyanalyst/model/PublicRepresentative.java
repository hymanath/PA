package com.itgrids.partyanalyst.model;

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
@Table(name = "public_representative")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PublicRepresentative implements java.io.Serializable{

	private Long publicRepresentativeId;
	private PublicRepresentativeType publicRepresentativeType;
	private Long publicRepresentativeTypeId;
	private Candidate candidate;
	private Long candidateId;
	private RepresentativeLevel representativeLevel;
	private Long levelId;
	private Long levelValue;
	private Long addressId;
	private UserAddress userAddress;
	private Nomination nomination;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "public_representative_id", unique = true, nullable = false)
	public Long getPublicRepresentativeId() {
		return publicRepresentativeId;
	}
	public void setPublicRepresentativeId(Long publicRepresentativeId) {
		this.publicRepresentativeId = publicRepresentativeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "public_representative_type_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicRepresentativeType getPublicRepresentativeType() {
		return publicRepresentativeType;
	}
	public void setPublicRepresentativeType(
			PublicRepresentativeType publicRepresentativeType) {
		this.publicRepresentativeType = publicRepresentativeType;
	}
	
	@Column(name="public_representative_type_id")
	public Long getPublicRepresentativeTypeId() {
		return publicRepresentativeTypeId;
	}
	public void setPublicRepresentativeTypeId(Long publicRepresentativeTypeId) {
		this.publicRepresentativeTypeId = publicRepresentativeTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "candidate_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	
	@Column(name="candidate_id")
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "representative_level_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RepresentativeLevel getRepresentativeLevel() {
		return representativeLevel;
	}
	public void setRepresentativeLevel(RepresentativeLevel representativeLevel) {
		this.representativeLevel = representativeLevel;
	}
	
	@Column(name="representative_level_id")
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	@Column(name="representative_level_value")
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "address_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "nomination_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Nomination getNomination() {
		return nomination;
	}
	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
	}
	
	
	
}
