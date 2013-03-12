package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterModification;

public interface IVoterModificationDAO extends GenericDao<VoterModification,Long>{
	
	public List<Object[]> getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList,Long ageFrom, Long ageTo);
	
	public List<Object[]> getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getModifiedVotersInALocationBetweenPublucations(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList,String status);
	
	public List<Long> getModifiedVotersByPartNo(String partNo,Long constituencyId, Long publicationDateId, String status);
	
	public List<Long> getModifiedVotersByConstituency(Long constituencyId, Long publicationDateId, String status);
	

	
	public List<Object[]> getConstitunecyGenderWiseVoterModificationsForEachPublicationByMandal(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getConstitunecyGenderWiseVoterModificationsForEachPublicationByLocalElectionBody(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getMandalGenderWiseVoterModificationsForEachPublicationByPanchayat(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getLocalElectionBodyGenderWiseVoterModificationsForEachPublicationByWard(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList);
			
	public List<Object[]> getPanchayatGenderWiseVoterModificationsForEachPublicationByBooth(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getWardGenderWiseVoterModificationsForEachPublicationByBooth(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList);
	
	public List<Object[]> getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId,Long ageFrom, Long ageTo, String queryStr);
	
	public List<Object[]> getGenderWiseVoterModificationByPublicationId(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId, String queryString);
	
	public Integer deleteVoterModifiedDataByCOnstituencyId(Long constituencyId,Long publicationDateId);
	
	
}
