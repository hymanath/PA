package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDuplicateWrongMobileNumbersDAO;
import com.itgrids.partyanalyst.model.DuplicateWrongMobileNumbers;

public class DuplicateWrongMobileNumbersDAOHibernateTest extends BaseDaoTestCase {

	private IDuplicateWrongMobileNumbersDAO duplicateWrongMobileNumbersDAO;

	public void setDuplicateWrongMobileNumbersDAO(
			IDuplicateWrongMobileNumbersDAO duplicateWrongMobileNumbersDAO) {
		this.duplicateWrongMobileNumbersDAO = duplicateWrongMobileNumbersDAO;
	}
	
	public void testDetails(){
		
		List<DuplicateWrongMobileNumbers> list = duplicateWrongMobileNumbersDAO.getAll();
		System.out.println(list);
	}
	
}
