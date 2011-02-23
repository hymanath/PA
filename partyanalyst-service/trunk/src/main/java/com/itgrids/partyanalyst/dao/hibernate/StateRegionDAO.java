package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.StateRegion;

public class StateRegionDAO extends GenericDaoHibernate<StateRegion, Long> implements
IStateRegionDAO {

	public StateRegionDAO() {
		super(StateRegion.class);
		
	} 
	@SuppressWarnings("unchecked")
	public List getStateRegionByType(Long stateId){
		return getHibernateTemplate().find("select model.regionName,model.stateRegionId " +
				" from StateRegion model where model.state.stateId = ?", stateId);
	}
	
	
	public Long getTotalRegionsInAState(Long stateId) {
		
		Long resultsCount = ( (Long) getSession().createQuery("select count(model.stateRegionId) from "+
				"StateRegion model where model.state.stateId = "+stateId).iterate().next() );
		
	 return resultsCount;
	}

}
