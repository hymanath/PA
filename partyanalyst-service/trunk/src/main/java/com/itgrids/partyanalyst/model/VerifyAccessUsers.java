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
@Table(name = "verify_access_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VerifyAccessUsers extends BaseModel implements java.io.Serializable {

	
	private static final long serialVersionUID = -9183875530485737798L;
	private Long verifyAccessUsersId;
	private Long userId; 
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "verify_access_users_id", unique = true, nullable = false)
	public Long getVerifyAccessUsersId() {
		return verifyAccessUsersId;
	}
	public void setVerifyAccessUsersId(Long verifyAccessUsersId) {
		this.verifyAccessUsersId = verifyAccessUsersId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
