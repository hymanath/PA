package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreIVREnquiryDAO;

public class CadreIVREnquiryDAOHibernateTest  extends BaseDaoTestCase{
	private ICadreIVREnquiryDAO cadreIVREnquiryDAO;

	public void setCadreIVREnquiryDAO(ICadreIVREnquiryDAO cadreIVREnquiryDAO) {
		this.cadreIVREnquiryDAO = cadreIVREnquiryDAO;
	}
	public void test(){
		cadreIVREnquiryDAO.getAll();
	}
}
