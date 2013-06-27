/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 25, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class ConstituencyInfoVO extends ResultStatus implements Comparable<ConstituencyInfoVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2585252474166363454L;
	private Long constituencyId;
	private String constituencyName;
	private Long districtId;
	private String districtName;
	private String stateName;
	private Date startDate;
	private Date deformDate;
	private String constituencyType;
	private List<SelectOptionVO> assembyConstituencies;
	private Boolean hasAnalize = false;
	private Boolean viewCompletePage = false;
	private Boolean analyzeComments = false;
	private Boolean postComments = false;
	private String area_type;
	private String reservation_zone;
	private List<SelectOptionVO> constituencies;
	private Boolean votingTrendz = false;
		
	//Upload Info
	private Long boothsIdentified = 0l;
	private Long boothsInserted = 0l;
	private Long nullTehsilsCount = 0l;
	private List<String> uploadInfo;
	private Long totalCandidates = 0l;
	private Long boothsInDB = 0l; 
	
	private Long districtCount;
	
	public Boolean getVotingTrendz() {
		return votingTrendz;
	}
	public void setVotingTrendz(Boolean votingTrendz) {
		this.votingTrendz = votingTrendz;
	}
	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}
	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}
	public String getReservation_zone() {
		return reservation_zone;
	}
	public void setReservation_zone(String reservation_zone) {
		this.reservation_zone = reservation_zone;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	//getters and setters
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
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
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDeformDate() {
		return deformDate;
	}
	public void setDeformDate(Date deformDate) {
		this.deformDate = deformDate;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public List<SelectOptionVO> getAssembyConstituencies() {
		return assembyConstituencies;
	}
	public void setAssembyConstituencies(List<SelectOptionVO> assembyConstituencies) {
		this.assembyConstituencies = assembyConstituencies;
	}
	public Boolean getHasAnalize() {
		return hasAnalize;
	}
	public void setHasAnalize(Boolean hasAnalize) {
		this.hasAnalize = hasAnalize;
	}
	public List<String> getUploadInfo() {
		return uploadInfo;
	}
	public void setUploadInfo(List<String> uploadInfo) {
		this.uploadInfo = uploadInfo;
	}
	public String getArea_type() {
		return area_type;
	}
	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}
	public Boolean getViewCompletePage() {
		return viewCompletePage;
	}
	public void setViewCompletePage(Boolean viewCompletePage) {
		this.viewCompletePage = viewCompletePage;
	}
	
	public Long getBoothsIdentified() {
		return boothsIdentified;
	}
	public void setBoothsIdentified(Long boothsIdentified) {
		this.boothsIdentified = boothsIdentified;
	}
	public Long getBoothsInserted() {
		return boothsInserted;
	}
	public void setBoothsInserted(Long boothsInserted) {
		this.boothsInserted = boothsInserted;
	}
	
	public Long getNullTehsilsCount() {
		return nullTehsilsCount;
	}
	public void setNullTehsilsCount(Long nullTehsilsCount) {
		this.nullTehsilsCount = nullTehsilsCount;
	}
	public Long getTotalCandidates() {
		return totalCandidates;
	}
	public void setTotalCandidates(Long totalCandidates) {
		this.totalCandidates = totalCandidates;
	}
	public Long getBoothsInDB() {
		return boothsInDB;
	}
	public void setBoothsInDB(Long boothsInDB) {
		this.boothsInDB = boothsInDB;
	}
	public Boolean getAnalyzeComments() {
		return analyzeComments;
	}
	public void setAnalyzeComments(Boolean analyzeComments) {
		this.analyzeComments = analyzeComments;
	}
	public Boolean getPostComments() {
		return postComments;
	}
	public void setPostComments(Boolean postComments) {
		this.postComments = postComments;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
		
	
	public int compareTo(ConstituencyInfoVO o) {

		String districtName = o.getDistrictName();
		
		return this.districtName.compareTo(districtName);
	}
	 
	
	
}
