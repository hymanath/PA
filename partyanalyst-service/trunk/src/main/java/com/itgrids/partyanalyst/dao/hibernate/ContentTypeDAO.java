package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.model.ContentType;

public class ContentTypeDAO extends GenericDaoHibernate<ContentType,Long>implements IContentTypeDAO {

	public ContentTypeDAO()
	{
		super(ContentType.class);
	}
}
