package com.itgrids.partyanalyst.dao;
import java.util.Date;
import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyGallery;

public interface IPartyGalleryDAO extends GenericDao<PartyGallery,Long>{
	public List<Object[]> getPartyGallaryDetail(Long partyId,String type,int startIndex , int maxRecords);
	public List<Object[]> getNewsCountDetailsForGallaries(List<Long> gallaryIds);

	public List<Object[]> getPartyGallaryDetail(Long partyId,int firstResult,int maxResult,String type) ;
	
	public List<File> getFirstFourNewsForParty(Long partyId,int firstResult,int maxResult,String queryType);
	
	public List<Object[]> getNewsByScope(Long partyId,Long scopeType,int startIndex,int maxResults,String queryType , String sourceStr , String languageStr);
	
	public List<Object[]> getAllRecordInGallary(Long gallaryId);
	
	public List<Object[]> getGallariesByPartyId(Long partyId,String contentType);
	
	public List<Object[]> getPartyGalleriesDescForUpdate(Long gallaryId,Long partyId);
	
	public List<Party> getPartyByGalleryId(Long gallaryId);
	
	public List<Object> getPartyVisibility(Long gallaryId);
	
	public List<Object> getOtherGalleries(Long partyId,List<Long>gallaryIds,String contentType);
	
	public List<Object[]> getNewsForParty(Long partyId,int firstResult,int maxResult,String queryType);
	
	public Integer deletePartyGallary(Long gallaryId);
	
	public List<Object[]> getAllNewsDetails(Long partyId,int firstResult,int maxResult,String queryType);
	
	public List<Long> getNewsCountByScope(Long partyId,Long scopeType,String queryType);
	
	public List<Object[]> getPartyGallaryByPartyId(Long partyId, String contentType, String gallaryName);
	//tdp news int
	public List<Object[]> getAllNewsDetailsForState(Long partyId,int firstResult,int maxResult,String queryType,long stateId,long scopeValue);
	public List<Object[]> getAllNewsDetailsForDistrict(Long partyId,int firstResult,int maxResult,String queryType,Long distScope ,List<Long> districtIds);
	public List<Object[]> getGallariesByPartyIdForState(Long partyId,String contentType,int firstResult,int maxResult);
	
	public int getCountOfNewsFiles(Long partyId,String queryType,long stateId , long scopeId);
	
	public int getCountOfNewsFilesForDistrict(Long partyId,String queryType,long stateId ,List<Long> districtIds);
	
	public List<Object[]> getCategoryIdsForParty(Long partyId,int firstResult,int maxResult,String queryType);
	public List<Object[]> getGalleriesForCategories(long partyId,int firstResult,int maxResult,String queryType,Long catId);
	
	public List<Object[]> getAllVideosDetailsForDistrict(Long partyId,int firstResult,int maxResult,String queryType,long stateId ,List<Long> districtIds);
	public List<Object[]> getAllVideosDetailsForState(Long partyId,int firstResult,int maxResult,String queryType,long stateId , long scopeId);
	
	public List<Object[]> getAllVideosOfParty(Long partyId,int firstResult,int maxResult,String queryType);
	public int getAllVideosOfPartyCount(Long partyId,int firstResult,int maxResult,String queryType);
	
	public List<Long> getGalleryIdsForSelectedParty(Long partyId);
	
	public List<Object[]> getNewsOfCandidate(Long candidateId,int firstResult,int maxResult,String queryType);
	
	public int getCountOfNewsOfCandidate(Long candidateId,int firstResult,int maxResult,String queryType);
	
	public int getResponseNewsCountOfCandidate(Long candidateId,String queryType,Long fileGallaryId);
	
	public List<Object[]> getLatestNewsResponses();
	
	public List<Object[]> getCandidateRelatedGallaries(Long candidateId,Long partyId,Date fromDate, Date toDate,String queryType);
	
	public List<Object[]> getCandidateRelatedCategories(Long candidateId,Long partyId,Date fromDate,Date toDate);
	
	public List<FileGallary> getLatestNewsResPonses(Integer startIndex, Integer maxIndex);
	
	public List<Object[]> getRespondedFilesCount(List<Long> gallaryIds,Long categoryId);
	
	public List<Object[]> getTotalCategories(Long partyId,String queryType);
	
	public List<String> getTotalKeywordsForKeywordSearch(Long partyId,String newsType);
	
}
