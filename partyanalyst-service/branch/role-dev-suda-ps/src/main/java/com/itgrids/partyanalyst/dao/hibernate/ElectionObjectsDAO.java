/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 12, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IElectionObjectsDAO;
import com.itgrids.partyanalyst.model.Election;


public class ElectionObjectsDAO extends GenericDaoHibernate<Election, Long> implements
		IElectionObjectsDAO {
    
	/*  Returns The List Election Objects 
	 * 
	 */
	
	public ElectionObjectsDAO() {
		super(Election.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<Election> findElections(Long electionType_id, Long country_id,
			Long state_id) {
		
		Query queryObject = getSession().createQuery("from Election as model where model.electionScope.electionScopeId  in ( select electionScopeId from ElectionScope as newmodel where newmodel.electionType.electionTypeId = ? and newmodel.country.countryId = ? and newmodel.state.stateId = ?)");
		 queryObject.setParameter(0, electionType_id);
		 queryObject.setParameter(1, country_id);
		 queryObject.setParameter(2, state_id);
		 
		return queryObject.list(); 
	}

	
	@SuppressWarnings("unchecked")
	public List<Election> findElections(Long electionType_id, Long country_id) {
		
		Query queryObject = getSession().createQuery("from Election as model where model.electionScope.electionScopeId  in ( select electionScopeId from ElectionScope as newmodel where newmodel.electionType.electionTypeId = ? and newmodel.country.countryId = ? and newmodel.state.stateId = ?)");
		 queryObject.setParameter(0, electionType_id);
		 queryObject.setParameter(1, country_id);
		 queryObject.setParameter(2, null);
		 
		return queryObject.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Election> findElections(Long state_id) {
		
		Query queryObject = getSession().createQuery("from Election as model where model.electionScope.state.stateId = ?");
		  queryObject.setParameter(0, state_id);
		 		 
		return queryObject.list();
	}	
	
	@SuppressWarnings("unchecked")
	public List findLatestParliamentaryElectionYear(Long state_id) {
		
		Query queryObject = getSession().createQuery("select max(model.electionYear) from Election as model where model.electionScope.state.stateId = ? and model.electionScope.electionType.electionType='Parliament'");
		  queryObject.setParameter(0, state_id);
		 		 
		return queryObject.list();
	}	
}
