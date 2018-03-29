package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AreaInchargeVO implements Serializable{
	
	private Long id;
	private String name;
	private String relativeName;
	private Long age;
	private String gender;
	private String address;
	private String caste;
	private String houseNo;
	private Long tehsilId;
	private String tehsilName;
    private String isActive;
    private String image;
    private List<AreaInchargeVO>  assignBoothList = new ArrayList<AreaInchargeVO>();
    private List<AreaInchargeVO>  unAssignBoothList = new ArrayList<AreaInchargeVO>();
    private Long panchayatId;
    private String panchayatName;
    private Long total=0l;
    private Long assignedCount =0l;
    private Long unAssignedCount =0l;
    private List<Long> assignBoothIds = new ArrayList<Long>();
    private Set<Long> assignIds =new HashSet<Long>();
    private String memberShipNo;
    private String mobileNo;
    private Double assignPer=0.0;
    private Double pendingPers=0.0;
    private Long inchargeCount =0l;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<AreaInchargeVO> getAssignBoothList() {
		return assignBoothList;
	}
	public void setAssignBoothList(List<AreaInchargeVO> assignBoothList) {
		this.assignBoothList = assignBoothList;
	}
	public List<AreaInchargeVO> getUnAssignBoothList() {
		return unAssignBoothList;
	}
	public void setUnAssignBoothList(List<AreaInchargeVO> unAssignBoothList) {
		this.unAssignBoothList = unAssignBoothList;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getAssignedCount() {
		return assignedCount;
	}
	public void setAssignedCount(Long assignedCount) {
		this.assignedCount = assignedCount;
	}
	public Long getUnAssignedCount() {
		return unAssignedCount;
	}
	public void setUnAssignedCount(Long unAssignedCount) {
		this.unAssignedCount = unAssignedCount;
	}
	public List<Long> getAssignBoothIds() {
		return assignBoothIds;
	}
	public void setAssignBoothIds(List<Long> assignBoothIds) {
		this.assignBoothIds = assignBoothIds;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Double getAssignPer() {
		return assignPer;
	}
	public void setAssignPer(Double assignPer) {
		this.assignPer = assignPer;
	}
	public Double getPendingPers() {
		return pendingPers;
	}
	public void setPendingPers(Double pendingPers) {
		this.pendingPers = pendingPers;
	}
	public Long getInchargeCount() {
		return inchargeCount;
	}
	public void setInchargeCount(Long inchargeCount) {
		this.inchargeCount = inchargeCount;
	}
	public Set<Long> getAssignIds() {
		return assignIds;
	}
	public void setAssignIds(Set<Long> assignIds) {
		this.assignIds = assignIds;
	}
	

}
