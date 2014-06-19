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
@Table(name = "member_matched_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberMatchedType extends BaseModel implements java.io.Serializable {
	
	private Long memberMatchedTypeId;
	private String memberMatchedType;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_matched_type_id", unique = true, nullable = false)
	public Long getMemberMatchedTypeId() {
		return memberMatchedTypeId;
	}
	public void setMemberMatchedTypeId(Long memberMatchedTypeId) {
		this.memberMatchedTypeId = memberMatchedTypeId;
	}
	public String getMemberMatchedType() {
		return memberMatchedType;
	}
	public void setMemberMatchedType(String memberMatchedType) {
		this.memberMatchedType = memberMatchedType;
	}

}
