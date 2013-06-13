package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CustomVoter;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.Voter;

public interface ICustomVoterDAO extends GenericDao<CustomVoter,Long>{
	
	public List<Object[]> getVoterGroupNamesByVoterIdsList(List<Long> voterIdsList);
	
	public void removeCustomVoterDetails(Long customVoterId);
	
	public List<CustomVoter> getCustomVoterByVoterIdAndUserId(Long voterId , Long customGroupId);
	
	public List<Long> getCustomGroupIdByVoterIdAndUserId(Long voterId , Long userId);
	
	public List<Object[]> getAllVotersGroups(List<Long> voterIds , Long userId);
	
	public List<Long> getCustomVoterIdByVoterIdAndUserId(Long voterId , Long userId);
	
	public List<Object[]> getVotersInfoBycustomVoterGroupId(Long customVoterGroupId,Long userId,Integer startIndex,
			Integer maxRecords, String order, String columnName,Long publicationDateId);
	
	public List<Object[]> getVotersInfoBycustomVoterGroupId(Long customVoterGroupId,Long userId);
	
	public List<Long> getCountBycustomvoterGroupId(Long customVoterGroupId,Long userId,Long publicationDateId);

	public List<Object[]> getCasteWiseCustomVotersCount(Long customVoterGroupId, Long userId);
	
	public List<Object[]> getCustomVotersCount(Long customVoterGroupId, Long userId,Long publicationId);
	
	 public List<Long> getInfluencingPeopleCountByCustomVoter(Long userId,Long publicationId,Long customVoterGroupId);
	 
	 public List<Long> getPoliticianCountByCustomVoter(Long userId,Long publicationId,Long customVoterGroupId);
	 
	 public List<Long> getCadrePeopleCountByCustomVoter(Long userId,Long publicationId,Long customVoterGroupId);
	 
	 public List<Cadre> getCadreDetails(Long userId,Long publicationId,Long customVoterGroupId,Integer startIndex,Integer maxIndex,String order,String columnName);
	 
	 public List<InfluencingPeople> getInfluencingPeopleDetails(Long userId,Long publicationId,Long customVoterGroupId,Integer startIndex,Integer maxIndex,String order,String columnName);
	 
	 public List<Candidate> getPoliticanDetails(Long userId,Long publicationId,Long customVoterGroupId,Integer startIndex,Integer maxIndex,String order,String columnName);
	 public List<Object[]> getCustomGroupWiseVotersDetailsForCaste(Long userId,String areaType, Long locationValue);
		
	 public List<Voter> getCasteWiseCustomVoterDetails(Long casteStateId,Long casteId,Long customVoterGroupId,Long userId);
	 public List<Object[]> getVotersCountForPartyByCustomGroup(Long userId,Long custGroupId);
	 
	  public Long getTotalVotersByCustomGroupId(Long custGroupId);
		
	 public List<Object[]> getCustomVoterCount(Long customVoterGroupId,Long publicationDateId,Long userId);
		
	 public List<Long> getImpFamiles(Long customVoterGroupId,Long publicationDateId,String queryString);
	 public List<Object[]> getVotersInfoBycustomVoterGroupId1(Long customVoterGroupId,Long userId,Integer startIndex,
				Integer maxRecords, String order, String columnName,Long publicationDateId);	
}
