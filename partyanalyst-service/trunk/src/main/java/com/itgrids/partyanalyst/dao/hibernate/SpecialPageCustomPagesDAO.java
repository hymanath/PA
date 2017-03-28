package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageCustomPagesDAO;
import com.itgrids.partyanalyst.model.SpecialPageCustomPages;

public class SpecialPageCustomPagesDAO extends GenericDaoHibernate<SpecialPageCustomPages,Long> implements ISpecialPageCustomPagesDAO{

	public SpecialPageCustomPagesDAO()
	{
		super(SpecialPageCustomPages.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCustomPagesOfASpecialPage(Long specialPageId)
	{
		return getHibernateTemplate().find("select model.customPage.name,model.customPage.customPageType.customPageType from SpecialPageCustomPages model where " +
				"model.specialPage.specialPageId = ? order by model.customPage.customPageType.orderNo",specialPageId);
		
	}
	@SuppressWarnings("unchecked")
	public List<SpecialPageCustomPages> specialIdExistsOrNot(Long pageId,String customPageName)
	{
		Object[] params = {pageId,customPageName};
		return getHibernateTemplate().find("select model.customPage.customPageId from SpecialPageCustomPages model where model.specialPage.specialPageId = ? and model.customPage.name = ?",params);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialCustomPage(Long pageId)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.customPage.name,model.customPage.customPageType.customPageType,model.customPage.customPageType.customPageTypeId from SpecialPageCustomPages model"
				+ " where model.specialPage.specialPageId =:pageId");
		
		Query queryobj = getSession().createQuery(query.toString());
		queryobj.setLong("pageId", pageId);
		
		 return queryobj.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object> getSpecialPageId(Long pageId)
	{
		return getHibernateTemplate().find("select model.customPage.customPageId from SpecialPageCustomPages model where model.specialPage.specialPageId = ? ",pageId);
	}
}
