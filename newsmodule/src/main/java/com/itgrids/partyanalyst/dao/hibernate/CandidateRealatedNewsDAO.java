package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateRealatedNewsDAO;
import com.itgrids.partyanalyst.model.CandidateRealatedNews;

public class CandidateRealatedNewsDAO extends GenericDaoHibernate<CandidateRealatedNews, Long> implements 
ICandidateRealatedNewsDAO {
	
	
	public CandidateRealatedNewsDAO() {
		super(CandidateRealatedNews.class);
	}

}
