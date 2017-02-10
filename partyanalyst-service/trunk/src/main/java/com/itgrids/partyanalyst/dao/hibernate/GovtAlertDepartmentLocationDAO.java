package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationDAO;
import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocation;

public class GovtAlertDepartmentLocationDAO extends GenericDaoHibernate<GovtAlertDepartmentLocation, Long> implements IGovtAlertDepartmentLocationDAO{

	public GovtAlertDepartmentLocationDAO() {
		super(GovtAlertDepartmentLocation.class);
		
	}
	public List<Long> getDeptListForUser(Long userId){
		Query query = getSession().createQuery("select distinct model.govtDepartment.govtDepartmentId from GovtAlertDepartmentLocation model where model.userId = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}  

}
