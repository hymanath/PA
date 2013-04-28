package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Locality;

public interface ILocalityDAO extends GenericDao<Locality, Long>{
	
	public List<Object[]> getAllLocalitiesForHamlet(Long userId , Long hamletId ,String type ,String queryCondition);
	public List<Object[]> getLocalitiesForWard(Long wardId , Long userId);

	public List<Object[]> getAllLocalitiesForHamlet(Long userId , Long hamletId );
}
