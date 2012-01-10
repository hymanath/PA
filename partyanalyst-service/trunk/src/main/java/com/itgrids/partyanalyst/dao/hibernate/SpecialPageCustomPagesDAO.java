package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
}
