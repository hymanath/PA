package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "alert_verification_user_type_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertVerificationUserTypeUser extends BaseModel implements Serializable {
	
         private Long alertVerificationUserTypeUserId;
         private Long userId;
         private Long alertVerificationUserTypeId;
         private String displayName;
         
         private User user;
         private AlertVerificationUserType alertVerificationUserType;
         
        @Id
     	@GeneratedValue(strategy = GenerationType.AUTO)
     	@Column(name = "alert_verification_user_type_user_id", unique = true, nullable = false)
		public Long getAlertVerificationUserTypeUserId() {
			return alertVerificationUserTypeUserId;
		}
		public void setAlertVerificationUserTypeUserId(
				Long alertVerificationUserTypeUserId) {
			this.alertVerificationUserTypeUserId = alertVerificationUserTypeUserId;
		}
		@Column(name="user_id")
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		@Column(name="alert_verification_user_type_id")
		public Long getAlertVerificationUserTypeId() {
			return alertVerificationUserTypeId;
		}
		public void setAlertVerificationUserTypeId(Long alertVerificationUserTypeId) {
			this.alertVerificationUserTypeId = alertVerificationUserTypeId;
		}
		@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
		@JoinColumn(name = "user_id" , insertable = false, updatable = false)
		@LazyToOne(LazyToOneOption.NO_PROXY)
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
		@JoinColumn(name = "alert_verification_user_type_id" , insertable = false, updatable = false)
		@LazyToOne(LazyToOneOption.NO_PROXY)
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public AlertVerificationUserType getAlertVerificationUserType() {
			return alertVerificationUserType;
		}
		public void setAlertVerificationUserType(
				AlertVerificationUserType alertVerificationUserType) {
			this.alertVerificationUserType = alertVerificationUserType;
		}
		@Column(name = "display_name")
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
        
         
}
