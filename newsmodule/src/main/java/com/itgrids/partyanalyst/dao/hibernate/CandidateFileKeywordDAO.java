package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateFileKeywordDAO;
import com.itgrids.partyanalyst.model.CandidateFileKeyword;

public class CandidateFileKeywordDAO extends GenericDaoHibernate<CandidateFileKeyword, Long> implements ICandidateFileKeywordDAO{

	public CandidateFileKeywordDAO() {
		super(CandidateFileKeyword.class);
	}
	

}
