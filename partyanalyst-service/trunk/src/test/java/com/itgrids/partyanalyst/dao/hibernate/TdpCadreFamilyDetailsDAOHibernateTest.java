package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;

public class TdpCadreFamilyDetailsDAOHibernateTest extends BaseDaoTestCase{

	private ITdpCadreFamilyDetailsDAO tdpCadreFamilyDetailsDAO;

	public void setTdpCadreFamilyDetailsDAO(
			ITdpCadreFamilyDetailsDAO tdpCadreFamilyDetailsDAO) {
		this.tdpCadreFamilyDetailsDAO = tdpCadreFamilyDetailsDAO;
	}
	
	
	public void testDetails()
	{
		List<Object[]> list = tdpCadreFamilyDetailsDAO.getCadreFamilyDetailsBytdpCadreId(1400565L);
		System.out.println(list.size());
	}
	
}
