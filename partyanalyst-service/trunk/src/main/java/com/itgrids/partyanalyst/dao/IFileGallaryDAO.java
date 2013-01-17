package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.PdfGenerationVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;

public interface IFileGallaryDAO extends GenericDao<FileGallary, Long>{
	
	public List<File> getStartingRecordInGallary(Long gallaryId);
	
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
	
	public List<Object[]> getNewsByGalleryId(List galleryIds);	
	
	 public void updateVisibility(Long fileId,String visibility);
	
	public List<File> getNewsForRegisterUsers(FileVO fileVO);
	
    public List<Object[]> getCountDetailsForCategory(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getCountDetailsForSource(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getCountDetailsForLanguage(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getCountDetailsForNewsImportance(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getCountDetailsForLocationScope(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getCountDetailsForLocationScopeValue(Date fromDate,Date toDate,String fileType,Long regId);
    
    public List<Object[]> getDetailsForCategory(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getDetailsForSource(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getDetailsForLanguage(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public List<Object[]> getDetailsForNewsImportance(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO);
    
    public void updateFileDate(Date updateDt,Long fileId);
    
    public void deleteFile(Long fileId);
    
    public List<FileGallary> getRecentlyUploadedPhotos(Integer startIndex,Integer maxResults);
        
    public List<Long> getTotalIndividualSources(Long candidateId,String queryType,String sourceStr , String languageStr,String categoryStr,String newsImportance );
    
    public Object getFileIdByFileGallaryId(Long fileGallaryId);
    
    public List<Object> getGalleryIdsOfAFile(Long fileId);
    
    public List<FileGallary> getFilesOfInGallaries(List<Long> gallaryIdsList);
    
    public List<Object[]> getFirstFileAndGallaryInfo(Long gallaryId,String queryString);
    
    public List<Object> getFileGallaryIdByFileId(Long fileId);
    
    public List<Long> getRecentlyUploadedFileIds(Integer startIndex , Integer maxResults , String queryStr);
    
    public List<Long> getRecentlyUploadedPhotoIds(Integer startIndex,Integer maxResults);
    
    public List<FileGallary> getFileGallaryByFileIdsList(List<Long> fileIdsList);
    
    public List<FileGallary> getCandidateGallaryDetailsForSubscribers(Date fromDate,Date toDate,Set<Long> candidateIds,String type);
    
    public List<Object[]> getPartyGallaryDetailsForSubscribers(Date fromDate,Date toDate,Set<Long> partyIds,String type);
    
    public List<Object[]> getSpecialPageGallaryDetailsForSubscribers(Date fromDate,Date toDate,Set<Long> specialPageIds,String type);
    
    public List<Object[]> getAllNewsDetails(Long candidateId,int firstResult,int maxResult,String queryType);
    
    public List<Long> getAllRecordCountInGallary(Long gallaryId);
    
    public List<FileGallary> getFileGallaryByFileIdsListForNews(List<Long> fileIdsList);
    
    public List<FileGallary> getHomePageNewsDetails(Integer startIndex , Integer maxResults );
    
    public FileGallary getFileGallary(Long fileGallaryId);
    
    public List<FileGallary> getRecentlyUploadedGallaries(Integer startIndex,Integer maxResults);
    
    public List<FileGallary> getStartingRecordInGallaries(Long gallaryId);
    
    public List<FileGallary> getRecentlyUploadedVedioGallaryIds(Integer startIndex,Integer maxResults,String queryStr2);
    public List<Long> getRecentlyUploadedNewsGallaryIds(Integer startIndex , Integer maxResults,String queryStr3);
    
    public List<FileGallary> getStartingRecordInNewsGallaries(Long gallaryId);
	
    public List<FileGallary> getRecentlyUploadedNewsFileIds(Integer startIndex , Integer maxResults , String queryStr);
    
    public List<FileGallary> getCandidateGallaryDetailsForProfilePageStreaming(Date fromDate,Date toDate,Set<Long> candidateIds);
    
    public List<Object[]> getNewsForRegisterUsers1(FileVO fileVO);
    
    public List<FileGallary> getFilesOfInGallariesForCustomer(List<Long> gallaryIdsList);
    
    public List<File> getAllFilesInAGallry(String queryString,PdfGenerationVO pdfGenerationVO);
    
    public List<Object[]> getCandidateGallariesByCategory(List<Long> candidateds , Long categoryId);
    
    public List<Object[]> getFirstFileAndGallaryInfoForCustomer(Long gallaryId,String queryString);
}

