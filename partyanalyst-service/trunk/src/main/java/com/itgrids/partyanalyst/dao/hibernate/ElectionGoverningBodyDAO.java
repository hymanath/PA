package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public class ElectionGoverningBodyDAO extends GenericDaoHibernate<ElectionGoverningBody, Long>
											implements IElectionGoverningBodyDAO{

	public ElectionGoverningBodyDAO(){
		super(ElectionGoverningBody.class);
	}
	
	public List<Object[]> getAllMinistersIdsAndMinistry(Long electionId)
	{
		
		return getHibernateTemplate().find("select model.candidate.candidateId,model.candidate.lastname,model.positionScope.electionGoverningBodyPosition.governingBodyPosition ," +
				" model.party.partyId ,model.party.shortName from ElectionGoverningBody model where model.election.electionId = ?",electionId);
	}
}
