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
@Table(name = "area_inchargers_voter_mapping_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AreaInchargersVoterMappingDetails {
	
	private Long areaInchargersVoterMappingDetailsId;
	private Long areaInchargerId;
	private Long voterId;
	private Long memberVoterId;
	private Long memberMatchedTypeId;
	private String isAgeMatched;
	private String matchCount;
	private Long memberPartNo;
	private Long voterPartNo;
	private Long constituencyId;
	private Long voterAge;
	private Long memberAge;
	
	private Constituency constituency;
	private MemberMatchedType memberMatchedType;
	private Voter voter;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="area_inchargers_voter_mapping_details_id")
	public Long getAreaInchargersVoterMappingDetailsId() {
		return areaInchargersVoterMappingDetailsId;
	}
	public void setAreaInchargersVoterMappingDetailsId(
			Long areaInchargersVoterMappingDetailsId) {
		this.areaInchargersVoterMappingDetailsId = areaInchargersVoterMappingDetailsId;
	}
	
	@Column(name="area_incharger_id")
	public Long getAreaInchargerId() {
		return areaInchargerId;
	}
	public void setAreaInchargerId(Long areaInchargerId) {
		this.areaInchargerId = areaInchargerId;
	}
	
	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name="member_voter_id")
	public Long getMemberVoterId() {
		return memberVoterId;
	}
	public void setMemberVoterId(Long memberVoterId) {
		this.memberVoterId = memberVoterId;
	}
	
	@Column(name="member_matched_type_id")
	public Long getMemberMatchedTypeId() {
		return memberMatchedTypeId;
	}
	public void setMemberMatchedTypeId(Long memberMatchedTypeId) {
		this.memberMatchedTypeId = memberMatchedTypeId;
	}
	
	@Column(name="is_age_matched")
	public String getIsAgeMatched() {
		return isAgeMatched;
	}
	public void setIsAgeMatched(String isAgeMatched) {
		this.isAgeMatched = isAgeMatched;
	}
	
	@Column(name="match_count")
	public String getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(String matchCount) {
		this.matchCount = matchCount;
	}
	
	@Column(name="member_part_no")
	public Long getMemberPartNo() {
		return memberPartNo;
	}
	public void setMemberPartNo(Long memberPartNo) {
		this.memberPartNo = memberPartNo;
	}
	
	@Column(name="voter_part_no")
	public Long getVoterPartNo() {
		return voterPartNo;
	}
	public void setVoterPartNo(Long voterPartNo) {
		this.voterPartNo = voterPartNo;
	}
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name="voter_age")
	public Long getVoterAge() {
		return voterAge;
	}
	public void setVoterAge(Long voterAge) {
		this.voterAge = voterAge;
	}
	
	@Column(name="member_age")
	public Long getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(Long memberAge) {
		this.memberAge = memberAge;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_matched_type_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MemberMatchedType getMemberMatchedType() {
		return memberMatchedType;
	}
	public void setMemberMatchedType(MemberMatchedType memberMatchedType) {
		this.memberMatchedType = memberMatchedType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
}
