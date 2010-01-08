package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.service.impl.ConstituencyManagementService;
import com.itgrids.partyanalyst.service.impl.DistrictPageService;

public class DistrictPageServiceTest {
private IDelimitationConstituencyDAO delimitationConstituencyDAO;
private ITehsilDAO tehsilDAO;
	
	@Before
	public void init(){
		delimitationConstituencyDAO = EasyMock.createMock(IDelimitationConstituencyDAO.class);
		tehsilDAO = EasyMock.createMock(ITehsilDAO.class);
	}
		
	@Test
	public void testPartyResultsInfo(){

		DistrictPageService service = new DistrictPageService();
		List district = new ArrayList();			
		
		Object[] obj1 = {new Long(1257),"BAPATLA",new Long(2009)};
		Object[] obj2 = {new Long(1247),"CHILAKALURIPET",new Long(2009)};
		Object[] obj3 = {new Long(1359),"Guntur East",new Long(2009)};
		Object[] obj4 = {new Long(1360),"Guntur West",new Long(2009)};
		Object[] obj5 = {new Long(1251),"GURZALA",new Long(2009)};		
		
		district.add(obj1); 
		district.add(obj2); 
		district.add(obj3); 
		district.add(obj4); 
		district.add(obj5); 
		
		
		EasyMock.expect(delimitationConstituencyDAO.getConstituenciesByDistrictID(new Long(17))).andReturn(district);
		EasyMock.replay(delimitationConstituencyDAO);
		service.setDelimitationConstituencyDAO(delimitationConstituencyDAO);
		List serviceVO = service.getConstituenciesForDistrict(new Long(17));
		SelectOptionVO selectOptionVO = new SelectOptionVO();
		
		Assert.assertEquals(5, serviceVO.size());
		
	}
	
	
	@Test
	public void testTehsilDetails(){

		DistrictPageService service = new DistrictPageService();
		List district = new ArrayList();			
		
		Object[] obj1 = {new Long(729),"Amaravathi"};
		Object[] obj2 = {new Long(763),"Amruthalur"};
		Object[] obj3= {new Long(727),"Atchampet"};
		Object[] obj4 = {new Long(770),"Bapatla"};
		Object[] obj5 = {new Long(726),"Bellamkonda"};		
		
		district.add(obj1); 
		district.add(obj2); 
		district.add(obj3); 
		district.add(obj4); 
		district.add(obj5); 		
		
		EasyMock.expect( tehsilDAO.findTehsilsByDistrict(new Long(17))).andReturn(district);
		EasyMock.replay(tehsilDAO);
		service.setTehsilDAO(tehsilDAO);
		List tehsil = service.getMandalsForDistrict(new Long(17));
		MandalVO mandal = new MandalVO();		
	}

}
