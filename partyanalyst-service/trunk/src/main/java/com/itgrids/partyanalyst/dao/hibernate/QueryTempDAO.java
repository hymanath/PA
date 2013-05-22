package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IQueryTempDAO;
import com.itgrids.partyanalyst.model.QueryTemp;

public class QueryTempDAO extends GenericDaoHibernate<QueryTemp,Long> implements IQueryTempDAO{
	
	public QueryTempDAO(){
		super(QueryTemp.class);
	}
	
	public Integer deleteAll()
	{
		Query query = getSession().createQuery("delete from QueryTemp");
		return query.executeUpdate();
	}
}
