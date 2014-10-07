package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidatePartyFile;
import com.itgrids.partyanalyst.model.File;

public interface ICandidatePartyFileDAO extends GenericDao<CandidatePartyFile, Long>{
	
	public List<Object[]> getCandidatesNewsCount();
	
	public List<Object[]> getCandidateNamesByFileId(Long fileId);
	
	public List<Object[]> getCandidateNamesByFileIds(Set<Long> fileIds);
	
	public List<Object[]> getSourceCandidates();
	
	public List<Object[]> getDestinationCandidates();
	
	public List<File> getCandidateFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate);
	
	public List<Long> getFilesIdsByCandidateFileId(List<Long> candidateFileId);
	 
	public List<Object[]> getKeywordsCountByFileIds(Set<Long> fileIds);
	
	public List<Object[]> getSourcePartyCommentsOnly(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getSourcePartyCandidateComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getSourcePartyComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getDestinationPartyCommentsOnly(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getDestinationPartyComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getDestinationPartyCandidateComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getSelectedNewsBySearchCriteria(String queryStr ,Date fromDate,Date toDate, Integer startIndex , Integer maxIndex);
	
	public Long getSelectedNewsCountBySearchCriteria(String queryStr,Date fromDate,Date toDate);

	public List<CandidatePartyFile> getInvolvedCandidatesInANews(Long fileId);
	
	//public int deleteCandidatePartyFileById(String type,Long candidatePArtyFileId);
	
	public List<CandidatePartyFile> getdetailsBySourceCandiIdForFile(Long fileId,Long sourceCandId,String type);
	
	public List<Long> getCandidatePartyFileIds(Long fileId);
	
	public void deleteCandidatePartyFiles(Long fileId);

	public Long getNewsCountBySelectedCriteria(String queryString,Date fromDate,Date toDate,Long partyId,Long candidateId);
	
	public List<Object[]> getNewsCount(String queryString,Date fromDate,Date toDate,Long partyId,Long candidateId);
	
	public List<Object[]> getNewsByCriteria(String queryString,Date fromDate,Date toDate,Long partyId,Long candidateId,Integer startIndex,Integer maxIndex);
	
	public Long tdpEffectOnOtherPartiesTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	public List<Object[]> tdpEffectOnOtherPartiesBenifitWiseCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	public List<Object[]> tdpEffectOnOthersPartyWiseTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	public List<Object[]> tdpEffectOnOthersPartyWiseBenifitCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	
	public Long otherPartiesOnTdpEffectTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	public List<Object[]> otherPartiesEffectOnTdpBenifitWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	public List<Object[]> otherPartiesWiseEffectOnTdpTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	public List<Object[]> otherPartiesWiseEffectOnTdpBenifitCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate);
	
	public List<Object[]> getPoliticalActivitiesNews(Date fromDate,Date toDate,List<Long> categoryIds,List<Long> districtIds,Integer startIndex,Integer maxIndex);
	
	public Long getPoliticalActivitiesNewsCount(Date fromDate,Date toDate,List<Long> categoryIds,List<Long> districtIds);
	
	public List<Long> getDestinationDetails(Long partyId,Long fileId);
	
	public List<Long> getSourceDetails(Long partyId,Long fileId);
	
	public List<Object[]> getAllPoliticalActivitiesCount(Date fromDate,Date toDate,List<Long> locationIds,Long locationType,List<Long> partyIds,Long categoryId);
	
	public List<Object[]> getAllElectionCampanionCount(Date fromDate,Date toDate,List<Long> locationIds,Long locationType,List<Long> partyIds,Long categoryId);
	
	public List<Object[]> getAllPartyIds(Date fromDate,Date toDate);
	
	public List<Object[]> getTotalCounts(Date fromDate,Date toDate,String queryStr);
	
	public List<Object[]> getAllCounts(Date fromDate,Date toDate,String queryStr,Long partyId);
	
	public List<Object[]> getCandidateGroupWiseBenifit(Date fromDate,Date toDate,Long stateId,Long groupId,Long partyId);
	
	public List<Object[]>  getCandidateGroupBenifitWiseNews(Date fromDate,Date toDate,Long candidateId,Long benfitId,int startIndex,int maxIndex);
	
	public Long  getCandidateGroupBenifitWiseNewsCount(Date fromDate,Date toDate,Long candidateId,Long benfitId);
	
	public List<Object[]> getDistrictWiseBenifit(Date fromDate,Date toDate,Long stateId,Long partyId);
	
	public List<Object[]> getAssemblyWiseBenifit(Date fromDate,Date toDate,Long stateId,Long partyId);
	
	public List<Object[]> getParliamentWiseBenifit(Date fromDate,Date toDate,Long stateId,Long partyId);
	
	public Long getAssemblyBenifitNewsCount(Date fromDate,Date toDate,Long constituencyId,Long benfitId,Long partyId);
	  
	public List<Object[]>  getAssemblyBenifitNews(Date fromDate,Date toDate,Long constituencyId,Long benfitId,Long partyId,int startIndex,int maxIndex);
	
	public Long getParliamentBenifitNewsCount(Date fromDate,Date toDate,Long pcID,Long benfitId,Long partyId);
	
	public List<Object[]>  getParliamentBenifitNews(Date fromDate,Date toDate,Long pcID,Long benfitId,Long partyId,int startIndex,int maxIndex);
	
	public Long getDistrictBenifitNewsCount(Date fromDate,Date toDate,Long districtId,Long benfitId,Long partyId);
	
	public List<Object[]>  getDistrictBenifitNews(Date fromDate,Date toDate,Long districtId,Long benfitId,Long partyId,int startIndex,int maxIndex);
	
	 	 
	 public List<Object[]>  getDistrictBenifitNewsDetails(Date fromDate,Date toDate,List<Long> districtId,Long benfitId,Long partyId);
	 
	 public List<Object[]>  getParliamentBenifitNewsDetails(Date fromDate,Date toDate,List<Long> pcID,Long benfitId,Long partyId);
	
	 public List<Object[]>  getAssemblyBenifitNewsDetails(Date fromDate,Date toDate,List<Long> constituencyId,Long benfitId,Long partyId);
	
	 public List<Object[]>  getCandidateGroupBenifitWiseNewsDetails(Date fromDate,Date toDate,List<Long> candidateId,Long benfitId);
	 
}
