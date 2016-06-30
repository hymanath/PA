package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;

public class AppointmentCandidateRelationDAOHibernateTest extends BaseDaoTestCase {
	private IAppointmentCandidateRelationDAO appointmentCandidateRelationDAO;
	public void test(){
		//List<Object[]> list = appointmentCandidateRelationDAO.getAllAppointmentDetails();
		//System.out.println(list);
	}
	
	public void testGetCandidateAppointmentDetails()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(2l);
		List<Object[]> a = appointmentCandidateRelationDAO.getCandidateAppointmentDetails(list, 8598052l);
		System.out.println(a.size());
	}
}
