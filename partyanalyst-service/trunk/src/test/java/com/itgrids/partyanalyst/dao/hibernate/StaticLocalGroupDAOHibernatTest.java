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

import com.itgrids.partyanalyst.dao.IStaticLocalGroupDAO;
import com.itgrids.partyanalyst.model.StaticLocalGroup;

/**
 * @author Sai Krishna
 *
 */
public class StaticLocalGroupDAOHibernatTest extends BaseDaoTestCase {

	private IStaticLocalGroupDAO staticLocalGroupDAO;

	public IStaticLocalGroupDAO getStaticLocalGroupDAO() {
		return staticLocalGroupDAO;
	}

	public void setStaticLocalGroupDAO(IStaticLocalGroupDAO staticLocalGroupDAO) {
		this.staticLocalGroupDAO = staticLocalGroupDAO;
	}
	
	@Test
	public void testGetAllStaticLocalGroups(){
		List<StaticLocalGroup> list = staticLocalGroupDAO.getAll();
		if(list != null)
			System.out.println("Results List Size :"+ list.size());
	}
}
