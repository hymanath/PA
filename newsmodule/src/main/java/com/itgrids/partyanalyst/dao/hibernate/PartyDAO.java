package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.model.Party;

public class PartyDAO extends GenericDaoHibernate<Party, Long> implements IPartyDAO{
	public PartyDAO(){
		super(Party.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesList()
	{
	  Query query = getSession().createQuery(" select model.partyId,model.shortName from Party model where model.isNewsPortal = 'Y' order by model.shortName");
	  return query.list();
	}
	
	public Long getPartyShortName(String partyShortName)
	{
		Query query = getSession().createQuery("select model.partyId from Party model where model.shortName = :partyShortName");
		query.setParameter("partyShortName", partyShortName);
		return (Long)query.uniqueResult();
	}
	public List<Object[]> getPartyNames(String partyIds)
	{
	  Query query = getSession().createQuery(" select model.partyId,model.shortName from Party model where model.partyId in("+partyIds+") order by model.shortName");
	  return query.list();
	}
	
	public List<String> getPartyShortNames(List<Long> partyIds){
		 Query query = getSession().createQuery(" select distinct model.shortName from Party model where model.partyId in(:partyIds) order by model.shortName");
		 query.setParameterList("partyIds", partyIds);
		 return query.list();
	}
	
	public List<Object[]> getPartyNames(Set<Long> partyIds){
		 Query query = getSession().createQuery(" select distinct model.shortName,model.partyId from Party model where model.partyId in(:partyIds) order by model.shortName");
		 query.setParameterList("partyIds", partyIds);
		 return query.list();
	}
	
	public List<Object[]> getPartiesListByStateId(List<Long> statesList)
	{
		
		Query  query = getSession().createQuery(" select model.partyId,model.shortName from Party model where model.isNewsPortal = 'Y' and model.partyRecognization='NP' or model.state.stateId in(:statesList) order by model.shortName");
	    query.setParameterList("statesList", statesList);
		
		return query.list();
	}
	
	public List<Object[]> getPartyDetails(List<Long> partyIds){
		 Query query = getSession().createQuery(" select distinct model.partyId,model.shortName from Party model where model.partyId in(:partyIds) order by model.shortName");
		 query.setParameterList("partyIds", partyIds);
		 return query.list();
	}
}
