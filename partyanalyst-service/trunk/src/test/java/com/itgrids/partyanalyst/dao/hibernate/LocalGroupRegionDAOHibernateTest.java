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

import com.itgrids.partyanalyst.dao.ILocalGroupRegionDAO;
import com.itgrids.partyanalyst.model.LocalGroupRegion;

/**
 * @author Sai Krishna
 *
 */
public class LocalGroupRegionDAOHibernateTest extends BaseDaoTestCase {

	private ILocalGroupRegionDAO localGroupRegionDAO;

	public ILocalGroupRegionDAO getLocalGroupRegionDAO() {
		return localGroupRegionDAO;
	}

	public void setLocalGroupRegionDAO(ILocalGroupRegionDAO localGroupRegionDAO) {
		this.localGroupRegionDAO = localGroupRegionDAO;
	}
	
	
	@Test
	public void testGetAllLocalGroupRegions(){
		List<LocalGroupRegion> list = localGroupRegionDAO.getAll();
		if(list != null)
			System.out.println("Results List Size :" + list.size());
	}
}
