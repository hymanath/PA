package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDemoRequestActionsDAO;
import com.itgrids.partyanalyst.model.DemoRequestActions;

public class DemoRequestActionsDAO extends GenericDaoHibernate<DemoRequestActions, Long> implements IDemoRequestActionsDAO{

	public DemoRequestActionsDAO(){
		super(DemoRequestActions.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDemoRequestActionsByDemoRequestId(Long demoRequestId)
	{
	 Query query = getSession().createQuery(" select model.type,model.content,model.response,model.actionTime,model.user.firstName,model.user.lastName " +
	 		" from DemoRequestActions model where model.demoRequest.demoRequestId =:demoRequestId order by model.actionTime desc ");
	 
	 query.setParameter("demoRequestId", demoRequestId);
	 return query.list();
		
	}
	
}
