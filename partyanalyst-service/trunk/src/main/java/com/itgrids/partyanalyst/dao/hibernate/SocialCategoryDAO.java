package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.model.SocialCategory;

public class SocialCategoryDAO extends GenericDaoHibernate<SocialCategory, Long> implements ISocialCategoryDAO  {

	public SocialCategoryDAO() {
		super(SocialCategory.class);		 
	}

	@SuppressWarnings("unchecked")
	public List<SocialCategory> getSocialCategoryDetailsByCategoryType(String categoryType) {
		
		return getHibernateTemplate().find("from SocialCategory model where model.category = ?",categoryType);
	}

}
