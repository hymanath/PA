package com.itgrids.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meeseva_kpi_centers")
public class MeesevaKpiCenters extends BaseModel implements Serializable{
	
	private Long meesevaKpiCentersId;
	private String districtId;
	private String districtName;
	private String mandalId;
	private String mandalName;
	private String villageId;
	private String villageName;
	private String agentId;
	private String agentName;
	private String mobileNo;
	private String caste;
	private String identityNo;
	private String address;
	private String centerType;
	private Date estdate;
	private Date insertedTime;
	
	@Id
	@Column(name="meeseva_kpi_centers_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getMeesevaKpiCentersId() {
		return meesevaKpiCentersId;
	}
	public void setMeesevaKpiCentersId(Long meesevaKpiCentersId) {
		this.meesevaKpiCentersId = meesevaKpiCentersId;
	}
	
	@Column(name="district_id")
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@Column(name="mandal_id")
	public String getMandalId() {
		return mandalId;
	}
	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}
	
	@Column(name="mandal_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	@Column(name="village_id")
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	
	@Column(name="village_name")
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	@Column(name="agent_id")
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
	@Column(name="agent_name")
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="caste")
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	
	@Column(name="identity_no")
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="center_type")
	public String getCenterType() {
		return centerType;
	}
	public void setCenterType(String centerType) {
		this.centerType = centerType;
	}
	
	@Column(name="estdate")
	public Date getEstdate() {
		return estdate;
	}
	public void setEstdate(Date estdate) {
		this.estdate = estdate;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

}
