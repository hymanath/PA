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
	 public Long getCandidateId(Long tdpCadreId){
		 Query query = getSession().createQuery("select model.selfAppraisalCandidateId from SelfAppraisalCandidate model where model.tdpCadreId=:tdpCadreId");
		 query.setParameter("tdpCadreId", tdpCadreId);
		 return (Long) query.uniqueResult();
	 }
	 
}
