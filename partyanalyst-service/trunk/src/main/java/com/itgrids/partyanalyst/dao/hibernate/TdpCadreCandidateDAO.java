package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.model.TdpCadreCandidate;


public class TdpCadreCandidateDAO extends GenericDaoHibernate<TdpCadreCandidate, Long>  implements ITdpCadreCandidateDAO{

	public TdpCadreCandidateDAO() {
		super(TdpCadreCandidate.class);
	}
	
	public List<Object[]> getPublicRepresentativeDetailsByCadre(Long cadreId){
		
			Query query=getSession().createQuery(" select model1.publicRepresentativeType.publicRepresentativeTypeId," +
					" model1.publicRepresentativeType.type " +
					" from TdpCadreCandidate model,PublicRepresentative model1 " +
					" where model.candidate.candidateId = model1.candidate.candidateId " +
					" and model.tdpCadre.tdpCadreId =:cadreId ");
		
			query.setParameter("cadreId", cadreId);
			
		return query.list();
	}
	public Long getTdpCadreCandidate(Long cadreId){
		
		Query query=getSession().createQuery(" select model.candidate.candidateId from  TdpCadreCandidate model " +
				" where model.tdpCadre.tdpCadreId =:cadreId");
		
		query.setParameter("cadreId", cadreId);
		
		return (Long) query.uniqueResult();
	}
	
	

}
