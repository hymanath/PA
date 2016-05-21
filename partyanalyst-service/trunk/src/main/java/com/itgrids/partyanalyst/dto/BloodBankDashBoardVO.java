package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class BloodBankDashBoardVO {
	
	private Long id;
	private String name;
	private Long totalApplicants;
	private Long totalAccepted;
	private Long totalRejected;
	private Long dayWiseCount;
	private List<BloodBankDashBoardVO> bloodBankDashBoardVO = new ArrayList<BloodBankDashBoardVO>(0);
	
	private List<BloodBankDashBoardVO> subList = new ArrayList<BloodBankDashBoardVO>(0);
	private List<BloodBankDashBoardVO> singleBagList = new ArrayList<BloodBankDashBoardVO>(0);
	private List<BloodBankDashBoardVO> doubleBagList = new ArrayList<BloodBankDashBoardVO>(0);
	private List<BloodBankDashBoardVO> tripleBagList = new ArrayList<BloodBankDashBoardVO>(0);
	private List<BloodBankDashBoardVO> quadrupleList = new ArrayList<BloodBankDashBoardVO>(0);
	private Long totalBlood;
	private String date;
	
	private Long bloodBagTypeId;
	private String bagType;
	private Long bloodBagQuantityId;
	private String quantityType;
	private Long quantity;
	private Long count;
	private Long ageGroupId;
	private String ageGroup;
	private Long totalCount;
	private String DaySatus;
	
	
	public Long getAgeGroupId() {
		return ageGroupId;
	}
	public void setAgeGroupId(Long ageGroupId) {
		this.ageGroupId = ageGroupId;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getDaySatus() {
		return DaySatus;
	}
	public void setDaySatus(String daySatus) {
		DaySatus = daySatus;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalApplicants() {
		return totalApplicants;
	}
	public void setTotalApplicants(Long totalApplicants) {
		this.totalApplicants = totalApplicants;
	}
	public Long getTotalAccepted() {
		return totalAccepted;
	}
	public void setTotalAccepted(Long totalAccepted) {
		this.totalAccepted = totalAccepted;
	}
	public Long getTotalRejected() {
		return totalRejected;
	}
	public void setTotalRejected(Long totalRejected) {
		this.totalRejected = totalRejected;
	}
	public Long getDayWiseCount() {
		return dayWiseCount;
	}
	public void setDayWiseCount(Long dayWiseCount) {
		this.dayWiseCount = dayWiseCount;
	}
	public List<BloodBankDashBoardVO> getBloodBankDashBoardVO() {
		return bloodBankDashBoardVO;
	}
	public void setBloodBankDashBoardVO(
			List<BloodBankDashBoardVO> bloodBankDashBoardVO) {
		this.bloodBankDashBoardVO = bloodBankDashBoardVO;
	}
	public Long getTotalBlood() {
		return totalBlood;
	}
	public void setTotalBlood(Long totalBlood) {
		this.totalBlood = totalBlood;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
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
	public Long getBloodBagTypeId() {
		return bloodBagTypeId;
	}
	public void setBloodBagTypeId(Long bloodBagTypeId) {
		this.bloodBagTypeId = bloodBagTypeId;
	}
	public String getBagType() {
		return bagType;
	}
	public void setBagType(String bagType) {
		this.bagType = bagType;
	}
	public Long getBloodBagQuantityId() {
		return bloodBagQuantityId;
	}
	public void setBloodBagQuantityId(Long bloodBagQuantityId) {
		this.bloodBagQuantityId = bloodBagQuantityId;
	}
	public String getQuantityType() {
		return quantityType;
	}
	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<BloodBankDashBoardVO> getSubList() {
		return subList;
	}
	public void setSubList(List<BloodBankDashBoardVO> subList) {
		this.subList = subList;
	}
	public List<BloodBankDashBoardVO> getSingleBagList() {
		return singleBagList;
	}
	public void setSingleBagList(List<BloodBankDashBoardVO> singleBagList) {
		this.singleBagList = singleBagList;
	}
	public List<BloodBankDashBoardVO> getDoubleBagList() {
		return doubleBagList;
	}
	public void setDoubleBagList(List<BloodBankDashBoardVO> doubleBagList) {
		this.doubleBagList = doubleBagList;
	}
	public List<BloodBankDashBoardVO> getTripleBagList() {
		return tripleBagList;
	}
	public void setTripleBagList(List<BloodBankDashBoardVO> tripleBagList) {
		this.tripleBagList = tripleBagList;
	}
	public List<BloodBankDashBoardVO> getQuadrupleList() {
		return quadrupleList;
	}
	public void setQuadrupleList(List<BloodBankDashBoardVO> quadrupleList) {
		this.quadrupleList = quadrupleList;
	}
}
