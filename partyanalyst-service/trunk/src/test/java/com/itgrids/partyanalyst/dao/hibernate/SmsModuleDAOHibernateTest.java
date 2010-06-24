package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISmsModuleDAO;

public class SmsModuleDAOHibernateTest extends BaseDaoTestCase {

	
	private ISmsModuleDAO smsModuleDAO;

	public ISmsModuleDAO getSmsModuleDAO() {
		return smsModuleDAO;
	}

	public void setSmsModuleDAO(ISmsModuleDAO smsModuleDAO) {
		this.smsModuleDAO = smsModuleDAO;
	}
	
	
	public void testGetBysmsModuleId(){
		List result = smsModuleDAO.findBySmsModuleId(1l);
		System.out.println(result.size());
	}
	

}
