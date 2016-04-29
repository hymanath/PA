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
@Table(name = "user_feedback_module")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserFeedbackModule extends BaseModel implements Serializable{

	private Long userFeedbackModuleId;
	private String moduleName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_feedback_module_id", unique = true, nullable = false)
	public Long getUserFeedbackModuleId() {
		return userFeedbackModuleId;
	}
	public void setUserFeedbackModuleId(Long userFeedbackModuleId) {
		this.userFeedbackModuleId = userFeedbackModuleId;
	}
	
	@Column(name="module_name")
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
