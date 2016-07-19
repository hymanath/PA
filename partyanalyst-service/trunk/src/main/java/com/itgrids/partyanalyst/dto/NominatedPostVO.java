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
	
	private Long stateId;
	private Long districtId;
	private Long ConstituencyId;
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
	private Long locationVal;
	
	public NominatedPostVO(){}
	public NominatedPostVO(Long id, String name){this.id = id; this.name = name;}
	
	
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
		return ConstituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		ConstituencyId = constituencyId;
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
}
