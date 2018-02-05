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
@Table(name = "affiliate_member_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AffiliateMemberType{
	
	private Long affiliateMemberTypeId;
	private String affiliate_member_type;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "affiliate_member_type_id", unique = true, nullable = false)
	public Long getAffiliateMemberTypeId() {
		return affiliateMemberTypeId;
	}
	public void setAffiliateMemberTypeId(Long affiliateMemberTypeId) {
		this.affiliateMemberTypeId = affiliateMemberTypeId;
	}
	@Column(name="affiliate_member_type")
	public String getAffiliate_member_type() {
		return affiliate_member_type;
	}
	public void setAffiliate_member_type(String affiliate_member_type) {
		this.affiliate_member_type = affiliate_member_type;
	}
	
}
