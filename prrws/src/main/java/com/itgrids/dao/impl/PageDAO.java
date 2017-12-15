package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPageDAO;
import com.itgrids.model.Page;
@Repository
public class PageDAO extends GenericDaoHibernate<Page, Long> implements IPageDAO{

	public PageDAO() {
		super(Page.class);
		
	}

}
