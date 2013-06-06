/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICandidateDetailsService {
	
	
	public List<FileVO> getFilesOfAGallary(Long gallaryId , int startIndex , int endIndex);
	public List<SelectOptionVO> getLatestgallaries();
	
	/*
	
	public CandidateVO getCandidateDetails(Long candidateId);

	public List<CandidateDetailsVO> getCandidateElectionDetails(Long candidateId);
	
	public List<CandidateOppositionVO> getCandidatesOppositionList(Long candidateId,Long electionId,Long constituencyId);
	
	public List<FileVO> getCandidatesPhotoGallaryDetail(Long candidateId,int firstRecord,int maxRecord,String type);
	
	public List<FileVO> getCandidatesPhotosInAGallary(Long gallaryId);
	
	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,String createOrUpdate);
	
	public List<FileVO> searchNewsDetails(FileVO inputs);
	
	
	
	
	*/
	public List<FileVO> getDistrictDetailsByStateId(Long stateId);
	
	public ResultStatus uploadAFile(FileVO fileVO);
	public List<FileVO> getScopesForNewSearch();
	public List<SelectOptionVO> getSource();
	public List<SelectOptionVO> getLanguage();
	public List<SelectOptionVO> getCategory();
	 public List<SelectOptionVO> getNewsImportance();
	 public List<FileVO> getStateDetails();
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
	
	public List<SelectOptionVO> getCandidatesOfAUser(Long userId);
	
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
	 
	 public List<FileVO> getNewsGalleryByUserIdFromUserGallery(Long userId);
	 	 
	 public String getLocationDetails(Long scope,Long locationValue);
	 
	 public List<CandidateCommentsVO> getMessages(String fromDate, String toDate,String selectstatus,String decidestatus);
	 
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
	 public Map<String, List<FileVO>> getPhotosNewsVideosUpdateForACandidate(int startIndex,int maxResults);
	 
	
}