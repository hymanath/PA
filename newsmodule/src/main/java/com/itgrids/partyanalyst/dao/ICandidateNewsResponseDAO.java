package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.CandidateNewsResponse;

public interface ICandidateNewsResponseDAO extends GenericDao<CandidateNewsResponse, Long>{

   public List<Object[]> getResponsefileGallaryIds(Long fileGallaryId, Integer startIndex, Integer maxIndex);
	
   public List<Object[]> getFileGallaryIdsByResponseGallaryId(Long fileGallaryId, Integer startIndex, Integer maxIndex);
   
   public List<Long> getFileGallaryIdsByResponseGallaryId(Long responseFileGallaryId);
   public List<Long> getResponseFileGallaryidForANews(List<Long> fileGallaryIds);
	
}
