package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPageComponentDAO;
import com.itgrids.model.PageComponent;
@Repository
public class PageComponentDAO extends GenericDaoHibernate<PageComponent, Long> implements IPageComponentDAO{

	public PageComponentDAO() {
		super(PageComponent.class);
		
	}

	public List<Object[]> getPageWiseComponents(){
		Query query = getSession().createQuery("select distinct model.page.pageId,model.page.pageName,model.pageComponentId,model.component,model.orderNo"
				+ " from PageComponent model"
				+ " where model.isDeleted = 'N' and model.page.isDeleted = 'N' and model.page.pageId > 1"
				+ " group by model.pageId,model.orderNo");
		return query.list();
	}
}
