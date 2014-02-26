package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAddressDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.model.Address;
import com.itgrids.partyanalyst.model.PartyTrends;

public class PartyTrendsDAO extends GenericDaoHibernate<PartyTrends, Long> implements IPartyTrendsDAO{

	public PartyTrendsDAO() {
		super(PartyTrends.class);
	}
	@Override
	public List<?> loadConst() {
		
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}
	@Override
	public List<?> loadEntitiesForXl(List<Long> constIds) {
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name,model.name,model.pervTrenzWt,model.prpWt,model.youngVotersWt,model.totalWt  from   PartyTrends model  where model.constituency.constituencyId in(:constIds)  group by  model.partyTrendsId  order by model.constituency.name ");
	
		query.setParameterList("constIds", constIds);
		return query.list();
		
	}
	public List<Object[]> findAssemblyConstituenciesForSimaAndra(Long electionScopeId,Long staetId,List<String> areas,List<Long> districIds) {
		
		Query query = getSession().createQuery("select model.constituencyId , model.name ,model.areaType from Constituency model   " +
				"  where model.state.stateId= :stateId  and model.electionScope.electionScopeId=:electionScopeId  and  model.areaType in (:areas) and model.deformDate is null and model.district.districtId not in(:districIds)  "   +
				"order by model.name");
		
		
		//query.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		query.setParameter("electionScopeId", electionScopeId);
		query.setParameter("stateId", staetId);
		query.setParameterList("areas", areas);
		query.setParameterList("districIds", districIds);


		return query.list();
	}
	@Override
	public List<?> loadConst(List<Long> constIds) {
		
		Query query= getSession().createQuery("select model.constituencyId from   Constituency model  where  model.constituencyId  in(:constIds)  and model.constituencyId not in(select distinct model1.constituency.constituencyId from PartyTrends model1 ) ");
		query.setParameterList("constIds", constIds);
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}
	

	
	
}
