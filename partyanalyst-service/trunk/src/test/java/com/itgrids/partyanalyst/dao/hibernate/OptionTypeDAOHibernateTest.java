package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOptionTypeDAO;

public class OptionTypeDAOHibernateTest extends BaseDaoTestCase  {

	private IOptionTypeDAO optionTypeDAO;

	public void setOptionTypeDAO(IOptionTypeDAO optionTypeDAO) {
		this.optionTypeDAO = optionTypeDAO;
	}
	public void testGetAll(){
		optionTypeDAO.getAll();
	}
}
