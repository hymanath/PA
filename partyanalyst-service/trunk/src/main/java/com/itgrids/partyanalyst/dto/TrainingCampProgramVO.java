package com.itgrids.partyanalyst.dto;

import java.util.List;

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
	private List<TrainingCampProgramVO> trainingProgramList;
	private List<TrainingCampProgramVO> districtList;
	private List<TrainingCampProgramVO> constituencyList;
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
}
