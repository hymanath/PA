package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficer;

public class GovtDepartmentDesignationOfficerDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficer, Long> implements IGovtDepartmentDesignationOfficerDAO{

	public GovtDepartmentDesignationOfficerDAO() {
		super(GovtDepartmentDesignationOfficer.class);
		
	}

	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficerId," +
												" model.officerName" +
												" from GovtDepartmentDesignationOfficer model" +
												" where model.govtDepartmentDesignationLocation.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
												" and model.govtDepartmentDesignationLocation.govtDepartmentLevel.govtDepartmentLevelId = :levelId" +
												" and model.govtDepartmentDesignationLocation.levelValue = :levelValue");
		query.setParameter("designationId", designationId);
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		
		return query.list();
	}
}
