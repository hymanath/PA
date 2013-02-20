package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterModification;

public interface IVoterModificationDAO extends GenericDao<VoterModification,Long>{
	
	public List<Object[]> getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList,Long ageFrom, Long ageTo);
}
