package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateNewsCountVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsCountVO;
import com.itgrids.partyanalyst.dto.NewsDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface INewsMonitoringService {/*
	public List<FileVO> getNewsForRegisterUsers(FileVO inputs);
	public List<FileVO> getNewsForRegisterUsers1(FileVO inputs);
	public List<FileVO> getAllCountDetails(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
	
	public List<FileVO> getCategoryCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO);
	public List<FileVO> getSourceCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO);
	public List<FileVO> getLanguageCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO);
	public List<FileVO> getNewsImpCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO);
	public ResultStatus updateDeleteNews(FileVO fileVO,String task,List<FileVO> sourceIds,List<FileVO> languageIds);
    public List<FileVO> getAllRegionScopes();
	
	public List<FileVO> getNewsCountForALocationByCategory(Long registrationId,
			Long locationValue, Long locationId, Long publicationId);
	
	public List<FileVO> getNewsByLocationAndCategory(FileVO fileVO);
	
	public ContentDetailsVO getNewsByLocationAndCategoryInPopup(FileVO fileVo);
	
	public Long saveContentNotesByContentId(Long contentId , String commentText);
	public List<CommentVO> getContentNotesByContentId(Long contentId,Long registrationId);
	public String removeContentNotes(Long contentNotesId);
	
	public String addFlagToNews(Long contentId , Long userId);
	
	public String checkForFlag(Long contentId);
	public String removeFlagForNews(Long contentId);
	
	public String updateVisibility(Long contentId , String visibility);
	
	public String checkForVisibilityStatus(Long contentId);
	
	public List<FileVO> getNewsForFlagedAndNoted(FileVO inputs);
	
		
*/
	public List<FileVO> getNewsForAuser(FileVO inputs);
	public List<FileVO> getAllSourceDetails();
	public List<FileVO> getAllCategoryDetails();
	public List<FileVO> getAllSourceLanguageDetails();
	public List<FileVO> getAllNewsImportanceDetails();
	public ResultStatus updateDeleteNews(FileVO fileVO,String task,List<FileVO> sourceIds,List<FileVO> languageIds);
	public List<SelectOptionVO> getCandidates();
	public ResultStatus storeSourceDetails(String value);
	public List<SelectOptionVO> getCandidatesByRemovingDots();

	public List<CandidateNewsCountVO> getNewsCountForACandidate(String fromDateStr,String toDateStr,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,String tempVar,String locationScope);
	
	public List<FileVO> getLocationWiseNewsDetailsForACandidate(Long candidateId,String fromDateStr,String toDateStr,String locationScope,Integer startIndex,Integer maxIndex,String galleryIdsStr,String categoryIdsStr);

	public List<SelectOptionVO> getCategoryList(String fromDateStr, String toDateStr,String locationScope,List<Long> locationIdsList);
	
	public List<SelectOptionVO> getGalleryListForSelectedCategory(List<Long> categoryIdsList);
	
	public List<SelectOptionVO> getLocationsListByScopeId(String locationScope,String fromDateStr, String toDateStr);
	
	public NewsCountVO getRespondedAndNotRespondedNewsCount(String fromDateStr,String toDateStr,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,String locationScope,String tempVar);
	
	public List<FileVO> getNewsDetailsForAParty(NewsDetailsVO newsDetailsVO);
	
	public List<CandidateNewsCountVO> getCandidateCritiesNewsDetails(String fromDateStr,String toDateStr);//,String tempVar);
	
	public ResultStatus changePassword(final String currentPWD,final String newPWD,final Long userId);
		
	public List<FileVO> getAllNewsDetails(FileVO fileVO);
	
	public ResultStatus saveNewsReport(final List<Long> fileGallaryIds,final Long userId,final String decription);
	
	public ResultStatus updateGallaryKeyword(List<Long> gallaryIds,List<Long> keywords,Long userId)  ;
	
	public List<SelectOptionVO> getKeywords(Long userId,Boolean flag);
	
	public ResultStatus updateExistingGallaryKeyword(List<Long> checkedgallaryIds,List<Long> uncheckedgallaryIds,Long keyword,Long userId);
	
	public List<Long> getGallaryId(Long userId,Long keyword);
	
	public List<FileVO> getNewsReports(Long userId);
	
	public List<SelectOptionVO> getMainCategories();
	
	public List<SelectOptionVO> getCandidatesByPartyIdsList(List<Long> partyIdsList);
	
	public ResultStatus saveCandidatesAndParty(Long partyId,String candidateName,Long designationId);
	
	public List<SelectOptionVO> getCandidatesNewsCount();
	
	public FileVO getTotalNews(FileVO inputs);
	
}



