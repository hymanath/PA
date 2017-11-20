package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWorkMainCategoryDAO;
import com.itgrids.model.WorkMainCategory;

@Repository
public class WorkMainCategoryDAO extends GenericDaoHibernate<WorkMainCategory, Long> implements IWorkMainCategoryDAO{

	public WorkMainCategoryDAO() {
		super(WorkMainCategory.class);
		
	}

}
