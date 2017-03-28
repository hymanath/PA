package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageInfoDAO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.model.SpecialPageInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class SpecialPageInfoDAO extends GenericDaoHibernate<SpecialPageInfo,Long> implements ISpecialPageInfoDAO {

	public SpecialPageInfoDAO()
	{
		super(SpecialPageInfo.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpecialPageInfo> getSpecialPageInfo(Long specialPageId)
	{
		return getHibernateTemplate().find("select model from SpecialPageInfo model where model.specialPage.specialPageId=?",specialPageId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPagesForHomePage()
	{
		Query queryObj = getSession().createQuery("select model.title,model.description,model.showImgPath,model.specialPage.specialPageId from SpecialPageInfo" +
				" model where model.isDisplayEnabled = 'Yes'");
		queryObj.setFirstResult(0);
		if("tdpserver".equalsIgnoreCase(IConstants.DEPLOYED_HOST))
		 queryObj.setMaxResults(3);
		else
		 queryObj.setMaxResults(5);	
		return queryObj.list();
		
	}
	
		
	@SuppressWarnings("unchecked")
	public List<SpecialPageInfo> getAllSpecialPageListForHomePage()
	{
		return getHibernateTemplate().find("select model from SpecialPageInfo model");
	}
	
	public Long getSpecialPageInfoIdBySpecialPageId(Long specialPageId)
	{
		Query query = getSession().createQuery("select model.specialPageInfoId from SpecialPageInfo model where model.specialPage.specialPageId=?");
		query.setParameter(0, specialPageId);
		return (Long)query.uniqueResult();
	}
	
	
}
