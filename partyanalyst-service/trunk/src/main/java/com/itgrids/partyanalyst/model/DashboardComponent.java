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
@Table(name = "dashboard_component")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardComponent extends BaseModel{
	
	
	private static final long serialVersionUID = -4437344667993777145L;
	private Long  dashboardComponentId;
	private String  componentName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dashboard_component_id", unique = true, nullable = false)
	public Long getDashboardComponentId() {
		return dashboardComponentId;
	}
	public void setDashboardComponentId(Long dashboardComponentId) {
		this.dashboardComponentId = dashboardComponentId;
	}
	
	@Column(name="component_name")
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	
	
}
