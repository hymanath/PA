package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.model.ApplicationStatus;

public class ApplicationStatusDAO extends GenericDaoHibernate<ApplicationStatus, Long> implements IApplicationStatusDAO{

	public ApplicationStatusDAO() {
		super(ApplicationStatus.class);
		
	}
	
	public List<Object[]> getAllApplicationStatusList(){
		
		Query query = getSession().createQuery("select model.applicationStatusId,model.status from ApplicationStatus model ");
		return query.list();
		
	}

}
