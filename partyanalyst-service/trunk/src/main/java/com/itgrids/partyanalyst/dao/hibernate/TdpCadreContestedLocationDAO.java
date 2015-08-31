package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreContestedLocationDAO;
import com.itgrids.partyanalyst.model.TdpCadreContestedLocation;

public class TdpCadreContestedLocationDAO extends GenericDaoHibernate<TdpCadreContestedLocation, Long> implements ITdpCadreContestedLocationDAO{

	public TdpCadreContestedLocationDAO() {
		super(TdpCadreContestedLocation.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getParticipatedCandidateConstituency(Long tdpCadreId){
		
		Query constitunecyQuery = getSession().createQuery(" select model.constituencyElection.constituency.constituencyId, " +
				" model.constituencyElection.constituency.name,model.constituencyElection.election.electionScope.electionType.electionTypeId," +
				" model.constituencyElection.election.electionScope.electionType.electionType " +
				" from TdpCadreContestedLocation model " +
				" where model.tdpCadreCandidate.tdpCadre.tdpCadreId = :tdpCadreId " +
				" and model.isActive = 'Y' ");  
		
		constitunecyQuery.setParameter("tdpCadreId", tdpCadreId);
		return constitunecyQuery.list();
	}
	
}
