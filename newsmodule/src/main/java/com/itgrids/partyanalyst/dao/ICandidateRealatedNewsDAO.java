package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateRealatedNews;

public interface ICandidateRealatedNewsDAO extends GenericDao<CandidateRealatedNews, Long>{
	public List<Object[]> getCandidates();
}
