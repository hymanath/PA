package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class JanmabhoomiCommitteeVO {	
	private Long id;
	private String name;
	private Long  totalCommitteeCnt=0l;
	private Long  notStartedCommitteeCnt=0l;
	private Long  inprogressCommitteeCnt=0l;
	private Long  readyForApprovelCommitteeCnt=0l;
	private Long  totalApprovedCommitteeCnt=0l;    
	private Long  submitedCommittees=0l;
	private List<JanmabhoomiCommitteeVO>  positinsList= new ArrayList<JanmabhoomiCommitteeVO>();
	private String statusType ="";
	private Long statusId;
	private Long committeeId = 0l;
	private List<JanmabhoomiCommitteeVO> list = new ArrayList<JanmabhoomiCommitteeVO>();
	private String notStartedCommitteePerc="0";
	private String inprogressCommitteePerc="0";
	private String readyForApprovelCommitteeperc="0";
	private String totalApprovedCommitteeperc="0";
	private String submitedCommitteesperc="0";
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
	private Long mandalId;
	private String mandalName;
	private Long panchayatId;
	private String panchayatName;
	private Long count =0l;
	private String color;
	
	private String status;
	private Long statusCount =0L;
	private String statusPercentage ="0";

	private List<JanmabhoomiCommitteeVO> committeeStatusVOList = new ArrayList<JanmabhoomiCommitteeVO>();
	private List<JanmabhoomiCommitteeVO> levelWisecommitteeStatusVOList = new ArrayList<JanmabhoomiCommitteeVO>();
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public List<JanmabhoomiCommitteeVO> getList() {
		return list;
	}
	public void setList(List<JanmabhoomiCommitteeVO> list) {
		this.list = list;
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
	public Long getTotalCommitteeCnt() {
		return totalCommitteeCnt;
	}
	public void setTotalCommitteeCnt(Long totalCommitteeCnt) {
		this.totalCommitteeCnt = totalCommitteeCnt;
	}
	public Long getNotStartedCommitteeCnt() {
		return notStartedCommitteeCnt;
	}
	public void setNotStartedCommitteeCnt(Long notStartedCommitteeCnt) {
		this.notStartedCommitteeCnt = notStartedCommitteeCnt;
	}
	public Long getInprogressCommitteeCnt() {
		return inprogressCommitteeCnt;
	}
	public void setInprogressCommitteeCnt(Long inprogressCommitteeCnt) {
		this.inprogressCommitteeCnt = inprogressCommitteeCnt;
	}
	public Long getReadyForApprovelCommitteeCnt() {
		return readyForApprovelCommitteeCnt;
	}
	public void setReadyForApprovelCommitteeCnt(Long readyForApprovelCommitteeCnt) {
		this.readyForApprovelCommitteeCnt = readyForApprovelCommitteeCnt;
	}
	public Long getTotalApprovedCommitteeCnt() {
		return totalApprovedCommitteeCnt;
	}
	public void setTotalApprovedCommitteeCnt(Long totalApprovedCommitteeCnt) {
		this.totalApprovedCommitteeCnt = totalApprovedCommitteeCnt;
	}
	public Long getSubmitedCommittees() {
		return submitedCommittees;
	}
	public void setSubmitedCommittees(Long submitedCommittees) {
		this.submitedCommittees = submitedCommittees;
	}
	public JanmabhoomiCommitteeVO(){
		
	}
	public JanmabhoomiCommitteeVO(Long id,String name)
    {
    	this.id = id;
    	this.name = name;
    	
    	
    }
	@Override
	public boolean equals(Object obj){
		if(id==null)
			id = -1L;
		if(obj instanceof JanmabhoomiCommitteeVO){
			JanmabhoomiCommitteeVO vo = (JanmabhoomiCommitteeVO) obj;
			return this.id.equals(vo.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(id==null)
			id = -1L;
		return this.id.intValue();
	}
	public List<JanmabhoomiCommitteeVO> getPositinsList() {
		return positinsList;
	}
	public void setPositinsList(List<JanmabhoomiCommitteeVO> positinsList) {
		this.positinsList = positinsList;
	}
	public String getNotStartedCommitteePerc() {
		return notStartedCommitteePerc;
	}
	public void setNotStartedCommitteePerc(String notStartedCommitteePerc) {
		this.notStartedCommitteePerc = notStartedCommitteePerc;
	}
	public String getInprogressCommitteePerc() {
		return inprogressCommitteePerc;
	}
	public void setInprogressCommitteePerc(String inprogressCommitteePerc) {
		this.inprogressCommitteePerc = inprogressCommitteePerc;
	}
	public String getReadyForApprovelCommitteeperc() {
		return readyForApprovelCommitteeperc;
	}
	public void setReadyForApprovelCommitteeperc(
			String readyForApprovelCommitteeperc) {
		this.readyForApprovelCommitteeperc = readyForApprovelCommitteeperc;
	}
	public String getTotalApprovedCommitteeperc() {
		return totalApprovedCommitteeperc;
	}
	public void setTotalApprovedCommitteeperc(String totalApprovedCommitteeperc) {
		this.totalApprovedCommitteeperc = totalApprovedCommitteeperc;
	}
	public String getSubmitedCommitteesperc() {
		return submitedCommitteesperc;
	}
	public void setSubmitedCommitteesperc(String submitedCommitteesperc) {
		this.submitedCommitteesperc = submitedCommitteesperc;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<JanmabhoomiCommitteeVO> getCommitteeStatusVOList() {
		return committeeStatusVOList;
	}
	public void setCommitteeStatusVOList(
			List<JanmabhoomiCommitteeVO> committeeStatusVOList) {
		this.committeeStatusVOList = committeeStatusVOList;
	}
	public List<JanmabhoomiCommitteeVO> getLevelWisecommitteeStatusVOList() {
		return levelWisecommitteeStatusVOList;
	}
	public void setLevelWisecommitteeStatusVOList(
			List<JanmabhoomiCommitteeVO> levelWisecommitteeStatusVOList) {
		this.levelWisecommitteeStatusVOList = levelWisecommitteeStatusVOList;
	}
	public Long getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(Long statusCount) {
		this.statusCount = statusCount;
	}
	public String getStatusPercentage() {
		return statusPercentage;
	}
	public void setStatusPercentage(String statusPercentage) {
		this.statusPercentage = statusPercentage;
	}
	

}
