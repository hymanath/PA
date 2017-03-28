package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreInfo;



public interface ITdpCadreInfoDAO extends GenericDao<TdpCadreInfo, Long>{
	
	public Integer updateTdpCadreInfoTableByScheduler(String cadreType, String locationType,Long enrollmentYearId);
	
	public Integer deleteTdpCadreInfoTableBeforeUpdate(Long enrollmentYearId);
	
	public List<Object[]> getLocationWiseCadreRegisterCount(Set<Long> locationIds,String locationType,Long constituencyId,String type,Long tdpCommitteeEnrollMentYearId);

	public Long getTdpCadreCountForLocations(String userAccessType,List<Long> locationIdsList,String type,String locationType,Long tdpCommitteeEnrollMentYearId);
	
	public List<Object[]> getVoterCadreDetailsBySearchCriteria(Long stateId, String locationType,List<Long> locationIdsList,Long tdpCommitteeEnrollMentYearId);
}
