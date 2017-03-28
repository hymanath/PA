package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardComponentDAO;
import com.itgrids.partyanalyst.model.DashboardComponent;

public class DashboardComponentDAO extends GenericDaoHibernate<DashboardComponent,Long> implements IDashboardComponentDAO {
	
	public DashboardComponentDAO() {
		super(DashboardComponent.class);
	}
	
	

}
