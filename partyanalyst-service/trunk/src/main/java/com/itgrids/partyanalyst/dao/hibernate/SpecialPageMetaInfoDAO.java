package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.ISpecialPageMetaInfoDAO;
import com.itgrids.partyanalyst.model.SpecialPageMetaInfo;

public class SpecialPageMetaInfoDAO extends GenericDaoHibernate<SpecialPageMetaInfo,Long> implements ISpecialPageMetaInfoDAO{
	
	public SpecialPageMetaInfoDAO()
	{
		super(SpecialPageMetaInfo.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMetaInfoForASpecialPage(Long specialPageId)
	{
		return getHibernateTemplate().find(" select model.keywords, model.description from SpecialPageMetaInfo model where model.specialPage.specialPageId = ?",specialPageId);
	}

}
