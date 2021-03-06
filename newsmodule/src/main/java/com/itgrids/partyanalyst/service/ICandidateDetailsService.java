/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dto.CategoryVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.File;

public interface ICandidateDetailsService {
	
	
	public List<FileVO> getFilesOfAGallary(Long gallaryId , int startIndex , int endIndex,String newsType,Long categoryId,String fromDateStr,String toDateStr,String requestFor);
	
	public List<SelectOptionVO> getLatestgallaries();
	
	public List<SelectOptionVO> getNewsContainedCandidates();
 
	public List<SelectOptionVO> getCandidateRelatedCategories(Long candidateId,String fromDateStr,String toDateStr,Long partyId);
 
	public List<SelectOptionVO> getGallariesForSelectedCategories(List<Long> categoryIdsList,Long candidateId);
 
	
	
	/*
	
	public CandidateVO getCandidateDetails(Long candidateId);

	public List<CandidateDetailsVO> getCandidateElectionDetails(Long candidateId);
	
	public List<CandidateOppositionVO> getCandidatesOppositionList(Long candidateId,Long electionId,Long constituencyId);
	
	public List<FileVO> getCandidatesPhotoGallaryDetail(Long candidateId,int firstRecord,int maxRecord,String type);
	
	public List<FileVO> getCandidatesPhotosInAGallary(Long gallaryId);
	
	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,String createOrUpdate);
	
	public List<FileVO> searchNewsDetails(FileVO inputs);
	
	
	
	
	*/
	public List<FileVO> getDistrictDetailsByStateId(Long stateId,String accessType,Long accessValue);
	
	public ResultStatus uploadAFile(FileVO fileVO);
	public List<FileVO> getScopesForNewSearch();
	public List<SelectOptionVO> getSource();
	public List<SelectOptionVO> getLanguage();
	public List<SelectOptionVO> getCategory();
	public List<SelectOptionVO> getNewsImportance();
	public List<FileVO> getStateDetails(String accessType,Long accessValue,String userType);
	public List<SelectOptionVO> getCandidateGallaries(Long registrationId,String contentType);
	public List<SelectOptionVO> getCandidatesOfAUser(Long userId);
	/*public List<SelectOptionVO> getCandidateGallarySelectList(Long candidateId,String contentType);
	
	public List<String> getCandidateProfileDescriptionByCandidateID(Long candidateId);
	
	public List<FileVO> getFirstFourNewsRecordsToDisplay(Long candidateId);
	
	public List<FileVO> getNewsToDisplay(Long candidateId,int firstResult,int maxResult,String queryType);
	
	public List<FileVO> getFileByFileId(Long fileId);
	
	public List<FileVO> getFirstThreeImagesToDisplay(Long candidateId,int firstResult,int maxResult);
	
	public List<FileVO> getFirstThreePhotoGallaryDetail(Long candidateId);
	
	public List<FileVO> getCandidateLatestVideos(Long candidateId,Integer startIndex, Integer maxRecords);
	
	public ResultStatus saveDescription(GallaryVO gallaryVO);
	
	public List<GallaryVO> getCandidateProfileInfo(Long candidateId);
	
	public List<FileVO> getCandidatesPhotoGallaryDetailWithOutGallerySizeZero(Long candidateId,int firstRecord,int maxRecord,String type);
	
	public ResultStatus updateProfileDescription(List<GallaryVO> gallaryVO , Long candidateId);
	
	public List<SelectOptionVO> getCandidateDetailsBySearchCriteria(String gender,String name,Long constituencyId,Long userId,Long stateId);
	
	public ResultStatus saveUserCandidateRelation(Long userId,Long candidateId);
	
	public ResultStatus deleteUserCandidateRelation(String userCandidateRelationIds);
	
	public List<FileVO> getAllCandidateDetailsAssignedToAUser(Long userId);
	
	public List<FileVO> getAllVideosInAGalleryForACandidate(Long gallaryId);
	
	public ResultStatus subScribeEmailAlertForAUser(String emailId ,Long candidateId);
	
	public ResultStatus saveMessage(GallaryVO gallaryVO);
	
	public ResultStatus deleteProfileDescById(Long profDescId);
	
	public List<FileVO> getNewsCountByScope(Long candidateId,String queryType);
	
	public List<FileVO> getNewsByScope(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType);
	
	public Long getUserCandidateRelationCount(Long userId,Long candidateId);
	
	public List<FileVO> getOtherNews(Long candidateId,int startIndex,int maxResults,String queryType);
	

	
	
	
	

	public FileVO copyFileToFileVO(File file);
	
	
	
	 public ResultStatus deleteFilesAndPhotos(Long fileId , Long gallaryId);
	 
	 public ResultStatus deleteGallary(Long gallaryId);
	 
	 public List<FileVO> getNewsByLanguage(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType,String language);
	 
	 public List<FileVO> getNewsBySource(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String source);

	 public	FileVO getCandidatesGallaryDescForUpdate(Long gallaryId , Long candidateId);
	 
	 public	FileVO getPhotoUploadDescForUpdate(Long gallaryId , Long fileId);
	 
	 public ResultStatus updateIndividualPhoto(FileVO fileVO);

	
	 
	 public SelectOptionVO assignAndReturnCandidate(Long userId,Long candidateId);
	
	 public boolean checkForCandidateExistence(List<SelectOptionVO> candidatesList,Long candidateId);
	 public List<FileVO> getNewsByCategory(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType,String category);
	
	 public List<FileVO> getNewsByNewsImportance(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String newsImportance);
	 
	 public List<FileVO> getNewsGalleryByUserIdFromUserGallery(Long userId);*/
	 	 
	 public String getLocationDetails(Long scope,Long locationValue);
	 
	 /*public List<CandidateCommentsVO> getMessages(String fromDate, String toDate,String selectstatus,String decidestatus);
	 
	 public ResultStatus controlMessages(List<CandidateCommentsVO> VO,String actionType);
	 
	 public List<CandidateCommentsVO> getUserMessages(Long candidateId,int startIndex,int resultsCount);
	 	 
	 public List<SelectOptionVO> getCandidatesBasedOnSelection(String candidateName,Long partyId,Long electionId);
	 
	 public List<FileVO> getSpecialPagePhotoGallaryDetail(Long specialPageId,int firstRecord,int maxRecord,String type);
	 
	 public	FileVO getSpecialPageGallaryDescForUpdate(Long gallaryId , Long specialPageId);
	 	 
	 public FileVO getContentDetails(Long contentId);

	 public String getCandidateGallaryVisibility(Long gallaryId);
	 	 
	 public ResultStatus adminModifiedMessages(List<CandidateCommentsVO> VO,String actionType);
	 
	 public ElectionGoverningBodyVO getElectionDetailsForMinister(Long electionId);
	    
	 public List<CandidateMinistriesVO> getAllMinistersDetailsForAnElection(Long electionId);
	 
	 public List<SelectOptionVO> getMinistryYearsForAState(String electionType, Long stateId);
	 
	 public List<CustomPageVO> getCustomPagesOfACandidatePage(Long candidateId);
	 
	 public MetaInfoVO getMetaInfoOfMinistersPage(ElectionGoverningBodyVO electionGoverningBodyVO,List<CandidateMinistriesVO> ministersList);
	 
	 public List<FileVO> customPagesType();
	 
	 public ResultStatus createCustomPages(GallaryVO gallaryVO);
	 
	 public List<CustomPageVO> getCustomPages(Long pageId,String pageName);
	 
	 public ResultStatus updateCustomPages(GallaryVO gallaryVO);
	 
	 public String getMandalName(Long mandalId);
	 
	 public List<FileVO> getAllNewsdetails(Long candidateId,int firstResult,int maxResult,String queryType);
	 
	 public RegistrationVO getStateAndConstituency(Long userId);	
	 
	 public Boolean getSubscriptionDetails(Long userId,Long candidateId,String page);
	 
	 public ResultStatus subscriberDetails(Long candidateId,Long userId,String category);
	 
	 public ResultStatus unSubscriptionDetails(Long candidateId,Long userId,String category);
	 
	 public List<SelectOptionVO> getAllRegisterUsersForAssigningParty();
	 
	 public List<FileVO> getAllPartyDetailsAssignedToAUser(Long userId);
	 
	 public ResultStatus deleteUserPartyRelation(String userPartyRelationIds);
	 
	 public List<CandidateCommentsVO> getAbuseComment(String fromDate, String toDate,String selectstatus);
	 
	 public ResultStatus controlAbuseComments(List<CandidateCommentsVO> VO,String actionType);
	 
	 public ResultStatus deletePartyGallary(Long gallaryId);
	 
	 public Long getLocationScopeValue(Long scope,String locationValue);
	 
	 public String checkForMinisterData(String electionType , Long electionId);
	 
	 public List<SelectOptionVO> getCandidateGallaries(Long registrationId,String contentType);
	 
	 public String saveFileComment(Long fileId , String comment);
	 
	 public List<SelectOptionVO> getFilesOfAGallary(Long gallaryId);
	 
	 public PdfGenerationVO generatePdfForAGallary(PdfGenerationVO pdfGenerationVO);
	 
	 
	 public List<SelectOptionVO> getCandidateGallariesByCategory(Long categoryId , Long registrationId);
	 
	 public List<SelectOptionVO> getCandidateDetailsBySearch(String gender,String name,Long constituencyId,Long stateId,String selectedType);
	 
	 public ResultStatus saveCandidateVoterDetails(Long CandidateId,Long voterId);
*/
	 public Map<String, List<FileVO>> getPhotosNewsVideosUpdateForACandidate(int startIndex,int maxResults,String level,String newsType);
	 
	 public List<FileVO> getVideosForSelectedParty(Long partyId,String newsType);
	 
	 public List<FileVO> getVideosListForSelectedFile(Long fileId);
	 
	 public List<FileVO> getAllVideosList(Long partyId,int firstResult,int maxResult,String queryType);
			 
	 public List<FileVO> getAllNews(int startIndex,int maxIndex,String contenttype,Long partyId,String newsType);
	 
	 public List<FileVO> getRecentlyUploadedNewsTitles(int startIndex,int maxIndex,String contenttype,Long partyId,String newsType);
	 
	 public List<FileVO> getVideosForGalleryId(Long galId,int maxRecord,int startRecord);
	 
	 public List<FileVO> getCandidatesNews(Long candidateId,int firstRecord,int maxRecord,String type,String fromDateStr, String toDateStr,String gallaryIdsStr,String categoryIdsStr);
	 
	 public List<SelectOptionVO> getNewsForCandidate(Long candidateId , Date fromDate , Date toDate );
	 
	 public List<FileVO> getLatestResponsedNews();
	 
	 public List<FileVO> getNewsBetweenSelectedDates(String fromDateStr,String toDateStr,Integer starIndex, Integer maxResults, String newsType,String level,Long stateId);
	 
	 public List<SelectOptionVO> getCandidatesOfAParty(Long partyId);
	 public String insertMLCCandidateDetails(Long partyId ,String candidateName ,String  education , String gender,Long userId,Long designationId,Long locationId,Long LocationValue);

	 public ResultStatus createUserNewsCategory(String name, String visibility, Long userId,Long mainCategoryId);

	 public List<SelectOptionVO> getCandidateRelatedGallaries(Long candidateId,String fromDateStr,String toDateStr,Long partyId,String queryType);
	 
	public List<SelectOptionVO> getAllTheFilesOfAGallary(Long gallaryId  , Date fromDate , Date toDate);
	 
	public List<SelectOptionVO> getAllGallariesOfAParty(Long partyId);

	public List<SelectOptionVO> getNewsTitlesForACandidateByGalleryId(Long candidateId,Long gallaryId,String fromDateStr,String toDateStr);
	
	public List<SelectOptionVO> getNewsForACandidateByCategoryId(Long candidateId,Long categoryId,String fromDateStr,String toDateStr);
	
	public List<SelectOptionVO> getGalleryListForAParty(String fromDateStr,String toDateStr,List<Long> locationIdsList,String locationScope);
	
	public List<SelectOptionVO> getNewsByGalleryId(Long galleryId,String fromDateStr,String toDateStr);
	
	public ResultStatus assignResToCandidateOrAGallary(Long candidateId,Long fileGalleryId,Long resFileGalId,String tempVar);
	
	public List<FileVO> getCandidateNewsResponseNews(Integer startIndex,Integer maxIndex);
	
	public void setfileDetails(List<File> fileList,List<FileVO> fileVOsList);
	
	public List<SelectOptionVO> getLocationValuesByRegionScope(String regionScope, String queryType);
	
	public List<CategoryVO> getAllCategoriesOfUser(Long userId);
	
	public ResultStatus updateCategory(final Long userId,final Long categoryId,final String cateName,final String visibility);
	
	public ResultStatus updateCategoryStatus(Long userId,Long categoryId,String name);
	
	 public List<SelectOptionVO> getTotalCategoriesList(String queryType);
	 
	 public List<SelectOptionVO> getAllCategories();
	 
	 public List<SelectOptionVO> getGallariesInCategory(Long categoryId);
	 
	 public LocationVO getLocationListForSelectedUser(String userAccessType,Long accessValue,Long userId,String userType);
	
	 public List<SelectOptionVO> getConstituencyList(Long districtId,String accessType,Long accessValue);
	 
	 public List<SelectOptionVO> getTotalKeyWords(Long partyId,String newsType);
	 
	 public List<FileVO> getNewsForSelectedKeyword(String keyWord,Long partyId,String newsType,Integer startIndex,Integer maxIndex);
	 
	 public List<SelectOptionVO> getTotalKeyWords();
	 
	 public List<FileVO> getFilesOfACategory(Long gallaryId,Integer startIndex,Integer endIndex,String newsType,Long categoryId,String fromDateStr,String toDateStr);
	
	 public List<SelectOptionVO> getAllNewsGallaries();
	 public ResultStatus uploadAFileForCandidateParty(final FileVO fileVO);
	 
	 public List<SelectOptionVO> getCandidatesByPartyIdFromCandidateTable(Long userId,Long partyId);
	 
	 public SelectOptionVO getDesignationOfCandidateFromCandidateTable(Long candidateId);
	 
	 public List<SelectOptionVO> getBenefitList();
	 public List<FileVO> getCandidatesNewsForHomePage(Long candidateId,int firstRecord,int maxRecord,String type,String fromDateStr, String toDateStr,String categoryIdsStr,String keywordIdStr);
	 
	 public List<SelectOptionVO> getCandidateRelatedSubCategoriesByCandidateId(Long candidateId,String fromDateStr,String toDateStr,String queryType);
	 
	 public List<SelectOptionVO> getCandidateRelatedKeywordsByCandidateId(Long candidateId,String fromDateStr,String toDateStr,String queryType);
	 
	 public List<SelectOptionVO> getLatestGalleries();
	 
	 public List<FileVO> getSelectedGallaryDetails(int startIndex,int maxIndex,Long gallaryId);
	 
	 public List<GallaryVO> getAllGalariyes();
	 
	 public List<SelectOptionVO> getLocationValuesByRegionScope1(String regionScope, String queryType);
	 
	 public List<SelectOptionVO> getPartiesList();
	 
	 public List<SelectOptionVO> getDesignationsList();
	 
	 public String isKeywordExist(Long userId,String keyword);
	 
	 public String mergeSelectedKeywords(Long userId,List<Long> keywordsList,String newKeyword);
	 
	 public void testgetCandidatesListByPartyIdsList();

	 public List<SelectOptionVO> getFilteredCandidates(Long stateId,String partyId,String designationId,String locationType,String locationId,String text);
	 
	 public List<FileVO> getCandidateNewsResponseNewsForSelectdDates(Integer startIndex,Integer maxIndex,String fromDate,String toDate);
		 
	 public void populateNewsDataToVO(List<Object[]> newsList,List<Long> fileIds,LinkedHashMap<Long,FileVO> fileMap,Long count);

	 public Map<Long,String> getCandidateNames(Set<Long> fileIds);
	 
	 public Map<Long,String> getKeywordsCountByFileId(Set<Long> fileIds);
	 
	 public Map<Long,String> getCategorysCountByFileId(Set<Long> fileIds);
	 	
	 public List<SelectOptionVO> getKeywordsListCount();	 
	 
	 public ResultStatus editUploadedFileForCandidateParty(final FileVO fileVO);
	 
	 public List<SelectOptionVO> getUsersNewsUploadStatus(Date fromDate,Date toDate);
	 
	 public SelectOptionVO generateExcelForUsersNewsUploadStatus(Date fromDate,Date toDate,String from,String to);
	 
	 public List<SelectOptionVO> getKeyWordsBySearchCriteria(String searchString);
	 
     public List<SelectOptionVO> getPartiesListByStateId(Long stateId);

}