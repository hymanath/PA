package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.CommentVO;
import com.itgrids.partyanalyst.dto.ContentDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.FileGallary;

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
	public ResultStatus storeSourceDetails(String value);


}



