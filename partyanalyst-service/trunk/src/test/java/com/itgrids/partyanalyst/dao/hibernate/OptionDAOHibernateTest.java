package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOptionDAO;

public class OptionDAOHibernateTest extends BaseDaoTestCase  {

	private IOptionDAO optionDAO;

	public void setOptionDAO(IOptionDAO optionDAO) {
		this.optionDAO = optionDAO;
	}
	public void testGetAll(){
		optionDAO.getAll();
	}
}
