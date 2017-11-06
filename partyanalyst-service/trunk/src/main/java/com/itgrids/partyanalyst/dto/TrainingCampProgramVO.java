package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainingCampProgramVO {

	private Long id;
	private String name;
	private Long totalEligibleCount=0l;
	private Long totalAttenedCount=0l;
	private Long totalNotAttenedCount=0l;
	private Double totalAttenedCountPer=0.0;
	private Double totalNotAttenedCountPer=0.0;
	private Double totalEligibleCountPer=0.0;
	private TrainingCampProgramVO villageWardVO;
	private TrainingCampProgramVO mandalTownDivisionVO;
	private TrainingCampProgramVO districtVO;
	private TrainingCampProgramVO stateVO;
	private List<TrainingCampProgramVO> trainingProgramList = new ArrayList<TrainingCampProgramVO>();
	private List<TrainingCampProgramVO> districtList;
	private List<TrainingCampProgramVO> constituencyList;
	private List<TrainingCampProgramVO> mandalList;
	private List<TrainingCampProgramVO> villageList;
	private List<TrainingCampProgramVO> locationList;
	
	private List<TrainingCampProgramVO> leaderTrainingList = new ArrayList<TrainingCampProgramVO>();
	
	private String lastUpdatedTime;
	private Long InviteeAttended = 0l;
	private Long nonInviteeAttended=0l;
	private Long only1dayCount =0L;
	private Long only2daysCount =0L;
	private Long only3daysCount =0L;
	private Long othersCount = 0l;
	private Long totalBath = 0L;
	private Long only1dayCountInvited =0L;
	private Long only2daysCountInvited =0L;
	private Long only3daysCountInvited =0L;
	
	private Long only1dayCountNonInvited =0L;
	private Long only2daysCountNonInvited =0L;
	private Long only3daysCountNonInvited =0L;
	
	private Set<Long> inviteesIds = new HashSet<Long>(0);
	private Set<Long> nonInviteesIds = new HashSet<Long>(0);
	private Set<Long> othersIds = new HashSet<Long>(0);
	private Long totalBatch = 0L;
	private Long runningBatch = 0L;
	private Long completedBatch = 0L;
	private Long upcommintbatch = 0L;
	public Set<Long> getInviteesIds() {
		return inviteesIds;
	}
	public void setInviteesIds(Set<Long> inviteesIds) {
		this.inviteesIds = inviteesIds;
	}
	public Set<Long> getNonInviteesIds() {
		return nonInviteesIds;
	}
	public void setNonInviteesIds(Set<Long> nonInviteesIds) {
		this.nonInviteesIds = nonInviteesIds;
	}
	public Set<Long> getOthersIds() {
		return othersIds;
	}
	public void setOthersIds(Set<Long> othersIds) {
		this.othersIds = othersIds;
	}
	public Long getOthersCount() {
	    return othersCount;
	  }
	  public void setOthersCount(Long othersCount) {
	    this.othersCount = othersCount;
	  }
	   public Long getOnly1dayCount() {
	    return only1dayCount;
	  }
	  public void setOnly1dayCount(Long only1dayCount) {
	    this.only1dayCount = only1dayCount;
	  }
	  public Long getOnly2daysCount() {
	    return only2daysCount;
	  }
	  public void setOnly2daysCount(Long only2daysCount) {
	    this.only2daysCount = only2daysCount;
	  }
	  public Long getOnly3daysCount() {
	    return only3daysCount;
	  }
	  public void setOnly3daysCount(Long only3daysCount) {
	    this.only3daysCount = only3daysCount;
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
	public Long getTotalEligibleCount() {
		return totalEligibleCount;
	}
	public void setTotalEligibleCount(Long totalEligibleCount) {
		this.totalEligibleCount = totalEligibleCount;
	}
	public Long getTotalAttenedCount() {
		return totalAttenedCount;
	}
	public void setTotalAttenedCount(Long totalAttenedCount) {
		this.totalAttenedCount = totalAttenedCount;
	}
	public Long getTotalNotAttenedCount() {
		return totalNotAttenedCount;
	}
	public void setTotalNotAttenedCount(Long totalNotAttenedCount) {
		this.totalNotAttenedCount = totalNotAttenedCount;
	}
   public Double getTotalAttenedCountPer() {
		return totalAttenedCountPer;
	}
	public void setTotalAttenedCountPer(Double totalAttenedCountPer) {
		this.totalAttenedCountPer = totalAttenedCountPer;
	}
	public Double getTotalNotAttenedCountPer() {
		return totalNotAttenedCountPer;
	}
	public void setTotalNotAttenedCountPer(Double totalNotAttenedCountPer) {
		this.totalNotAttenedCountPer = totalNotAttenedCountPer;
	}
	public TrainingCampProgramVO getVillageWardVO() {
		return villageWardVO;
	}
	public void setVillageWardVO(TrainingCampProgramVO villageWardVO) {
		this.villageWardVO = villageWardVO;
	}
	public TrainingCampProgramVO getMandalTownDivisionVO() {
		return mandalTownDivisionVO;
	}
	public void setMandalTownDivisionVO(TrainingCampProgramVO mandalTownDivisionVO) {
		this.mandalTownDivisionVO = mandalTownDivisionVO;
	}
	public List<TrainingCampProgramVO> getTrainingProgramList() {
		return trainingProgramList;
	}
	public void setTrainingProgramList(
			List<TrainingCampProgramVO> trainingProgramList) {
		this.trainingProgramList = trainingProgramList;
	}
	public List<TrainingCampProgramVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<TrainingCampProgramVO> districtList) {
		this.districtList = districtList;
	}
	public TrainingCampProgramVO getDistrictVO() {
		return districtVO;
	}
	public void setDistrictVO(TrainingCampProgramVO districtVO) {
		this.districtVO = districtVO;
	}
	public TrainingCampProgramVO getStateVO() {
		return stateVO;
	}
	public void setStateVO(TrainingCampProgramVO stateVO) {
		this.stateVO = stateVO;
	}
	public List<TrainingCampProgramVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<TrainingCampProgramVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public Double getTotalEligibleCountPer() {
		return totalEligibleCountPer;
	}
	public void setTotalEligibleCountPer(Double totalEligibleCountPer) {
		this.totalEligibleCountPer = totalEligibleCountPer;
	}
	public List<TrainingCampProgramVO> getMandalList() {
		if(mandalList == null){
			mandalList = new ArrayList<TrainingCampProgramVO>();
		}
		return mandalList;
	}
	public List<TrainingCampProgramVO> getVillageList() {
		if(villageList == null){
			villageList = new ArrayList<TrainingCampProgramVO>();
		}
		return villageList;
	}
	public List<TrainingCampProgramVO> getLocationList() {
		if(locationList == null){
			locationList = new ArrayList<TrainingCampProgramVO>();
		}
		return locationList;
	}
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public Long getInviteeAttended() {
		return InviteeAttended;
	}
	public void setInviteeAttended(Long inviteeAttended) {
		InviteeAttended = inviteeAttended;
	}
	public Long getNonInviteeAttended() {
		return nonInviteeAttended;
	}
	public void setNonInviteeAttended(Long nonInviteeAttended) {
		this.nonInviteeAttended = nonInviteeAttended;
	}
	public Long getTotalBath() {
		return totalBath;
	}
	public void setTotalBath(Long totalBath) {
		this.totalBath = totalBath;
	}
	public Long getOnly1dayCountInvited() {
		return only1dayCountInvited;
	}
	public void setOnly1dayCountInvited(Long only1dayCountInvited) {
		this.only1dayCountInvited = only1dayCountInvited;
	}
	public Long getOnly2daysCountInvited() {
		return only2daysCountInvited;
	}
	public void setOnly2daysCountInvited(Long only2daysCountInvited) {
		this.only2daysCountInvited = only2daysCountInvited;
	}
	public Long getOnly3daysCountInvited() {
		return only3daysCountInvited;
	}
	public void setOnly3daysCountInvited(Long only3daysCountInvited) {
		this.only3daysCountInvited = only3daysCountInvited;
	}
	public Long getOnly1dayCountNonInvited() {
		return only1dayCountNonInvited;
	}
	public void setOnly1dayCountNonInvited(Long only1dayCountNonInvited) {
		this.only1dayCountNonInvited = only1dayCountNonInvited;
	}
	public Long getOnly2daysCountNonInvited() {
		return only2daysCountNonInvited;
	}
	public void setOnly2daysCountNonInvited(Long only2daysCountNonInvited) {
		this.only2daysCountNonInvited = only2daysCountNonInvited;
	}
	public Long getOnly3daysCountNonInvited() {
		return only3daysCountNonInvited;
	}
	public void setOnly3daysCountNonInvited(Long only3daysCountNonInvited) {
		this.only3daysCountNonInvited = only3daysCountNonInvited;
	}
	public List<TrainingCampProgramVO> getLeaderTrainingList() {
		return leaderTrainingList;
	}
	public void setLeaderTrainingList(List<TrainingCampProgramVO> leaderTrainingList) {
		this.leaderTrainingList = leaderTrainingList;
	}
	public Long getTotalBatch() {
		return totalBatch;
	}
	public void setTotalBatch(Long totalBatch) {
		this.totalBatch = totalBatch;
	}
	public Long getRunningBatch() {
		return runningBatch;
	}
	public void setRunningBatch(Long runningBatch) {
		this.runningBatch = runningBatch;
	}
	public Long getCompletedBatch() {
		return completedBatch;
	}
	public void setCompletedBatch(Long completedBatch) {
		this.completedBatch = completedBatch;
	}
	public Long getUpcommintbatch() {
		return upcommintbatch;
	}
	public void setUpcommintbatch(Long upcommintbatch) {
		this.upcommintbatch = upcommintbatch;
	}
	
	
  	
}
