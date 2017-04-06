package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentNewDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentCommentNew;

public class AlertDepartmentCommentNewDAO extends GenericDaoHibernate<AlertDepartmentCommentNew, Long> implements
IAlertDepartmentCommentNewDAO {
     public AlertDepartmentCommentNewDAO(){
    	 super(AlertDepartmentCommentNew.class);
     }
}
