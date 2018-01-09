package com.itgrids.model;

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

@Entity
@Table(name = "component_target")
public class ComponentTarget {
	
	private Long componentTargetId;	
	private Long   nregaComponentId;
	private String	target;
	private Long   orderNo;
	private String isDeleted;
	private  NregaComponent nregaComponent;
	@Id 
	@Column(name="component_target_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getComponentTargetId() {
		return componentTargetId;
	}
	public void setComponentTargetId(Long componentTargetId) {
		this.componentTargetId = componentTargetId;
	}
	@Column(name="nrega_component_id")
	public Long getNregaComponentId() {
		return nregaComponentId;
	}
	public void setNregaComponentId(Long nregaComponentId) {
		this.nregaComponentId = nregaComponentId;
	}
	@Column(name="target")
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_component_id", insertable = false, updatable = false)
	public NregaComponent getNregaComponent() {
		return nregaComponent;
	}
	public void setNregaComponent(NregaComponent nregaComponent) {
		this.nregaComponent = nregaComponent;
	}
	
}

