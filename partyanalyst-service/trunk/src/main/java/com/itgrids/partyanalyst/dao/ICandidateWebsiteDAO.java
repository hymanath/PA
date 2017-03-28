package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateCaste;
import com.itgrids.partyanalyst.model.CandidateWebsite;

public interface ICandidateWebsiteDAO extends GenericDao<CandidateWebsite, Long>  {
	
	public List<CandidateWebsite> getCandidateWebsiteDetails1(Long candidateId);

}
