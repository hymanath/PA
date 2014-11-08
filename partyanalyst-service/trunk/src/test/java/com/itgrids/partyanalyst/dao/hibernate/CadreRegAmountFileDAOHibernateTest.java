package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreRegAmountFileDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;

public class CadreRegAmountFileDAOHibernateTest extends BaseDaoTestCase{

	private ICadreRegAmountFileDAO cadreRegAmountFileDAO;

	public void setCadreRegAmountFileDAO(
			ICadreRegAmountFileDAO cadreRegAmountFileDAO) {
		this.cadreRegAmountFileDAO = cadreRegAmountFileDAO;
	}
	
	public void test()
	{
		cadreRegAmountFileDAO.getAll();
	}
}
