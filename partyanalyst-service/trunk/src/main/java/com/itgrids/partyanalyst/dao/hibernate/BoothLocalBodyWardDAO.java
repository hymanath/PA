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
		query.append("where model.booth.boothId in (:boothIds) ");
		query.append("and model.localBodyWard.constituencyId = ?");	
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameterList("boothIds", boothIds);	
		queryObject.setParameter(0, wardId);
		return queryObject.executeUpdate();
	}
	
}
