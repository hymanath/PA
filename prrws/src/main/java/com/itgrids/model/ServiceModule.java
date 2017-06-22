package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "constituency")
public class ServiceModule implements Serializable{

	private static final long serialVersionUID = -360465960029008718L;

	private Long serviceModuleId;
	private String moduleName;

	@Id
	@Column(name="service_module_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getServiceModuleId() {
		return serviceModuleId;
	}

	public void setServiceModuleId(Long serviceModuleId) {
		this.serviceModuleId = serviceModuleId;
	}

	@Column(name="module_name")
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
