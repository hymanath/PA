package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomPageDAO;
import com.itgrids.partyanalyst.model.CustomPage;

public class CustomPageDAO extends GenericDaoHibernate<CustomPage, Long> implements ICustomPageDAO{
	
	public CustomPageDAO()
	{
		super(CustomPage.class);
	}
	
	@SuppressWarnings("unchecked")
	public int updateCustompage( Long customPageId,String customPageName,Long CustomPageType)
	{
		StringBuilder query = new StringBuilder();
		query.append("Update CustomPage model set model.name = ? , model.customPageType.customPageTypeId = ? where model.customPageId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setString(0, customPageName);
		queryObject.setLong(1, CustomPageType);
		queryObject.setLong(2, customPageId);
		
		return queryObject.executeUpdate();
		
	}

}
