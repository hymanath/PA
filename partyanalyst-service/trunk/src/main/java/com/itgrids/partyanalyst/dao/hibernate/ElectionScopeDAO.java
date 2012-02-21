package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionScopeDAO extends GenericDaoHibernate<ElectionScope, Long> implements IElectionScopeDAO{

	public ElectionScopeDAO() {
		super(ElectionScope.class);
	}

	public List<ElectionScope> findByCountryStateId(Object countryStateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ElectionScope> findByPropertyElectionTypeId(final Object value) {
		return getHibernateTemplate().find("from ElectionScope as model where model.electionType.electionTypeId=?",value);
	}

	@SuppressWarnings("unchecked")
	public List<ElectionScope> findByTypeIdCountryIdStateId(Long typeId, Long countryID, Long stateID){
		Long[] params = {typeId, countryID, stateID};
		List<ElectionScope> list = getHibernateTemplate().find("from ElectionScope as model where model.electionType.electionTypeId=? and model.country.countryId= ? and model.state.stateId=?",params);
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public ElectionScope findByElectionTypeCountry(ElectionType electionType, Country country){
		ElectionScope electionScope=null;
		Query query = getSession().createQuery("from ElectionScope as model where model.country.countryId=? and model.electionType.electionTypeId=?");
		query.setParameter(0, country.getCountryId());
		query.setParameter(1, electionType.getElectionTypeId());
		List<ElectionScope> list= query.list();
		if(list!=null && list.size()>0){
			electionScope = list.get(0);
		}
		return electionScope;
	}
	
	@SuppressWarnings("unchecked")
	public ElectionScope findByElectionTypeCountryState(ElectionType electionType, Country country,State state){
		ElectionScope electionScope=null;
		Query query = getSession().createQuery("from ElectionScope as model where model.country.countryId=? and model.electionType.electionTypeId=? and model.state.stateId=?");
		query.setParameter(0, country.getCountryId());
		query.setParameter(1, electionType.getElectionTypeId());
		query.setParameter(2, state.getStateId());
		List<ElectionScope> list= query.list();
		if(list!=null && list.size()>0){
			electionScope = list.get(0);
		}
		
		return electionScope;
	}

	@SuppressWarnings("unchecked")
	public List<ElectionScope> findByPropertyElectionTypeIdandStateId(Long electionTypeId,Long stateId) {
		Long[] params = {electionTypeId,stateId};
		List<ElectionScope> list = getHibernateTemplate().find("from ElectionScope as model where model.electionType.electionTypeId=? and model.state.stateId = ?",params);
		return list;
	}

	public List getElectionScopes() {
		return getHibernateTemplate().find("select model.electionScopeId, model.electionType.electionType from ElectionScope model");
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionScope> getElectionScopes(Long stateId) {
		return getHibernateTemplate().find("select distinct model from ElectionScope model "+
				"where model.state.stateId = ? or model.state is null",stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionScope> getElectionScopeForAElectionType(Long stateId,String electionType,Long countryId){
		Object[] params = {stateId,electionType,countryId};
		return getHibernateTemplate().find("from ElectionScope model where model.state.stateId = ? and "+
				"model.electionType.electionType = ? and model.country.countryId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ElectionScope> getElectionScopeForAElectionType(String electionType,Long countryId){
		Object[] params = {electionType,countryId};
		return getHibernateTemplate().find("from ElectionScope model where model.electionType.electionType = ? and " +
				                  "model.country.countryId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllStatesAndTheirIds(String electionType){
		return getHibernateTemplate().find("select distinct model.state.stateId,model.state.stateName " +
				"from ElectionScope model where model.electionType.electionType = ? order by model.state.stateId asc",electionType);
	}
	
	public List<ElectionScope> findByTypeIdStateId(Long typeId,Long stateID){
		StringBuilder query = new StringBuilder();
		query.append(" from ElectionScope as model where model.electionType.electionTypeId = :typeId ");
		if(stateID != null && stateID >0l)
			query.append(" and model.state.stateId = :stateID ");	
		Query queryObject = getSession().createQuery(query.toString());		
		queryObject.setLong("typeId",typeId);
		if(stateID != null && stateID >0l)
		queryObject.setLong("stateID", stateID);
		return queryObject.list();		
	}
}
 

