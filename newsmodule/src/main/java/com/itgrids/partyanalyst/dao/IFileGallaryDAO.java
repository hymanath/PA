package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.NewsCountVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.UserAddress;

public interface IFileGallaryDAO extends GenericDao<FileGallary, Long>{/*
	
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
	*/
	public void updateVisibility(Long fileId,String visibility);
	/*
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
    
	
	public List<Object[]> getNewsCountForALocationByCategoryForACandidate(List<Long> candidateIds,
			Long locationScopeId,List<Long> locationValuesList);
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForACandidate(
			List<Long> candidateIds, Long categoryId, Long locationId,
			List<Long> locationValuesList);
	
	public List<Object[]> getNewsByLocationAndCategory(List<Long> candidateIds,
			FileVO fileVO, List<Long> locationValuesList);
	
	 
	 
	 
	 public List<String> checkForVisibilityStatus(Long contentId);
	 
	 public List<FileGallary> getNewsByLocationAndCategoryInPopup(List<Long> candidateIds,
				FileVO fileVO, List<Long> locationValuesList);
	 */
	 public List<Object[]> getNewsCountForMandalLevel(List<Long> candidateIds,
				Long tehsilScopeId, List<Long> tehsilIdsList,
				Long panchayatScopeId, List<Long> panchayatIdsList,Date fromdDate,Date toDate);
	 /*
	public List<File> getNewsCountForALocationByCategoryAndImportance(
				List<Long> candidateIds,Long categoryId ,Long locationScopeId ,List<Long> locationValuesList,Long hamletScopeId ,
				List<Long> hamletIds );
	 
		*/
	public List<Object[]> getNewsCountForConstituencyLevel(
				List<Long> candidateIds, Long constituencyScopeId, Long constituencyVal,
				Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
				List<Long> hamletIds);
	/*
	public List<Object[]> getNewsCountForConstituencyLevelWithMuncipality(
			List<Long> candidateIds, Long constituencyScopeId, Long constituencyVal,
			Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
			List<Long> hamletIds,Long muncipalityScopeId ,List<Long> localElectionBodyIds);
	
	public List<Object[]> getNewsCountForConstituencyLevelWithMuncipalityAndWards(
			List<Long> candidateIds, Long constituencyScopeId, Long constituencyVal,
			Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
			List<Long> hamletIds,Long muncipalityScopeId ,List<Long> localElectionBodyIds,Long wardScopeId,List<Long> wardIdsList);
			
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForConstituency(
            Long categoryId , NewsCountVO newsCountVO);
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForMandal(
            Long categoryId , NewsCountVO newsCountVO);
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForPanchayat(
            Long categoryId , NewsCountVO newsCountVO);
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipality(
            Long categoryId , NewsCountVO newsCountVO);
	
	
	public List<Object[]> getNewsByForPanchayat(NewsCountVO newsCountVO);
	public List<Object[]> getNewsByForMandal(NewsCountVO newsCountVO);
	public List<Object[]> getNewsByForConstituency(NewsCountVO newsCountVO);
	public List<Object[]> getNewsByForMuncipality(NewsCountVO newsCountVO);
	public List<Object[]> getNewsByForConstituencyWithMuncipality(NewsCountVO newsCountVO);

	
	public List<FileGallary> getNewsDetailsByForMandal(NewsCountVO newsCountVO);
	public List<FileGallary> getNewsDetailsForConstituency(NewsCountVO newsCountVO);
	public List<FileGallary> getNewsDetailsByForMuncipality(NewsCountVO newsCountVO);
	public List<FileGallary> getNewsDetailsForConstituencyWithMuncipality(NewsCountVO newsCountVO);
	public List<FileGallary> getNewsDetailsByForMuncipalityWithWards(NewsCountVO newsCountVO);
	public List<FileGallary> getNewsDetailsForConstituencyWithMuncipalityAndWards(NewsCountVO newsCountVO);
	*/
	public List<Object[]> getNewsCountForMuncipality(
			List<Long> candidateIds ,Long muncipalityScopeId ,List<Long> muncipalityValuesList,Date fromDate,Date toDate);
	/*
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForMuncipality(
			Long categoryId , NewsCountVO newsCountVO);
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForMuncipalityWithWards(
			Long categoryId , NewsCountVO newsCountVO);
	
	public List<FileGallary> getFilegallaryDetailsForPanchayat(NewsCountVO newsCountVO);
	*/
	public List<Object[]> getNewsCountForMuncipalityWithWards(NewsCountVO newsCountVO,Date fromDate,Date toDate);
	/*
	public List<Object[]> getNewsByForMuncipalityWithWards(NewsCountVO newsCountVO);
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipalityAndWards(
            Long categoryId , NewsCountVO newsCountVO);
	
	public List<Object[]> getNewsByForConstituencyWithMuncipalityWithWards(NewsCountVO newsCountVO);
	*/
	public List<Object[]> getNewsCountForWards(List<Long> candidateIds,
			Long wardScopeId, List<Long> wardValuesList,Date fromDate,Date toDate);
	/*
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForWard(
            Long categoryId , NewsCountVO newsCountVO);
	
	public List<FileGallary> getNewsDetailsByForWards(NewsCountVO newsCountVO);
	
	public List<Object[]> getNewsByWard(NewsCountVO newsCountVO);
	*/
	public List<Object[]> getNewsCountForHamlets(List<Long> candidateIds,
			Long hamletScopeId, List<Long> hamletIds,Date fromDate,Date toDate);
	/*
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForHamlet( Long categoryId , NewsCountVO newsCountVO);
	
	public List<Object[]> getNewsByHamlet(NewsCountVO newsCountVO);
	
	public List<FileGallary> getNewsDetailsByForHamlets(NewsCountVO newsCountVO);
*/
	
    public List<FileGallary> getFilesOfInGallaries(List<Long> gallaryIdsList , int startIndex  , int endIndex,String newsType);
	public List<Long> getAllRecordCountInGallary(Long gallaryId);
   public List<File> getStartingRecordInGallary(Long gallaryId);
	
	public List<Object[]> getAllRecordInGallary(Long gallaryId);
	
	 public List<?> getPartyWiseAllNewsDetailsInLocation(List<Long> partyId, Long districtScopeId, List<Long> districtIds, Long constituencyScopeId, List<Long> constituencyVal,
				Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
				List<Long> hamletIds,Long muncipalityScopeId ,List<Long> localElectionBodyIds,Long 
				wardScopeId,List<Long> wardIdsList,String queryType,int firstResult,int maxResult,String type,Date fromDate,Date toDate);
	 
	public List<Object[]> getAllVideoFilesOfInGallaries(int startIndex , int endIndex,String type);
	
	public List<FileGallary> getAllVodeosForSelectedFile(Long fileId);


	
	public List<File> getRecentlyUploadedNewsDetails(Integer starIndex, Integer maxResults);
	
	public Object getFileIdByFileGallaryId(Long fileGallaryId);
	
	public List<Object> getGalleryIdsOfAFile(Long fileId);
	
	public List<FileGallary> getFilesOfInGallariesForCustomer(List<Long> gallaryIdsList);
	
	public List<FileGallary> getFilesOfInGallaries(List<Long> gallaryIdsList);
	
	public List<Object[]> getFirstFileAndGallaryInfo(Long gallaryId,String queryString);
	
	public List<Object[]> getFirstFileAndGallaryInfoForCustomer(Long gallaryId,String queryString);
	
	public List<Object[]> getRecentlyUploadedNews(Integer starIndex, Integer maxResults,String contentType,Long partyId,String newsType);
	
	public FileGallary getSelectedNewsDetails(Long fileGallaryId);
	public List<Object[]> getVideosOfGalleryId(Long gallaryId,int maxRcrd,int firstRcrd);
	
	public int getVideosCountOfGalleryId(Long galleryId);
	
	public Long getVideosCountIntheGallary(Long galleryId,String queryType);
	
	
	public List<Object[]> getAllTheNewsForAUser(Long userId,Date fromDate,Date toDate);
	public List<FileGallary> getFileGallariesByFileId(Long fileId);
	public void deleteFile(Long fileId);
	
	public List<FileGallary> getFileGallariesByFileGallaryIdsList(List<Long> fileGallaryIdsList);
	
    public List<FileGallary> getFilesOfGallaries(List<Long> gallaryIdsList , int startIndex  , int endIndex,String newsType,Long categoryId,Date fromDate,Date toDate,String requestFor);
    
    public List<Long> getAllRecordsCountInGallary(Long gallaryId,String newsType,Long categoryId,Date fromDate,Date toDate);
    
    public List<File> getNewsDetailsBetweenSelectedDates(Date fromDate,Date toDate, Integer starIndex, Integer maxResults,Long scopeId,Long stateId);
    public List<Object[]> getGalleriesByCategoryIds(List<Long> categoryIdsList,Long partyId,Long candidateId);
    
    public List<Object[]> getAllFilesOfAGallaryByGallaryId(Long gallaryId , Date fromDate , Date toDate);

    public List<Object[]> getnewsListByGalleryId(Long galleryId,Date fromDate,Date toDate);
    
    public List<Long> getFileGallaryIdsListByGallaryId(Long gallaryId);
    
    public List<Long> getLocationValuesByRegionScopeId(Long regionScopeId, String queryType,Long partyId);
    
    public List<FileGallary> getFilesByGalleryIdsList(List<Long> gallaryIdsList , Integer startIndex  , Integer endIndex,String newsType,Long categoryId,Date fromDate,Date toDate,String requestFor);
    
    public List<FileGallary> getFileGallaryByGalleryIdsList(List<Long> gallaryIdsList , Integer startIndex  , Integer endIndex,String newsType,Long categoryId,Date fromDate,Date toDate,String requestFor);
    
    public List<Object[]> getAllTheNewsForAUserBasedOnUserAddressId(Long userId,Date fromDate,Date toDate,Long regionLevel,Long importanceId);
    
    public List<Object[]> getNewsByLocationWise(Long locationValue,Long locationScope,Long userId);
  	
	//public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userIds,Date fromDate,Date toDate,Long importanceId,Long regionValue);
    
    public List<FileGallary> getNewsForSelectedKeyWord(String keyWord,Long partyId,String newsType,Integer startIndex,Integer maxIndex);
  	
  	 public Integer deleteDefaultGallaries(List<Long> fileIds);
  	 
  	 public Long checkFileGallaryExist(Long gallaryId,Long fileId);
  	 
  	public Long getRecentlyUploadedNewsDetailsCount();
  	
  	public Long getNewsDetailsBetweenSelectedDatesCount(Date fromDate,Date toDate, long scopeId,Long stateId);
      
  	public List<Long> getLocationValuesByRegionScopeId1(Long regionScopeId, String queryType,Long partyId);
  	
  	public List<Long> getLocationValuesByRegionScopeId2(Long regionScopeId, String queryType,Long partyId);
  	
  	public List<UserAddress> getNewsCountForALocation1(Long locationId,Integer startRecord,Integer maxRecord,String queryType,Date fromDateStr,Date toDateStr);
  	
  	public Long getNewsTotalCountForALocation1(Long locationId,Integer startRecord,Integer maxRecord,String queryType,Date fromDateStr,Date toDateStr);
  	
  	public List<Object[]> getLocationValuesCountByRegionScopeId2(Long regionScopeId, String queryType,Long partyId);
  	
  	public List<Object[]> getLocationValuesCountByRegionScopeId1(Long regionScopeId, String queryType,Long partyId);
}

