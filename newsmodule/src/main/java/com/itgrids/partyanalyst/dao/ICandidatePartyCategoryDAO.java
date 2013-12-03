package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidatePartyCategory;
import com.itgrids.partyanalyst.model.File;

public interface ICandidatePartyCategoryDAO extends GenericDao<CandidatePartyCategory, Long>{
	public List<Object[]> getCandidateRelatedCategories(Long candidateId,Date fromDate, Date toDate,String newsType);
	 public List<File> getFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> categoryIdsList);

	public List<Object[]> getSelectdGalleryNews(int startIndex,int maxIndex,Long gallaryId);
	
	public List<Object[]> getLatestGallerices();
	
	public Long getCountForNewsInASelectedGallery(Long gallaryId);
	
	public List<Object[]> getAllCategoryes();
	
	public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> gallaryIds,Integer startIndex,Integer maxIndex);
	
	public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds,Integer startIndex,Integer maxIndex);
	
	public Long getAllTheNewsCountForAUserBasedByUserIdCount(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> gallaryIds);
	
	public Long getAllTheNewsCountForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds);
}
