package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Teja
 *
 */
public class NominatedPostVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;

	private Long totalDept=0L;
	private Long totalCorp=0L;
	private Long totalPositions=0L;
	private String perc="0.0";
	private String perc1="0.0";
	
	private Long count =0L;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long panchayatId;
	private String hno;
	private String address1;
	private String address2;
	private String pincode;
	private String 	mobileNo;
	private List<NominatedPostVO> subList =null;
	private Long boardLevelId;
	private Long deptId;
	private Long deptBoardId;
	private Long deptBoardPostnId;
	private Long mandalId;
	private String voterCardNo;
	private List<NominatedPostVO> nominatdList = new ArrayList<NominatedPostVO>();
		
	private List<IdNameVO> distList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> consList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> panList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> mandalsList = new ArrayList<IdNameVO>(0);
	private List<String> candiImageUrls;
	private String fileExtension;
	private File file;
	private Map<File,String> files = new HashMap<File,String>();
	
	private Long receivedCount=0l;
	private Long shortListedCount=0l;
	
	private List<IdNameVO> idNameVoList =  new ArrayList<IdNameVO>(0);
	private Long firstAgeGroupCount=0l;
	private Long secondAgeGroupCount=0l;
	private Long thirdAgeGroupCount=0l;
	private Long fourthAgeGroupCount=0l;
	private Long fifthAgeGroupCount=0l;
	private Long maleCount = 0l;
	private Long femaleCount = 0l;
	private String contains = "false";
	private Long locationVal;
	private String positions;
	private String refferCadreIds;
	private Long nominatedCandId;
	private String candidateType;
	
	public String phoneNumName;
	public String houseNumberName ;
	public String addressLane1Name;
	public String addressLane2Name;
 
	public Long addStateName;
	public Long addDistrictName;
	public Long addConstituencyName;
	public Long addMandalsName;
	public Long addVillageName;
	public String addPincodeName;
	private Long postTypeId;
	private String isCheckedMigrateAddressField;
	private Long totalApplicationReceivedCnt=0l;
	private Long positionLinkedCnt=0l;
	private Long readyToShortListedCnt=0l;
	private Long pstnLnkedAndRjctdCnt = 0l;
	private Long pstnLnkedAndShrtLstdCnt =0l;
	private Long pstnLnkedAndFinalized =0l;
	private Long pstnLnkedAndFinalReview =0l;
	private Long finalizeAndGOCount =0L;
	private Long readyToFinalReview  =0L;
	private Long totalPosts =0L;
	private Long openPosts =0L;
	
	
	public Long getTotalPosts() {
		return totalPosts;
	}
	public void setTotalPosts(Long totalPosts) {
		this.totalPosts = totalPosts;
	}
	public Long getOpenPosts() {
		return openPosts;
	}
	public void setOpenPosts(Long openPosts) {
		this.openPosts = openPosts;
	}
	public Long getFinalizeAndGOCount() {
		return finalizeAndGOCount;
	}
	public void setFinalizeAndGOCount(Long finalizeAndGOCount) {
		this.finalizeAndGOCount = finalizeAndGOCount;
	}
	public NominatedPostVO(){}
	public NominatedPostVO(Long id, String name){this.id = id; this.name = name;}
	
	
	public String getIsCheckedMigrateAddressField() {
		return isCheckedMigrateAddressField;
	}
	public void setIsCheckedMigrateAddressField(String isCheckedMigrateAddressField) {
		this.isCheckedMigrateAddressField = isCheckedMigrateAddressField;
	}
	public Long getPostTypeId() {
		return postTypeId;
	}
	public void setPostTypeId(Long postTypeId) {
		this.postTypeId = postTypeId;
	}
	
	public Long getNominatedCandId() {
		return nominatedCandId;
	}
	public void setNominatedCandId(Long nominatedCandId) {
		this.nominatedCandId = nominatedCandId;
	}
	public String getRefferCadreIds() {
		return refferCadreIds;
	}
	public void setRefferCadreIds(String refferCadreIds) {
		this.refferCadreIds = refferCadreIds;
	}
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	public Long getReceivedCount() {
		return receivedCount;
	}
	public void setReceivedCount(Long receivedCount) {
		this.receivedCount = receivedCount;
	}
	public Long getShortListedCount() {
		return shortListedCount;
	}
	public void setShortListedCount(Long shortListedCount) {
		this.shortListedCount = shortListedCount;
	}
	public List<IdNameVO> getIdNameVoList() {
		return idNameVoList;
	}
	public void setIdNameVoList(List<IdNameVO> idNameVoList) {
		this.idNameVoList = idNameVoList;
	}
	public Long getFirstAgeGroupCount() {
		return firstAgeGroupCount;
	}
	public void setFirstAgeGroupCount(Long firstAgeGroupCount) {
		this.firstAgeGroupCount = firstAgeGroupCount;
	}
	public Long getSecondAgeGroupCount() {
		return secondAgeGroupCount;
	}
	public void setSecondAgeGroupCount(Long secondAgeGroupCount) {
		this.secondAgeGroupCount = secondAgeGroupCount;
	}
	public Long getThirdAgeGroupCount() {
		return thirdAgeGroupCount;
	}
	public void setThirdAgeGroupCount(Long thirdAgeGroupCount) {
		this.thirdAgeGroupCount = thirdAgeGroupCount;
	}
	public Long getFourthAgeGroupCount() {
		return fourthAgeGroupCount;
	}
	public void setFourthAgeGroupCount(Long fourthAgeGroupCount) {
		this.fourthAgeGroupCount = fourthAgeGroupCount;
	}
	public Long getFifthAgeGroupCount() {
		return fifthAgeGroupCount;
	}
	public void setFifthAgeGroupCount(Long fifthAgeGroupCount) {
		this.fifthAgeGroupCount = fifthAgeGroupCount;
	}
	public Map<File, String> getFiles() {
		return files;
	}
	public void setFiles(Map<File, String> files) {
		this.files = files;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public List<String> getCandiImageUrls() {
		return candiImageUrls;
	}
	public void setCandiImageUrls(List<String> candiImageUrls) {
		this.candiImageUrls = candiImageUrls;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}

	public List<NominatedPostVO> getNominatdList() {
		return nominatdList;
	}
	public void setNominatdList(List<NominatedPostVO> nominatdList) {
		this.nominatdList = nominatdList;
	}
	public Long getBoardLevelId() {
		return boardLevelId;
	}
	public void setBoardLevelId(Long boardLevelId) {
		this.boardLevelId = boardLevelId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getDeptBoardId() {
		return deptBoardId;
	}
	public void setDeptBoardId(Long deptBoardId) {
		this.deptBoardId = deptBoardId;
	}
	public Long getDeptBoardPostnId() {
		return deptBoardPostnId;
	}
	public void setDeptBoardPostnId(Long deptBoardPostnId) {
		this.deptBoardPostnId = deptBoardPostnId;
	}
	
	
	public Long getTotalDept() {
		return totalDept;
	}
	public void setTotalDept(Long totalDept) {
		this.totalDept = totalDept;
	}
	public Long getTotalCorp() {
		return totalCorp;
	}
	public void setTotalCorp(Long totalCorp) {
		this.totalCorp = totalCorp;
	}
	public Long getTotalPositions() {
		return totalPositions;
	}
	public void setTotalPositions(Long totalPositions) {
		this.totalPositions = totalPositions;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public List<NominatedPostVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NominatedPostVO> subList) {
		this.subList = subList;
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
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
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
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<IdNameVO> getDistList() {
		return distList;
	}
	public void setDistList(List<IdNameVO> distList) {
		this.distList = distList;
	}
	public List<IdNameVO> getConsList() {
		return consList;
	}
	public void setConsList(List<IdNameVO> consList) {
		this.consList = consList;
	}
	public List<IdNameVO> getPanList() {
		return panList;
	}
	public void setPanList(List<IdNameVO> panList) {
		this.panList = panList;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public List<IdNameVO> getMandalsList() {
		return mandalsList;
	}
	public void setMandalsList(List<IdNameVO> mandalsList) {
		this.mandalsList = mandalsList;
	}
	public Long getLocationVal() {
		return locationVal;
	}
	public void setLocationVal(Long locationVal) {
		this.locationVal = locationVal;
	}
	public String getPerc1() {
		return perc1;
	}
	public void setPerc1(String perc1) {
		this.perc1 = perc1;
	}
	public String getCandidateType() {
		return candidateType;
	}
	public void setCandidateType(String candidateType) {
		this.candidateType = candidateType;
	}
	public String getPhoneNumName() {
		return phoneNumName;
	}
	public void setPhoneNumName(String phoneNumName) {
		this.phoneNumName = phoneNumName;
	}
	public String getHouseNumberName() {
		return houseNumberName;
	}
	public void setHouseNumberName(String houseNumberName) {
		this.houseNumberName = houseNumberName;
	}
	public String getAddressLane1Name() {
		return addressLane1Name;
	}
	public void setAddressLane1Name(String addressLane1Name) {
		this.addressLane1Name = addressLane1Name;
	}
	public String getAddressLane2Name() {
		return addressLane2Name;
	}
	public void setAddressLane2Name(String addressLane2Name) {
		this.addressLane2Name = addressLane2Name;
	}
	public Long getAddStateName() {
		return addStateName;
	}
	public void setAddStateName(Long addStateName) {
		this.addStateName = addStateName;
	}
	public Long getAddDistrictName() {
		return addDistrictName;
	}
	public void setAddDistrictName(Long addDistrictName) {
		this.addDistrictName = addDistrictName;
	}
	public Long getAddConstituencyName() {
		return addConstituencyName;
	}
	public void setAddConstituencyName(Long addConstituencyName) {
		this.addConstituencyName = addConstituencyName;
	}
	public Long getAddMandalsName() {
		return addMandalsName;
	}
	public void setAddMandalsName(Long addMandalsName) {
		this.addMandalsName = addMandalsName;
	}
	public Long getAddVillageName() {
		return addVillageName;
	}
	public void setAddVillageName(Long addVillageName) {
		this.addVillageName = addVillageName;
	}
	public String getAddPincodeName() {
		return addPincodeName;
	}
	public void setAddPincodeName(String addPincodeName) {
		this.addPincodeName = addPincodeName;
	}
	public Long getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}
	public Long getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(Long femaleCount) {
		this.femaleCount = femaleCount;
	}
	public String getContains() {
		return contains;
	}
	public void setContains(String contains) {
		this.contains = contains;
	}
	public Long getTotalApplicationReceivedCnt() {
		return totalApplicationReceivedCnt;
	}
	public void setTotalApplicationReceivedCnt(Long totalApplicationReceivedCnt) {
		this.totalApplicationReceivedCnt = totalApplicationReceivedCnt;
	}
	public Long getPositionLinkedCnt() {
		return positionLinkedCnt;
	}
	public void setPositionLinkedCnt(Long positionLinkedCnt) {
		this.positionLinkedCnt = positionLinkedCnt;
	}
	public Long getReadyToShortListedCnt() {
		return readyToShortListedCnt;
	}
	public void setReadyToShortListedCnt(Long readyToShortListedCnt) {
		this.readyToShortListedCnt = readyToShortListedCnt;
	}
	public Long getPstnLnkedAndRjctdCnt() {
		return pstnLnkedAndRjctdCnt;
	}
	public void setPstnLnkedAndRjctdCnt(Long pstnLnkedAndRjctdCnt) {
		this.pstnLnkedAndRjctdCnt = pstnLnkedAndRjctdCnt;
	}
	public Long getPstnLnkedAndShrtLstdCnt() {
		return pstnLnkedAndShrtLstdCnt;
	}
	public void setPstnLnkedAndShrtLstdCnt(Long pstnLnkedAndShrtLstdCnt) {
		this.pstnLnkedAndShrtLstdCnt = pstnLnkedAndShrtLstdCnt;
	}
	public Long getPstnLnkedAndFinalized() {
		return pstnLnkedAndFinalized;
	}
	public void setPstnLnkedAndFinalized(Long pstnLnkedAndFinalized) {
		this.pstnLnkedAndFinalized = pstnLnkedAndFinalized;
	}
	public Long getPstnLnkedAndFinalReview() {
		return pstnLnkedAndFinalReview;
	}
	public void setPstnLnkedAndFinalReview(Long pstnLnkedAndFinalReview) {
		this.pstnLnkedAndFinalReview = pstnLnkedAndFinalReview;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getReadyToFinalReview() {
		return readyToFinalReview;
	}
	public void setReadyToFinalReview(Long readyToFinalReview) {
		this.readyToFinalReview = readyToFinalReview;
	}
	
	
}
