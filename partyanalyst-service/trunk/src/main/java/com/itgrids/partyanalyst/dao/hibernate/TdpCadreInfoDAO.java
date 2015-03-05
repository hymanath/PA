package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreInfo;


public class TdpCadreInfoDAO extends GenericDaoHibernate<TdpCadreInfo, Long> implements ITdpCadreInfoDAO{

	public TdpCadreInfoDAO() {
		super(TdpCadreInfo.class);
	}
	
	
	public List<Object[]> getLocationWiseCadreRegisterCount(Set<Long> locationIds,String locationType,Long constituencyId,String type){
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count
	
		queryStr.append("select model.locationId,model.count from TdpCadreInfo model where " +
				" model.locationId in (:locationIds) and  model.locationType = :locationType " +
				" and model.type = :type");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("locationIds", locationIds);
		query.setParameter("type", type);
		query.setParameter("locationType", locationType);
		
		return query.list();
	}
	
	
}
