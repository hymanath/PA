package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGallaryKeywordDAO;
import com.itgrids.partyanalyst.model.GallaryKeyword;


public class GallaryKeywordDAO extends GenericDaoHibernate<GallaryKeyword, Long> implements IGallaryKeywordDAO{
	
	public GallaryKeywordDAO() {
		super(GallaryKeyword.class);
		
	}

}
