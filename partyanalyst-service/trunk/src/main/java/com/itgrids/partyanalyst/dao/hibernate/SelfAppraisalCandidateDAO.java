package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidate;

public class SelfAppraisalCandidateDAO extends GenericDaoHibernate<SelfAppraisalCandidate, Long> implements
		ISelfAppraisalCandidateDAO {
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
	 public List<Object[]> getTotalLeadersDesignationBy(Long desigId){
		 StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select " +
		   		          " model.selfAppraisalDesignation.selfAppraisalDesignationId, " +
		   		          " model.selfAppraisalDesignation.designation," +
		   		          " count(distinct model.selfAppraisalCandidateId) " +
		   		          " from SelfAppraisalCandidate model where model.isActive='Y' and model.selfAppraisalDesignation.isActive='Y' ");
		   if(desigId != null){
			   queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId = :desigId ");
		   }
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId " +  
		   				  " order by model.selfAppraisalDesignation.selfAppraisalDesignationId ");
		   Query query = getSession().createQuery(queryStr.toString());
		   if(desigId != null){
			   query.setParameter("desigId",desigId);
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
}
