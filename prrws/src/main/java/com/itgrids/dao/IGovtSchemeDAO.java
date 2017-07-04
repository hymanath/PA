package com.itgrids.dao;

import java.util.List;


import org.appfuse.dao.GenericDao;
import com.itgrids.model.GovtScheme;

public interface IGovtSchemeDAO extends GenericDao<GovtScheme,Long>{
	
	public List<Object[]> getGovtSchemesDetails();
}
