package com.itgrids.model;

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


@Entity
@Table(name = "nrega_component")
public class NregaComponent extends BaseModel implements Serializable{
	
	private Long nregaComponentId;
	private String componentName;
	private Long convergenceTypeId;
	
	private ConvergenceType convergenceType;

	@Id
	@Column(name="nrega_component_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaComponentId() {
		return nregaComponentId;
	}
	public void setNregaComponentId(Long nregaComponentId) {
		this.nregaComponentId = nregaComponentId;
	}

	@Column(name="component_name")
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	@Column(name="convergence_type_id")
	public Long getConvergenceTypeId() {
		return convergenceTypeId;
	}
	public void setConvergenceTypeId(Long convergenceTypeId) {
		this.convergenceTypeId = convergenceTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "convergence_type_id", insertable = false, updatable = false)
	public ConvergenceType getConvergenceType() {
		return convergenceType;
	}
	public void setConvergenceType(ConvergenceType convergenceType) {
		this.convergenceType = convergenceType;
	}
}
