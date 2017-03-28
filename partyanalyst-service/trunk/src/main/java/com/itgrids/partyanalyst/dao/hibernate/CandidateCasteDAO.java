package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateCasteDAO;
import com.itgrids.partyanalyst.model.CandidateCaste;
import com.itgrids.partyanalyst.model.CandidatePhone;

public class CandidateCasteDAO extends GenericDaoHibernate<CandidateCaste, Long> implements 
ICandidateCasteDAO {
	public CandidateCasteDAO(){
		super(CandidateCaste.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateCaste> getCandidatesCasteDetails(Long candidateCasteId){
		CandidateCasteDAO candidateCasteDAO=null;
		Query query = getSession().createQuery("");
		
		//Object[] obj = list.get(0);
		//System.out.println(obj[0]);
		//System.out.println(obj[1]);
		return query.list();
	
	}
	public List<CandidateCaste> getCandidateCasteDetails1(Long candidateId){
		
		Query query = getSession().createQuery(" from CandidateCaste model where model.candidate.candidateId = ?");
		query.setParameter(0,candidateId);
		return query.list();
		
	}
	
	
}
