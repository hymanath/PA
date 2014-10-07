package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
	
	public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds,Long impotanceId,Integer startIndex,Integer maxIndex);
	
	public Long getAllTheNewsCountForAUserBasedByUserIdCount(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> gallaryIds);
	
	public Long getAllTheNewsCountForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds,Long importanceId);
	
	public List<Object[]> getCategorysCountByFileId(Set<Long> fileIds);
	
	public List<Object[]> getCategoriesList(List<Long> fileIds);
	
	public List<Object[]> getCategoeryAndConsttituencyWiseNews(List<Long> categIds,List<Long> constituencyIds,Date fromdate , Date toDate,int startIndex,int maxIndex,List<Long> partyIds);
	
	public List<Object[]> getCategoeryAndConsttituencyWiseCount(List<Long> categIds,List<Long> constituencyIds , Date fromDate , Date toDate,List<Long> partyIds);
	
	public List<Object[]> getCategoeryAndConsttituencyWiseTotalCount(List<Long> categIds,List<Long> constituencyIds , Date fromDate , Date toDate,List<Long> partyIds);
	
	public List<Object[]> getCategoeryAndDisrictWiseCount(List<Long> categIds,List<Long> districtIds , Date fromDate , Date toDate,List<Long> partyIds);
	
	public List<Long> getCategoeryAndConsttituencyWiseNewsIds(List<Long> categIds,List<Long> constituencyIds , Date fromDate , Date toDate,List<Long> partyIds);

	public List<Object[]> getCandidatePartyCategoryDetialsByFileId(Long gallaryId);
	
	public void deleteCandidatePartyCategories(List<Long> candidatePartyFileIds);
	
	 public List<Object[]> getProblemsCount(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,Long categoryId);
	 
	 public List<Object[]> getElectionIssues(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,Long categoryId,List<Long> partyIds);
	 
	 public List<Object[]> getCategoryWiseBenifit(Date fromDate,Date toDate,Long stateId,Long partyId);
	 
	 public List<Object[]>  getCategoryBenifitWiseNews(Date fromDate,Date toDate,Long partyId,Long categoryId,Long benfitId,Long stateId);
	 
	 public Long getCategoryBenifitWiseNewsCount(Date fromDate,Date toDate,Long partyId,Long categoryId,Long benfitId,Long stateId);
	 
	 public List<Object[]>  getCategoryBenifitWiseNewsDetails(Date fromDate,Date toDate,Long partyId,List<Long> categoryId,Long benfitId,Long stateId);
	
}
