package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IWebMonitoringAssignedUsersDAO;

public class WebMonitoringAssignedUsersDAOHibernateTest extends BaseDaoTestCase{

	private IWebMonitoringAssignedUsersDAO webMonitoringAssignedUsersDAO;
	
	public void setWebMonitoringAssignedUsersDAO(
			IWebMonitoringAssignedUsersDAO webMonitoringAssignedUsersDAO) {
		this.webMonitoringAssignedUsersDAO = webMonitoringAssignedUsersDAO;
	}

/*
	public void testDtails(){
		List<Long> listIds = new ArrayList<Long>();
		listIds.add(40L);
		listIds.add(41L);
		listIds.add(42L);
		listIds.add(43L);
		listIds.add(44L);
		listIds.add(45L);
		listIds.add(46L);
		listIds.add(47L);
		listIds.add(48L);
		listIds.add(52L);
		listIds.add(53L);
		listIds.add(54L);
		listIds.add(89L);
		listIds.add(91L);
		listIds.add(92L);
		listIds.add(93L);
		listIds.add(94L);
		listIds.add(95L);
		listIds.add(96L);
		listIds.add(97L);
		listIds.add(98L);
		listIds.add(99L);
		listIds.add(55L);
		listIds.add(57L);
		listIds.add(58L);
		listIds.add(59L);
		listIds.add(60L);
		listIds.add(61L);
		listIds.add(62L);
		listIds.add(63L);
		listIds.add(64L);
		listIds.add(65L);
		listIds.add(66L);
		listIds.add(67L);
		listIds.add(68L);
		listIds.add(69L);
		listIds.add(70L);
		listIds.add(71L);
		listIds.add(72L);
		listIds.add(73L);
		listIds.add(74L);
		listIds.add(75L);
		listIds.add(76L);
		listIds.add(77L);
		listIds.add(78L);
		listIds.add(79L);
		listIds.add(80L);
		listIds.add(81L);
		listIds.add(82L);
		listIds.add(83L);
		listIds.add(84L);
		listIds.add(85L);
		listIds.add(86L);
		listIds.add(87L);
		listIds.add(88L);
		
		List<Long> ids = webMonitoringAssignedUsersDAO.getConstiteuncyUsersInConsti(listIds);
		
		System.out.println(ids.size());
	}*/
}
