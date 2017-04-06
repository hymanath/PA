package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocationNew;

public class GovtAlertDepartmentLocationNewDAO extends GenericDaoHibernate<GovtAlertDepartmentLocationNew, Long> implements IGovtAlertDepartmentLocationNewDAO {

	public GovtAlertDepartmentLocationNewDAO(){
		super(GovtAlertDepartmentLocationNew.class);
	}
	
}
