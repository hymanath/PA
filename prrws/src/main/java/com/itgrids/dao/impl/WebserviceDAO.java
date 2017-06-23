package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWebserviceDAO;
import com.itgrids.model.Webservice;

@Repository
public class WebserviceDAO extends GenericDaoHibernate<Webservice,Long> implements IWebserviceDAO{

	public WebserviceDAO()
	{
		super(Webservice.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getWebserviceIdByUrl(String url)
	{
		Query query = getSession().createQuery("SELECT model.webserviceId FROM Webservice model WHERE model.url = :url");
		query.setParameter("url",url);
		return query.list();
	}
}
