package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "news_bullition_values")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsBullitionValues  extends BaseModel implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Long  newsBullitionValuesId;
	private Long  newsBullitionId;
	private Long  newsBullitionTypeId;
	private Long  newsBullitionNewsTypeId;
	private Long  districtId;
	private Long  mandalId;
	private Long  villageId;
	private Date  insertedTime;
	private Date  updateTime;
	
	private NewsBullition newsBullition;
	private NewsBullitionNewsType newsBullitionNewsType;
	private NewsBullitionType newsBullitionType;
	
	private District district;
	private Tehsil tehsil;
	private Panchayat panchayat;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "news_bullition_values_id", unique = true, nullable = false)
	public Long getNewsBullitionValuesId() {
		return newsBullitionValuesId;
	}
	public void setNewsBullitionValuesId(Long newsBullitionValuesId) {
		this.newsBullitionValuesId = newsBullitionValuesId;
	}
	@Column(name="news_bullition_id")
	public Long getNewsBullitionId() {
		return newsBullitionId;
	}
	public void setNewsBullitionId(Long newsBullitionId) {
		this.newsBullitionId = newsBullitionId;
	}
	
	@Column(name="news_bullition_type_id")
	public Long getNewsBullitionTypeId() {
		return newsBullitionTypeId;
	}
	public void setNewsBullitionTypeId(Long newsBullitionTypeId) {
		this.newsBullitionTypeId = newsBullitionTypeId;
	}
	
	@Column(name="news_bullition_news_type_id")
	public Long getNewsBullitionNewsTypeId() {
		return newsBullitionNewsTypeId;
	}
	public void setNewsBullitionNewsTypeId(Long newsBullitionNewsTypeId) {
		this.newsBullitionNewsTypeId = newsBullitionNewsTypeId;
	}
	
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	@Column(name="mandal_id")
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	
	@Column(name="village_id")
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name="news_bullition_id" , insertable = false , updatable = false)
	public NewsBullition getNewsBullition() {
		return newsBullition;
	}
	public void setNewsBullition(NewsBullition newsBullition) {
		this.newsBullition = newsBullition;
	}
	
	@Column(name="news_bullition_news_type_id" , insertable = false , updatable = false)
	public NewsBullitionNewsType getNewsBullitionNewsType() {
		return newsBullitionNewsType;
	}
	public void setNewsBullitionNewsType(NewsBullitionNewsType newsBullitionNewsType) {
		this.newsBullitionNewsType = newsBullitionNewsType;
	}
	
	@Column(name="news_bullition_type_id" , insertable = false , updatable = false)
	public NewsBullitionType getNewsBullitionType() {
		return newsBullitionType;
	}
	public void setNewsBullitionType(NewsBullitionType newsBullitionType) {
		this.newsBullitionType = newsBullitionType;
	}
	
	@Column(name="district_id" , insertable = false , updatable = false)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	@Column(name="mandal_id" , insertable = false , updatable = false)
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	
	@Column(name="village_id" , insertable = false , updatable = false)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	
	
}
