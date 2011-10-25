package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
}
