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

	public Integer inActiveCadreElectionDetailsById(List<Long> tdpCadreIdList)
	{
		Query query = getSession().createQuery("update CadreParticipatedElection model set model.isDeleted = 'H' where model.tdpCadreId in (:tdpCadreIdList) and model.isDeleted = 'N'");
		query.setParameterList("tdpCadreIdList", tdpCadreIdList);
		Integer count = query.executeUpdate();
		
		return count;
	}
	
	public List<Object[]> getPreviousParticipationInfoByTdpCadreId(Long tdpCadreId)
	{
		Query query = getSession().createQuery("select model.election, model.constituencyId, model.candidateId  from  " +
				" CadreParticipatedElection model  where model.tdpCadreId = :tdpCadreId and model.isDeleted = 'N' and model.election is not null ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}	

}
