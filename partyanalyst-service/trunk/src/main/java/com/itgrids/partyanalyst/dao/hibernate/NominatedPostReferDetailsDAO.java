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

	public List<Object[]> getReferedCountForCandidateList(Set<Long> applicationIds){
		Query query = getSession().createQuery("select model.nominatedPostApplicationId," +
												" count(distinct model.referCadreId)" +
												" from NominatedPostReferDetails model" +
												" where model.nominatedPostApplicationId in (:applicationIds)" +
												" and model.isDeleted = 'N' and model.nominatedPostApplication.isDeleted = 'N'" +
												" group by model.nominatedPostApplicationId");
		query.setParameterList("applicationIds", applicationIds);
		return query.list();
	}
	
	public List<Object[]> getReferedCadreDetailsForCandidate(Long applicationId){
		Query query = getSession().createQuery("select model1.tdpCadreId," +
												" model1.firstname," +
												" model1.memberShipNo," +
												" model1.mobileNo," +
												" model1.image" +
												" from NominatedPostReferDetails model,TdpCadre model1" +
												" where model.referCadre.tdpCadreId = model1.tdpCadreId" +
												" and model.nominatedPostApplication.nominatedPostApplicationId = :applicationId" +
												" and model.isDeleted = 'N' and model.nominatedPostApplication.isDeleted = 'N'");
		query.setParameter("applicationId", applicationId);
		return query.list();
	}
	
	public List<Object[]> getReferedCandidatesCountForCandidate(List<Long> cadreIds){
		Query query = getSession().createQuery("select model.referCadreId," +
											" count(distinct model.nominationPostCandidate.nominationPostCandidateId)" +
											" from NominatedPostReferDetails model" +
											" where model.referCadreId in (:cadreIds)" +
											" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
											" group by model.referCadreId");
		query.setParameterList("cadreIds", cadreIds);
		return query.list();
	}
}
