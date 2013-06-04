package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.model.ContentType;

public class ContentTypeDAO extends GenericDaoHibernate<ContentType,Long>implements IContentTypeDAO {

	public ContentTypeDAO()
	{
		super(ContentType.class);
	}
	
	public Object getContentTypeByType(String contentType)
	{
		Query query = getSession().createQuery("select model from ContentType model where model.contentType = ?");
		query.setParameter(0,contentType);
		return query.uniqueResult();
	}
}
