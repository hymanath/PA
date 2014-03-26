package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IForgetPasswordAccessKeyDAO;

public class ForgetPasswordAccessKeyDAOHibernateTest extends BaseDaoTestCase{
	
	private IForgetPasswordAccessKeyDAO forgetPasswordAccessKeyDAO;

	public IForgetPasswordAccessKeyDAO getForgetPasswordAccessKeyDAO() {
		return forgetPasswordAccessKeyDAO;
	}

	/*public void test(){
		String UUIDUserToken = UUID.randomUUID().toString();
		System.out.println(UUIDUserToken);
	}*/
	
	/*public void testByRandomNumber(){
			ForgetPasswordAccessKey fo=forgetPasswordAccessKeyDAO.getModelByRandomNumber("46e8d235-f5c0-4f50-b080-cf47f55394af");
			System.out.println("Hi SASI");
		
	}*/
	
	public void testUpdateIsValidated(){
		int count=forgetPasswordAccessKeyDAO.updateIsValidated("d3b97c46-7b75-4310-8ffc-a8dac8333de3");
		System.out.println("Hi SASI");
	}
		

}
