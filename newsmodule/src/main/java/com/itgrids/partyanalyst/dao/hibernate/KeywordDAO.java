package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.model.Keyword;

public class KeywordDAO extends GenericDaoHibernate<Keyword, Long> implements IKeywordDAO{

	public KeywordDAO() {
		super(Keyword.class);
		
	}
	

}
