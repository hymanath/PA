package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sms_module")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsModule extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long smsModuleId;
	private String moduleName;
	private String description;
	private Set<SmsHistory> smsHistory = new HashSet<SmsHistory>(0);

	// Constructors

	/** default constructor */
	public SmsModule() {
	}

	/** minimal constructor */
	public SmsModule(Long smsModuleId) {
		this.smsModuleId = smsModuleId;
	}

	/** full constructor */
	public SmsModule(Long smsModuleId,String moduleName,String description,Set<SmsHistory> smsHistory) {
		this.smsModuleId = smsModuleId;
		this.moduleName = moduleName;
		this.description = description;
		this.smsHistory = smsHistory;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_module_id", unique = true, nullable = false)
	public Long getSmsModuleId() {
		return smsModuleId;
	}

	public void setSmsModuleId(Long smsModuleId) {
		this.smsModuleId = smsModuleId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smsModule")
	public Set<SmsHistory> getSmsHistory() {
		return smsHistory;
	}

	public void setSmsHistory(Set<SmsHistory> smsHistory) {
		this.smsHistory = smsHistory;
	}

	@Column(name = "module_name", length = 250)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
