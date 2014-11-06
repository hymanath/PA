package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISmsJobStatusDAO;

public class SmsJobStatusDAOHibernateTest extends BaseDaoTestCase{
	private ISmsJobStatusDAO smsJobStatusDAO;

	public ISmsJobStatusDAO getSmsJobStatusDAO() {
		return smsJobStatusDAO;
	}

	public void setSmsJobStatusDAO(ISmsJobStatusDAO smsJobStatusDAO) {
		this.smsJobStatusDAO = smsJobStatusDAO;
	}
	
	public void test(){
		System.out.println("sasi");
		smsJobStatusDAO.getAll();
	}
}
