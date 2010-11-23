package com.itgrids.partyanalyst.service;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.impl.ConstituencyManagementService;
import com.itgrids.partyanalyst.util.MockData;

public class ConstituencyManagementServiceTest extends BaseDaoTestCase{
	
	private IConstituencyManagementService constituencyManagementService;

	public IConstituencyManagementService getConstituencyManagementService() {
		return constituencyManagementService;
	}

	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}
	
	
	
	/*	@Before
	public void init(){
		constituencyManagementService = new ConstituencyManagementService();
	}*/
	
	/*@Test
	public void checkForVoterCastDetails(){
		VoterCastInfoVO voterCastInfoVO = constituencyManagementService.caluculatePercentage(MockData.getVoterCastDetails());
		Assert.assertEquals(new Long(85), voterCastInfoVO.getTotalVoters());
		Assert.assertEquals(new Long(35), voterCastInfoVO.getMaleVoters());
		Assert.assertEquals(new Long(50), voterCastInfoVO.getFemaleVoters());
		Assert.assertEquals(3, voterCastInfoVO.getCastVOs().size());
		Assert.assertEquals(new Long(35), voterCastInfoVO.getMaleVoters());
		
		for(CastVO obj:voterCastInfoVO.getCastVOs()){
			if(obj.getCastName().equals("Balija"))
				Assert.assertEquals("35.29", obj.getCastPercentage());
		}
		
	}*/
	
	public void testVoter(){
		List<VoterVO> list = constituencyManagementService.getVoterInfo(42l, "2009", 0, 100,"", "firstName");
		int i=0;
		for(VoterVO voterVO:list)
			System.out.println((i++)+" "+ voterVO.getFirstName()+" "+voterVO.getVoterLastName());
	}
	
	
}
