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

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.File;

public interface ICandidateDetailsService {
	
	public CandidateVO getCandidateDetails(Long candidateId);

	public List<CandidateDetailsVO> getCandidateElectionDetails(Long candidateId);
	
	public List<CandidateOppositionVO> getCandidatesOppositionList(Long candidateId,Long electionId,Long constituencyId);
	
	public List<FileVO> getCandidatesPhotoGallaryDetail(Long candidateId,int firstRecord,int maxRecord,String type);
	
	public List<FileVO> getCandidatesPhotosInAGallary(Long gallaryId);
	
	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,String createOrUpdate);
	
	public List<FileVO> searchNewsDetails(FileVO inputs);
	
	public List<FileVO> getScopesForNewSearch();
	
	public List<FileVO> getStateDetails();
	
	public List<FileVO> getDistrictDetailsByStateId(Long stateId);
	
	public ResultStatus uploadAFile(FileVO fileVO);
	
	public List<SelectOptionVO> getCandidateGallarySelectList(Long candidateId,String contentType);
	
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
	
	public List<SelectOptionVO> getCategory();
	
	public List<SelectOptionVO> getSource();
	
	public List<SelectOptionVO> getLanguage();

	public FileVO copyFileToFileVO(File file);
	
	public List<SelectOptionVO> getCandidatesOfAUser(Long userId);
	
	 public ResultStatus deleteFilesAndPhotos(Long fileId , Long gallaryId);
	 
	 public ResultStatus deleteGallary(Long gallaryId);
	 
	 public List<FileVO> getNewsByLanguage(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType,String language);
	 
	 public List<FileVO> getNewsBySource(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String source);

	 public	FileVO getCandidatesGallaryDescForUpdate(Long gallaryId , Long candidateId);
	 
	 public	FileVO getPhotoUploadDescForUpdate(Long gallaryId , Long fileId);
	 
	 public ResultStatus updateIndividualPhoto(FileVO fileVO);

	 public Map<String, List<FileVO>> getPhotosNewsVideosUpdateForACandidate(int startIndex,int maxResults);
	 
	 public SelectOptionVO assignAndReturnCandidate(Long userId,Long candidateId);
	
	 public boolean checkForCandidateExistence(List<SelectOptionVO> candidatesList,Long candidateId);
	 public List<FileVO> getNewsByCategory(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType,String category);
	 public List<SelectOptionVO> getNewsImportance();
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
}