package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDepartmentAssignedOfficialDAO;
import com.itgrids.partyanalyst.model.DepartmentAssignedOfficial;

public class DepartmentAssignedOfficialDAO extends GenericDaoHibernate<DepartmentAssignedOfficial, Long> 
implements IDepartmentAssignedOfficialDAO{

	public DepartmentAssignedOfficialDAO(){
		super(DepartmentAssignedOfficial.class);
	}
	
}
