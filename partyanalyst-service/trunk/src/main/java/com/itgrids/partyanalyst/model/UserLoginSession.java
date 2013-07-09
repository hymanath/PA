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
@Table(name = "user_login_session")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLoginSession extends BaseModel implements Serializable {
	private Long userLoginSessionId;
	private String sessionId;
	
public UserLoginSession(){
		
	}
	
public UserLoginSession(Long userLoginSessionId,String sessionId){
	this.userLoginSessionId =userLoginSessionId;
	this.sessionId = sessionId;
}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "user_login_session_id", unique = true, nullable = false)
public Long getUserLoginSessionId() {
	return userLoginSessionId;
}

public void setUserLoginSessionId(Long userLoginSessionId) {
	this.userLoginSessionId = userLoginSessionId;
}
@Column(name = "session_id", length = 100)
public String getSessionId() {
	return sessionId;
}

public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
}


}
