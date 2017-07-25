package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

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
		Query query=getSession().createQuery("select model.tdpCadre.tdpCadreId, model.candidate.candidateId," +
				" model.tdpCadre.memberShipNo,model.tdpCadre.voter.voterIDCardNo from TdpCadreCandidate model where " +
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
		StringBuilder sb =new StringBuilder();
		sb.append(" select model.tdpCadre.tdpCadreId,'',model1.publicRepresentativeType.publicRepresentativeTypeId," +
				" model1.publicRepresentativeType.type,model1.levelId, model1.levelValue,C.name " +
				" from TdpCadreCandidate model,PublicRepresentative model1, Constituency C " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model.tdpCadre.tdpCadreId in (:cadreIdsList)  and model1.levelValue = C.constituencyId   and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014  ");
		
		Query query =getSession().createQuery(sb.toString());
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
		
		sb.append(" select distinct tcc.tdpCadreId,tcc.tdpCadre.firstname,prt.publicRepresentativeTypeId,prt.type,pr.levelId,pr.levelValue,tcc.tdpCadre.image " +
				" from TdpCadreCandidate tcc,PublicRepresentative pr,PublicRepresentativeType prt " +
				" where " +
				"  tcc.candidateId=pr.candidateId " +
				" and pr.publicRepresentativeTypeId=prt.publicRepresentativeTypeId " +
				" and tcc.tdpCadreId in (:cadreIds) and tcc.tdpCadre.isDeleted = 'N' and tcc.tdpCadre.enrollmentYear = 2014");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("cadreIds", cadreIds);
		return query.list();
	}
	
	public List<Object[]> getCandidateDetailsForCommittee(List<Long> cadreIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct TCM.tdpCadre.tdpCadreId,TCM.tdpCadre.firstname,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,TCM.tdpCommitteeRole.tdpRoles.role,TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,TCM.tdpCadre.image " +
				" from TdpCommitteeMember TCM " +
				" where " +
			    " TCM.tdpCadre.tdpCadreId in (:cadreIds) and TCM.tdpCadre.isDeleted = 'N' and TCM.tdpCadre.enrollmentYear = 2014");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("cadreIds", cadreIds);
		return query.list();
	}
	public List<Object[]> getTdpCadreIdsOfCandidates(Set<Long> candidateIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.candidateId,model.tdpCadreId from " +
				" TdpCadreCandidate model ");
		
		if(candidateIds != null && candidateIds.size() > 0)
			sb.append(" where model.candidateId in (:candidateIds) ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(candidateIds != null && candidateIds.size() > 0)
			query.setParameterList("candidateIds", candidateIds);
		
		return query.list();
	}
	
	public List<Object[]> geTdpCadreCandidateDetailsByMemberShipIds(List<String> memberShipNos){
		  StringBuilder sb = new StringBuilder();
		  sb.append("select  tdpCadreCandidate.tdpCadre.memberShipNo," +//0
					 "tdpCadreCandidate.tdpCadre.tdpCadreId," +//1
					  "tdpCadreCandidate.tdpCadre.firstname," +//2
					  "tdpCadreCandidate.tdpCadre.mobileNo," +//3
					  "tdpCadreCandidate.tdpCadre.image," +  //4
					  "publicRepresentativeType.type " +// 5
					  "from TdpCadreCandidate tdpCadreCandidate," +
					  "PublicRepresentative publicRepresentative " +
					  "left join publicRepresentative.publicRepresentativeType publicRepresentativeType " +
					  "where  tdpCadreCandidate.tdpCadre.isDeleted = 'N' " +
					  "and publicRepresentative.candidateId=tdpCadreCandidate.candidateId ");										
		  if(memberShipNos != null && memberShipNos.size() > 0l)
			  sb.append("and tdpCadreCandidate.tdpCadre.memberShipNo in (:memberShipNos)  ");	
		  	Query query = getSession().createQuery(sb.toString());
			if(memberShipNos != null && memberShipNos.size() > 0l)
				query.setParameterList("memberShipNos", memberShipNos);
	  return query.list();
	}
	public List<Object[]> geTdpCadreCandidateDesiganationsByCadreaIds(Set<Long> tdpCadreIds){
		  StringBuilder sb = new StringBuilder();
		  sb.append("select  tdpCadreCandidate.tdpCadreId," +//0
		  		"tdpCadreCandidate.candidateId," +//1
		  		"publicRepresentative.publicRepresentativeType.type " +//2
		  		"from TdpCadreCandidate tdpCadreCandidate, " +
		  		"PublicRepresentative publicRepresentative " +
		  		"left join publicRepresentative.publicRepresentativeType publicRepresentativeType " +
		  		"where  publicRepresentative.candidateId=tdpCadreCandidate.candidateId ");
		  if(tdpCadreIds != null && tdpCadreIds.size() > 0l){
			  sb.append("and tdpCadreCandidate.tdpCadreId in (:tdpCadreIds)");	
		  }
		  Query query = getSession().createQuery(sb.toString());
		  if(tdpCadreIds != null && tdpCadreIds.size() > 0l)
				query.setParameterList("tdpCadreIds", tdpCadreIds);
		  return query.list();
	}
	
}
