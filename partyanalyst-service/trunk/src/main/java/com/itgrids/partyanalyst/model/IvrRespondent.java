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
@Table(name = "ivr_respondent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrRespondent extends BaseModel implements java.io.Serializable {
	

	private Long ivrRespondentId;
	private String mobileNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_respondent_id", unique = true, nullable = false)
	public Long getIvrRespondentId() {
		return ivrRespondentId;
	}
	public void setIvrRespondentId(Long ivrRespondentId) {
		this.ivrRespondentId = ivrRespondentId;
	}
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}	
}
