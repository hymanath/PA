package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPanchayatResultDAO;

public class PanchayatResultDAOHibernateTest extends BaseDaoTestCase{

	private IPanchayatResultDAO panchayatResultDAO;

	public void setPanchayatResultDAO(IPanchayatResultDAO panchayatResultDAO) {
		this.panchayatResultDAO = panchayatResultDAO;
	}
	
	
	public void test(){
		List<Long> panchayatIds=new ArrayList<Long>();
		panchayatIds.add(9146l);
		panchayatIds.add(9147l);
		
		List<Object[]> list=panchayatResultDAO.getPartyWiseWonInPanchayts(panchayatIds);
		System.out.println(list.size());
	}
	
	
}
