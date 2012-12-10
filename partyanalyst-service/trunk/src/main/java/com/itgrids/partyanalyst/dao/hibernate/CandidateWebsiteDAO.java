package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateWebsiteDAO;
import com.itgrids.partyanalyst.model.CandidateCaste;
import com.itgrids.partyanalyst.model.CandidateWebsite;
import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;
import com.itgrids.partyanalyst.social.model.CandidateSocial;

public class CandidateWebsiteDAO extends GenericDaoHibernate<CandidateWebsite, Long> implements ICandidateWebsiteDAO {

	public CandidateWebsiteDAO() {
		super(CandidateWebsite.class);
		// TODO Auto-generated constructor stub
	}

	public List<CandidateWebsite> getCandidateWebsiteDetails1(Long candidateId){
		
		
		Query query = getSession().createQuery("from CandidateWebsite model where model.candidate.candidateId = ? order by model.websiteAddress desc");
		query.setParameter(0,candidateId);
		return query.list();
		
		
		
		
		
	}
	
}
