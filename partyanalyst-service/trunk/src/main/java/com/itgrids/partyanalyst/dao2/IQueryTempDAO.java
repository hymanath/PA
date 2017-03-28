package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.QueryTemp;

public interface IQueryTempDAO extends GenericDao<QueryTemp,Long>{
	
	public Integer deleteAll();
}
