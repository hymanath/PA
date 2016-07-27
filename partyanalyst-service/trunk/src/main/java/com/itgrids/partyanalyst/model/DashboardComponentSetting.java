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
@Table(name = "dashboard_component_setting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardComponentSetting extends BaseModel{

	private static final long serialVersionUID = -8763882882204328292L;
	
	private Long dashboardComponentSettingId;
	private Long dahboardSettingTypeId;
	private Long dashboardSettingValueId;
	
	private DashboardSettingType dashboardSettingType;
	private DashboardSettingValue dashboardSettingValue;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dashboard_component_setting_id", unique = true, nullable = false)
	public Long getDashboardComponentSettingId() {
		return dashboardComponentSettingId;
	}
	public void setDashboardComponentSettingId(Long dashboardComponentSettingId) {
		this.dashboardComponentSettingId = dashboardComponentSettingId;
	}
	
	@Column(name="dashboard_setting_type_id")
	public Long getDahboardSettingTypeId() {
		return dahboardSettingTypeId;
	}
	public void setDahboardSettingTypeId(Long dahboardSettingTypeId) {
		this.dahboardSettingTypeId = dahboardSettingTypeId;
	}
	
	@Column(name="dashboard_setting_value_id")
	public Long getDashboardSettingValueId() {
		return dashboardSettingValueId;
	}
	public void setDashboardSettingValueId(Long dashboardSettingValueId) {
		this.dashboardSettingValueId = dashboardSettingValueId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="dashboard_setting_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DashboardSettingType getDashboardSettingType() {
		return dashboardSettingType;
	}
	public void setDashboardSettingType(DashboardSettingType dashboardSettingType) {
		this.dashboardSettingType = dashboardSettingType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="dashboard_setting_value_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DashboardSettingValue getDashboardSettingValue() {
		return dashboardSettingValue;
	}
	public void setDashboardSettingValue(DashboardSettingValue dashboardSettingValue) {
		this.dashboardSettingValue = dashboardSettingValue;
	}
	
	
}
