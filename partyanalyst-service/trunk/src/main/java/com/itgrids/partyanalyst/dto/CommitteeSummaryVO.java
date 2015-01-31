package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
public class CommitteeSummaryVO implements Serializable{

	private Long mainComittees = 0l;
	private Long mainComitteesConformed = 0l;
	private Long mainComitteesNotConformed = 0l;
	
	private String affilatedCommitteeName;
	private Long affComitteesConformed = 0l;
	private Long affComitteesNotConformed = 0l;
	private Long totalAffilatedCommittees = 0l;
	
	private Long startedCount = 0l;
	private Long affilatedStartedCount = 0l;
	private Long mainCommitteId;
	private Long affilatedCommitteId;
	
	private Long districtId;
	private String districtName;
	private Long totalCommittees;
	private Long membersCount;
	private List<CommitteeSummaryVO> districtWiseList;
	private Long mainStarted;
	private Long mainCompleted;
	private Long afflStarted;
	private Long afflCompleted;
	
	private CommitteeSummaryVO townMandalDivisionVO;
	private CommitteeSummaryVO villageWardVO;
	private String startPerc;
	private Long constiId;
	private String name;
	
	public String getStartPerc() {
		return startPerc;
	}
	public void setStartPerc(String startPerc) {
		this.startPerc = startPerc;
	}
	public Long getMainStarted() {
		return mainStarted;
	}
	public void setMainStarted(Long mainStarted) {
		this.mainStarted = mainStarted;
	}
	public Long getMainCompleted() {
		return mainCompleted;
	}
	public void setMainCompleted(Long mainCompleted) {
		this.mainCompleted = mainCompleted;
	}
	public Long getAfflStarted() {
		return afflStarted;
	}
	public void setAfflStarted(Long afflStarted) {
		this.afflStarted = afflStarted;
	}
	public Long getAfflCompleted() {
		return afflCompleted;
	}
	public void setAfflCompleted(Long afflCompleted) {
		this.afflCompleted = afflCompleted;
	}
	public CommitteeSummaryVO getTownMandalDivisionVO() {
		return townMandalDivisionVO;
	}
	public void setTownMandalDivisionVO(CommitteeSummaryVO townMandalDivisionVO) {
		this.townMandalDivisionVO = townMandalDivisionVO;
	}
	public CommitteeSummaryVO getVillageWardVO() {
		return villageWardVO;
	}
	public void setVillageWardVO(CommitteeSummaryVO villageWardVO) {
		this.villageWardVO = villageWardVO;
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
	public Long getTotalCommittees() {
		return totalCommittees;
	}
	public void setTotalCommittees(Long totalCommittees) {
		this.totalCommittees = totalCommittees;
	}
	public Long getMembersCount() {
		return membersCount;
	}
	public void setMembersCount(Long membersCount) {
		this.membersCount = membersCount;
	}
	public List<CommitteeSummaryVO> getDistrictWiseList() {
		return districtWiseList;
	}
	public void setDistrictWiseList(List<CommitteeSummaryVO> districtWiseList) {
		this.districtWiseList = districtWiseList;
	}
	public Long getMainCommitteId() {
		return mainCommitteId;
	}
	public void setMainCommitteId(Long mainCommitteId) {
		this.mainCommitteId = mainCommitteId;
	}
	public Long getAffilatedCommitteId() {
		return affilatedCommitteId;
	}
	public void setAffilatedCommitteId(Long affilatedCommitteId) {
		this.affilatedCommitteId = affilatedCommitteId;
	}
	public Long getMainComittees() {
		return mainComittees;
	}
	public void setMainComittees(Long mainComittees) {
		this.mainComittees = mainComittees;
	}
	public Long getMainComitteesConformed() {
		return mainComitteesConformed;
	}
	public void setMainComitteesConformed(Long mainComitteesConformed) {
		this.mainComitteesConformed = mainComitteesConformed;
	}
	public Long getMainComitteesNotConformed() {
		return mainComitteesNotConformed;
	}
	public void setMainComitteesNotConformed(Long mainComitteesNotConformed) {
		this.mainComitteesNotConformed = mainComitteesNotConformed;
	}
	public String getAffilatedCommitteeName() {
		return affilatedCommitteeName;
	}
	public void setAffilatedCommitteeName(String affilatedCommitteeName) {
		this.affilatedCommitteeName = affilatedCommitteeName;
	}
	public Long getAffComitteesConformed() {
		return affComitteesConformed;
	}
	public void setAffComitteesConformed(Long affComitteesConformed) {
		this.affComitteesConformed = affComitteesConformed;
	}
	public Long getAffComitteesNotConformed() {
		return affComitteesNotConformed;
	}
	public void setAffComitteesNotConformed(Long affComitteesNotConformed) {
		this.affComitteesNotConformed = affComitteesNotConformed;
	}
	public Long getTotalAffilatedCommittees() {
		return totalAffilatedCommittees;
	}
	public void setTotalAffilatedCommittees(Long totalAffilatedCommittees) {
		this.totalAffilatedCommittees = totalAffilatedCommittees;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Long getAffilatedStartedCount() {
		return affilatedStartedCount;
	}
	public void setAffilatedStartedCount(Long affilatedStartedCount) {
		this.affilatedStartedCount = affilatedStartedCount;
	}
	public Long getConstiId() {
		return constiId;
	}
	public void setConstiId(Long constiId) {
		this.constiId = constiId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
