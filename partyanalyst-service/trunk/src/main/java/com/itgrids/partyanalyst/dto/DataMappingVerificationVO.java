package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DataMappingVerificationVO implements Serializable{
	
	private static final long serialVersionUID = -193906150458051035L;
	private String name;
	private Long id;
	private String status;
	private Long constituencyId;
	private Long publicationDateId;
	private String areaType;
	private String constituencyName;
	
	private Long totalCount;
	private Long mappedCount;
	private Long unMappedCount;
	
	private List<SelectOptionVO> totalList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> mappedList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> unMappedList = new ArrayList<SelectOptionVO>(0);
	
	private List<DataMappingVerificationVO> verificationInfoVOsList = new ArrayList<DataMappingVerificationVO>(0);
	
	List<Long> totalIdsList = new ArrayList<Long>(0);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getMappedCount() {
		return mappedCount;
	}
	public void setMappedCount(Long mappedCount) {
		this.mappedCount = mappedCount;
	}
	public Long getUnMappedCount() {
		return unMappedCount;
	}
	public void setUnMappedCount(Long unMappedCount) {
		this.unMappedCount = unMappedCount;
	}
	public List<SelectOptionVO> getTotalList() {
		return totalList;
	}
	public void setTotalList(List<SelectOptionVO> totalList) {
		this.totalList = totalList;
	}
	public List<SelectOptionVO> getMappedList() {
		return mappedList;
	}
	public void setMappedList(List<SelectOptionVO> mappedList) {
		this.mappedList = mappedList;
	}
	public List<SelectOptionVO> getUnMappedList() {
		return unMappedList;
	}
	public void setUnMappedList(List<SelectOptionVO> unMappedList) {
		this.unMappedList = unMappedList;
	}
	public List<Long> getTotalIdsList() {
		return totalIdsList;
	}
	public void setTotalIdsList(List<Long> totalIdsList) {
		this.totalIdsList = totalIdsList;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	public List<DataMappingVerificationVO> getVerificationInfoVOsList() {
		return verificationInfoVOsList;
	}
	public void setVerificationInfoVOsList(
			List<DataMappingVerificationVO> verificationInfoVOsList) {
		this.verificationInfoVOsList = verificationInfoVOsList;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
}
