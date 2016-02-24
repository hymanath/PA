package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tdp_member_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpMemberType extends BaseModel implements Serializable{

	private Long tdpMemberTypeId;
	private String memberType;
	private String isDeleted;
	private Long orderNo;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tdp_member_type_id", unique=true, nullable=false)
	public Long getTdpMemberTypeId() {
		return tdpMemberTypeId;
	}
	public void setTdpMemberTypeId(Long tdpMemberTypeId) {
		this.tdpMemberTypeId = tdpMemberTypeId;
	}
	
	@Column(name="member_type")
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
