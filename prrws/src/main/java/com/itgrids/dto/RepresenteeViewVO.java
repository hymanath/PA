package com.itgrids.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.web.multipart.MultipartFile;

public class RepresenteeViewVO {

	private Long id;
	private String name;
	private Long petitionStatusId;
	private String endorsementNO;
	private Long petitionId;
	private String endorsmentDate;
	private String referrerName;
	private String desigName;
	private String workName;
	private Long noOfWorks = 0l;
	private String estimationCost="0.0";
	private String statusType="";
	private List<String> desigList = new ArrayList<String>();
	private String raisedDate;
	private Long totalRepresents=0l;
	private List<RepresenteeViewVO> referrerList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> subList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> deptList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> statusList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> list = new ArrayList<RepresenteeViewVO>();
	private Long statusId;
	private Set<Long> petitionIds = new HashSet<Long>();
	private Set<Long> subWorkIds = new HashSet<Long>();
	private List<MultipartFile> filesList  = new ArrayList<MultipartFile>();
	private Long leadId;
	private Long grantId;
	private Long deptDesigId;
	private Long deptDesigOffcrId;
	private String remark;
	private Long order;
	private String amountInCrores="0.0";
	private List<Long> workIds = new ArrayList<Long>();
	private List<String> referList = new ArrayList<String>();
	private String coverLetterPath;
	private Long actionTypeId;
	private String actionType;
	private Long documentTypeId;
	private String refNo;
	private String dataType;
	private String subjectName;
	private String subSubjectname;
	private String deptName;
	private String leadName;
	private List<String> hasCoverLtr = new ArrayList<String>() ;
	private List<String> hasActionCopy = new ArrayList<String>();
	private List<String> hasDetailedReport= new ArrayList<String>();
	private List<String> hasFinalCopy= new ArrayList<String>();
	private List<String> hasOthersCopy= new ArrayList<String>();
	private List<String> hasWorkDocs= new ArrayList<String>();
	private List<String> hasRepRefDocs= new ArrayList<String>();
	private String workType;
	private String grantName;
	private Set<Long> documentIds = new TreeSet<Long>();
	private String representDesig;
	private String officerName;
	private String ofcrDesig;
	private String ofcrDept;
	private String lastUpdatedTime="";
	
	
	
	public List<String> getHasRepRefDocs() {
		return hasRepRefDocs;
	}
	public void setHasRepRefDocs(List<String> hasRepRefDocs) {
		this.hasRepRefDocs = hasRepRefDocs;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getOfcrDesig() {
		return ofcrDesig;
	}
	public void setOfcrDesig(String ofcrDesig) {
		this.ofcrDesig = ofcrDesig;
	}
	public String getOfcrDept() {
		return ofcrDept;
	}
	public void setOfcrDept(String ofcrDept) {
		this.ofcrDept = ofcrDept;
	}
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public String getRepresentDesig() {
		return representDesig;
	}
	public void setRepresentDesig(String representDesig) {
		this.representDesig = representDesig;
	}
	public Set<Long> getDocumentIds() {
		return documentIds;
	}
	public void setDocumentIds(Set<Long> documentIds) {
		this.documentIds = documentIds;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getGrantName() {
		return grantName;
	}
	public void setGrantName(String grantName) {
		this.grantName = grantName;
	}
	
	public List<String> getHasCoverLtr() {
		return hasCoverLtr;
	}
	public void setHasCoverLtr(List<String> hasCoverLtr) {
		this.hasCoverLtr = hasCoverLtr;
	}
	public List<String> getHasActionCopy() {
		return hasActionCopy;
	}
	public void setHasActionCopy(List<String> hasActionCopy) {
		this.hasActionCopy = hasActionCopy;
	}
	public List<String> getHasDetailedReport() {
		return hasDetailedReport;
	}
	public void setHasDetailedReport(List<String> hasDetailedReport) {
		this.hasDetailedReport = hasDetailedReport;
	}
	public List<String> getHasFinalCopy() {
		return hasFinalCopy;
	}
	public void setHasFinalCopy(List<String> hasFinalCopy) {
		this.hasFinalCopy = hasFinalCopy;
	}
	public List<String> getHasOthersCopy() {
		return hasOthersCopy;
	}
	public void setHasOthersCopy(List<String> hasOthersCopy) {
		this.hasOthersCopy = hasOthersCopy;
	}
	public List<String> getHasWorkDocs() {
		return hasWorkDocs;
	}
	public void setHasWorkDocs(List<String> hasWorkDocs) {
		this.hasWorkDocs = hasWorkDocs;
	}
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubSubjectname() {
		return subSubjectname;
	}
	public void setSubSubjectname(String subSubjectname) {
		this.subSubjectname = subSubjectname;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Long getActionTypeId() {
		return actionTypeId;
	}
	public void setActionTypeId(Long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
	public Long getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(Long documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getCoverLetterPath() {
		return coverLetterPath;
	}
	public void setCoverLetterPath(String coverLetterPath) {
		this.coverLetterPath = coverLetterPath;
	}
	public String getAmountInCrores() {
		return amountInCrores;
	}
	public void setAmountInCrores(String amountInCrores) {
		this.amountInCrores = amountInCrores;
	}
	public List<String> getReferList() {
		return referList;
	}
	public void setReferList(List<String> referList) {
		this.referList = referList;
	}
	public List<Long> getWorkIds() {
		return workIds;
	}
	public void setWorkIds(List<Long> workIds) {
		this.workIds = workIds;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public Long getGrantId() {
		return grantId;
	}
	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}
	public List<MultipartFile> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<MultipartFile> filesList) {
		this.filesList = filesList;
	}
	public Set<Long> getPetitionIds() {
		return petitionIds;
	}
	public void setPetitionIds(Set<Long> petitionIds) {
		this.petitionIds = petitionIds;
	}
	public Set<Long> getSubWorkIds() {
		return subWorkIds;
	}
	public void setSubWorkIds(Set<Long> subWorkIds) {
		this.subWorkIds = subWorkIds;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public List<RepresenteeViewVO> getList() {
		return list;
	}
	public void setList(List<RepresenteeViewVO> list) {
		this.list = list;
	}
	public List<RepresenteeViewVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<RepresenteeViewVO> statusList) {
		this.statusList = statusList;
	}
	public List<RepresenteeViewVO> getReferrerList() {
		return referrerList;
	}
	public void setReferrerList(List<RepresenteeViewVO> referrerList) {
		this.referrerList = referrerList;
	}
	public List<RepresenteeViewVO> getSubList() {
		return subList;
	}
	public void setSubList(List<RepresenteeViewVO> subList) {
		this.subList = subList;
	}
	public List<RepresenteeViewVO> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<RepresenteeViewVO> deptList) {
		this.deptList = deptList;
	}
	public Long getTotalRepresents() {
		return totalRepresents;
	}
	public void setTotalRepresents(Long totalRepresents) {
		this.totalRepresents = totalRepresents;
	}
	public String getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(String raisedDate) {
		this.raisedDate = raisedDate;
	}
	public List<String> getDesigList() {
		return desigList;
	}
	public void setDesigList(List<String> desigList) {
		this.desigList = desigList;
	}
	
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
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
	public String getEndorsementNO() {
		return endorsementNO;
	}
	public void setEndorsementNO(String endorsementNO) {
		this.endorsementNO = endorsementNO;
	}
	public String getEndorsmentDate() {
		return endorsmentDate;
	}
	public void setEndorsmentDate(String endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}
	public String getReferrerName() {
		return referrerName;
	}
	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Long getNoOfWorks() {
		return noOfWorks;
	}
	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}
	public String getEstimationCost() {
		return estimationCost;
	}
	public void setEstimationCost(String estimationCost) {
		this.estimationCost = estimationCost;
	}
	public Long getDeptDesigId() {
		return deptDesigId;
	}
	public void setDeptDesigId(Long deptDesigId) {
		this.deptDesigId = deptDesigId;
	}
	public Long getDeptDesigOffcrId() {
		return deptDesigOffcrId;
	}
	public void setDeptDesigOffcrId(Long deptDesigOffcrId) {
		this.deptDesigOffcrId = deptDesigOffcrId;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public Long getPetitionStatusId() {
		return petitionStatusId;
	}
	public void setPetitionStatusId(Long petitionStatusId) {
		this.petitionStatusId = petitionStatusId;
	}
}
