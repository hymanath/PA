package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterFlag;

public interface IVoterFlagDAO extends GenericDao<VoterFlag, Long>{
	public Integer deleteVoterFlag(Long flagId);
	
	public List<Object[]> getFlagWiseVotersCountByLocationId(Long locationId,Long constituencyId,String locationType,Long publicationDateId);
	
	public List<Object[]> getFlagWiseVotersCountByLocationIdForHamlet(Long locationId,Long constituencyId,String locationType,Long publicationDateId);
	
	
	public List<Object[]> getFlagInfoByBoterIds(List<Long> voterIds);
	
	public List<Long> getFlagsByVoterIds(Long voterId);
	
	public List<Object> getvoterFlagByFlagIdAndUser(Long flagId,Long userId,Long voterId);
	
	public Integer deleteVoterFlagById(Long voterFlagId);
	
    public List<Long> getFlagCountForSelectedLevel(List<Long> boothIds ,long constituencyId,Long userId);
    
    public List<Long> getCountForSelectedTypeInHamlet(Long hamletId,Long userId,String selLevel);
    
    
	
	 public List<Object[]> getFlagVoterDetailsByLocationId(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId, Integer startIndex,
				Integer maxRecords);
	 public List<Object[]> getFlagVoterDetailsForHamlet(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId, Integer startIndex,
				Integer maxRecords);
	 
	 public List getFlagVoterDetailsByLocationIdCount(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId
				);
	 
	 public List getFlagVoterDetailsForHamletCount(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId
				);
	 
	 public List<Long> checkFlagExistanceForVoter(Long flagId,Long voterId,Long userId);

	 
}
