package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;

public class CadreParticipatedElectionDAO extends GenericDaoHibernate<CadreParticipatedElection, Long> implements ICadreParticipatedElectionDAO{

	public CadreParticipatedElectionDAO() {
		super(CadreParticipatedElection.class);
	}

	public Integer inActiveCadreElectionDetailsById(Long tdpCadreId)
	{
		Query query = getSession().createQuery("update CadreParticipatedElection model set model.isDeleted = 'Y' where model.tdpCadreId = :tdpCadreId");
		query.setParameter("tdpCadreId", tdpCadreId);
		Integer count = query.executeUpdate();
		
		return count;
	}
	
	public List<Object[]> getPreviousParticipationInfoByTdpCadreId(Long tdpCadreId)
	{
		Query query = getSession().createQuery("select model.election.electionScope.electionType.electionTypeId ,model.electionId, model.constituencyId  from  " +
				" CadreParticipatedElection model  where model.tdpCadreId = :tdpCadreId and model.isDeleted = 'N' order by model.cadreParticipatedElectionId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	
}
