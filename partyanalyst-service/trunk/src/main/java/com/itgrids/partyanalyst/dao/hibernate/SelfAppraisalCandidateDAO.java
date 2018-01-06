package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidate;

public class SelfAppraisalCandidateDAO extends GenericDaoHibernate<SelfAppraisalCandidate, Long> implements ISelfAppraisalCandidateDAO {
	 public SelfAppraisalCandidateDAO() {
		super(SelfAppraisalCandidate.class);
	 }
	 
	 public List<Object[]> getCandiateList(Long designationId){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select " +
		   		           " distinct model.selfAppraisalCandidateId," +
		   		           " model.tdpCadre.tdpCadreId," +
		   		           " model.tdpCadre.firstname " +
		   		           " from SelfAppraisalCandidate model " +
		   		           " where " +
		   		           " model.isActive='Y' " +
		   		           " and model.selfAppraisalDesignation.selfAppraisalDesignationId=:designationId ");
		   Query query = getSession().createQuery(queryStr.toString());
		     query.setParameter("designationId", designationId);
		     return query.list();
	 }
	 public Object[] getCandiateDetailsByCandidateId(Long candiateId){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select " +
		   		           " model.tdpCadre.tdpCadreId," +
		   		           " model.tdpCadre.firstname," +
		   		           " model.tdpCadre.voterId," +
		   		           " model.tdpCadre.memberShipNo," +
		   		           " model.tdpCadre.mobileNo," +
		   		           " model.tdpCadre.image," +
		   		           " model.tdpCadre.cardNo" +
		   		           " from SelfAppraisalCandidate model " +
		   		           " where " +
		   		           " model.isActive='Y' " +
		   		           " and model.selfAppraisalCandidateId=:designationId ");
		   Query query = getSession().createQuery(queryStr.toString());
		     query.setParameter("designationId", candiateId);
		     return (Object[]) query.uniqueResult();
	 }
	 public Long getCandidateId(Long tdpCadreId,Long designationId){
		 Query query = getSession().createQuery(" select model.selfAppraisalCandidateId from SelfAppraisalCandidate model" +
		 		                                "  where model.tdpCadreId=:tdpCadreId and model.selfAppraisalDesignationId=:designationId ");
		 query.setParameter("tdpCadreId", tdpCadreId);
		 query.setParameter("designationId", designationId);
		 return (Long) query.uniqueResult();
	 }
	 public List<Object[]> getTotalLeadersDesignationBy(List<Long> desigIds){
		 StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select " +
		   		          " model.selfAppraisalDesignation.selfAppraisalDesignationId, " +
		   		          " model.selfAppraisalDesignation.designation," +
		   		          " count(distinct model.selfAppraisalCandidateId),model.selfAppraisalDesignation.orderNo " +
		   		          " from SelfAppraisalCandidate model where model.isActive='Y' and model.selfAppraisalDesignation.isActive='Y' ");
		   if(desigIds != null && desigIds.size() > 0){
			   queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in  (:desigIds) ");  
		   }
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId ");
		   Query query = getSession().createQuery(queryStr.toString());
		   if(desigIds != null && desigIds.size() > 0){
			   query.setParameterList("desigIds",desigIds);
		   }  
		   return query.list();  
	 }
	 public List<Long> getCandidateList(Long desigId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select distinct SAC.selfAppraisalCandidateId from SelfAppraisalCandidate SAC " +
		 				" where " +
		 				" SAC.selfAppraisalDesignation.selfAppraisalDesignationId = :desigId and " +
		 				" SAC.selfAppraisalDesignation.isActive = 'Y' and " +
		 				" SAC.isActive = 'Y' ");
		 Query query = getSession().createQuery(queryStr.toString());
		 if(desigId != null){
			   query.setParameter("desigId",desigId);
		   }
		 return query.list();
	 }
	 
   public List<Object[]> getMemberAccessLevelIdsAndValue(){
	   
	   StringBuilder queryStr = new StringBuilder();
	   
	     queryStr.append(" select " +
	     				 " model.selfAppraisalCandidateId," +//0
	     				 " model1.activityMemberLevelId," +//1
	     				 " model1.activityLocationValue " +//2
	     				 " from SelfAppraisalCandidate model,ActivityMemberAccessLevel model1 " +
	     	         	 " where " +
	     	         	 " model.activityMember.activityMemberId=model1.activityMember.activityMemberId " +
	     	         	 " and model1.isActive='Y' and model.isActive='Y' " +
	     	         	 " and model.selfAppraisalDesignationId=3  ");
	     Query query = getSession().createQuery(queryStr.toString());
	      return query.list();
   }
  public List<Object[]> getCandidateIdsAndDesignationByActivityMemberIds(List<Long> activityMemberIds){
	  StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId,model.selfAppraisalDesignation.designation,model.selfAppraisalCandidateId  " +
	   				   " from SelfAppraisalCandidate model where model.activityMemberId in(:activityMemberIds) and model.isActive='Y' ");
	   Query query = getSession().createQuery(queryStr.toString());
	   query.setParameterList("activityMemberIds", activityMemberIds);
	   return query.list();
  }
  
  public List<Long> getCandidateIdOfCadre(Long tdpCadreId,Long designationId){
	  
	  Query query = getSession().createQuery(" select model.selfAppraisalCandidateId " +
	  		" from SelfAppraisalCandidate model " +
	  		" where model.tdpCadreId = :tdpCadreId " +
	  		" and model.selfAppraisalDesignationId = :designationId ");
	  
	  query.setParameter("tdpCadreId", tdpCadreId);
	  query.setParameter("designationId", designationId);
	   
	  return query.list();
	  
  }
  
  /**
	  * @author Srishailam Pittala
	  * date: 7th Jan, 2017
	  * desc: To get total designations for a cadre
	  * input : Long tdpCadreId
	  * return List<Obejct[]>
	  */
  public List<Object[]> getDesignationsList(Long tdpCadreId){
		 Query query = getSession().createQuery(" select distinct  model.selfAppraisalDesignation.selfAppraisalDesignationId,model.selfAppraisalDesignation.designation from SelfAppraisalCandidate model " +
		 		                                "  where model.tdpCadreId=:tdpCadreId and model.isActive='Y' order by model.selfAppraisalDesignation.designation  ");
		 query.setParameter("tdpCadreId", tdpCadreId);
		 return query.list();
	 }
  public List<Object[]> getSelfAppraisalCandidateIdAndDesignationByTdpCadreId(Long tdpCadreId){
	   StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select distinct model.selfAppraisalDesignation.selfAppraisalDesignationId,model.selfAppraisalDesignation.designation," +
	   				   "  model.selfAppraisalCandidateId,model.tdpCadre.firstname,model.tdpCadre.lastname from SelfAppraisalCandidate model where model.tdpCadreId=:tdpCadreId and model.isActive='Y' " +
	   				   "  order by model.selfAppraisalDesignation.designation ");
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("tdpCadreId", tdpCadreId);
	    return query.list();
  }
  public Long getTdpCadreId(Long selfAppraisalCandiateId){
	  Query query = getSession().createQuery("select model.tdpCadreId from SelfAppraisalCandidate model where model.isActive='Y' and model.selfAppraisalCandidateId=:selfAppraisalCandiateId ");
	  query.setParameter("selfAppraisalCandiateId", selfAppraisalCandiateId);
	  return (Long)query.uniqueResult();
  }
  
  public List<Long> getDesignationIdsList(Long tdpCadreId){
	  Query query = getSession().createQuery(" select distinct  model.selfAppraisalDesignation.selfAppraisalDesignationId from SelfAppraisalCandidate model " +
               "  where model.tdpCadreId=:tdpCadreId and model.isActive='Y' order by model.selfAppraisalDesignation.designation  ");
	  query.setParameter("tdpCadreId", tdpCadreId);
	  return query.list();
  }
  
  public List<Object[]> getCandidateInfoOfDesginations(Long tdpCadreId,Set<Long> designationIds){
	  
	  Query query = getSession().createQuery(" select model.selfAppraisalDesignationId,model.selfAppraisalCandidateId " +
	  		" from SelfAppraisalCandidate model " +
	  		" where model.tdpCadreId = :tdpCadreId " +
	  		" and model.selfAppraisalDesignationId in (:designationIds) ");
	  
	  query.setParameter("tdpCadreId", tdpCadreId);
	  query.setParameterList("designationIds", designationIds);
	   
	  return query.list();
	  
  }
  public List<Object[]> getTourMemberDetails(){
	  Query query = getSession().createQuery(" select distinct " +
	  										" model.tdpCadreId,model.selfAppraisalDesignation.designation " +
									  		" from SelfAppraisalCandidate model " +
									  		" where model.isActive = 'Y' " +
									  		" and model.selfAppraisalDesignation.isActive = 'Y'");
	   return query.list();
	  
  }

	public List<Object[]> getAllDesignationDetatilsByTdpCadreId(Long tdpCadreId) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select "
				+ " model.selfAppraisalDesignation.selfAppraisalDesignationId,"
				+ " model.selfAppraisalCandidateId,"
				+ " model.tdpCadre.firstname " 
				+ " from "
				+ " SelfAppraisalCandidate model " 
				+ " where "
				+ " model.isActive='Y' "
				+ " and model.selfAppraisalDesignation.isActive='Y' "
				+ " and model.tdpCadre.tdpCadreId=:tdpCadreId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
  
}
