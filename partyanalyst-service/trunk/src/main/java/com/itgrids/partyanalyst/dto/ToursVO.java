package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToursVO {

	private Long candidateId;
	private Long categoryId;
	private Long locationScopeId;
	private Long locationValue;
	private Long tourTypeId;
	private String comment;	
	private String filePath;
	
	private File file;
	private String fileExtension;
	private Long designationId;	
	
	private String tourDate;
	private Long userId;
	
	private Long tourId;
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long tehsilId;
	private Long localElectionBodyId;
	private Long panchayatId;
	private Long wardId;
	
	private Long panchayatWardId;
	private Long localBodyId;
	
	private Long selfAppraisalCandidateId;

	private List<ToursVO> toursVoList = new ArrayList<ToursVO>(0);
	private Map<File,String> files = new HashMap<File,String>();
	private List<ToursVO> toursVoListNew = new ArrayList<ToursVO>(0);
	private List<IdNameVO> subList = new ArrayList<IdNameVO>();
	private List<ToursVO> docList = new ArrayList<ToursVO>();
	private List<IdNameVO> subList1;
	
	private String tourDateId;
	private Long tourCategoryId;
	private Long tourLocationId;
	private String description;
	
	private String tourMonth;
	private Long toursMonthId;
	private Long detailsNewId;
	private Long tourDays;
	private String designation; 
	private String category;
	private Long id;
	private String remark;
	private Long tourTypeCategoryId = 0l;
	private Long tourTypeChildId = 0l;
	private String tourTypeCategory;
	private String childTtourType;
	
		
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<IdNameVO> getSubList() {
		return subList;
	}

	public void setSubList(List<IdNameVO> subList) {
		this.subList = subList;
	}

	public String getTourMonth() {
		return tourMonth;
	}

	public void setTourMonth(String tourMonth) {
		this.tourMonth = tourMonth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTourLocationId() {
		return tourLocationId;
	}

	public void setTourLocationId(Long tourLocationId) {
		this.tourLocationId = tourLocationId;
	}

	public Long getTourCategoryId() {
		return tourCategoryId;
	}

	public void setTourCategoryId(Long tourCategoryId) {
		this.tourCategoryId = tourCategoryId;
	}

	public String getTourDateId() {
		return tourDateId;
	}

	public void setTourDateId(String tourDateId) {
		this.tourDateId = tourDateId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getLocationScopeId() {
		return locationScopeId;
	}

	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public Long getTourTypeId() {
		return tourTypeId;
	}

	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<ToursVO> getToursVoList() {
		return toursVoList;
	}

	public void setToursVoList(List<ToursVO> toursVoList) {
		this.toursVoList = toursVoList;
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
	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public Map<File, String> getFiles() {
		return files;
	}

	public void setFiles(Map<File, String> files) {
		this.files = files;
	}

	public String getTourDate() {
		return tourDate;
	}

	public void setTourDate(String tourDate) {
		this.tourDate = tourDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
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

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}

	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public Long getWardId() {
		return wardId;
	}

	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}

	public Long getPanchayatWardId() {
		return panchayatWardId;
	}

	public void setPanchayatWardId(Long panchayatWardId) {
		this.panchayatWardId = panchayatWardId;
	}

	public Long getLocalBodyId() {
		return localBodyId;
	}

	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}

	public Long getSelfAppraisalCandidateId() {
		return selfAppraisalCandidateId;
	}

	public void setSelfAppraisalCandidateId(Long selfAppraisalCandidateId) {
		this.selfAppraisalCandidateId = selfAppraisalCandidateId;
	}

	public List<ToursVO> getToursVoListNew() {
		return toursVoListNew;
	}

	public void setToursVoListNew(List<ToursVO> toursVoListNew) {
		this.toursVoListNew = toursVoListNew;
	}

	public Long getToursMonthId() {
		return toursMonthId;
	}

	public void setToursMonthId(Long toursMonthId) {
		this.toursMonthId = toursMonthId;
	}

	public Long getDetailsNewId() {
		return detailsNewId;
	}

	public void setDetailsNewId(Long detailsNewId) {
		this.detailsNewId = detailsNewId;
	}

	public Long getTourDays() {
		return tourDays;
	}

	public void setTourDays(Long tourDays) {
		this.tourDays = tourDays;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ToursVO> getDocList() {
		return docList;
	}

	public void setDocList(List<ToursVO> docList) {
		this.docList = docList;
	}

	public List<IdNameVO> getSubList1() {
		return subList1;
	}

	public void setSubList1(List<IdNameVO> subList1) {
		this.subList1 = subList1;
	}

	public Long getTourTypeCategoryId() {
		return tourTypeCategoryId;
	}

	public void setTourTypeCategoryId(Long tourTypeCategoryId) {
		this.tourTypeCategoryId = tourTypeCategoryId;
	}

	public Long getTourTypeChildId() {
		return tourTypeChildId;
	}

	public void setTourTypeChildId(Long tourTypeChildId) {
		this.tourTypeChildId = tourTypeChildId;
	}

	public String getTourTypeCategory() {
		return tourTypeCategory;
	}

	public void setTourTypeCategory(String tourTypeCategory) {
		this.tourTypeCategory = tourTypeCategory;
	}

	public String getChildTtourType() {
		return childTtourType;
	}

	public void setChildTtourType(String childTtourType) {
		this.childTtourType = childTtourType;
	}
    
	
	
	
}
