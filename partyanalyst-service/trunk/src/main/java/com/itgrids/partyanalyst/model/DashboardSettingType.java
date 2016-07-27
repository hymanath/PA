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
@Table(name = "dashboard_setting_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardSettingType extends BaseModel{

	private static final long serialVersionUID = -5458630643704669803L;
	private Long dashboardSettingTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dashboard_setting_type_id", unique = true, nullable = false)
	public Long getDashboardSettingTypeId() {
		return dashboardSettingTypeId;
	}
	public void setDashboardSettingTypeId(Long dashboardSettingTypeId) {
		this.dashboardSettingTypeId = dashboardSettingTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
