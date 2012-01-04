package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

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
		return getHibernateTemplate().find("select model.description from SpecialPageDescription model where model.specialPage.specialPageId=? order by model.orderNo asc",specialPageId);
	}
	
	public List<Object[]> getEventDescription(Long specialPageId){
		return getHibernateTemplate().find("select model.orderNo,model.description,model.specialPageDescriptionId from SpecialPageDescription model where model.specialPage.specialPageId=?",specialPageId);
	}
	public Integer deleteEventProfileDescriptionById(Long specialPageDescriptionId)
	{
		Query queryObject=getSession().createQuery("delete from SpecialPageDescription model where model.specialPageDescriptionId=?");
		queryObject.setParameter(0,specialPageDescriptionId);
		return queryObject.executeUpdate();
	}
	public List<Object> getMaxOrderNo(Long specialPageId)
	{
		return getHibernateTemplate().find("select max(model.orderNo) from SpecialPageDescription model where model.specialPage.specialPageId=?",specialPageId);
	}
}
