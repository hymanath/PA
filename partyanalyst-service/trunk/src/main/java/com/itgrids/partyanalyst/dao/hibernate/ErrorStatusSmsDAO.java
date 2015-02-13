package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IErrorStatusSmsDAO;
import com.itgrids.partyanalyst.model.ErrorStatusSms;



public class ErrorStatusSmsDAO  extends GenericDaoHibernate<ErrorStatusSms, Long> implements IErrorStatusSmsDAO{

	public ErrorStatusSmsDAO() {
		super(ErrorStatusSms.class);
	}
	
	public List<ErrorStatusSms> getErrorSmsInfo()
	{
		Query query = getSession().createQuery("select model from ErrorStatusSms model order by model.insertedTime desc");
		query.setMaxResults(1000);
		return query.list();
		
	}

}
