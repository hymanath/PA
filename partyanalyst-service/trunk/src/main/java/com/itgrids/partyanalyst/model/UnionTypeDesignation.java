package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union.MemberTypes;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="union_type_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnionTypeDesignation extends BaseModel implements Serializable{

	private Long unionTypeDesignationId;
	private Long unionTypeId;
	private Long designationId;
	private Long tdpMemberTypeId;
	
	private UnionType unionType;
	private Designation designation;
	private TdpMemberType tdpMemberType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="union_type_designation_id", unique=true, nullable=false)
	public Long getUnionTypeDesignationId() {
		return unionTypeDesignationId;
	}
	public void setUnionTypeDesignationId(Long unionTypeDesignationId) {
		this.unionTypeDesignationId = unionTypeDesignationId;
	}
	
	@Column(name="union_type_id")
	public Long getUnionTypeId() {
		return unionTypeId;
	}
	public void setUnionTypeId(Long unionTypeId) {
		this.unionTypeId = unionTypeId;
	}
	
	@Column(name="tdp_member_type")
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="union_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UnionType getUnionType() {
		return unionType;
	}
	public void setUnionType(UnionType unionType) {
		this.unionType = unionType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="designation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	
	@Column(name="tdp_member_type_id")
	public Long getTdpMemberTypeId() {
		return tdpMemberTypeId;
	}
	public void setTdpMemberTypeId(Long tdpMemberTypeId) {
		this.tdpMemberTypeId = tdpMemberTypeId;
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
	
	
}
