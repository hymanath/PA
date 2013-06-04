package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ContentType;

public interface IContentTypeDAO extends GenericDao<ContentType,Long>{
	
	public Object getContentTypeByType(String contentType);

}
