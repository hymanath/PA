package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IErrorLogDAO;
import com.itgrids.model.ErrorLog;

@Repository
public class ErrorLogDAO extends GenericDaoHibernate<ErrorLog,Long> implements IErrorLogDAO{

	public ErrorLogDAO()
	{
		super(ErrorLog.class);
	}
}
