package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreInfo;



public interface ITdpCadreInfoDAO extends GenericDao<TdpCadreInfo, Long>{
	
	public Integer updateTdpCadreInfoTableByScheduler(String cadreType, String locationType);
	
	public Integer deleteTdpCadreInfoTableBeforeUpdate();
	
	public List<Object[]> getLocationWiseCadreRegisterCount(Set<Long> locationIds,String locationType,Long constituencyId,String type);

	public Long getTdpCadreCountForLocations(String userAccessType,List<Long> locationIdsList,String type,String locationType);
	
	public List<Object[]> getVoterCadreDetailsBySearchCriteria(Long stateId, String locationType,Long locationId);
}
