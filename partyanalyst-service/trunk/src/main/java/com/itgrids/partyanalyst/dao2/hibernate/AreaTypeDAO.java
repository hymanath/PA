package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAreaTypeDAO;
import com.itgrids.partyanalyst.model.AreaType;

public class AreaTypeDAO extends GenericDaoHibernate<AreaType,Long> implements IAreaTypeDAO{
	
	public AreaTypeDAO() {
		super(AreaType.class);
	}
	
	public List<Object[]> getElectionYears(Long stateId,Long electionType){
    	StringBuilder query = new StringBuilder();
    	query.append("select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear from BoothConstituencyElection model " +
				" where  model.constituencyElection.election.electionScope.electionType.electionTypeId = :electionType ");
		if(electionType.longValue() == 2l)
			query.append(" and model.constituencyElection.constituency.state.stateId = :stateId ");
		query.append(" order by model.constituencyElection.election.electionYear desc");
    	Query queryObj  = getSession().createQuery(query.toString());
    	queryObj.setParameter("electionType", electionType);
    	
    	if(electionType.longValue() == 2l)
    	queryObj.setParameter("stateId", stateId);
    	
    	return queryObj.list();	
	}
	
	public List<Object[]> getPartiesForElection(Long electionId,Long constituencyId)
	{
		
		Query query = getSession().createQuery("select distinct model.nomination.party.partyId, model.nomination.party.shortName,model.boothConstituencyElection.constituencyElection.election.electionId from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId order by model.nomination.party.shortName");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		return query.list();
	}

}
