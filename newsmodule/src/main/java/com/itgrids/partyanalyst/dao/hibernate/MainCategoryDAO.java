package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMainCategoryDAO;
import com.itgrids.partyanalyst.model.MainCategory;

public class MainCategoryDAO extends GenericDaoHibernate<MainCategory,Long>implements IMainCategoryDAO{
	
	public MainCategoryDAO() {
		super(MainCategory.class);
		
	}
	
	public List<Object[]> getCategories()
	{
		return getHibernateTemplate().find("select model.mainCategoryId,model.categoryType from MainCategory model");
	}
}
