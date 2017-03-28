package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
	
	
	@SuppressWarnings("unchecked")
	public List getStateRegionByTypeForRegion(Long stateId,String region){
		
		StringBuilder str = new StringBuilder();
		str.append("select model.regionName,model.stateRegionId " +
				" from StateRegion model where model.state.stateId = :stateId");
		if(region.equalsIgnoreCase("Andhra"))
		{
		str.append(" and model.stateRegionId in (2,3)");
		}
		else
		{
			str.append(" and model.stateRegionId in (1)");
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateId", stateId);
		//query.setParameter("region", region);
		return query.list();
		
		
	}
	

}
