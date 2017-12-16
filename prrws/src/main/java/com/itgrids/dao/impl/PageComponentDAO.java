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

	public List<Object[]> getPageWiseComponents(Long pageId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.page.pageId,model.page.pageName,"
				+ " model.pageComponentId,model.component,model.orderNo,"
				+ " model.page.shortName,model.url"
				+ " from PageComponent model"
				+ " where model.isDeleted = 'N' and model.page.isDeleted = 'N' and model.page.pageId > 1");
		if(pageId != null && pageId.longValue() > 0L)
			sb.append(" and model.pageId = :pageId");
		sb.append(" group by model.pageId,model.orderNo");
		/*Query query = getSession().createQuery("select distinct model.page.pageId,model.page.pageName,"
				+ " model.pageComponentId,model.component,model.orderNo,"
				+ " model.page.shortName,model.url"
				+ " from PageComponent model"
				+ " where model.isDeleted = 'N' and model.page.isDeleted = 'N' and model.page.pageId > 1"
				+ " group by model.pageId,model.orderNo");*/
		Query query = getSession().createQuery(sb.toString());
		if(pageId != null && pageId.longValue() > 0L)
			query.setParameter("pageId", pageId);
		return query.list();
	}
}
