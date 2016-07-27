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
@Table(name = "dashboard_setting_value")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardSettingValue extends BaseModel {
	
	private static final long serialVersionUID = -3564464284442371835L;
	private Long dashboardSettingValueId;
	private String value;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dashboard_setting_value_id", unique = true, nullable = false)
	public Long getDashboardSettingValueId() {
		return dashboardSettingValueId;
	}
	public void setDashboardSettingValueId(Long dashboardSettingValueId) {
		this.dashboardSettingValueId = dashboardSettingValueId;
	}
	
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
