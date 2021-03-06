package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.utils.IConstants;

public class SpecialPageDAO extends GenericDaoHibernate<SpecialPage,Long> implements ISpecialPageDAO {
	
	public SpecialPageDAO()
	{
		super(SpecialPage.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageDetails(Long specialPageId){
		
		Query query = getSession().createQuery("select model.title,model.heading,model.profileImgPath ,model.specialPageId FROM SpecialPage model where model.specialPageId =:specialPageId and model.isDelete =:isDelete");
			
			query.setParameter("specialPageId", specialPageId);
			query.setParameter("isDelete", IConstants.FALSE);
			
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageNames()
	{
		return getHibernateTemplate().find("select model.specialPageId,model.name from SpecialPage model where model.isDelete = 'false' order by name");
	}
	@SuppressWarnings("unchecked")
	public Object getSpecialPageName(Long specialPageId)
	{
		return getHibernateTemplate().find("select model.name from SpecialPage model where model.specialPageId = ?",specialPageId);
	}

}
