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
		
		sb.append(" SELECT  model.nominationPostCandidateId,model.mobileNo,model.candidateName,model.voter.voterIDCardNo," +
				"   model.imageurl,model.address.constituency.constituencyId,model.address.constituency.name" +
				"   FROM   NominationPostCandidate model" +
				"   WHERE  model.tdpCadreId is null and model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase("3")){
			
			sb.append(" AND model.mobileNo = :searchValue ");
			
		}else if(searchType.equalsIgnoreCase("2")){
			
			sb.append(" AND model.voter.voterIDCardNo = :searchValue ");
		}
		else if(searchType.equalsIgnoreCase("4"))
		{
			sb.append(" AND model.candidateName LIKE '%"+searchValue+"%' ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(!searchType.equalsIgnoreCase("4"))
				{
			query.setParameter("searchValue", searchValue);
				}
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

}
