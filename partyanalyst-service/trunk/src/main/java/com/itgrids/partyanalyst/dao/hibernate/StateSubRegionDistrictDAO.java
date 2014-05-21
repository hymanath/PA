package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStateSubRegionDistrictDAO;
import com.itgrids.partyanalyst.model.StateSubRegionDistrict;

public class StateSubRegionDistrictDAO extends GenericDaoHibernate<StateSubRegionDistrict, Long> implements IStateSubRegionDistrictDAO{

	public StateSubRegionDistrictDAO() {
		super(StateSubRegionDistrict.class);
	}

	public List<Object[]> getAssemblyConstituenciesBySubRegionIds(List<Long> subRegionId){
		
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select model1.constituencyId,model1.name from StateSubRegionDistrict model2 ,Constituency model1 where model2.district.districtId = model1.district.districtId and " +
				" model1.deformDate is null and model1.electionScope.electionScopeId = 2 and model2.stateSubRegion.stateSubRegionId in (:subRegionId) ");
		Query query = getSession().createQuery(queryString.toString());
		//query.setParameter("electionScopeId", electionScopeId);
		query.setParameterList("subRegionId", subRegionId);
		return query.list();
	}
	
}
