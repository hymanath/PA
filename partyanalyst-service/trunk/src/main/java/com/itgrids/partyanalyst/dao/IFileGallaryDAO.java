package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;

public interface IFileGallaryDAO extends GenericDao<FileGallary, Long>{
	
	public List<Object[]> getStartingRecordInGallary(Long gallaryId);
	
	public List<Object[]> getAllRecordInGallary(Long gallaryId);
	
	public List<Object[]> getNewsRecordsBySearchCriteria(FileVO fileVO,String type);
	
	public List<Object[]> getFirstFourNewsToDisplay(Long candidateId,int firstResult,int maxResult,String queryType);
	
	public List<Object[]> getAllNewsToDisplay(Long candidateId);
	
	public List<File> getCandidateLatestVideos(Long candidateId,Integer startIndex, Integer maxResults);
	
	public List<Object[]> getFirstThreeImagesToDisplay(Long candidateId,int firstResult,int maxResult);
	
	public List<Long> getNewsCountByScope(Long candidateId,Long scopeType,String queryType);
	
	public List<Object[]> getNewsByScope(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String spScope , String spScopeLang,String categoryStr,String newsImportance);
    
	public List<Object[]> getOtherNews(Long candidateId,int startIndex,int maxResults,String queryType);
	
	public Integer deleteFilesAndPhotos(Long fileId , Long gallaryId);
	
	public List<Object[]> getPhotoAndFileDescForUpdate(Long gallaryId , Long fileId);
	
	//public List<File> getFirstFourNewsForParty(Long partyId,int firstResult,int maxResult,String queryType);
	
	public List<File> getPartyLatestVideos(Long partyId,Integer startIndex, Integer maxResults);
	
	public List<FileGallary> getRecentlyUploadedFiles(Integer startIndex , Integer maxResults , String queryStr);
}
