package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.model.District;


public class DistrictDAOHibernateTest  extends BaseDaoTestCase{

	private IDistrictDAO districtDAO;

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	
	/*public void testFindByProperty(){
		List<District> list = districtDAO.getAllOrderByName();
		Assert.assertEquals(23, list.size());
	}*/
	
	/*public void testFindByStateId(){
		List<District> list =  districtDAO.findByStateId(new Long(1));
		for(District d : list){
			System.out.println(d.getDistrictName());
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public void testGetDistrictIdAndNameByState()
	{
		List<District> list2 = districtDAO.findByStateId(1l);
		System.out.println(list2.size());
		List<Object[]> list = districtDAO.getDistrictIdAndNameByState(1l);
		for(Object[] params : list)
			System.out.println(params[0]+"-------"+params[1]);
	}
}
