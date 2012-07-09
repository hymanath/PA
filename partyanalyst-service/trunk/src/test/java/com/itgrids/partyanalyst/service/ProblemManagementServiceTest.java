package com.itgrids.partyanalyst.service;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemClassificationDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.dao.IProblemSourceScopeDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemsOfUserVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.impl.ProblemManagementService;
import com.itgrids.partyanalyst.util.MockData;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemManagementServiceTest extends BaseDaoTestCase{

/*	private IProblemLocationDAO problemLocationDAO;
	private IRegistrationDAO registrationDAO;
	private IHamletDAO hamletDAO;
	private ITownshipDAO townshipDAO;
	private ProblemManagementService problemManagementService;	
*/	

	private IProblemManagementService problemManagementService;
		
	/*@Before
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
	*/
	
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}
	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	/*public void testGetUserProblems(){
		ProblemsOfUserVO obj = problemManagementService.getProblemsForUser(new Long(5));
		System.out.println(obj.getProblemRegionScopes().size());
		System.out.println(obj.getProblemTypes().size());
		System.out.println(obj.getProblemsByUser().size());
	}*/
	
	/*public void testSaveUpdatedProblems(){
		problemManagementService.updateAndGetClassifiedProblemDataIntoDB(MockData.getProblemBeanVOsToSave());
		setComplete();
	}*/
	
	public void testUpdateStatusOfProblem(){
		ResultStatus resultStatus=problemManagementService.updateStatusOfProblem(1L, 25L, IConstants.PENDING);
		System.out.println(resultStatus.getResultCode());
		System.out.println(resultStatus.getExceptionMsg());		
	}
	
}
