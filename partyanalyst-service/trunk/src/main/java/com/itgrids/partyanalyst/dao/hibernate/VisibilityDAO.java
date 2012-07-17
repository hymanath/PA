package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVisibilityDAO;
import com.itgrids.partyanalyst.model.Visibility;

public class VisibilityDAO extends GenericDaoHibernate<Visibility,Long> implements IVisibilityDAO{

	public VisibilityDAO()
	{
		super(Visibility.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Visibility> getVisibilityByVisibilityType(String visibilityType)
	{
		Query query = getSession().createQuery("from Visibility model where model.type=?");
		query.setParameter(0, visibilityType);
		return query.list();
	}
	
}
