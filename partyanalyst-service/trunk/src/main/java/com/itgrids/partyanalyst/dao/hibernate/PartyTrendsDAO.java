package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.model.PartyTrends;

public class PartyTrendsDAO extends GenericDaoHibernate<PartyTrends, Long> implements IPartyTrendsDAO{

	public PartyTrendsDAO() {
		super(PartyTrends.class);
	}
	
	public List<?> loadConst() {
		
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}
	
	public List<?> loadEntitiesForXl(List<Long> constIds) {
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name,model.name,model.pervTrenzWt,model.prpWt,model.youngVotersWt,model.totalWt,model.id,model.type  from   PartyTrends model  where model.constituency.constituencyId in(:constIds)  order by model.constituency.name asc,model.totalWt desc ");
	
		query.setParameterList("constIds", constIds);
		return query.list();
		
	}
	public List<Object[]> findAssemblyConstituenciesForSimaAndra(Long electionScopeId,Long staetId,List<String> areas,List<Long> districIds) {
		
		Query query = getSession().createQuery("select model.constituencyId , model.name ,model.areaType from Constituency model   " +
				"  where model.state.stateId= :stateId  and model.electionScope.electionScopeId=:electionScopeId  and  model.areaType in (:areas) and model.deformDate is null   "   +
				"order by model.name");
		
		
		//query.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		query.setParameter("electionScopeId", electionScopeId);
		query.setParameter("stateId", staetId);
		query.setParameterList("areas", areas);
		


		return query.list();
	}
	
	public List<?> loadConst(List<Long> constIds) {
		
		Query query= getSession().createQuery("select model.constituencyId from   Constituency model  where  model.constituencyId  in(:constIds)  and model.constituencyId not in(select distinct model1.constituency.constituencyId from PartyTrends model1 ) ");
		query.setParameterList("constIds", constIds);
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}
	
   public List<Long> getConstituencyIds() {
	   List<Long> constiIds = new ArrayList<Long>();
	   constiIds.add(108l);
	   constiIds.add(109l);
	   constiIds.add(111l);
	   constiIds.add(112l);
	   constiIds.add(114l);
	   constiIds.add(122l);
	   constiIds.add(125l);
	   constiIds.add(133l);
	   constiIds.add(135l);
	   constiIds.add(136l);
	   constiIds.add(141l);
	   constiIds.add(157l);
	   constiIds.add(171l);
	   constiIds.add(176l);
	   constiIds.add(192l);
	   constiIds.add(211l);
	   constiIds.add(222l);
	   constiIds.add(224l);
	   constiIds.add(225l);
	   constiIds.add(226l);
	   constiIds.add(227l);
	   constiIds.add(236l);
	   constiIds.add(244l);
	   constiIds.add(245l);
	   constiIds.add(248l);
	   constiIds.add(250l);
	   constiIds.add(251l);
	   constiIds.add(263l);
	   constiIds.add(264l);
	   constiIds.add(265l);
	   constiIds.add(271l);
	   constiIds.add(281l);
	   constiIds.add(283l);
	   constiIds.add(288l);
	   constiIds.add(289l);
	   constiIds.add(300l);
	   constiIds.add(301l);
	   constiIds.add(302l);
	   constiIds.add(303l);
	   constiIds.add(305l);
	   constiIds.add(327l);
	   constiIds.add(329l);
	   constiIds.add(330l);
	   constiIds.add(332l);
	   constiIds.add(333l);
	   constiIds.add(334l);
	   constiIds.add(352l);
	   constiIds.add(353l);
	   constiIds.add(359l);
	   constiIds.add(361l);

		Query query= getSession().createQuery("select distinct model.constituencyId from   PartyTrends model  where  model.constituencyId  not in(:constiIds)   ");
		query.setParameterList("constiIds", constiIds);
		return query.list();
		//Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
	}

    public List<PartyTrends> getAllTrends(Long constiId){
    	Query query= getSession().createQuery("select  model from   PartyTrends model  where  model.constituencyId  = :constiId  order by  model.totalWt desc");
		query.setParameter("constiId", constiId);
		return query.list();
    }
	
}
