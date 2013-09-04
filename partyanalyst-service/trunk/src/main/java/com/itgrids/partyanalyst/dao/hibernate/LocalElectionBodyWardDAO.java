package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.model.LocalElectionBodyWard;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocalElectionBodyWardDAO extends GenericDaoHibernate<LocalElectionBodyWard, Long>
		implements ILocalElectionBodyWardDAO {

	public LocalElectionBodyWardDAO() {
		super(LocalElectionBodyWard.class);
	}
	
	@SuppressWarnings("unchecked")
	public List getLocalBodyElectionInfo(List<Long> constituencyIds){
		StringBuilder locationQuery = new StringBuilder();		
		locationQuery.append("select model.constituency.constituencyId,");
		locationQuery.append("model.wardName,");
		locationQuery.append("model.cirlceName, ");
		locationQuery.append("model.cirlceZone ");
		locationQuery.append("from LocalElectionBodyWard model where ");			
		locationQuery.append("model.constituency.constituencyId in (:constituencyIds)");
		Query queryObject = getSession().createQuery(locationQuery.toString());		
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List findWardIdByWardNameAndLocalelectionId(String name,
			Long localBodyId) {
		
		Object[] params = {name,localBodyId};
		return getHibernateTemplate().find("select model.constituency.constituencyId from LocalElectionBodyWard model where "+
				"model.wardName = ? and model.constituency.localElectionBody.localElectionBodyId = ?",params);
	}
	
	public List findWardName(Long wardId) {
		
		//	Object[] params = {name,localBodyId};
			return getHibernateTemplate().find("select model.wardName  from LocalElectionBodyWard model where "+
					"model.constituency.constituencyId = ?",wardId);
		}
	
	public List<Object[]> getWardsByConstituency(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.constituency.constituencyId," +
				" model.constituency.name,model.wardName from LocalElectionBodyWard model where " +
				" model.constituency.constituencyId in (:constituencyIds)");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
}
