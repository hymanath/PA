package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rurban_component")
public class RurbanComponent {
	private Long rurbanComponentId;
	private String componentName;
	@Id
	@Column(name="rurban_component_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRurbanComponentId() {
		return rurbanComponentId;
	}
	public void setRurbanComponentId(Long rurbanComponentId) {
		this.rurbanComponentId = rurbanComponentId;
	}
	@Column(name="component_name")
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
}
