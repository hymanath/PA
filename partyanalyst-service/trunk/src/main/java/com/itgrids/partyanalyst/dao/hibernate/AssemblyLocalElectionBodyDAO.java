package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;


public class AssemblyLocalElectionBodyDAO extends GenericDaoHibernate<AssemblyLocalElectionBody, Long> implements IAssemblyLocalElectionBodyDAO {

	public AssemblyLocalElectionBodyDAO() {
		super(AssemblyLocalElectionBody.class);
		 
	}

	@SuppressWarnings("unchecked")
	public List findByConstituencyId(Long constituencyId, String year) {
		Object[] params = {constituencyId,year};
		return getHibernateTemplate().find("select model.localElectionBody.localElectionBodyId, model.localElectionBody.name , model.localElectionBody.electionType.electionType, model.isPartial  from AssemblyLocalElectionBody model " +
				"where model.constituency.constituencyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2 where model2.year <= ?)",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(List<Long> constituencyIds) {
		StringBuilder query = new StringBuilder();
		query.append("select model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model " +
				" where model.constituency.constituencyId in( :constituencyIds)" +
				" and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2)");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list();
	}
		
}
