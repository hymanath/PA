package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UrbanLocality;

public interface IUrbanLocalityDAO extends GenericDao<UrbanLocality, Long>{

	public List<Object[]> getUrbanLocalitiesForMuncipality(Long lebId);
}
