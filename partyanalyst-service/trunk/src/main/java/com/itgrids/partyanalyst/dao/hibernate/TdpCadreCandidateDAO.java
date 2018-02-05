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
	//getTdpCadreIds(candidateIds)
	public List<Object[]> nomiantionCandidateDetails(List<Long> candidateIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select TCC.tdpCadre.tdpCadreId,TCC.candidate.candidateId,TCC.tdpCadre.image " +
				"from TdpCadreCandidate TCC ");
		if(candidateIds != null && candidateIds.size()>0){
			 sb.append("where TCC.candidate.candidateId in (:candidateIds)");	
		}
		 Query query = getSession().createQuery(sb.toString());
		 if(candidateIds != null && candidateIds.size()>0){
			 query.setParameterList("candidateIds", candidateIds);
		 }
		 return query.list();
		
	}
	public List<Object[]> getPublicRepresetativesInLocation(Long locationValue,Long locationTypeId,List<Long> representativTypeIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct tcc.tdpCadreId,tcc.tdpCadre.firstname,prt.publicRepresentativeTypeId,prt.type,pr.levelId,pr.levelValue,tcc.tdpCadre.image " +
				" ,state.stateId,state.stateName,district.districtId,district.districtName,parliament.constituencyId,parliament.name," +
				" constituency.constituencyId,constituency.name," +
				" tehsil.tehsilId,tehsil.tehsilName," +
				" panchayat.panchayatId,panchayat.panchayatName," +
				" localElectionBody.localElectionBodyId,localElectionBody.name," +
				" ward.constituencyId,ward.name ,pr.candidateId,party.partyFlag   " +//21,22,23,24
				"from TdpCadreCandidate tcc ,PublicRepresentativeType prt ," +
				"  PublicRepresentative pr  " +
				" left join pr.userAddress.state state " +
				" left join pr.userAddress.district district " +
				" left join pr.userAddress.parliamentConstituency parliament " +
				" left join pr.userAddress.constituency constituency " +
				" left join pr.userAddress.tehsil tehsil " +
				" left join pr.userAddress.panchayat panchayat " +
				" left join pr.userAddress.localElectionBody localElectionBody " +
				" left join pr.userAddress.ward ward " +
				" left join pr.nomination nomination " +
				" left join nomination.party party " +
				"  where " +
				"  tcc.candidateId=pr.candidateId " +
				" and pr.publicRepresentativeTypeId=prt.publicRepresentativeTypeId " +
				" and tcc.tdpCadre.isDeleted = 'N' and tcc.tdpCadre.enrollmentYear = 2014");
		if (locationTypeId != null && locationTypeId == 3l){
			sb.append(" and tcc.tdpCadre.userAddress.district.districtId = :locationValue " );
		}else if (locationTypeId != null && locationTypeId == 10l){
			sb.append(" and tcc.tdpCadre.userAddress.parliamentConstituency.constituencyId = :locationValue " );
		}else if (locationTypeId != null && locationTypeId == 4l){
			sb.append(" and tcc.tdpCadre.userAddress.constituency.constituencyId = :locationValue " );
		}else if (locationTypeId != null && locationTypeId == 5l){
			sb.append(" and tcc.tdpCadre.userAddress.tehsil.tehsilId = :locationValue " );
		}else if (locationTypeId != null && locationTypeId == 6l){
			sb.append(" and tcc.tdpCadre.userAddress.panchayat.panchayatId = :locationValue " );
		}else if (locationTypeId != null && locationTypeId == 7l){
			sb.append(" and tcc.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :locationValue " );
		}else if (locationTypeId != null && locationTypeId == 8l){
			sb.append(" and tcc.tdpCadre.userAddress.ward.constituencyId = :locationValue " );
		}else if (locationTypeId != null && locationTypeId == 2l){
			sb.append(" and tcc.tdpCadre.userAddress.state.stateId = :locationValue " );
		}
		
		if(representativTypeIds != null && representativTypeIds.size() > 0){
			sb.append(" and prt.publicRepresentativeTypeId in (:representativTypeIds) ");
		}
		Query query = getSession().createQuery(sb.toString());
		if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValue != null && locationValue.longValue() >0l)
			query.setParameter("locationValue", locationValue);
		
		if(representativTypeIds != null && representativTypeIds.size() > 0){
			query.setParameterList("representativTypeIds", representativTypeIds);
		}
		return query.list();
	}
		public List<Long> getTdpCadreIds(List<Long> candidateIds){
			StringBuilder sb = new StringBuilder();
			sb.append(" select TCC.tdpCadre.tdpCadreId " +
					"from TdpCadreCandidate TCC ");
			if(candidateIds != null && candidateIds.size()>0){
				 sb.append("where TCC.candidate.candidateId in (:candidateIds)");	
			}
			sb.append(" and TCC.candidate.party.partyId = 872 ");	
			 Query query = getSession().createQuery(sb.toString());
			 if(candidateIds != null && candidateIds.size()>0){
				 query.setParameterList("candidateIds", candidateIds);
			 }
			 return query.list();
			
		}
	
}
