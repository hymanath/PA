/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICandidateDetailsService {
	
	public CandidateVO getCandidateDetails(Long candidateId);

	public List<CandidateDetailsVO> getCandidateElectionDetails(Long candidateId);
	
	public List<CandidateOppositionVO> getCandidatesOppositionList(Long candidateId,Long electionId,Long constituencyId);
	
	public List<FileVO> getCandidatesPhotoGallaryDetail(Long candidateId,int firstRecord,int maxRecord,String type);
	
	public List<FileVO> getCandidatesPhotosInAGallary(Long gallaryId);
	
	public ResultStatus createNewGallary(GallaryVO gallaryVO);
	
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
}