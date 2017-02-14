package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDetailsDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentLevelDetails;

public class GovtDepartmentLevelDetailsDAO extends GenericDaoHibernate<GovtDepartmentLevelDetails, Long> implements IGovtDepartmentLevelDetailsDAO{

	public GovtDepartmentLevelDetailsDAO() {
		super(GovtDepartmentLevelDetails.class);
	}
	
	public List<Object[]> getLocationNamesByDepmntId(Long departmentId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentLevel.govtDepartmentLevelId," +
				" model.govtDepartmentLevel.levelName " +
				" from GovtDepartmentLevelDetails model " +
				" where model.govtDepartment.govtDepartmentId = :departmentId " +
				" and model.isDeleted = 'N'");
		query.setParameter("departmentId", departmentId);
		return query.list();
		
	}

}
