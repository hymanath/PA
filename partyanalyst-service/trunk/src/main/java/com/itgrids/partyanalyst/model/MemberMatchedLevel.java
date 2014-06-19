package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "member_matched_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberMatchedLevel extends BaseModel implements java.io.Serializable {

	private Long memberMatchedLevelId;
	private String memberMatchedLevel;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_matched_level_id", unique = true, nullable = false)
	public Long getMemberMatchedLevelId() {
		return memberMatchedLevelId;
	}
	public void setMemberMatchedLevelId(Long memberMatchedLevelId) {
		this.memberMatchedLevelId = memberMatchedLevelId;
	}
	
	@Column(name="member_matched_level")
	public String getMemberMatchedLevel() {
		return memberMatchedLevel;
	}
	public void setMemberMatchedLevel(String memberMatchedLevel) {
		this.memberMatchedLevel = memberMatchedLevel;
	};


}
