package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentDocumentDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentDocument;

public class AlertDepartmentDocumentDAO extends GenericDaoHibernate<AlertDepartmentDocument, Long> implements IAlertDepartmentDocumentDAO{

	public AlertDepartmentDocumentDAO() {
		super(AlertDepartmentDocument.class);
		
	}

}
