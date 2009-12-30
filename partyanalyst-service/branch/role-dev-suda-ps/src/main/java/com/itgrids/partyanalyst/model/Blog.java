package com.itgrids.partyanalyst.model;

import com.itgrids.partyanalyst.BaseObject;

public class Blog extends BaseObject {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String subject;

	private String body;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
