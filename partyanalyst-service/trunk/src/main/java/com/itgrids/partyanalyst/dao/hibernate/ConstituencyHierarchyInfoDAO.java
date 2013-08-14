package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstituencyHierarchyInfoDAO;
import com.itgrids.partyanalyst.model.ConstituencyHierarchyInfo;

public class ConstituencyHierarchyInfoDAO extends GenericDaoHibernate<ConstituencyHierarchyInfo, Long> implements IConstituencyHierarchyInfoDAO{

	public ConstituencyHierarchyInfoDAO() {
		super(ConstituencyHierarchyInfo.class);
	}
	
	
	public Integer deleteConstituencyBasicInfo(Long constituencyId,Long publicationId,Long userId)
	{
		Query query = getSession().createQuery(" delete from ConstituencyHierarchyInfo model where model.constituencyId =:constituencyId and model.userId =:userId " +
				" and model.publicationDateId =:publicationDateId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationId);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyHierarchyInfo> getConstituencyHierarchyInfoList(Long constituencyId,Long userId)
	{
		Query query = getSession().createQuery(" from ConstituencyHierarchyInfo model where model.constituencyId =:constituencyId and model.userId =:userId ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}

}
