package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDemoRequestDAO;
import com.itgrids.partyanalyst.model.DemoRequest;
import com.itgrids.partyanalyst.utils.IConstants;

public class DemoRequestDAO extends GenericDaoHibernate<DemoRequest, Long> implements IDemoRequestDAO{

	public DemoRequestDAO()
	{
		super(DemoRequest.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DemoRequest> getDemoRequestList()
	{
		return getHibernateTemplate().find(" from DemoRequest model where model.isDelete = 'false' or model.isDelete is null order by model.requesteTime desc ");
	}
	
	public DemoRequest getDemoRequestByDemoRequestId(Long demoRequestId)
	{
		Query query = getSession().createQuery(" from DemoRequest model where model.demoRequestId =:demoRequestId ");
		query.setParameter("demoRequestId",demoRequestId);
		return (DemoRequest) query.uniqueResult();
	}
	
	public Integer deleteDemoRequestByDemoRequestId(Long demoRequestId)
	{
		Query query = getSession().createQuery(" update DemoRequest model set model.isDelete =:isDelete where model.demoRequestId =:demoRequestId ");
		query.setParameter("isDelete", IConstants.TRUE);
		query.setParameter("demoRequestId", demoRequestId);
		return query.executeUpdate();
	}
	
}
