package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITirupatiByelectionDAO;
import com.itgrids.partyanalyst.model.TirupatiByelection;

public class TirupatiByelectionDAO extends GenericDaoHibernate<TirupatiByelection, Long> implements ITirupatiByelectionDAO{

	public TirupatiByelectionDAO() {
		super(TirupatiByelection.class);
		
	}
	

	public List<TirupatiByelection> getModelByType(Long typeId,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model from TirupatiByelection model");
		if(typeId == 1)
		 str.append(" where model.clusterName = :type");
		else if(typeId == 2)
			 str.append(" where model.divisionName = :type");	
		Query query = getSession().createQuery(str.toString());	
		if(!type.isEmpty())
		query.setParameter("type", type);
		return query.list();
		
		
	}
	public List<TirupatiByelection> getModelByboothIds(List<Long> boothIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model from TirupatiByelection model where model.boothId2015 in(:boothIds)");
		Query query = getSession().createQuery(str.toString());	
		query.setParameterList("boothIds", boothIds);
		return query.list();
		
		
	}
	
	
	public List<String> getDivisionNames()
	{
		Query query = getSession().createQuery("select distinct model.divisionName from TirupatiByelection model");
		return query.list();
	}
	
	public List<String> getClusterNames()
	{
		Query query = getSession().createQuery("select distinct model.clusterName from TirupatiByelection model");
		return query.list();
	}
	

}
