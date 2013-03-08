package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;

public interface IVoterModificationAgeInfoDAO extends GenericDao<VoterModificationAgeInfo,Long>
{
	public Integer deleteVoterModicationAgeInfoById(List<Long> voterModificationInfoId);
	
	public List<Object[]> getGenderWiseVoterModificationsBetweenPublications(Long locationLvl,Long locationValue,Long constituencyId,List<Long> publicationIdsList,Set<Long> ageRangeIds);

}
