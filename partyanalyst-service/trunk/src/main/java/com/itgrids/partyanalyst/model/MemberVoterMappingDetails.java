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
	
	
	/*CREATE TABLE member_voter_mapping_details (
			  member_voter_mapping_details_id bigint(15) NOT NULL AUTO_INCREMENT,
			  member_id bigint(20) DEFAULT NULL ,
			  voter_id bigint(15) NOT NULL,
			  member_matched_level_type_mapping_id bigint(15),
			  KEY fk_member_membr_voter_mapping_details (member_id),
			  KEY fk_member_voter (voter_id),
			  KEY fk_member_voter (member_matched_level_type_mapping_id),
			  PRIMARY KEY (member_voter_mapping_details_id)
			) ;
*/
	
	private Long memberVoterMappingDetailsId;
	private Long memberId;
	private Long voterId;
	private Long memberMatchedLevelTypeMappingId;
	private TdMember tdMember;
	private Voter voter;
	private MemberMatchedLevelTypeMapping memberMatchedLevelTypeMapping;
	
	
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
	
	@Column(name="member_matched_level_type_mapping_id")
	public Long getMemberMatchedLevelTypeMappingId() {
		return memberMatchedLevelTypeMappingId;
	}
	public void setMemberMatchedLevelTypeMappingId(
			Long memberMatchedLevelTypeMappingId) {
		this.memberMatchedLevelTypeMappingId = memberMatchedLevelTypeMappingId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdMember getTdMember() {
		return tdMember;
	}
	public void setTdMember(TdMember tdMember) {
		this.tdMember = tdMember;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_matched_level_type_mapping_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MemberMatchedLevelTypeMapping getMemberMatchedLevelTypeMapping() {
		return memberMatchedLevelTypeMapping;
	}
	public void setMemberMatchedLevelTypeMapping(
			MemberMatchedLevelTypeMapping memberMatchedLevelTypeMapping) {
		this.memberMatchedLevelTypeMapping = memberMatchedLevelTypeMapping;
	}
}
