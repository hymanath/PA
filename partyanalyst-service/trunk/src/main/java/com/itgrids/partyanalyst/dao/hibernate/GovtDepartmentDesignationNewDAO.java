package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationNew;

public class GovtDepartmentDesignationNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationNew, Long> implements
		IGovtDepartmentDesignationNewDAO {
       public GovtDepartmentDesignationNewDAO(){
    	   super(GovtDepartmentDesignationNew.class);
       }
}
