package com.itgrids.partyanalyst.social.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.social.model.CandidateSocial;


public interface ICandidateSocialDAO extends GenericDao<CandidateSocial, Long>{
	public List<Object[]> getCandidateNames();
	public List<CandidateSocial> getCandidateUrlDetails(Long candidateId);
	//public List<Object[]> getAllCasteDetails(Long casteGroupId);
}
