package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateProfileDescriptionDAO;
import com.itgrids.partyanalyst.model.CandidateProfileDescription;

public class CandidateProfileDescriptionDAO extends
		GenericDaoHibernate<CandidateProfileDescription, Long> implements
		ICandidateProfileDescriptionDAO {

	public CandidateProfileDescriptionDAO() {
		super(CandidateProfileDescription.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCandidateProfileDescription(Long candidateId){		
		return getHibernateTemplate().find("select model.description from CandidateProfileDescription model where model.candidate.candidateId=? order by model.orderNo asc",candidateId);
	}
	@SuppressWarnings("unchecked")
	public List<Object> getMaxOrderNo(Long candidateId){
		
		return getHibernateTemplate().find("select max(model.orderNo) from CandidateProfileDescription model where model.candidate.candidateId=?",candidateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateProfileInfo(Long candidateId){		
		return getHibernateTemplate().find("select model.orderNo , model.description , model.candidateProfileDescriptionId from CandidateProfileDescription model where model.candidate.candidateId=?",candidateId);
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object> deleteCandidateProfileDescriptionById(Long profDescId){		
		return getHibernateTemplate().find("DELETE  FROM CandidateProfileDescription model where model.candidateProfileDescriptionId = ?",profDescId);
	}     */       
	
	public Integer deleteCandidateProfileDescriptionById(Long profDescId) {
		Query queryObject = getSession().createQuery("DELETE  FROM CandidateProfileDescription model where model.candidateProfileDescriptionId = ?");
		queryObject.setParameter(0, profDescId);
		return queryObject.executeUpdate();
		
	}
	
}
