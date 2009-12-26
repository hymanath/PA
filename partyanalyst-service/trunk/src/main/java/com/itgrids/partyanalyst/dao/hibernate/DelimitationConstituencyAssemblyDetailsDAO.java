package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyAssemblyDetails;

public class DelimitationConstituencyAssemblyDetailsDAO extends GenericDaoHibernate<DelimitationConstituencyAssemblyDetails, Long> implements IDelimitationConstituencyAssemblyDetailsDAO{

	public DelimitationConstituencyAssemblyDetailsDAO(){
		super(DelimitationConstituencyAssemblyDetails.class);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findAssemblyConstituencies(
			Long parliamentConstituencyId, Long electionYear) {
		Object[] params = {parliamentConstituencyId, electionYear};
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId = ? and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1 where model1.year <=?)",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findAssemblyConstituenciesByDelimitationConstituencyId(Long delimitationConstituencyId){
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.delimitationConstituencyID = ?",delimitationConstituencyId);
	}
}
