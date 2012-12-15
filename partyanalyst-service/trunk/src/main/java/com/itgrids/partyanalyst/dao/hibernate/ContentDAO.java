package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IContentDAO;
import com.itgrids.partyanalyst.model.Content;

public class ContentDAO extends GenericDaoHibernate<Content, Long> implements IContentDAO{

	public ContentDAO() {
		super(Content.class);
		
	}

}
