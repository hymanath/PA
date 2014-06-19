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
@Table(name = "booth")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberMatchedLevelTypeMapping extends BaseModel implements java.io.Serializable {
	
	private Long memberMatched_levelTypeMappingId;
	private Long memberMatchedTypeId;
	private Long memberMatchedLevelId;
	
	private MemberMatchedType memberMatchedType;
	private MemberMatchedLevel memberMatchedlevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_matched_level_type_mapping_id", unique = true, nullable = false)
	public Long getMemberMatched_levelTypeMappingId() {
		return memberMatched_levelTypeMappingId;
	}
	public void setMemberMatched_levelTypeMappingId(
			Long memberMatched_levelTypeMappingId) {
		this.memberMatched_levelTypeMappingId = memberMatched_levelTypeMappingId;
	}
	
	@Column(name="member_matched_type_id")
	public Long getMemberMatchedTypeId() {
		return memberMatchedTypeId;
	}
	public void setMemberMatchedTypeId(Long memberMatchedTypeId) {
		this.memberMatchedTypeId = memberMatchedTypeId;
	}
	
	@Column(name="member_matched_level_id")
	public Long getMemberMatchedLevelId() {
		return memberMatchedLevelId;
	}
	public void setMemberMatchedLevelId(Long memberMatchedLevelId) {
		this.memberMatchedLevelId = memberMatchedLevelId;
	}
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_matched_type_id",insertable = false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MemberMatchedType getMemberMatchedType() {
		return memberMatchedType;
	}
	public void setMemberMatchedType(MemberMatchedType memberMatchedType) {
		this.memberMatchedType = memberMatchedType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_matched_level_id",insertable = false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MemberMatchedLevel getMemberMatchedlevel() {
		return memberMatchedlevel;
	}
	public void setMemberMatchedlevel(MemberMatchedLevel memberMatchedlevel) {
		this.memberMatchedlevel = memberMatchedlevel;
	}
	
}
