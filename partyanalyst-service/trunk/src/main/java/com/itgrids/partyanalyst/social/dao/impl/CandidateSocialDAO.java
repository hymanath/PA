package com.itgrids.partyanalyst.social.dao.impl;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;


import com.itgrids.partyanalyst.dto.SocialNetworkVO;
import com.itgrids.partyanalyst.model.CandidateWebsite;
import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;
import com.itgrids.partyanalyst.social.model.CandidateSocial;

public class CandidateSocialDAO extends GenericDaoHibernate<CandidateSocial, Long> implements ICandidateSocialDAO{

	public CandidateSocialDAO() {
		super(CandidateSocial.class);
	} 
	
	public List<Object[]> getCandidateNames(){
		
		Query query = getSession().createQuery("select model.candidate.lastname,model.profileId from CandidateSocial model where model.socialNetworkSite.socialNetworkId=1");
		//select candidate_id,last_name from candidate where candidate_id=(select candidate_id from candidate_social where social_network_id=1)
		
		return query.list();
		
	}
	
public List<CandidateSocial> getCandidateUrlDetails(Long candidateId){
		
		
		Query query = getSession().createQuery("from CandidateSocial model where model.candidate.candidateId = ?");
		query.setParameter(0,candidateId);
		return query.list();
	
	}




}
