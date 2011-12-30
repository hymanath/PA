package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;
import com.itgrids.partyanalyst.model.SpecialPageDescription;

public class SpecialPageDescriptionDAO extends GenericDaoHibernate<SpecialPageDescription,Long> implements ISpecialPageDescriptionDAO {
	
	public SpecialPageDescriptionDAO()
	{
		super(SpecialPageDescription.class);
	} 
	@SuppressWarnings("unchecked")
	public List<Object> getSpecialPageDescription(Long specialPageId){		
		return getHibernateTemplate().find("select model.description from SpecialPageDescription model where model.specialPageId=? order by model.orderNo asc",specialPageId);
	}
}
