package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.model.BoothLocalBodyWard;

public class BoothLocalBodyWardDAO extends GenericDaoHibernate<BoothLocalBodyWard, Long> implements IBoothLocalBodyWardDAO{

	public BoothLocalBodyWardDAO(){
		super(BoothLocalBodyWard.class);
	}

	public Integer deleteRecords(List<Long> boothIds, Long wardId) {
		StringBuilder query = new StringBuilder();
		query.append("delete from BoothLocalBodyWard model ");
		query.append("where model.localBodyWard.constituencyId = ?");
		query.append(" and model.booth.boothId in (:boothIds) ");
			
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, wardId);
		queryObject.setParameterList("boothIds", boothIds);	
		
		return queryObject.executeUpdate();
	}
	
}
