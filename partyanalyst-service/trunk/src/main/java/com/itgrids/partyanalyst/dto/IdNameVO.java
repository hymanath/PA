package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Swadhin
 *
 */
public class IdNameVO implements Serializable,Comparator<IdNameVO>{
	private Long id;
	private String name;
	private Long districtid;
	private Long availableCount=0l;
	private Long actualCount=0l;
	private Long orderId;
	private String percentage;
	private Long count = 0l;
	private String dateStr;
	private List<String> subList = new ArrayList<String>();
	private List<IdNameVO> idnameList = new ArrayList<IdNameVO>();
	private String mobileNo;
	private String status;
	private String publicRepr;
	private String partyPos;
	private Long applicationStatusId;
	private String applicationStatus;
	private List<IdNameVO> subList1 = new ArrayList<IdNameVO>();
	private Long wishCount=0l;
	private String wish;
	private Long applicationsCount=0L;
	private Long memberId;
	private String districtName;
	private Long lessThan9 =0l;
	private Long between9To1030 =0l;
	private Long between1030To1130=0l;
	private Long between1130To13=0l;
	private Long greaterThan13=0l;
	private Long cadreId;
	private Long totalCount = 0l;
	private Long approvedCount = 0l;
	private Long rejectedCount = 0l;
	private Long pendingCount = 0l;
	private Long tabUserId;
	private String tabUserName;
	private String gender;
	private String image;
	private String voterImage;
	private Long statusId = 0l;
	private Long constitunecyId;
	private Long districtId;
	private Long cadreUserId;
	
	public int compare(IdNameVO o2, IdNameVO o1) {
		// TODO Auto-generated method stub
		return o2.getOrderId().compareTo(o1.getOrderId());
		
	}

	public IdNameVO(){}
	
	public IdNameVO(Long id,String name){
		this.id=id;
		this.name=name;
	}
	
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}

	public Long getActualCount() {
		return actualCount;
	}

	public void setActualCount(Long actualCount) {
		this.actualCount = actualCount;
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

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public List<String> getSubList() {
		return subList;
	}

	public void setSubList(List<String> subList) {
		this.subList = subList;
	}

	public List<IdNameVO> getIdnameList() {
		return idnameList;
	}

	public void setIdnameList(List<IdNameVO> idnameList) {
		this.idnameList = idnameList;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPublicRepr() {
		return publicRepr;
	}

	public void setPublicRepr(String publicRepr) {
		this.publicRepr = publicRepr;
	}

	public String getPartyPos() {
		return partyPos;
	}

	public void setPartyPos(String partyPos) {
		this.partyPos = partyPos;
	}

	public Long getApplicationStatusId() {
		return applicationStatusId;
	}

	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public List<IdNameVO> getSubList1() {
		return subList1;
	}

	public void setSubList1(List<IdNameVO> subList1) {
		this.subList1 = subList1;
	}

	public Long getWishCount() {
		return wishCount;
	}

	public void setWishCount(Long wishCount) {
		this.wishCount = wishCount;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}

	public Long getApplicationsCount() {
		return applicationsCount;
	}

	public void setApplicationsCount(Long applicationsCount) {
		this.applicationsCount = applicationsCount;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getLessThan9() {
		return lessThan9;
	}

	public void setLessThan9(Long lessThan9) {
		this.lessThan9 = lessThan9;
	}

	

	public Long getBetween9To1030() {
		return between9To1030;
	}

	public void setBetween9To1030(Long between9To1030) {
		this.between9To1030 = between9To1030;
	}

	public Long getBetween1030To1130() {
		return between1030To1130;
	}

	public void setBetween1030To1130(Long between1030To1130) {
		this.between1030To1130 = between1030To1130;
	}

	public Long getBetween1130To13() {
		return between1130To13;
	}

	public void setBetween1130To13(Long between1130To13) {
		this.between1130To13 = between1130To13;
	}

	public Long getGreaterThan13() {
		return greaterThan13;
	}

	public void setGreaterThan13(Long greaterThan13) {
		this.greaterThan13 = greaterThan13;
	}

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getApprovedCount() {
		return approvedCount;
	}

	public void setApprovedCount(Long approvedCount) {
		this.approvedCount = approvedCount;
	}

	public Long getRejectedCount() {
		return rejectedCount;
	}

	public void setRejectedCount(Long rejectedCount) {
		this.rejectedCount = rejectedCount;
	}

	public Long getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}

	public Long getTabUserId() {
		return tabUserId;
	}

	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}

	public String getTabUserName() {
		return tabUserName;
	}

	public void setTabUserName(String tabUserName) {
		this.tabUserName = tabUserName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVoterImage() {
		return voterImage;
	}

	public void setVoterImage(String voterImage) {
		this.voterImage = voterImage;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getConstitunecyId() {
		return constitunecyId;
	}

	public void setConstitunecyId(Long constitunecyId) {
		this.constitunecyId = constitunecyId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	public Long getCadreUserId() {
		return cadreUserId;
	}

	public void setCadreUserId(Long cadreUserId) {
		this.cadreUserId = cadreUserId;
	}
	
	
	
	
}
