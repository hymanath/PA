package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.model.NominationPostCandidate;

public class NominationPostCandidateDAO extends GenericDaoHibernate<NominationPostCandidate, Long> implements INominationPostCandidateDAO{

	public NominationPostCandidateDAO() {
		super(NominationPostCandidate.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]>  notCadresearch(String searchType,String searchValue)
	{
		StringBuilder sb=new StringBuilder();
		
		sb.append(" SELECT  model.nominationPostCandidateId,model.mobileNo,model.candidateName,voter.voterIDCardNo," +
				"   model.imageurl,constiteuncy.constituencyId,constiteuncy.name,tdpCadre.tdpCadreId " +
				"   FROM   NominationPostCandidate model " +
				" left join model.voter voter" +
				" left join model.address address" +
				" left join model.address.constituency constiteuncy " +
				" left join model.tdpCadre tdpCadre " +
			//	"   WHERE  model.tdpCadreId is null and model.isDeleted = 'N' ");
				"   WHERE  model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase("1"))
			sb.append(" AND model.tdpCadre.memberShipNo like '%"+searchValue.trim()+"%' ");
		else if(searchType.equalsIgnoreCase("3"))
			sb.append(" AND model.mobileNo = :searchValue ");
		else if(searchType.equalsIgnoreCase("2"))
			sb.append(" AND model.voter.voterIDCardNo = :searchValue ");
		else if(searchType.equalsIgnoreCase("4"))
			sb.append(" AND model.candidateName LIKE '%"+searchValue.trim()+"%' ");
		
		Query query = getSession().createQuery(sb.toString());
		if(!searchType.equalsIgnoreCase("4") && !searchType.equalsIgnoreCase("1"))
			query.setParameter("searchValue", searchValue);
		return query.list();
		
	}
	public List<Object[]> getNotCadreDetailsById(Long nominatedCandiId){
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT  model.nominationPostCandidateId,model.mobileNo,model.candidateName,model.voter.voterIDCardNo," +
				"   model.imageurl,model.address.constituency.constituencyId,model.address.constituency.name" +
				"   FROM   NominationPostCandidate model" +
				"   WHERE  model.nominationPostCandidateId = :nominatedCandiId and model.isDeleted = 'N' ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("nominatedCandiId", nominatedCandiId); 
		return query.list();
	}
	public NominationPostCandidate getUserAddressByCandidate(Long postCandidateId){
		
		Query query = getSession().createQuery("select model from  NominationPostCandidate model " +
				" where " +
				" model.nominationPostCandidateId = :postCandidateId" +
				" and model.isDeleted = 'N' ");
		
		query.setParameter("postCandidateId", postCandidateId);
		
		return (NominationPostCandidate) query.uniqueResult();
	}

	public List<Object[]> getNOminatedCadreList(List<Long> cadreIdsLsit){
		
		Query query = getSession().createQuery(" select distinct model.tdpCadreId, model.nominationPostCandidateId from " +
				" NominationPostCandidate model where model.tdpCadreId in (:cadreIdsLsit) and model.isDeleted='N' ");
		
		query.setParameterList("cadreIdsLsit", cadreIdsLsit);
		return query.list();
	}
	/*
	 * Swadhin
	 */
	public List<Object[]> getCastGroupList(){
		Query query = getSession().createQuery("select distinct model.casteState.casteCategoryGroup.casteCategory.casteCategoryId, model.casteState.casteCategoryGroup.casteCategory.categoryName" +
				   " from NominationPostCandidate model");
		return query.list();
	}
	
}
