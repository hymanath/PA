package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWebServiceDataDAO;
import com.itgrids.model.WebServiceData;

@Repository
public class WebServiceDataDAO extends GenericDaoHibernate<WebServiceData,Long> implements IWebServiceDataDAO{

	public WebServiceDataDAO()
	{
		super(WebServiceData.class);
	}
	
	@Override
	public List<Object[]> getRfidTrackingOverAllTargetsData(Long webserviceId) {

		
		Query query = getSession().createQuery("select  model.responceData from WebServiceData model where model.isDeleted ='N' ");
		query.setParameter("webserviceId", webserviceId);
		return query.list();
	}
	
}