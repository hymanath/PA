package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.model.LocationInfo;

public class LocationInfoDAO extends GenericDaoHibernate<LocationInfo, Long> implements ILocationInfoDAO{

	public LocationInfoDAO() {
		super(LocationInfo.class);
	}
    
	public List<Object[]> getAssemblyWiseTotalCounts(List<Long> levelIds,List<Long> constIds){
	
		Query query=getSession().createQuery(" " +
				  " select   model.scopeValue,sum(model.count) " +
				  " from     LocationInfo model " +
				  " where    model.scopeId =4 and model.scopeValue in (:constIds) and model.levelId in (:levelIds)" +
				  " group by model.scopeValue ");
		query.setParameterList("constIds",constIds);
		query.setParameterList("levelIds",levelIds);
		return query.list();
	}
}
