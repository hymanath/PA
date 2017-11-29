package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;

public class DistrictConstituenciesDAOHibernateTest extends BaseDaoTestCase{
	
	private IDistrictConstituenciesDAO districtConstituenciesDAO;

	public IDistrictConstituenciesDAO getDistrictConstituenciesDAO() {
		return districtConstituenciesDAO;
	}

	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}
	
	
	public void test(){
		List<Object[]> list = districtConstituenciesDAO.getConstituenciesOfDistrict(null);
		System.out.println(list.size());
	}

}
