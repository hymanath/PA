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

@Entity
@Table(name = "access_restricted_session")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccessRestrictedSession extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -843232939544655L;
	private Long accessRestrictedSessionId;
	private String sessionId;
	
	public AccessRestrictedSession(){}
	
	public AccessRestrictedSession(String sessionId){
		this.sessionId = sessionId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "access_restricted_session_id", unique = true, nullable = false)
	public Long getAccessRestrictedSessionId() {
		return accessRestrictedSessionId;
	}

	public void setAccessRestrictedSessionId(Long accessRestrictedSessionId) {
		this.accessRestrictedSessionId = accessRestrictedSessionId;
	}

	@Column(name = "session_id", length = 100)
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


}
