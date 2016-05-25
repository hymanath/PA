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
					" and model.tdpCadre.tdpCadreId =:cadreId  and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014  ");
		
			query.setParameter("cadreId", cadreId);
			
		return query.list();
	}
	
	public List<Object[]> getPublicRepresentativeDetailsForCadreIds(List<Long> cadreIds){
		
		Query query=getSession().createQuery(" select model1.publicRepresentativeType.publicRepresentativeTypeId," +
				" model1.publicRepresentativeType.type," +
				" model.tdpCadre.tdpCadreId " +
				" from TdpCadreCandidate model,PublicRepresentative model1 " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model.tdpCadre.tdpCadreId in (:cadreIds) and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014 ");
	
		query.setParameterList("cadreIds", cadreIds);
		
	return query.list();
}
	
	public List<Long> getTdpCadreCandidate(Long cadreId){
		Query query=getSession().createQuery(" select distinct model.candidate.candidateId from  TdpCadreCandidate model " +
				" where model.tdpCadre.tdpCadreId =:cadreId  and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014 ");
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
	
	public List<Object[]> getTdpCadreCandidateIds(List<Long> finalCadreIDsList)
	{
		Query query=getSession().createQuery("select model.tdpCadre.tdpCadreId, model.candidate.candidateId from TdpCadreCandidate model where " +
				" model.candidate.candidateId in (:finalCadreIDsList)  and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014 ");
		query.setParameterList("finalCadreIDsList", finalCadreIDsList);
		return query.list();
	}

	public List<Object[]> getPublicRepresentaativesDetailsForCadreIdsList(List<Long> cadreIdsList){
		
		Query query=getSession().createQuery(" select model.tdpCadre.tdpCadreId, model.tdpCadre.voter.voterIDCardNo, model1.publicRepresentativeType.publicRepresentativeTypeId," +
				" model1.publicRepresentativeType.type,model1.levelId, model1.levelValue " +
				" from TdpCadreCandidate model,PublicRepresentative model1 " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model.tdpCadre.tdpCadreId in (:cadreIdsList)  and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014  ");
	
		query.setParameterList("cadreIdsList", cadreIdsList);
		
	return query.list();
}
	
	public List<Object[]> getPublicsRepresentaativesDetailsForCadreIdsList(List<Long> cadreIdsList){
		
		Query query=getSession().createQuery(" select model.tdpCadre.tdpCadreId,'',model1.publicRepresentativeType.publicRepresentativeTypeId," +
				" model1.publicRepresentativeType.type,model1.levelId, model1.levelValue,C.name " +
				" from TdpCadreCandidate model,PublicRepresentative model1, Constituency C " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model.tdpCadre.tdpCadreId in (:cadreIdsList)  and model1.levelValue = C.constituencyId   and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014  ");
	
		query.setParameterList("cadreIdsList", cadreIdsList);
		
	return query.list();
	}
	
	public Long getCheckCadreIdExits(Long tdpCadreId)
	{
		Query query=getSession().createQuery("select model.candidateId from TdpCadreCandidate model where model.tdpCadreId =:tdpCadreId");
		query.setParameter("tdpCadreId", tdpCadreId);
		return (Long)query.uniqueResult(); 
	}
	public List<Object[]> getPublicRepresentativeDetailsByCadreIds(List<Long> cadreIds){
		
		Query query=getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId,model1.publicRepresentativeType.publicRepresentativeTypeId," +
				" model1.publicRepresentativeType.type " +
				" from TdpCadreCandidate model,PublicRepresentative model1 " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model.tdpCadre.tdpCadreId in (:cadreIds)  and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014  ");
	
		query.setParameterList("cadreIds", cadreIds);
		
	return query.list();
	}
	

	public List<Object[]> getCandidateDetails(List<Long> cadreIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select tcc.tdpCadreId,tcc.tdpCadre.firstname,prt.publicRepresentativeTypeId,prt.type,pr.levelId,pr.levelValue,tcc.tdpCadre.image " +
				" from TdpCadreCandidate tcc,PublicRepresentative pr,PublicRepresentativeType prt " +
				" where " +
				"  tcc.candidateId=pr.candidateId " +
				" and pr.publicRepresentativeTypeId=prt.publicRepresentativeTypeId " +
				" and tcc.tdpCadreId in (:cadreIds) ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("cadreIds", cadreIds);
		return query.list();
	}

	
}
