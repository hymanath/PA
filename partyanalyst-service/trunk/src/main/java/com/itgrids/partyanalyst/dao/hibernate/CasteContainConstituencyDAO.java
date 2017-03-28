package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICasteContainConstituencyDAO;
import com.itgrids.partyanalyst.model.CasteContainConstituency;


public class CasteContainConstituencyDAO extends GenericDaoHibernate<CasteContainConstituency,Long> implements ICasteContainConstituencyDAO{

	public CasteContainConstituencyDAO()
	{
		super(CasteContainConstituency.class);
	}
	
	
	public Long getRecordsCountToCasteContainConsti(Long constituencyId){
		Query query = getSession().createQuery("select count(*) from  CasteContainConstituency model where model.constituency.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return (Long)query.uniqueResult();
	}

}
