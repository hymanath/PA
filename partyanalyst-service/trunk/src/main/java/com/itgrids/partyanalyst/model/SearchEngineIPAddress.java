package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * search_engine_ipaddress entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="search_engine_ipaddress")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SearchEngineIPAddress extends BaseModel implements Serializable{

	private static final long serialVersionUID = 6997647658438208487L;
	
	private Long searchEngineIPAddressId;
	private String ipAddress;
	private String hostName;
	
	public SearchEngineIPAddress(){}
	
	public SearchEngineIPAddress(String ipAddress,String hostName)
	{
		this.ipAddress = ipAddress;
		this.hostName = hostName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_engine_ipaddress_id", unique=true, nullable=false)
	public Long getSearchEngineIPAddressId() {
		return searchEngineIPAddressId;
	}

	public void setSearchEngineIPAddressId(Long searchEngineIPAddressId) {
		this.searchEngineIPAddressId = searchEngineIPAddressId;
	}

	@Column(name="ip_address",length=25)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name="host_name",length=100)
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	

}
