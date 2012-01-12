package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.SourceLanguage;

public class CategoryDAO extends GenericDaoHibernate<Category, Long> implements ICategoryDAO {
	public CategoryDAO() {
		super(Category.class);
	}
	

}
