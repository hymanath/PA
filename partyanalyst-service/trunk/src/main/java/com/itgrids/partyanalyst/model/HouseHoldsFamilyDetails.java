package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="house_holds_family_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HouseHoldsFamilyDetails extends BaseModel implements Serializable{
	
	 private Long HouseHoldsFamilyDetailsId;
	 private String name;
	 private String relationshipType;
	 private String relativeName;
	 private String gender;
	 private Long age;
	 private Date insertedTime;
	 private String mobileNo;
	 private String isDelete;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "house_holds_family_details_id", unique = true, nullable = false)
	public Long getHouseHoldsFamilyDetailsId() {
		return HouseHoldsFamilyDetailsId;
	}
	public void setHouseHoldsFamilyDetailsId(Long houseHoldsFamilyDetailsId) {
		HouseHoldsFamilyDetailsId = houseHoldsFamilyDetailsId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="relationship_type")
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	
	@Column(name="relative_name")
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	@Column(name="is_delete")  
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	 

}
