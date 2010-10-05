/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 05, 2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IInformationSourceDAO;
import com.itgrids.partyanalyst.model.InformationSource;

/**
 * @author Sai Krishna
 *
 */
public class InformationSourceDAOHibernateTest extends BaseDaoTestCase {

	private IInformationSourceDAO informationSourceDAO;

	public IInformationSourceDAO getInformationSourceDAO() {
		return informationSourceDAO;
	}

	public void setInformationSourceDAO(IInformationSourceDAO informationSourceDAO) {
		this.informationSourceDAO = informationSourceDAO;
	}
	
	@Test
	public void testGetInformationSource(){
		List<InformationSource> values = informationSourceDAO.getInformationSourceByType("User");
		if(values != null){
			InformationSource infSource = values.get(0);
			System.out.println(" Information Source :" + infSource.getInformationSource());
		}
	}
}
