package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
	public void testGetOptionTypes(){
		
		List<Object[]> list = optionTypeDAO.getOptionTypes();
		System.out.println(list.size());
	}
}
