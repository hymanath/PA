package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetailsNew;

public class GovtDepartmentDesignationOfficerDetailsNewDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerDetailsNew, Long>
		implements IGovtDepartmentDesignationOfficerDetailsNewDAO {
      public GovtDepartmentDesignationOfficerDetailsNewDAO(){
    	  super(GovtDepartmentDesignationOfficerDetailsNew.class);
      }
}
