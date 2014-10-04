package com.itgrids.partyanalyst.dao.hibernate;

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
}
