package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IContentDAO;
import com.itgrids.partyanalyst.model.Content;

public class ContentDAO extends GenericDaoHibernate<Content, Long> implements IContentDAO{

	public ContentDAO() {
		super(Content.class);
		
	}
	
	public List<Content> getContentTypes(){
		
		return getHibernateTemplate().find("from Content");
		
		
	}

}
