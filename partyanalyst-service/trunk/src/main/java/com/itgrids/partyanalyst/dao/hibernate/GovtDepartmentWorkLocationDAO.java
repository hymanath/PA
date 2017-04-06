package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocation;

public class GovtDepartmentWorkLocationDAO extends GenericDaoHibernate<GovtDepartmentWorkLocation, Long> implements IGovtDepartmentWorkLocationDAO{

	public GovtDepartmentWorkLocationDAO(){
		super(GovtDepartmentWorkLocation.class);
	}
}
