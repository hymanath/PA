package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.CustomPage;

public interface ICustomPageDAO extends GenericDao<CustomPage, Long>{
	
	public int updateCustompage( Long customPageId,String customPageName,Long CustomPageType);

}
