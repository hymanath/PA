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
@Table(name = "committe_ivr_district_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommitteIvrDistrictDetail extends BaseModel implements Serializable {

	private Long committeIvrDistrictDetailId;
	private Long districtId;
	private String districtName;
	private String callStatus;
	private Long optionId;
	private Long count;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="committe_ivr_district_detail_id", unique=true, nullable=false)
	public Long getCommitteIvrDistrictDetailId() {
		return committeIvrDistrictDetailId;
	}
	public void setCommitteIvrDistrictDetailId(Long committeIvrDistrictDetailId) {
		this.committeIvrDistrictDetailId = committeIvrDistrictDetailId;
	}
	
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@Column(name="call_status")
	public String getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}
	
	@Column(name="option_id")
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	
	@Column(name="count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
