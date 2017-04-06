package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationRelationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocationRelation;

public class GovtDepartmentWorkLocationRelationDAO extends GenericDaoHibernate<GovtDepartmentWorkLocationRelation, Long> implements IGovtDepartmentWorkLocationRelationDAO{

	public GovtDepartmentWorkLocationRelationDAO(){
		super(GovtDepartmentWorkLocationRelation.class);
	}
}
