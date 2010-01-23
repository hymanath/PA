package com.itgrids.partyanalyst.service;

import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.service.impl.ProblemManagementService;
import com.itgrids.partyanalyst.util.MockData;

public class ProblemManagementServiceTest {

	private IProblemLocationDAO problemLocationDAO;
	private IRegistrationDAO registrationDAO;
	private IHamletDAO hamletDAO;
	private ITownshipDAO townshipDAO;
	private ProblemManagementService problemManagementService;	
	
	@Before
	public void init()
	{
		registrationDAO = EasyMock.createMock(IRegistrationDAO.class);
		hamletDAO = EasyMock.createMock(IHamletDAO.class);
		problemLocationDAO = EasyMock.createMock(IProblemLocationDAO.class);
		problemManagementService = new ProblemManagementService();
	}
	@Test
	public void testGetProblemsForUser()
	{
		List<ProblemBeanVO> beans = problemManagementService.getUserProblems(MockData.getProblemsForUser());
		Assert.assertEquals(2, beans.size());
		Assert.assertEquals("No Trasportation FAcilities",beans.get(0).getProblem());
		Assert.assertEquals("No Bus service provided to Ramanthapur Hamlet",beans.get(0).getDescription());
		Assert.assertEquals("IsakaPalli",beans.get(0).getHamlet());
		Assert.assertEquals("User",beans.get(0).getProbSource());	
	}
	
	
	
}
