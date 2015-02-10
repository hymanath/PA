package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.model.TirupatiByelection;

public class TirupatiByelectionDAO extends GenericDaoHibernate<TirupatiByelection, Long>{

	public TirupatiByelectionDAO() {
		super(TirupatiByelection.class);
		
	}
	

	public List<TirupatiByelection> getModelByType(Long typeId,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append("select * from model from TirupatiByelection model");
		if(typeId == 1)
		 str.append(" where model.clusterName = :type");
		else if(typeId == 2)
			 str.append(" where model.divisionName = :type");	
		Query query = getSession().createQuery(str.toString());	
		query.setParameter("type", type);
		return query.list();
		
		
	}
	

}
