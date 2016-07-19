package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostReferDetailsDAO;
import com.itgrids.partyanalyst.model.NominatedPostReferDetails;

public class NominatedPostReferDetailsDAO extends GenericDaoHibernate<NominatedPostReferDetails, Long> implements INominatedPostReferDetailsDAO{

	public NominatedPostReferDetailsDAO() {
		super(NominatedPostReferDetails.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getReferedCountForCandidateList(Set<Long> candidateIds){
		Query query = getSession().createQuery("select model.nominationPostCandidate.nominationPostCandidateId," +
												" count(distinct model.referCadreId)" +
												" from NominatedPostReferDetails model" +
												" where model.nominationPostCandidate.nominationPostCandidateId in (:candidateIds)" +
												" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
												" group by model.nominationPostCandidate.nominationPostCandidateId");
		query.setParameterList("candidateIds", candidateIds);
		return query.list();
	}
	
	public List<Object[]> getReferedCadreDetailsForCandidate(Long candidateId){
		Query query = getSession().createQuery("select model1.tdpCadreId," +
												" model1.firstname," +
												" model1.memberShipNo," +
												" model1.mobileNo," +
												" model1.image" +
												" from NominatedPostReferDetails model,TdpCadre model1" +
												" where model.referCadre.tdpCadreId = model1.tdpCadreId" +
												" and model.nominationPostCandidate.nominationPostCandidateId = :candidateId" +
												" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'");
		query.setParameter("candidateId", candidateId);
		return query.list();
	}
}
