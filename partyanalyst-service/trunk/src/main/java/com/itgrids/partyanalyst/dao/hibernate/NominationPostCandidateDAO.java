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
		
		sb.append(" AND model.tdpCadreId is null ");
		sb.append(" AND model.voterId is not null ");
		
		Query query = getSession().createQuery(sb.toString());
		if(!searchType.equalsIgnoreCase("4") && !searchType.equalsIgnoreCase("1"))
			query.setParameter("searchValue", searchValue);
		return query.list();
		
	}
	public List<Object[]> getNotCadreDetailsById(Long nominatedCandiId){
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT  model.nominationPostCandidateId,model.mobileNo,model.candidateName,voter.voterIDCardNo," +
				"   model.imageurl,model.address.constituency.constituencyId,model.address.constituency.name,model.tdpCadreId " +
				"   FROM   NominationPostCandidate model left join model.voter voter " +
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
				   							   " from NominationPostCandidate model " +
				   							   " where " +
				   							   " model.isDeleted = 'N' ");
		return query.list();
	}
	
	public List<Object[]> getLevelName(String levelType,Long tdpCadreId,String searchType,Long applicationId){
		StringBuilder str=new StringBuilder();
		 
		if(applicationId != null && applicationId.longValue()>0L)
			searchType="Not Cadre";
		if(levelType.equalsIgnoreCase("State")){
			str.append(" SELECT model.address.state.stateId,model.address.state.stateName from NominatedPostApplication model ");
		}else if(levelType.equalsIgnoreCase("District")){
			str.append(" SELECT model.address.district.districtId,model.address.district.districtName from NominatedPostApplication model ");
		}else if(levelType.equalsIgnoreCase("Assembly")){
			str.append(" SELECT model.address.constituency.constituencyId,model.address.constituency.name from NominatedPostApplication model ");
		}else if(levelType.equalsIgnoreCase("Mandal")){
			str.append(" SELECT model.address.tehsil.tehsilId,model.address.tehsil.tehsilName from NominatedPostApplication model ");
		}else if(levelType.equalsIgnoreCase("Muncipality/Corporation")){
			str.append(" SELECT model.address.localElectionBody.localElectionBodyId,model.address.localElectionBody.name from NominatedPostApplication model ");
		}
		
		if(searchType !=null && searchType.equalsIgnoreCase("Cadre")){
        	str.append(" where model.tdpCadre.tdpCadreId = :tdpCadreId ");
        }
        else if(searchType !=null && searchType.equalsIgnoreCase("Not Cadre")){
        	//str.append(" where model.nominationPostCandidateId = :nominateCandId ");
        	str.append(" where model.nominatedPostApplicationId = :applicationId and model.isDeleted='N' ");
        }
		Query query = getSession().createQuery(str.toString());
        if(searchType !=null && searchType.equalsIgnoreCase("Cadre")){
        	  query.setParameter("tdpCadreId", tdpCadreId);
        }
        else if(searchType !=null && searchType.equalsIgnoreCase("Not Cadre")){
        	  query.setParameter("applicationId", applicationId);
        }
      
        return query.list();
	}
	public List<Long> getCandidateByVoterId(Long voterId)
	{
		Query query = getSession().createQuery(" select model.nominationPostCandidateId from NominationPostCandidate model " +
				" where " +
				" model.voter.voterId=:voterId and model.isDeleted='N' ");
		query.setParameter("voterId", voterId);
		return query.list();
		
	}
	public List<Object[]>  updateCadresearch(String searchType,String searchValue)
	{
		StringBuilder sb=new StringBuilder();
		
		sb.append(" SELECT  model.nominationPostCandidateId,model.mobileNo,model.candidateName,voter.voterIDCardNo," +
				"   model.imageurl,constiteuncy.constituencyId,constiteuncy.name,tdpCadre.tdpCadreId,tdpCadre.memberShipNo " +
				"   FROM   NominationPostCandidate model " +
				" left join model.voter voter" +
				//" left join model.address address" +
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
		
		//sb.append(" AND model.tdpCadreId is null ");
		//sb.append(" AND model.voterId is not null ");
		
		Query query = getSession().createQuery(sb.toString());
		if(!searchType.equalsIgnoreCase("4") && !searchType.equalsIgnoreCase("1"))
		 query.setParameter("searchValue", searchValue);
		return query.list();
		
	}
	
public List<Long> getNominatedPostCondidates(Long tdpCadreId){
		
		Query query = getSession().createQuery(" select distinct model.nominationPostCandidateId from " +
				" NominationPostCandidate model where model.tdpCadreId =:tdpCadreId and model.isDeleted='N' ");
		
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
 public List<Long> getNominatedPstCandidateIds(List<Long> tdpCadreIds){
	 StringBuilder sb = new StringBuilder();
	 sb.append("select distinct model.nominationPostCandidateId" +
	 		" from NominationPostCandidate model" +
	 		" where model.isDeleted = 'N'");
	 if(tdpCadreIds != null && tdpCadreIds.size() > 0l){
		 sb.append(" and model.tdpCadre.tdpCadreId in (:tdpCadreIds)");
	 }
	 
	 Query query = getSession().createQuery(sb.toString());
	 if(tdpCadreIds != null && tdpCadreIds.size() > 0l)
		 query.setParameterList("tdpCadreIds", tdpCadreIds);
	 
	 return query.list();
	 
 }
}
