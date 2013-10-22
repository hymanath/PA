package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.CandidateNewsResponse;

public interface ICandidateNewsResponseDAO extends GenericDao<CandidateNewsResponse, Long>{

   public List<Object[]> getResponsefileGallaryIds(Long fileGallaryId, Integer startIndex, Integer maxIndex);
	
   public List<Object[]> getFileGallaryIdsByResponseGallaryId(Long fileGallaryId, Integer startIndex, Integer maxIndex);
   
   public List<Long> getFileGallaryIdsByResponseGallaryId(Long responseFileGallaryId);
   public List<Long> getResponseFileGallaryidForANews(List<Long> fileGallaryIds);
   
   public List<Long> getFileGalleryIdByResponseGalleryId(Long fileGalleryId);
   
   public List<Long> getResponseNewsCount(List<Long> fileGalleryIdsList);
   
   public List<Object[]> getResponseNewsCountForAParty(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar);
   
   public List<Object[]> getResponsefileGallaryDetails(List<Long> fileGallaryIdsList);
   
   public List<Object[]> getMainArticalIdsGallaryIdsByResponseGallaryId(List<Long> fileGallaryIdsList);
	
}
