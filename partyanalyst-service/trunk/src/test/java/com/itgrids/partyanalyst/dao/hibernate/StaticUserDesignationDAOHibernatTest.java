/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IStaticUserDesignationDAO;
import com.itgrids.partyanalyst.model.StaticUserDesignation;

/**
 * @author Sai Krishna
 *
 */
public class StaticUserDesignationDAOHibernatTest extends BaseDaoTestCase {

	private IStaticUserDesignationDAO staticUserDesignationDAO;

	public IStaticUserDesignationDAO getStaticUserDesignationDAO() {
		return staticUserDesignationDAO;
	}

	public void setStaticUserDesignationDAO(
			IStaticUserDesignationDAO staticUserDesignationDAO) {
		this.staticUserDesignationDAO = staticUserDesignationDAO;
	}
	
	@Test
	public void testGetAllStaticUserDesignations(){
		List<StaticUserDesignation> list = staticUserDesignationDAO.getAll();
		if(list != null)
			System.out.println(" Results List Size :" + list.size());
	}
}
