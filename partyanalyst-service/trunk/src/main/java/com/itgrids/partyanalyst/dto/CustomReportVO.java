package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomReportVO implements Serializable {
	
	private Long id;
	private String name;
	private Long count = 0l;
	private Long submited=0l;
	private Long notSubmited=0l;
	private File file;
	private String selected = "false";
	private Long reportId;
	private String scope;
	private Long locationValue;
	private String path;
	private String membershipNo;
	private String voterNum;
	private String mobileNum;
	
	private List<CustomReportVO> observersList = new ArrayList<CustomReportVO>(0);
	private List<CustomReportVO> locationsList = new ArrayList<CustomReportVO>(0);
	private List<CustomReportVO> imagesList = new ArrayList<CustomReportVO>(0);
	private List<CustomReportVO> fileList = new ArrayList<CustomReportVO>(0);
		
	
	
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getVoterNum() {
		return voterNum;
	}
	public void setVoterNum(String voterNum) {
		this.voterNum = voterNum;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSubmited() {
		return submited;
	}
	public void setSubmited(Long submited) {
		this.submited = submited;
	}
	public Long getNotSubmited() {
		return notSubmited;
	}
	public void setNotSubmited(Long notSubmited) {
		this.notSubmited = notSubmited;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		
		this.count = count;
	}
	public List<CustomReportVO> getObserversList() {
		return observersList;
	}
	public void setObserversList(List<CustomReportVO> observersList) {
		this.observersList = observersList;
	}
	public List<CustomReportVO> getLocationsList() {
		return locationsList;
	}
	public void setLocationsList(List<CustomReportVO> locationsList) {
		this.locationsList = locationsList;
	}
	public List<CustomReportVO> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<CustomReportVO> imagesList) {
		this.imagesList = imagesList;
	}
	public List<CustomReportVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<CustomReportVO> fileList) {
		this.fileList = fileList;
	}	
	
}
