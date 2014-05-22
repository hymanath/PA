package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStateSubRegionDAO;
import com.itgrids.partyanalyst.model.StateSubRegion;

public class StateSubRegionDAO extends GenericDaoHibernate<StateSubRegion, Long>implements IStateSubRegionDAO{

	public StateSubRegionDAO() {
		super(StateSubRegion.class);
	}
	
	public List<Object[]> getStateRegionsBySubRegionIds(List<Long> subRegionIds){		
		Query query = getSession().createQuery(" select distinct model.stateRegion.stateRegionId ,model.stateRegion.regionName from StateSubRegion model where model.stateSubRegionId in (:subRegionIds)");
		query.setParameterList("subRegionIds", subRegionIds);
		return query.list();
	}
	
	public List<Object[]> getStateSubRegionsByRegionId(Long stateRegionId){		
		Query query = getSession().createQuery(" select distinct model.stateSubRegionId ,model.name from StateSubRegion model where  model.stateRegion.stateRegionId in (:stateRegionId)");
		query.setParameter("stateRegionId", stateRegionId);
		return query.list();
	}
	
}
