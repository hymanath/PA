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
@Table(name = "member_voter_mapping_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberVoterMappingDetails extends BaseModel implements java.io.Serializable {
	
	private Long memberVoterMappingDetailsId;
	private Long memberId;
	private Long voterId;
	//private Long memberMatchedLevelTypeMappingId;
	private TdMember tdMember;
	private Voter voter;
	//private MemberMatchedLevelTypeMapping memberMatchedLevelTypeMapping;
	private Long memberMatchedTypeId;
	private MemberMatchedType memberMatchedType;
	
	private String ageMatched;
	private String genderMatched;
	private String relativeNameMatched;
	private Long matchCount;
	private String memberPanchayatName;
	private String voterPanchayatName;
	private Long constituencyId;
	private Constituency constituency;
	
	private String memberGender;
	private String voterGender;
	private Long voterAge;
	private Long memberAge;
	private String memberRelativeName;
	private String voterRelativeName;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_voter_mapping_details_id", unique = true, nullable = false)
	public Long getMemberVoterMappingDetailsId() {
		return memberVoterMappingDetailsId;
	}
	public void setMemberVoterMappingDetailsId(Long memberVoterMappingDetailsId) {
		this.memberVoterMappingDetailsId = memberVoterMappingDetailsId;
	}
	
	
	@Column(name="member_id")
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
/*	@Column(name="member_matched_level_type_mapping_id")
	public Long getMemberMatchedLevelTypeMappingId() {
		return memberMatchedLevelTypeMappingId;
	}
	public void setMemberMatchedLevelTypeMappingId(
			Long memberMatchedLevelTypeMappingId) {
		this.memberMatchedLevelTypeMappingId = memberMatchedLevelTypeMappingId;
	}*/
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdMember getTdMember() {
		return tdMember;
	}
	public void setTdMember(TdMember tdMember) {
		this.tdMember = tdMember;
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
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_matched_level_type_mapping_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MemberMatchedLevelTypeMapping getMemberMatchedLevelTypeMapping() {
		return memberMatchedLevelTypeMapping;
	}
	public void setMemberMatchedLevelTypeMapping(
			MemberMatchedLevelTypeMapping memberMatchedLevelTypeMapping) {
		this.memberMatchedLevelTypeMapping = memberMatchedLevelTypeMapping;
	}
	
*/
	@Column(name="is_age_matched")
	public String getAgeMatched() {
		return ageMatched;
	}
	public void setAgeMatched(String ageMatched) {
		this.ageMatched = ageMatched;
	}
	
	@Column(name="is_gender_matched")
	public String getGenderMatched() {
		return genderMatched;
	}
	public void setGenderMatched(String genderMatched) {
		this.genderMatched = genderMatched;
	}
	
	@Column(name="is_relative_name_matched")
	public String getRelativeNameMatched() {
		return relativeNameMatched;
	}
	public void setRelativeNameMatched(String relativeNameMatched) {
		this.relativeNameMatched = relativeNameMatched;
	}
	

	@Column(name="member_matched_type_id")
	public Long getMemberMatchedTypeId() {
		return memberMatchedTypeId;
	}
	public void setMemberMatchedTypeId(Long memberMatchedTypeId) {
		this.memberMatchedTypeId = memberMatchedTypeId;
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
	
	
   @Column(name="match_count")
	public Long getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(Long matchCount) {
		this.matchCount = matchCount;
	}
	

	@Column(name="member_panchayat_name")
	public String getMemberPanchayatName() {
		return memberPanchayatName;
	}
	public void setMemberPanchayatName(String memberPanchayatName) {
		this.memberPanchayatName = memberPanchayatName;
	}
	
	@Column(name="voter_panchayat_name")
	public String getVoterPanchayatName() {
		return voterPanchayatName;
	}
	public void setVoterPanchayatName(String voterPanchayatName) {
		this.voterPanchayatName = voterPanchayatName;
	}
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
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
	
    @Column(name="member_gender")
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	
	@Column(name="voter_gender")
	public String getVoterGender() {
		return voterGender;
	}
	public void setVoterGender(String voterGender) {
		this.voterGender = voterGender;
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
	
	@Column(name="member_relative_name")
	public String getMemberRelativeName() {
		return memberRelativeName;
	}
	public void setMemberRelativeName(String memberRelativeName) {
		this.memberRelativeName = memberRelativeName;
	}
	
	@Column(name="voter_relative_name")
	public String getVoterRelativeName() {
		return voterRelativeName;
	}
	public void setVoterRelativeName(String voterRelativeName) {
		this.voterRelativeName = voterRelativeName;
	}
}
