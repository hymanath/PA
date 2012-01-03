package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.model.SpecialPage;

public class SpecialPageDAO extends GenericDaoHibernate<SpecialPage,Long> implements ISpecialPageDAO {
	
	public SpecialPageDAO()
	{
		super(SpecialPage.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageDetails(Long specialPageId){
		
		Query query = getSession().createQuery("SELECT model.title,model.heading,model.profileImgPath ,model.specialPageId FROM SpecialPage model where model.specialPageId =:specialPageId and model.isDelete =:isDelete");
			
			query.setParameter("specialPageId", specialPageId);
			query.setParameter("isDelete", "false");
			
		return query.list();
	}

}
