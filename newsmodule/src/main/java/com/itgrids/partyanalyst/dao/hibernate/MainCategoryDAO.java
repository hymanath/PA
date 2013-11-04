package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMainCategoryDAO;
import com.itgrids.partyanalyst.model.MainCategory;

public class MainCategoryDAO extends GenericDaoHibernate<MainCategory,Long>implements IMainCategoryDAO{
	
	public MainCategoryDAO() {
		super(MainCategory.class);
		
	}
}
