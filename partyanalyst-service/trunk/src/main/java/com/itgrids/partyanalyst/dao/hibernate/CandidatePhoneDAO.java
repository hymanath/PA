package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePhoneDAO;
import com.itgrids.partyanalyst.model.CandidateAddress;
import com.itgrids.partyanalyst.model.CandidatePhone;



public class CandidatePhoneDAO extends GenericDaoHibernate<CandidatePhone, Long> implements ICandidatePhoneDAO{

	public CandidatePhoneDAO() {
		super(CandidatePhone.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<CandidatePhone> getCandidatePhoneDetails(Long candidateId){
	
		Query query = getSession().createQuery(" from CandidatePhone model where model.candidate.candidateId = ?");
		query.setParameter(0,candidateId);
		return query.list();
		
	}


}
