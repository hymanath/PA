package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import com.itgrids.partyanalyst.model.BaseModel;

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
@Table(name= "group_entitlement_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GroupEntitlementRelation extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937418507411279467L;
	
	private Long groupEntitlementRelationId;
	private GroupEntitlement groupEntitlement;
	private Entitlement entitlement;
	
	public GroupEntitlementRelation(){
		
	}
	
	public GroupEntitlementRelation(Long groupEntitlementRelationId){
		this.groupEntitlementRelationId = groupEntitlementRelationId;
	}
	
	public GroupEntitlementRelation(Long groupEntitlementRelationId,
			GroupEntitlement groupEntitlement, Entitlement entitlement) {
		super();
		this.groupEntitlementRelationId = groupEntitlementRelationId;
		this.groupEntitlement = groupEntitlement;
		this.entitlement = entitlement;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="group_entitlement_relation_id", unique = true, nullable = false)
	public Long getGroupEntitlementRelationId() {
		return groupEntitlementRelationId;
	}
	
	public void setGroupEntitlementRelationId(Long groupEntitlementRelationId) {
		this.groupEntitlementRelationId = groupEntitlementRelationId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "group_entitlement_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GroupEntitlement getGroupEntitlement() {
		return groupEntitlement;
	}
	
	public void setGroupEntitlement(GroupEntitlement groupEntitlement) {
		this.groupEntitlement = groupEntitlement;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "entitlement_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Entitlement getEntitlement() {
		return entitlement;
	}
	
	public void setEntitlement(Entitlement entitlement) {
		this.entitlement = entitlement;
	}
		
}
