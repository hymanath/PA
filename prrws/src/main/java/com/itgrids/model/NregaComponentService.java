package com.itgrids.model;


import java.io.Serializable;

import javax.jws.WebService;
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
@Table(name = "nrega_component_service")
public class NregaComponentService extends BaseModel implements Serializable{
	
	private Long nregaComponentServiceId;
	private Long nregaComponentId;
	private Long webserviceId;
	private String serviceType;
	
	private  NregaComponent nregaComponent;
	private Webservice webService;
	
	@Id
	@Column(name="nrega_component_service_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaComponentServiceId() {
		return nregaComponentServiceId;
	}
	public void setNregaComponentServiceId(Long nregaComponentServiceId) {
		this.nregaComponentServiceId = nregaComponentServiceId;
	}
	
	@Column(name="nrega_component_id")
	public Long getNregaComponentId() {
		return nregaComponentId;
	}
	public void setNregaComponentId(Long nregaComponentId) {
		this.nregaComponentId = nregaComponentId;
	}
	
	@Column(name="webservice_id")
	public Long getWebserviceId() {
		return webserviceId;
	}
	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}
	
	@Column(name="service_type")
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_component_id", insertable = false, updatable = false)
	public NregaComponent getNregaComponent() {
		return nregaComponent;
	}
	public void setNregaComponent(NregaComponent nregaComponent) {
		this.nregaComponent = nregaComponent;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "webservice_id", insertable = false, updatable = false)
	public Webservice getWebService() {
		return webService;
	}
	public void setWebService(Webservice webService) {
		this.webService = webService;
	}
}
