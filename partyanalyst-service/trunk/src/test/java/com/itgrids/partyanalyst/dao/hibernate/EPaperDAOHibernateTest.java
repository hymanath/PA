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
import com.itgrids.partyanalyst.dto.EPaperVO;
import com.itgrids.partyanalyst.model.EPaper;

public class EPaperDAOHibernateTest extends BaseDaoTestCase {

	private IEPaperDAO epaperDAO;

	public IEPaperDAO getEpaperDAO() {
		return epaperDAO;
	}


	public void setEpaperDAO(IEPaperDAO epaperDAO) {
		this.epaperDAO = epaperDAO;
	}

/*
	public void testGet(){
		assertEquals(0,0);
	}
	
	public void testFindStateEPapersByStateId(){
		List result = epaperDAO.findStateEPapersByStateId(1l);
		assertEquals(4, result.size());
	}
*/	
	public void testFfindStateEPapersByStateId(){
		List<EPaper> result = epaperDAO.findByStateId(1l);
		assertEquals(7,result.size());
	}
}
