package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.RegionScopesProblemType;

public interface IRegionScopesProblemTypeDAO extends GenericDao<RegionScopesProblemType, Long>{
	
	public List<Object[]> getProblemTypesByRegionScopeId(Long regionScopeId);

}
