package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidate;
import com.itgrids.partyanalyst.utils.IConstants;

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
		   		           " model.tdpCadre.image" +
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
		   		          " count(distinct model.selfAppraisalCandidateId) " +
		   		          " from SelfAppraisalCandidate model where model.isActive='Y' and model.selfAppraisalDesignation.isActive='Y' ");
		   if(desigIds != null){
			   queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in  (:desigIds) ");  
		   }
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId " +  
		   				  " order by model.selfAppraisalDesignation.selfAppraisalDesignationId ");
		   Query query = getSession().createQuery(queryStr.toString());
		   if(desigIds != null){
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
}
