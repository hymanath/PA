package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="house_hold_voter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HouseHoldVoter extends BaseModel implements Serializable {
	
	private Long houseHoldVoterId;
	private Long voterId;
	private Long houseHoldId;
	private Long voterFamilyRelationId;
	private VoterFamilyRelation voterFamilyRelation;
	private Voter voter;
	private Long educationId;
	private Long occupationId;
	private Long socialCategoryId;
	private Long houseHoldsFamilyDetailsId;
	private HouseHoldsFamilyDetails houseHoldsFamilyDetails;
	private String isDelete;
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "house_hold_voter_id", unique = true, nullable = false)
	public Long getHouseHoldVoterId() {
		return houseHoldVoterId;
	}
	public void setHouseHoldVoterId(Long houseHoldVoterId) {
		this.houseHoldVoterId = houseHoldVoterId;
	}
	
	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name = "house_hold_id")
	public Long getHouseHoldId() {
		return houseHoldId;
	}
	public void setHouseHoldId(Long houseHoldId) {
		this.houseHoldId = houseHoldId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "relationship_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterFamilyRelation getVoterFamilyRelation() {
		return voterFamilyRelation;
	}
	public void setVoterFamilyRelation(VoterFamilyRelation voterFamilyRelation) {
		this.voterFamilyRelation = voterFamilyRelation;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@Column(name = "relationship_id")
	public Long getVoterFamilyRelationId() {
		return voterFamilyRelationId;
	}
	public void setVoterFamilyRelationId(Long voterFamilyRelationId) {
		this.voterFamilyRelationId = voterFamilyRelationId;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "house_holds_family_details_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HouseHoldsFamilyDetails getHouseHoldsFamilyDetails() {
		return houseHoldsFamilyDetails;
	}
	public void setHouseHoldsFamilyDetails(
			HouseHoldsFamilyDetails houseHoldsFamilyDetails) {
		this.houseHoldsFamilyDetails = houseHoldsFamilyDetails;
	}
	
    @Column(name="education_id")
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	
	@Column(name="occupation_id")
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	
	@Column(name="social_category_id")
	public Long getSocialCategoryId() {
		return socialCategoryId;
	}
	public void setSocialCategoryId(Long socialCategoryId) {
		this.socialCategoryId = socialCategoryId;
	}
	
	@Column(name="house_holds_family_details_id")
	public Long getHouseHoldsFamilyDetailsId() {
		return houseHoldsFamilyDetailsId;
	}
	public void setHouseHoldsFamilyDetailsId(Long houseHoldsFamilyDetailsId) {
		this.houseHoldsFamilyDetailsId = houseHoldsFamilyDetailsId;
	}

	@Column(name="is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	

}
