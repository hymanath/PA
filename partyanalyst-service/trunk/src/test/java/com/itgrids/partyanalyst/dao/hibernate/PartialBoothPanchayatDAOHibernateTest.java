package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public class PartialBoothPanchayatDAOHibernateTest extends BaseDaoTestCase{

	private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;

	public IPartialBoothPanchayatDAO getPartialBoothPanchayatDAO() {
		return partialBoothPanchayatDAO;
	}

	public void setPartialBoothPanchayatDAO(
			IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
		this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
	}
	
	public void testgetPartialBoothPanchayatDetailsByPanchayatId(){
		List<PartialBoothPanchayat> list1 = new ArrayList<PartialBoothPanchayat>();
		System.out.println("hyderabad");
		list1 = partialBoothPanchayatDAO.getPartialBoothPanchayatDetailsByPanchayatId(3282L);
		System.out.println(list1.size());
		System.out.println(list1);
	}
	
}
