package com.itgrids.partyanalyst.model;

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
@Table(name="tdp_member_union_tab_user_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpMemberUnionTabUserRelation implements java.io.Serializable{

	private Long tdpMemberUnionTabUserRelationId;
	private Long tdpMemberTypeId;
	private Long unionTabUserId;
	private String isActive;
	private String isDeleted;
	
	
	private TdpMemberType tdpMemberType;
	private UnionTabUser unionTabUser;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tdp_member_union_tab_user_relation_id", unique=true, nullable=false)
	public Long getTdpMemberUnionTabUserRelationId() {
		return tdpMemberUnionTabUserRelationId;
	}
	public void setTdpMemberUnionTabUserRelationId(
			Long tdpMemberUnionTabUserRelationId) {
		this.tdpMemberUnionTabUserRelationId = tdpMemberUnionTabUserRelationId;
	}
	
	@Column(name="tdp_member_type_id")
	public Long getTdpMemberTypeId() {
		return tdpMemberTypeId;
	}
	public void setTdpMemberTypeId(Long tdpMemberTypeId) {
		this.tdpMemberTypeId = tdpMemberTypeId;
	}
	
	@Column(name="union_tab_user_id")
	public Long getUnionTabUserId() {
		return unionTabUserId;
	}
	public void setUnionTabUserId(Long unionTabUserId) {
		this.unionTabUserId = unionTabUserId;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_member_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpMemberType getTdpMemberType() {
		return tdpMemberType;
	}
	public void setTdpMemberType(TdpMemberType tdpMemberType) {
		this.tdpMemberType = tdpMemberType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="union_tab_user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UnionTabUser getUnionTabUser() {
		return unionTabUser;
	}
	public void setUnionTabUser(UnionTabUser unionTabUser) {
		this.unionTabUser = unionTabUser;
	}
}
