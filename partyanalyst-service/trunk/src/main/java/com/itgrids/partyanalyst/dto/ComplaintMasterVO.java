/**
 * @author SRAVANTH
 * MAR 27, 2017
 * ComplaintMasterVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date MAR 27, 2017
 */
public class ComplaintMasterVO {

	private Long id;
	private String name;
	
	private Long complaintId;
	private String mobileNo;
	private String districtName;
	private String constituencyName;
	private String mandalName;
	private String villageName;
	private String subject;
	private String description;
	private String typeOfIssue;
	private String status;
	private String postedDate;
	private String updatedDate;
	private String comment;
	private Long statusId;
	private Long districtId;
	private Long constituencyId;
	private Long madalId;
	private Long villageId;
	private String memberShipNo;
	private List<ComplaintScanCopyVO> scanCopyList = new ArrayList<ComplaintScanCopyVO>();
	
	
	public List<ComplaintScanCopyVO> getScanCopyList() {
		return scanCopyList;
	}
	public void setScanCopyList(List<ComplaintScanCopyVO> scanCopyList) {
		this.scanCopyList = scanCopyList;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
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
	public Long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeOfIssue() {
		return typeOfIssue;
	}
	public void setTypeOfIssue(String typeOfIssue) {
		this.typeOfIssue = typeOfIssue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getMadalId() {
		return madalId;
	}
	public void setMadalId(Long madalId) {
		this.madalId = madalId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
}
