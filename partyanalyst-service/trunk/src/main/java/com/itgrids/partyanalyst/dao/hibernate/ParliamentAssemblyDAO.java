package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.model.ParliamentAssembly;

public class ParliamentAssemblyDAO extends GenericDaoHibernate<ParliamentAssembly, Long> implements
		IParliamentAssemblyDAO {
	public ParliamentAssemblyDAO() {
		super(ParliamentAssembly.class);
	}
	public List<Long> getAssemblyConstituencyforParliament(List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.assembly.constituencyId from ParliamentAssembly model where model.parliamentAssembly.constituencyId in (:userAccessLevelValues)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		return query.list();  
		
	}
	public List<Object[]> getAssemblyConstituencyforByPaliament(List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.parliamentAssembly.constituencyId,model.assembly.constituencyId,model.assembly.name from ParliamentAssembly model where model.parliamentAssembly.constituencyId in (:userAccessLevelValues)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		return query.list();  
	}
}
