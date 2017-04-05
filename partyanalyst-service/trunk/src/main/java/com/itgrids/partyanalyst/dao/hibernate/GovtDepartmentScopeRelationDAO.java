package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeRelationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentScopeRelation;

public class GovtDepartmentScopeRelationDAO extends GenericDaoHibernate<GovtDepartmentScopeRelation, Long> implements IGovtDepartmentScopeRelationDAO{

	public GovtDepartmentScopeRelationDAO() {
		super(GovtDepartmentScopeRelation.class);
		
	}
	

}
