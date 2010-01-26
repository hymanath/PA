/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IEPaperDAO;
import com.itgrids.partyanalyst.model.EPaper;

public class EPaperDAOHibernateTest extends BaseDaoTestCase {

	private IEPaperDAO epaperDAO;

	public IEPaperDAO getEpaperDAO() {
		return epaperDAO;
	}


	public void setEpaperDAO(IEPaperDAO epaperDAO) {
		this.epaperDAO = epaperDAO;
	}


	@SuppressWarnings("unchecked")
	public void testFindEPapersForDistrictByDistrictId(){
		List list = epaperDAO.findEPapersForDistrictByDistrictId(new Long(19));
		assertEquals(4, list.size());
	}

	@SuppressWarnings("unchecked")
	public void testFindByMainUrl(){
		List list = epaperDAO.findMainEPapersForStateByStateId(new Long(19));
		assertEquals(4, list.size());
	}
}
