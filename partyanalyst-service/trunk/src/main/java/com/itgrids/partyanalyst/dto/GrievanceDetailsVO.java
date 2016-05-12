package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Teja
 * @date may 3, 2016
 */
/**
 * @author Client
 *
 */

public class GrievanceDetailsVO implements Serializable{

	private Long id;
	private String name;
	private Long Count;
	private String status;
	private String locationName;
	private Long partyCount=0l;
	private Long govtCount=0l;
	private Long welfareCount=0l;
	private String idStr;
	
	private Long deathCount = 0l;
	private Long hospitCount = 0l;
	private List<GrievanceDetailsVO> subList = new LinkedList<GrievanceDetailsVO>();	
	private List<IdNameVO> locationList = new ArrayList<IdNameVO>();
	private String partyPerc;
	private String govtPerc;
	private String welfarePerc;
	private Long benifitCount=0l;
	
	private String firstName;
	private String membershipNo;
	private String mobileNo;
	private String description;
	private String subject;
	private Long complaintId;
	private String raisedDate;
	private String typeOfIssue;
	private String updatedDate;
	private Long statusId;
	private Long tdpCadreId;
	
	private String dateStr;
	private String comment;
	private String approvedAmount;
	private String constituency;
	private String villageName;
	private String mandalName;
	private String supportPurpose;
	private String issueType;
	
	
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getSupportPurpose() {
		return supportPurpose;
	}
	public void setSupportPurpose(String supportPurpose) {
		this.supportPurpose = supportPurpose;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(String approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getBenifitCount() {
		return benifitCount;
	}
	public void setBenifitCount(Long benifitCount) {
		this.benifitCount = benifitCount;
	}
	public String getPartyPerc() {
		return partyPerc;
	}
	public void setPartyPerc(String partyPerc) {
		this.partyPerc = partyPerc;
	}
	public String getGovtPerc() {
		return govtPerc;
	}
	public void setGovtPerc(String govtPerc) {
		this.govtPerc = govtPerc;
	}
	public String getWelfarePerc() {
		return welfarePerc;
	}
	public void setWelfarePerc(String welfarePerc) {
		this.welfarePerc = welfarePerc;
	}
	
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}
	public String getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(String raisedDate) {
		this.raisedDate = raisedDate;
	}
	public String getTypeOfIssue() {
		return typeOfIssue;
	}
	public void setTypeOfIssue(String typeOfIssue) {
		this.typeOfIssue = typeOfIssue;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public List<IdNameVO> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<IdNameVO> locationList) {
		this.locationList = locationList;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public List<GrievanceDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<GrievanceDetailsVO> subList) {
		this.subList = subList;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getDeathCount() {
		return deathCount;
	}
	public void setDeathCount(Long deathCount) {
		this.deathCount = deathCount;
	}
	public Long getHospitCount() {
		return hospitCount;
	}
	public void setHospitCount(Long hospitCount) {
		this.hospitCount = hospitCount;
	}
	public Long getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}
	public Long getGovtCount() {
		return govtCount;
	}
	public void setGovtCount(Long govtCount) {
		this.govtCount = govtCount;
	}
	public Long getWelfareCount() {
		return welfareCount;
	}
	public void setWelfareCount(Long welfareCount) {
		this.welfareCount = welfareCount;
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
	public Long getCount() {
		return Count;
	}
	public void setCount(Long count) {
		Count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
