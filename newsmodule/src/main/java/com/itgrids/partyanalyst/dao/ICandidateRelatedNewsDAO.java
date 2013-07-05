package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateRealatedNews;
import com.itgrids.partyanalyst.model.FileGallary;

public interface ICandidateRelatedNewsDAO extends GenericDao<CandidateRealatedNews, Long>{
	public List<Object[]> getAllfileGallariesOfCandidate(Long candidateId  ,Date fromDate  ,Date toDate);
	public List<Object[]> getCandidates();
	public List<Object[]> getCandidatesContainsNews();
	
	public List<FileGallary> getFileGallaryListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> gallaryIdsList,List<Long> categoryIdsList);
	
	public List<Object[]> getCandidatesWithCount();
	
	public List<Object[]> getNewsForAParty(Long partyId  ,Date fromDate  ,Date toDate);
	
	public List<Object[]> getCandidateRelatedNewsByGallaryId(Long candidateId,Long gallaryId,Date fromDate,Date toDate);
	
	public List<Object[]> getNewsForACandidateByCategoryId(Long candidateId,Long categoryId,Date fromDate,Date toDate);
	
	public List<Long> getFileGalleryIdByCandidateId(Long candidateId);
	
	public List<Object[]> getNewsCountForACandidate(Date fromDate, Date toDate,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar, Long partyId);
	
	public List<FileGallary> getLocationWiseFileGalleryList(Long candidateId,Date fromDate,Date toDate,Long locationScopeId,Integer startIndex,Integer maxIndex,List<Long> galleryIdsList,List<Long> categoryIdsList);
	
	public List<Long> getLocationValuesByLocationScopeId(Long locationScopeId,Date fromDate, Date toDate);
	
	public List<Object[]> getAllGallariesListForParty(Date fromDate,Date toDate,Long locationScopeId,List<Long> locationIdsList);
	
	public List<Object[]> getCategoryList(Date fromDate, Date toDate,Long locationScopeId,List<Long> locationIdsList);
	
	public List<Object[]> getGalleryListForSelectedCategory(List<Long> categoryIdsList);
	
	public List<Object[]> getCandidateByFileGallaryId(List<Long> gallaryIdsList);
	
	public List<Long> getTotalNewsCount(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar);
	
	public List<Object[]> getTotalNewsCountForAParty(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar);

	public List<Long> getNotRespondFileGalleryIds(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,List<Long> respondFileGalleryIds);
	
	public List<Object[]> getRespondNewsPartyDetails(List<Long> respondFileGalleryIdsList);
	
	public List<Long> getResponseCountBasedTotalNewsCount(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar);
	public List<Long> getNotResponseCountBasedTotalNewsCount(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar);
	public List<Object[]> getRespondNewsPartyDetailsCustom(List<Long> respondFileGalleryIdsList);

	public List<Long> getTotalNewsCountCustom(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar);
	
	public List<Long> getResponseCountBasedTotalNewsCountCustom(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar);


}
