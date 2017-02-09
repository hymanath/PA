package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetails;

public class GovtDepartmentDesignationOfficerDetailsDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerDetails, Long> implements IGovtDepartmentDesignationOfficerDetailsDAO{

	public GovtDepartmentDesignationOfficerDetailsDAO() {
		super(GovtDepartmentDesignationOfficerDetails.class);
		
	}

	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtOfficer.govtOfficerId," +
												" model.govtOfficer.officerName" +
												" from GovtDepartmentDesignationOfficerDetails model" +
												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
												" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId" +
												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
												" and model.isDeleted = 'N'");
		query.setParameter("designationId", designationId);
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		
		return query.list();
	}
}
