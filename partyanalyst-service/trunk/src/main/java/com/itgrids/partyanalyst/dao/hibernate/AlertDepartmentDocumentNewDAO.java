package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentDocumentNewDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentDocumentNew;

public class AlertDepartmentDocumentNewDAO extends GenericDaoHibernate<AlertDepartmentDocumentNew, Long> implements
		IAlertDepartmentDocumentNewDAO {
      public AlertDepartmentDocumentNewDAO(){
    	  super(AlertDepartmentDocumentNew.class);
      }
      
}
