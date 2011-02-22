/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2011
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.ListIterator;

import org.appfuse.dao.BaseDaoTestCase;
import org.hsqldb.lib.Iterator;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IDepartmentOrganisationDAO;

/**
 * @author Sai Krishna
 *
 */
public class DepartmentOrganisationDAOHibernateTest extends BaseDaoTestCase {

	private IDepartmentOrganisationDAO departmentOrganisationDAO;

	public IDepartmentOrganisationDAO getDepartmentOrganisationDAO() {
		return departmentOrganisationDAO;
	}


	public void setDepartmentOrganisationDAO(
			IDepartmentOrganisationDAO departmentOrganisationDAO) {
		this.departmentOrganisationDAO = departmentOrganisationDAO;
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDepartmentsCategoryBasedOnScope(){
		
		List resultsList = departmentOrganisationDAO.getDepartmentCategorysBasedOnProblemResolvingRegionScope(4L);
		
		System.out.println(" Results Size : " + resultsList.size());
		
		if(resultsList != null && resultsList.size() > 0){
			
			ListIterator lstItr = resultsList.listIterator();
			
			while(lstItr.hasNext()){
				
				Object[] values = (Object[])lstItr.next();
				
				System.out.println(" Department Category ID : " + values[0] + " -- Department Category : " + values[1]);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDepartmentOrganisationsForADepartment(){
		
		List resultsList = departmentOrganisationDAO.getDepartmentOrganisationsForADepartmentByScope(1L,2L);
		
		System.out.println(" Results Size : " + resultsList.size());
		
		if(resultsList != null && resultsList.size() > 0){
			
			ListIterator lstItr = resultsList.listIterator();
			
			while(lstItr.hasNext()){
				
				Object[] values = (Object[])lstItr.next();
				
				System.out.println(" Department ID : " + values[0] + " -- Department : " + values[1]);
			}
		}
	}
}
