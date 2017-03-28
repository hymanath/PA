package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.model.WebServiceBaseUrl;

public class WebServiceBaseUrlDAO extends GenericDaoHibernate<WebServiceBaseUrl, Long>  implements IWebServiceBaseUrlDAO{
	
	public WebServiceBaseUrlDAO()
	{
		super(WebServiceBaseUrl.class);
	}
	
	public String getBaseURLForAnApp(String appName)
	{
		Query query = getSession().createQuery("select model.url from WebServiceBaseUrl model where model.appName = :appName");
		query.setParameter("appName",appName);
		return (String)query.uniqueResult();
	}
	
	public WebServiceBaseUrl getBaseUrlDataForAnApp(String appName)
	{
		Query query = getSession().createQuery("select model from WebServiceBaseUrl model where model.appName = :appName");
		query.setParameter("appName",appName);
		return (WebServiceBaseUrl)query.uniqueResult();
	}
	public List<WebServiceBaseUrl> getBaseUrlsForAnApp(String appName)
	{
		Query query = getSession().createQuery("select model from WebServiceBaseUrl model where model.appName = :appName" +
				" and model.isEnabled ='Y' ");
		query.setParameter("appName",appName);
		return query.list();
	}
}
