package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileVO extends ResultStatus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<String> fileContentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>();
	private List<String> fileTitle;
	private List<String> fileDescription;
	private List<FileVO> fileVOList;
	private Long fileTypeId,gallaryId;
	private Long fileId;
	private Long problemFileId;
	private List<String> filePath;
	private String file,gallaryName,gallaryDescription,gallaryCreatedDate;
	private String title,gallaryUpdatedDate,name,path;
	private String description;
	private String pathOfFile;
	private String fileName1;
	private String fileTitle1;
	private String fileDescription1;
	private String filePath1;
	private String problem;
	private String scope;
	private Date existingFrom;
	private Date identifiedOn;
	private Long sizeOfGallary;
	private Long candidateId;
	private Long locationScope;
	private Long location;
	private String keywords;
	private String locationValue;
	private String locationScopeValue;
	private Long ids;
	private String names;
	private String contentType;
	private String visibility;
	private String source;
	private String fileDate;
	private String fileType;
	private Long sourceId;
	private Long languegeId;
	private String language;
	private String candidateName;
	private Long electionId;
	private Long stateId;
	private Long categoryId;
	private String categoryType;
	private String importance;
	private Long newsImportanceId;
	private Long contentId;
	private int startIndex;
	private int maxResult;
	private int count;
	private Long totalResultsCount;
	private Boolean isSelectedContent = false;
	private List<Long> uploadOtherProfileGalleryIds;
	private List<Long> uploadPartyGalleryId;
	private List<Long> uploadCandidateGalleryId;
	private List<Long> uploadSPGalleryId;
	private List<Long> fileSource;
	private List<Long> sourceLangIds;
	private List<String> fileTypesList ;	
	private Long fileSourceLanguageId;
	private Long orderNo;
	private String orderName;
	private int multipleSource = 0;
	private int multipleNews = 0;
	private String existingDateFrom;
	private String identifiedDateOn;
	private Date reqFileDate;
	private Long regionValue;
	private FileVO fileVOForDiaplyImage;
	

	private String displayImageName;
	private String displayImageContentType;
	private String displayImagePath;
	private String imagePathInUpperCase;
	
	private String fileDateAsString;
	private String comments;
	
	private String categoryName;
	private Long highImpactCount;
	private Long mediumImpactCount;
	private Long lowImpactCount;
	
	private Long userId;
	private Long locationVal;
	private Long locationId;
	private Long publicationId;
	private Long importanceId;
	
	private Long fileGallaryId;
	
	private ResultStatus resultStatus;
	private String flagSet;
	private String notesExist;
	
	public int totalFlaggedNews;
	private int totalNotesNews;
	private boolean tempvar = false;
	private String requestFrom;
	private Long requestPageId;
	private String isCustomer;
	private String locationName;
	private String isProblem;
	private Long problemId;
	private String isPrivate;
	private Date fileGallaryDate;
	private boolean isUpdatable = false;
	private String task;
	
	private List<Integer> newsedition;
	private List<Integer> pageno;
	private List<String> newslength;
	
	
	public Date getFileGallaryDate() {
		return fileGallaryDate;
	}

	public void setFileGallaryDate(Date fileGallaryDate) {
		this.fileGallaryDate = fileGallaryDate;
	}

	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	public String getIsProblem() {
		return isProblem;
	}

	public void setIsProblem(String isProblem) {
		this.isProblem = isProblem;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public Long getRequestPageId() {
		return requestPageId;
	}

	public void setRequestPageId(Long requestPageId) {
		this.requestPageId = requestPageId;
	}

	public String getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(String isCustomer) {
		this.isCustomer = isCustomer;
	}

	public boolean isTempvar() {
		return tempvar;
	}

	public void setTempvar(boolean tempvar) {
		this.tempvar = tempvar;
	}

	public int getTotalFlaggedNews() {
		return totalFlaggedNews;
	}

	public void setTotalFlaggedNews(int totalFlaggedNews) {
		this.totalFlaggedNews = totalFlaggedNews;
	}

	public int getTotalNotesNews() {
		return totalNotesNews;
	}

	public void setTotalNotesNews(int totalNotesNews) {
		this.totalNotesNews = totalNotesNews;
	}

	public String getNotesExist() {
		return notesExist;
	}

	public void setNotesExist(String notesExist) {
		this.notesExist = notesExist;
	}

	public String getFlagSet() {
		return flagSet;
	}

	public void setFlagSet(String flagSet) {
		this.flagSet = flagSet;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Long getFileGallaryId() {
		return fileGallaryId;
	}

	public void setFileGallaryId(Long fileGallaryId) {
		this.fileGallaryId = fileGallaryId;
	}

	public Long getImportanceId() {
		return importanceId;
	}

	public void setImportanceId(Long importanceId) {
		this.importanceId = importanceId;
	}

	public Long getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLocationVal() {
		return locationVal;
	}

	public void setLocationVal(Long locationVal) {
		this.locationVal = locationVal;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getHighImpactCount() {
		return highImpactCount;
	}

	public void setHighImpactCount(Long highImpactCount) {
		this.highImpactCount = highImpactCount;
	}

	public Long getMediumImpactCount() {
		return mediumImpactCount;
	}

	public void setMediumImpactCount(Long mediumImpactCount) {
		this.mediumImpactCount = mediumImpactCount;
	}

	public Long getLowImpactCount() {
		return lowImpactCount;
	}

	public void setLowImpactCount(Long lowImpactCount) {
		this.lowImpactCount = lowImpactCount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFileDateAsString() {
		return fileDateAsString;
	}

	public void setFileDateAsString(String fileDateAsString) {
		this.fileDateAsString = fileDateAsString;
	}

	public String getImagePathInUpperCase() {
		return imagePathInUpperCase;
	}

	public void setImagePathInUpperCase(String imagePathInUpperCase) {
		this.imagePathInUpperCase = imagePathInUpperCase;
	}

	public String getDisplayImagePath() {
		return displayImagePath;
	}

	public void setDisplayImagePath(String displayImagePath) {
		this.displayImagePath = displayImagePath;
	}

	public String getDisplayImageName() {
		return displayImageName;
	}

	public void setDisplayImageName(String displayImageName) {
		this.displayImageName = displayImageName;
	}

	public String getDisplayImageContentType() {
		return displayImageContentType;
	}

	public void setDisplayImageContentType(String displayImageContentType) {
		this.displayImageContentType = displayImageContentType;
	}

	public String getExistingDateFrom() {
		return existingDateFrom;
	}

	public void setExistingDateFrom(String existingDateFrom) {
		this.existingDateFrom = existingDateFrom;
	}

	public String getIdentifiedDateOn() {
		return identifiedDateOn;
	}

	public void setIdentifiedDateOn(String identifiedDateOn) {
		this.identifiedDateOn = identifiedDateOn;
	}
	
	public Boolean getIsSelectedContent() {
		return isSelectedContent;
	}

	public void setIsSelectedContent(Boolean isSelectedContent) {
		this.isSelectedContent = isSelectedContent;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getLanguegeId() {
		return languegeId;
	}

	public void setLanguegeId(Long languegeId) {
		this.languegeId = languegeId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public String getFileTitle1() {
		return fileTitle1;
	}

	public void setFileTitle1(String fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}

	public String getFileDescription1() {
		return fileDescription1;
	}

	public void setFileDescription1(String fileDescription1) {
		this.fileDescription1 = fileDescription1;
	}

	public String getFilePath1() {
		return filePath1;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getExistingFrom() {
		return existingFrom;
	}

	public void setExistingFrom(Date existingFrom) {
		this.existingFrom = existingFrom;
	}

	public Date getIdentifiedOn() {
		return identifiedOn;
	}

	public void setIdentifiedOn(Date identifiedOn) {
		this.identifiedOn = identifiedOn;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private String lastName;
	private String fullName;

	public void setFile(String file) {
		this.file = file;
	}

	public String getFile() {
		return file;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getPathOfFile() {
		return pathOfFile;
	}

	public void setPathOfFile(String pathOfFile) {
		this.pathOfFile = pathOfFile;
	}
	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(List<String> fileTitle) {
		this.fileTitle = fileTitle;
	}

	public List<String> getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(List<String> fileDescription) {
		this.fileDescription = fileDescription;
	}

	public Long getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(Long fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getProblemFileId() {
		return problemFileId;
	}

	public void setProblemFileId(Long problemFileId) {
		this.problemFileId = problemFileId;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}

	public Long getGallaryId() {
		return gallaryId;
	}

	public String getGallaryName() {
		return gallaryName;
	}

	public String getGallaryDescription() {
		return gallaryDescription;
	}

	public String getGallaryCreatedDate() {
		return gallaryCreatedDate;
	}

	public String getGallaryUpdatedDate() {
		return gallaryUpdatedDate;
	}

	public String getName() {
		return name;
	}

	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}

	public void setGallaryName(String gallaryName) {
		this.gallaryName = gallaryName;
	}

	public void setGallaryDescription(String gallaryDescription) {
		this.gallaryDescription = gallaryDescription;
	}

	public void setGallaryCreatedDate(String gallaryCreatedDate) {
		this.gallaryCreatedDate = gallaryCreatedDate;
	}

	public void setGallaryUpdatedDate(String gallaryUpdatedDate) {
		this.gallaryUpdatedDate = gallaryUpdatedDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getSizeOfGallary() {
		return sizeOfGallary;
	}

	public void setSizeOfGallary(Long sizeOfGallary) {
		this.sizeOfGallary = sizeOfGallary;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getLocationScope() {
		return locationScope;
	}

	public void setLocationScope(Long locationScope) {
		this.locationScope = locationScope;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}

	public String getLocationScopeValue() {
		return locationScopeValue;
	}

	public void setLocationScopeValue(String locationScopeValue) {
		this.locationScopeValue = locationScopeValue;
	}

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public Long getNewsImportanceId() {
		return newsImportanceId;
	}

	public void setNewsImportanceId(Long newsImportanceId) {
		this.newsImportanceId = newsImportanceId;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public List<FileVO> getFileVOList() {
		return fileVOList;
	}

	public void setFileVOList(List<FileVO> fileVOList) {
		this.fileVOList = fileVOList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(Long totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}

	public void setUploadPartyGalleryId(List<Long> uploadPartyGalleryId) {
		this.uploadPartyGalleryId = uploadPartyGalleryId;
	}

	public List<Long> getUploadPartyGalleryId() {
		return uploadPartyGalleryId;
	}

	public void setUploadCandidateGalleryId(List<Long> uploadCandidateGalleryId) {
		this.uploadCandidateGalleryId = uploadCandidateGalleryId;
	}

	public List<Long> getUploadCandidateGalleryId() {
		return uploadCandidateGalleryId;
	}

	public void setUploadOtherProfileGalleryIds(
			List<Long> uploadOtherProfileGalleryIds) {
		this.uploadOtherProfileGalleryIds = uploadOtherProfileGalleryIds;
	}

	public List<Long> getUploadOtherProfileGalleryIds() {
		return uploadOtherProfileGalleryIds;
	}

	public void setUploadSPGalleryId(List<Long> uploadSPGalleryId) {
		this.uploadSPGalleryId = uploadSPGalleryId;
	}

	public List<Long> getUploadSPGalleryId() {
		return uploadSPGalleryId;
	}

	public List<Long> getFileSource() {
		return fileSource;
	}

	public void setFileSource(List<Long> fileSource) {
		this.fileSource = fileSource;
	}

	public List<Long> getSourceLangIds() {
		return sourceLangIds;
	}

	public void setSourceLangIds(List<Long> sourceLangIds) {
		this.sourceLangIds = sourceLangIds;
	}

	public List<String> getFileTypesList() {
		return fileTypesList;
	}

	public void setFileTypesList(List<String> fileTypesList) {
		this.fileTypesList = fileTypesList;
	}
	
	public Long getFileSourceLanguageId() {
		return fileSourceLanguageId;
	}

	public void setFileSourceLanguageId(Long fileSourceLanguageId) {
		this.fileSourceLanguageId = fileSourceLanguageId;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public int getMultipleSource() {
		return multipleSource;
	}

	public void setMultipleSource(int multipleSource) {
		this.multipleSource = multipleSource;
	}

	public int getMultipleNews() {
		return multipleNews;
	}

	public void setMultipleNews(int multipleNews) {
		this.multipleNews = multipleNews;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getReqFileDate() {
		return reqFileDate;
	}

	public void setReqFileDate(Date reqFileDate) {
		this.reqFileDate = reqFileDate;
	}
	
	public FileVO getFileVOForDiaplyImage() {
		return fileVOForDiaplyImage;
	}

	public void setFileVOForDiaplyImage(FileVO fileVOForDiaplyImage) {
		this.fileVOForDiaplyImage = fileVOForDiaplyImage;
	}
	
	public Long getRegionValue() {
		return regionValue;
	}

	public void setRegionValue(Long regionValue) {
		this.regionValue = regionValue;
	}

	public boolean isUpdatable() {
		return isUpdatable;
	}

	public void setUpdatable(boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<Integer> getNewsedition() {
		return newsedition;
	}

	public void setNewsedition(List<Integer> newsedition) {
		this.newsedition = newsedition;
	}

	public List<Integer> getPageno() {
		return pageno;
	}

	public void setPageno(List<Integer> pageno) {
		this.pageno = pageno;
	}

	public List<String> getNewslength() {
		return newslength;
	}

	public void setNewslength(List<String> newslength) {
		this.newslength = newslength;
	}

	
	

}
