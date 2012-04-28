package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class EmailDetailsVO implements Serializable{

	private static final long serialVersionUID = 2598121949467285613L;
	
	private String subject;
	private String toAddress;
	private String fromAddress;
	private String content;
	private String host;
	private String welcomeName;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWelcomeName() {
		return welcomeName;
	}
	public void setWelcomeName(String welcomeName) {
		this.welcomeName = welcomeName;
	}
	
}
