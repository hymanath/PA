package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IErrorStatusSmsDAO;
import com.itgrids.partyanalyst.model.ErrorStatusSms;



public class ErrorStatusSmsDAO  extends GenericDaoHibernate<ErrorStatusSms, Long> implements IErrorStatusSmsDAO{

	public ErrorStatusSmsDAO() {
		super(ErrorStatusSms.class);
	}

}
