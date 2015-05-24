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
@Table(name = "messaging_props_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessagingPropsDetails {
	private  Long messagingPropsDetailsId;
	private String username;
	private String password;
	private String virtualHost;
	private String host;
	private String port;
	private String exchangeName;
	private String secreatKey;
	private String appName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="messaging_props_details_id", unique=true, nullable=false)
	public Long getMessagingPropsDetailsId() {
		return messagingPropsDetailsId;
	}
	public void setMessagingPropsDetailsId(Long messagingPropsDetailsId) {
		this.messagingPropsDetailsId = messagingPropsDetailsId;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="virtual_host")
	public String getVirtualHost() {
		return virtualHost;
	}
	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}
	@Column(name="host")
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	@Column(name="port")
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Column(name="exchange_name")
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	@Column(name="secreat_key")
	public String getSecreatKey() {
		return secreatKey;
	}
	public void setSecreatKey(String secreatKey) {
		this.secreatKey = secreatKey;
	}
	@Column(name="app_name")
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	

}
