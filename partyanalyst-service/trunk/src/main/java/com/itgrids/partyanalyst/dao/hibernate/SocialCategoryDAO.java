package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.model.SocialCategory;

public class SocialCategoryDAO extends GenericDaoHibernate<SocialCategory, Long> implements ISocialCategoryDAO  {

	public SocialCategoryDAO() {
		super(SocialCategory.class);		 
	}

}
