package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class PetitionsPDFVO {

	private Long id=0l;
	private String name;
	private Long petitionId=0l;
	private Long workId=0l;
	private String representationType="";
	private String endorsmentNo="";
	private String workDescription="";
	private String endorsDate="";
	private String representationDate="";
	private String estimationCost="";
	private String repName="";
	private String repDes="";
	private String refferrersName="";
	private String refferrersDes="";
	private String actionMemo="";
	private String goRefNo="";
	private String pendingAt="";
	
	private Long totalCount=0l;
	private Long noOfWorksWithCost=0l;
	private Long noOfWorksWithoutCost=0l;
	private Long sanctionedWorksCount=0l;
	private Long sanctionedCostCount=0l;
	private Long toBeSanctionedWorksCount=0l;
	private Long toBeSanctionedCostCount=0l;
	private Long noOfMemoIssuedCount=0l;
	private Long noOfGOIssuedCount=0l;
	private Long totalWorksCount=0l;
	private Long noOfWorksCount=0L;
	private Long statusId=0L;
	
	private AddressVO addressVO = new AddressVO();
	private List<PetitionsPDFVO>  subWorksList= new ArrayList<PetitionsPDFVO>(0);
	
	private List<KeyValueVO>  subList1= new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO>  subList2= new ArrayList<KeyValueVO>(0);
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public String getEndorsmentNo() {
		return endorsmentNo;
	}
	public void setEndorsmentNo(String endorsmentNo) {
		this.endorsmentNo = endorsmentNo;
	}
	public String getWorkDescription() {
		return workDescription;
	}
	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
	public String getEndorsDate() {
		return endorsDate;
	}
	public void setEndorsDate(String endorsDate) {
		this.endorsDate = endorsDate;
	}
	public String getRepresentationDate() {
		return representationDate;
	}
	public void setRepresentationDate(String representationDate) {
		this.representationDate = representationDate;
	}
	public String getEstimationCost() {
		return estimationCost;
	}
	public void setEstimationCost(String estimationCost) {
		this.estimationCost = estimationCost;
	}
	public String getRefferrersName() {
		return refferrersName;
	}
	public void setRefferrersName(String refferrersName) {
		this.refferrersName = refferrersName;
	}
	public String getActionMemo() {
		return actionMemo;
	}
	public void setActionMemo(String actionMemo) {
		this.actionMemo = actionMemo;
	}
	public String getGoRefNo() {
		return goRefNo;
	}
	public void setGoRefNo(String goRefNo) {
		this.goRefNo = goRefNo;
	}
	public String getPendingAt() {
		return pendingAt;
	}
	public void setPendingAt(String pendingAt) {
		this.pendingAt = pendingAt;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getNoOfWorksWithCost() {
		return noOfWorksWithCost;
	}
	public void setNoOfWorksWithCost(Long noOfWorksWithCost) {
		this.noOfWorksWithCost = noOfWorksWithCost;
	}
	public Long getNoOfWorksWithoutCost() {
		return noOfWorksWithoutCost;
	}
	public void setNoOfWorksWithoutCost(Long noOfWorksWithoutCost) {
		this.noOfWorksWithoutCost = noOfWorksWithoutCost;
	}
	public Long getSanctionedWorksCount() {
		return sanctionedWorksCount;
	}
	public void setSanctionedWorksCount(Long sanctionedWorksCount) {
		this.sanctionedWorksCount = sanctionedWorksCount;
	}
	public Long getSanctionedCostCount() {
		return sanctionedCostCount;
	}
	public void setSanctionedCostCount(Long sanctionedCostCount) {
		this.sanctionedCostCount = sanctionedCostCount;
	}
	public Long getToBeSanctionedWorksCount() {
		return toBeSanctionedWorksCount;
	}
	public void setToBeSanctionedWorksCount(Long toBeSanctionedWorksCount) {
		this.toBeSanctionedWorksCount = toBeSanctionedWorksCount;
	}
	public Long getToBeSanctionedCostCount() {
		return toBeSanctionedCostCount;
	}
	public void setToBeSanctionedCostCount(Long toBeSanctionedCostCount) {
		this.toBeSanctionedCostCount = toBeSanctionedCostCount;
	}
	public Long getNoOfMemoIssuedCount() {
		return noOfMemoIssuedCount;
	}
	public void setNoOfMemoIssuedCount(Long noOfMemoIssuedCount) {
		this.noOfMemoIssuedCount = noOfMemoIssuedCount;
	}
	public Long getNoOfGOIssuedCount() {
		return noOfGOIssuedCount;
	}
	public void setNoOfGOIssuedCount(Long noOfGOIssuedCount) {
		this.noOfGOIssuedCount = noOfGOIssuedCount;
	}
	public Long getTotalWorksCount() {
		return totalWorksCount;
	}
	public void setTotalWorksCount(Long totalWorksCount) {
		this.totalWorksCount = totalWorksCount;
	}
	public String getRepresentationType() {
		return representationType;
	}
	public void setRepresentationType(String representationType) {
		this.representationType = representationType;
	}
	public Long getNoOfWorksCount() {
		return noOfWorksCount;
	}
	public void setNoOfWorksCount(Long noOfWorksCount) {
		this.noOfWorksCount = noOfWorksCount;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getRepDes() {
		return repDes;
	}
	public void setRepDes(String repDes) {
		this.repDes = repDes;
	}
	public String getRefferrersDes() {
		return refferrersDes;
	}
	public void setRefferrersDes(String refferrersDes) {
		this.refferrersDes = refferrersDes;
	}
	public List<KeyValueVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<KeyValueVO> subList1) {
		this.subList1 = subList1;
	}
	public List<KeyValueVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<KeyValueVO> subList2) {
		this.subList2 = subList2;
	}
	public List<PetitionsPDFVO> getSubWorksList() {
		return subWorksList;
	}
	public void setSubWorksList(List<PetitionsPDFVO> subWorksList) {
		this.subWorksList = subWorksList;
	}
}
	
