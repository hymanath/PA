package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Content;

public interface IContentDAO extends GenericDao<Content, Long>{
	
	public List<Content> getContentTypes();

}
