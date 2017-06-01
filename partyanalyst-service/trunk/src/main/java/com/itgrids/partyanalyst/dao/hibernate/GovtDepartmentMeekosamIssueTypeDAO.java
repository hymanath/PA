package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentMeekosamIssueTypeDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentMeekosamIssueType;

public class GovtDepartmentMeekosamIssueTypeDAO extends	GenericDaoHibernate<GovtDepartmentMeekosamIssueType, Long> implements IGovtDepartmentMeekosamIssueTypeDAO {
	
	public GovtDepartmentMeekosamIssueTypeDAO() {
		super(GovtDepartmentMeekosamIssueType.class);
	}
	
	public List<Object[]> getMeekosamIssueTypeListByDept(Long deptId){
		Query query = getSession().createQuery("select distinct model.meekosamIssueType.meekosamIssueTypeId," +
											" model.meekosamIssueType.issueType" +
											" from GovtDepartmentMeekosamIssueType model" +
											" where model.govtDepartment.govtDepartmentId = :deptId" +
											" and model.isDeleted = 'N' and model.meekosamIssueType.isActive = 'Y'" +
											" and model.meekosamIssueType.parentMeekosamIssueTypeId is null");
		query.setParameter("deptId", deptId);
		return query.list();
	}
}
