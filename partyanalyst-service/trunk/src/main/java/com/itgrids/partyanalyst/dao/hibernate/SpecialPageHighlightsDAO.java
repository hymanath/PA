package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageHighlightsDAO;
import com.itgrids.partyanalyst.model.SpecialPageHighlights;

public class SpecialPageHighlightsDAO extends GenericDaoHibernate<SpecialPageHighlights, Long> implements ISpecialPageHighlightsDAO{

	public SpecialPageHighlightsDAO() {
		super(SpecialPageHighlights.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMaxOrderNo(Long specialPageId)
	{
		return getHibernateTemplate().find("select max(model.orderNo) from SpecialPageHighlights model where model.specialPage.specialPageId=?",specialPageId);
	}
	
	public Integer deleteSpecialHighlightsDescription(Long id)
	{
		Query queryObject=getSession().createQuery("delete from specialpagehighlights model where model.specialPageHighlightsId=?");
		queryObject.setParameter(0,id);
		return queryObject.executeUpdate();
	}

	
	public List<Object[]> getSpecialPageHighLightsBySpecailPageId(Long specialPageId)
	{
		return getHibernateTemplate().find("select model.orderNo,model.description from SpecialPageHighlights model where model.specialPage.specialPageId = ? order by model.orderNo",specialPageId);
	}
}
