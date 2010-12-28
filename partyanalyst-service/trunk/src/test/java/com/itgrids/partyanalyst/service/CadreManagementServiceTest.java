package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;


import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;

import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.util.DummyCadreData;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreManagementServiceTest {

	private ICadreDAO cadreDAO;
	private ICountryDAO countryDAO;
	private ITehsilDAO tehsilDAO;
	@Before
	public void init(){
		System.out.println("called init method..........");
		cadreDAO = EasyMock.createMock(ICadreDAO.class);
		countryDAO = EasyMock.createMock(ICountryDAO.class);
		tehsilDAO = EasyMock.createMock(ITehsilDAO.class);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testCountry_GetUserCadresInfo(){ 		
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadreInfo = new UserCadresInfoVO();
		userCadreInfo.setUserAccessType("COUNTRY");
		userCadreInfo.setUserAccessValue("1");
		userCadreInfo.setUserID(new Long(1));
		EasyMock.expect(cadreDAO.findTotalCadresByUserID(userCadreInfo.getUserID(), IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(40L);		

		List states = DummyCadreData.getStates();
		EasyMock.expect(cadreDAO.findStatesByCountryID(userCadreInfo.getUserAccessValue())).andReturn(states);
		
		List list = new ArrayList();
		Object[] obj1 = {new Long(1), new Long(11)};// AP state has 11 cadres
		Object[] obj2 = {new Long(2), new Long(21)};//UP state has 21 cadres
		list.add(obj1); list.add(obj2); 
		EasyMock.expect(cadreDAO.findCadreSizeStateWise(userCadreInfo.getUserID())).andReturn(list);
		
		List districts = DummyCadreData.getDistricts();
		EasyMock.expect(cadreDAO.findDistrictsByStateID("1,2")).andReturn(districts);
		
		List cadreSizeDistrictWise = new ArrayList();
		Object[] obj1110 = {new Long(10), new Long(11)};
		Object[] obj1120 = {new Long(12), new Long(21)};
		Object[] obj113 = {new Long(14), new Long(12)};
		Object[] obj114 = {new Long(22), new Long(12)};
		Object[] obj115 = {new Long(25), new Long(12)};
		Object[] obj116 = {new Long(31), new Long(12)};
		Object[] obj117 = {new Long(33), new Long(12)};
		cadreSizeDistrictWise.add(obj1110); cadreSizeDistrictWise.add(obj1120); cadreSizeDistrictWise.add(obj113); 
		cadreSizeDistrictWise.add(obj114); cadreSizeDistrictWise.add(obj115);  cadreSizeDistrictWise.add(obj116); cadreSizeDistrictWise.add(obj117); 
		EasyMock.expect(cadreDAO.findCadreSizeDistrictWise(userCadreInfo.getUserID())).andReturn(cadreSizeDistrictWise);

		List mandals = DummyCadreData.getMandals();
		EasyMock.expect(cadreDAO.findMandalsByDistrictID("10,12,14,22,25,31,33")).andReturn(mandals);

		List cadreSizeMandalWise = new ArrayList();
		Object[] mandalWiseCadres1 = {new Long(1), new Long(33)};
		Object[] mandalWiseCadres2 = {new Long(2), new Long(25)};
		Object[] mandalWiseCadres3 = {new Long(5), new Long(52)};
		Object[] mandalWiseCadres4 = {new Long(10), new Long(45)};
		Object[] mandalWiseCadres5 = {new Long(12), new Long(16)};
		Object[] mandalWiseCadres6 = {new Long(13), new Long(24)};
		cadreSizeMandalWise.add(mandalWiseCadres1); cadreSizeMandalWise.add(mandalWiseCadres2); cadreSizeMandalWise.add(mandalWiseCadres3); 
		cadreSizeMandalWise.add(mandalWiseCadres4); cadreSizeMandalWise.add(mandalWiseCadres5); cadreSizeMandalWise.add(mandalWiseCadres6); 
		EasyMock.expect(cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID())).andReturn(cadreSizeMandalWise);
		
		List villages = DummyCadreData.getVillages();
		EasyMock.expect(cadreDAO.findVillagesByTehsilID("1,2,5,10,12,13")).andReturn(villages);
		
		List cadreSizeVillageWise = new ArrayList();
		Object[] villageWiseCadres1 = {new Long(2), new Long(33)};
		Object[] villageWiseCadres2 = {new Long(4), new Long(125)};
		Object[] villageWiseCadres3 = {new Long(5), new Long(52)};
		Object[] villageWiseCadres4 = {new Long(9), new Long(45)};
		Object[] villageWiseCadres5 = {new Long(12), new Long(116)};
		Object[] villageWiseCadres6 = {new Long(15), new Long(24)};
		Object[] villageWiseCadres7 = {new Long(16), new Long(24)};
		Object[] villageWiseCadres8 = {new Long(18), new Long(24)};
		Object[] villageWiseCadres9 = {new Long(21), new Long(24)};
		Object[] villageWiseCadres10 = {new Long(27), new Long(24)};
		cadreSizeVillageWise.add(villageWiseCadres1); cadreSizeVillageWise.add(villageWiseCadres2); cadreSizeVillageWise.add(villageWiseCadres3); 
		cadreSizeVillageWise.add(villageWiseCadres4); cadreSizeVillageWise.add(villageWiseCadres5); cadreSizeVillageWise.add(villageWiseCadres6); 
		cadreSizeVillageWise.add(villageWiseCadres7); cadreSizeVillageWise.add(villageWiseCadres8); cadreSizeVillageWise.add(villageWiseCadres9); 
		cadreSizeVillageWise.add(villageWiseCadres10);
		EasyMock.expect(cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID())).andReturn(cadreSizeVillageWise);
		
		List dummyData = new ArrayList();
		Object[] dummy1 = {"COUNTRY","6"};
		Object[] dummy2 = {"STATE","8"};
		Object[] dummy3 = {"DISTRICT","12"};
		Object[] dummy4 = {"MANDAL","23"}; 
		dummyData.add(dummy1);
		dummyData.add(dummy2);
		dummyData.add(dummy3);
		dummyData.add(dummy4);
		EasyMock.expect(cadreDAO.findCadresByLevels(new Long(1),IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		
		userCadreInfo=service.getUserCadresInfo(userCadreInfo);
		List<SelectOptionVO> voData = userCadreInfo.getRegionLevelZeroCadres();
		
		Assert.assertEquals(new Long(1), voData.get(0).getId());
		Assert.assertEquals(new Long(6), voData.get(1).getId());
		Assert.assertEquals(new Long(9), voData.get(2).getId());
		Assert.assertEquals(new Long(20), voData.get(3).getId());
		
		Long countryLevelCadres = userCadreInfo.getRegionLevelCadres().get("COUNTRY");
		Long stateLevelCadres = userCadreInfo.getRegionLevelCadres().get("STATE");
		Long districtLevelCadres = userCadreInfo.getRegionLevelCadres().get("DISTRICT");
		Long mandalLevelCadres = userCadreInfo.getRegionLevelCadres().get("MANDAL");
		Assert.assertEquals( new Long(6),countryLevelCadres);
		Assert.assertEquals(new Long(8),stateLevelCadres);
		Assert.assertEquals(new Long(12),districtLevelCadres);
		Assert.assertEquals(new Long(23),mandalLevelCadres);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCountry_GetUserAccessRegions(){ 		
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadreInfo = new UserCadresInfoVO();
		userCadreInfo.setUserAccessType("COUNTRY");
		userCadreInfo.setUserAccessValue("1");
		userCadreInfo.setUserID(new Long(1));
		
		List states = DummyCadreData.getStates();
		EasyMock.expect(cadreDAO.findStatesByCountryID(userCadreInfo.getUserAccessValue())).andReturn(states);
		
		List list = new ArrayList();
		Object[] obj1 = {new Long(1), new Long(11)};// AP state has 11 cadres
		Object[] obj2 = {new Long(2), new Long(21)};//UP state has 21 cadres
		list.add(obj1); list.add(obj2); 
		EasyMock.expect(cadreDAO.findCadreSizeStateWise(userCadreInfo.getUserID())).andReturn(list);
		
		List districts = DummyCadreData.getDistricts();
		EasyMock.expect(cadreDAO.findDistrictsByStateID("1,2")).andReturn(districts);
		

		List cadreSizeDistrictWise = new ArrayList();
		Object[] obj1110 = {new Long(10), new Long(11)};
		Object[] obj1120 = {new Long(12), new Long(21)};
		Object[] obj113 = {new Long(14), new Long(12)};
		Object[] obj114 = {new Long(22), new Long(12)};
		Object[] obj115 = {new Long(25), new Long(12)};
		Object[] obj116 = {new Long(31), new Long(12)};
		Object[] obj117 = {new Long(33), new Long(12)};
		cadreSizeDistrictWise.add(obj1110); cadreSizeDistrictWise.add(obj1120); cadreSizeDistrictWise.add(obj113); 
		cadreSizeDistrictWise.add(obj114); cadreSizeDistrictWise.add(obj115);  cadreSizeDistrictWise.add(obj116); cadreSizeDistrictWise.add(obj117); 
		EasyMock.expect(cadreDAO.findCadreSizeDistrictWise(userCadreInfo.getUserID())).andReturn(cadreSizeDistrictWise);

		List mandals = DummyCadreData.getMandals();
		EasyMock.expect(cadreDAO.findMandalsByDistrictID("10,12,14,22,25,31,33")).andReturn(mandals);

		List cadreSizeMandalWise = new ArrayList();
		Object[] mandalWiseCadres1 = {new Long(1), new Long(33)};
		Object[] mandalWiseCadres2 = {new Long(2), new Long(25)};
		Object[] mandalWiseCadres3 = {new Long(5), new Long(52)};
		Object[] mandalWiseCadres4 = {new Long(10), new Long(45)};
		Object[] mandalWiseCadres5 = {new Long(12), new Long(16)};
		Object[] mandalWiseCadres6 = {new Long(13), new Long(24)};
		cadreSizeMandalWise.add(mandalWiseCadres1); cadreSizeMandalWise.add(mandalWiseCadres2); cadreSizeMandalWise.add(mandalWiseCadres3); 
		cadreSizeMandalWise.add(mandalWiseCadres4); cadreSizeMandalWise.add(mandalWiseCadres5); cadreSizeMandalWise.add(mandalWiseCadres6); 
		EasyMock.expect(cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID())).andReturn(cadreSizeMandalWise);
		
		List villages = DummyCadreData.getVillages();
		EasyMock.expect(cadreDAO.findVillagesByTehsilID("1,2,5,10,12,13")).andReturn(villages);
		
		List cadreSizeVillageWise = new ArrayList();
		Object[] villageWiseCadres1 = {new Long(2), new Long(33)};
		Object[] villageWiseCadres2 = {new Long(4), new Long(125)};
		Object[] villageWiseCadres3 = {new Long(5), new Long(52)};
		Object[] villageWiseCadres4 = {new Long(9), new Long(45)};
		Object[] villageWiseCadres5 = {new Long(12), new Long(116)};
		Object[] villageWiseCadres6 = {new Long(15), new Long(24)};
		Object[] villageWiseCadres7 = {new Long(16), new Long(24)};
		Object[] villageWiseCadres8 = {new Long(18), new Long(24)};
		Object[] villageWiseCadres9 = {new Long(21), new Long(24)};
		Object[] villageWiseCadres10 = {new Long(27), new Long(24)};
		cadreSizeVillageWise.add(villageWiseCadres1); cadreSizeVillageWise.add(villageWiseCadres2); cadreSizeVillageWise.add(villageWiseCadres3); 
		cadreSizeVillageWise.add(villageWiseCadres4); cadreSizeVillageWise.add(villageWiseCadres5); cadreSizeVillageWise.add(villageWiseCadres6); 
		cadreSizeVillageWise.add(villageWiseCadres7); cadreSizeVillageWise.add(villageWiseCadres8); cadreSizeVillageWise.add(villageWiseCadres9); 
		cadreSizeVillageWise.add(villageWiseCadres10);
		EasyMock.expect(cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID())).andReturn(cadreSizeVillageWise);
		
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		
		userCadreInfo = service.getUserAccessRegions(userCadreInfo);
		List<SelectOptionVO> voData = userCadreInfo.getRegionLevelZeroCadres();
		
		Assert.assertEquals(new Long(1), voData.get(0).getId());
		Assert.assertEquals(new Long(6), voData.get(1).getId());
		Assert.assertEquals(new Long(9), voData.get(2).getId());
		Assert.assertEquals(new Long(20), voData.get(3).getId());
		
	}
	
	@Test
	public void testState_GetUserAccessRegions(){
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadreInfo = new UserCadresInfoVO();
		userCadreInfo.setUserAccessType("STATE");
		userCadreInfo.setUserAccessValue("1");
		userCadreInfo.setUserID(new Long(1));
		
		List districts = DummyCadreData.getDistricts(); 
		EasyMock.expect(cadreDAO.findDistrictsByStateID(userCadreInfo.getUserAccessValue())).andReturn(districts);

		List data11 = new ArrayList();
		Object[] obj1110 = {new Long(10), new Long(11)};
		Object[] obj1120 = {new Long(12), new Long(21)};
		Object[] obj113 = {new Long(14), new Long(12)};
		Object[] obj114 = {new Long(22), new Long(12)};
		Object[] obj115 = {new Long(25), new Long(12)};
		Object[] obj116 = {new Long(31), new Long(12)};
		Object[] obj117 = {new Long(33), new Long(12)};
		data11.add(obj1110); data11.add(obj1120); data11.add(obj113); 
		data11.add(obj114); data11.add(obj115);  data11.add(obj116); data11.add(obj117); 
		EasyMock.expect(cadreDAO.findCadreSizeDistrictWise(userCadreInfo.getUserID())).andReturn(data11);
		
		List mandals = DummyCadreData.getMandals(); 
		EasyMock.expect(cadreDAO.findMandalsByDistrictID("10,12,14,22,25,31,33")).andReturn(mandals);
		
		List cadreSizeMandalWise = new ArrayList();
		Object[] mandalWiseCadres1 = {new Long(1), new Long(33)};
		Object[] mandalWiseCadres2 = {new Long(2), new Long(25)};
		Object[] mandalWiseCadres3 = {new Long(5), new Long(52)};
		Object[] mandalWiseCadres4 = {new Long(10), new Long(45)};
		Object[] mandalWiseCadres5 = {new Long(12), new Long(16)};
		Object[] mandalWiseCadres6 = {new Long(13), new Long(24)};
		cadreSizeMandalWise.add(mandalWiseCadres1); cadreSizeMandalWise.add(mandalWiseCadres2); cadreSizeMandalWise.add(mandalWiseCadres3); 
		cadreSizeMandalWise.add(mandalWiseCadres4); cadreSizeMandalWise.add(mandalWiseCadres5); cadreSizeMandalWise.add(mandalWiseCadres6); 
		EasyMock.expect(cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID())).andReturn(cadreSizeMandalWise);
		
		List villages = DummyCadreData.getVillages();
		EasyMock.expect(cadreDAO.findVillagesByTehsilID("1,2,5,10,12,13")).andReturn(villages);
		
		List cadreSizeVillageWise = new ArrayList();
		Object[] villageWiseCadres1 = {new Long(2), new Long(33)};
		Object[] villageWiseCadres2 = {new Long(4), new Long(125)};
		Object[] villageWiseCadres3 = {new Long(5), new Long(52)};
		Object[] villageWiseCadres4 = {new Long(9), new Long(45)};
		Object[] villageWiseCadres5 = {new Long(12), new Long(116)};
		Object[] villageWiseCadres6 = {new Long(15), new Long(24)};
		Object[] villageWiseCadres7 = {new Long(16), new Long(24)};
		Object[] villageWiseCadres8 = {new Long(18), new Long(24)};
		Object[] villageWiseCadres9 = {new Long(21), new Long(24)};
		Object[] villageWiseCadres10 = {new Long(27), new Long(24)};
		cadreSizeVillageWise.add(villageWiseCadres1); cadreSizeVillageWise.add(villageWiseCadres2); cadreSizeVillageWise.add(villageWiseCadres3); 
		cadreSizeVillageWise.add(villageWiseCadres4); cadreSizeVillageWise.add(villageWiseCadres5); cadreSizeVillageWise.add(villageWiseCadres6); 
		cadreSizeVillageWise.add(villageWiseCadres7); cadreSizeVillageWise.add(villageWiseCadres8); cadreSizeVillageWise.add(villageWiseCadres9); 
		cadreSizeVillageWise.add(villageWiseCadres10);
		EasyMock.expect(cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID())).andReturn(cadreSizeVillageWise);
		
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		
		userCadreInfo = service.getUserAccessRegions(userCadreInfo);
		List<SelectOptionVO> voData = userCadreInfo.getRegionLevelZeroCadres();
		/*
		Assert.assertEquals(null, voData.get(1));//country
		Assert.assertEquals(null, voData.get(1));//state
*/		Assert.assertEquals(new Long(6), voData.get(0).getId());//district
		Assert.assertEquals(new Long(9), voData.get(1).getId());//mandal
		Assert.assertEquals(new Long(20), voData.get(2).getId());//village
	} 
	//007
	
	@Test
	public void testDistrict_GetUserAccessRegions(){
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadreInfo = new UserCadresInfoVO();
		userCadreInfo.setUserAccessType("DISTRICT");
		userCadreInfo.setUserAccessValue("1");
		userCadreInfo.setUserID(new Long(1));
		
		List mandals = DummyCadreData.getMandals();
		EasyMock.expect(cadreDAO.findMandalsByDistrictID(userCadreInfo.getUserAccessValue())).andReturn(mandals);
		
		List cadreSizeMandalWise = new ArrayList();
		Object[] mandalWiseCadres1 = {new Long(1), new Long(33)};
		Object[] mandalWiseCadres2 = {new Long(2), new Long(25)};
		Object[] mandalWiseCadres3 = {new Long(5), new Long(52)};
		Object[] mandalWiseCadres4 = {new Long(10), new Long(45)};
		Object[] mandalWiseCadres5 = {new Long(12), new Long(16)};
		Object[] mandalWiseCadres6 = {new Long(13), new Long(24)};
		cadreSizeMandalWise.add(mandalWiseCadres1); cadreSizeMandalWise.add(mandalWiseCadres2); cadreSizeMandalWise.add(mandalWiseCadres3); 
		cadreSizeMandalWise.add(mandalWiseCadres4); cadreSizeMandalWise.add(mandalWiseCadres5); cadreSizeMandalWise.add(mandalWiseCadres6); 
		EasyMock.expect(cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID())).andReturn(cadreSizeMandalWise);
		
		//------------------------------------------------------------------------
		

		List villages = DummyCadreData.getVillages();
		EasyMock.expect(cadreDAO.findVillagesByTehsilID("1,2,5,10,12,13")).andReturn(villages);
		
		List cadreSizeVillageWise = new ArrayList();
		Object[] villageWiseCadres1 = {new Long(2), new Long(33)};
		Object[] villageWiseCadres2 = {new Long(4), new Long(125)};
		Object[] villageWiseCadres3 = {new Long(5), new Long(52)};
		Object[] villageWiseCadres4 = {new Long(9), new Long(45)};
		Object[] villageWiseCadres5 = {new Long(12), new Long(116)};
		Object[] villageWiseCadres6 = {new Long(15), new Long(24)};
		Object[] villageWiseCadres7 = {new Long(16), new Long(24)};
		Object[] villageWiseCadres8 = {new Long(18), new Long(24)};
		Object[] villageWiseCadres9 = {new Long(21), new Long(24)};
		Object[] villageWiseCadres10 = {new Long(27), new Long(24)};
		cadreSizeVillageWise.add(villageWiseCadres1); cadreSizeVillageWise.add(villageWiseCadres2); cadreSizeVillageWise.add(villageWiseCadres3); 
		cadreSizeVillageWise.add(villageWiseCadres4); cadreSizeVillageWise.add(villageWiseCadres5); cadreSizeVillageWise.add(villageWiseCadres6); 
		cadreSizeVillageWise.add(villageWiseCadres7); cadreSizeVillageWise.add(villageWiseCadres8); cadreSizeVillageWise.add(villageWiseCadres9); 
		cadreSizeVillageWise.add(villageWiseCadres10);
		EasyMock.expect(cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID())).andReturn(cadreSizeVillageWise);
		
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		
		userCadreInfo = service.getUserAccessRegions(userCadreInfo);
		List<SelectOptionVO> voData = userCadreInfo.getRegionLevelZeroCadres();
		/*java.util.Set set = mapData.entrySet();
		java.util.Iterator it = set.iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			System.out.println(entry.getKey()+"="+entry.getValue());
		}*//*
		Assert.assertEquals(null, voData.get(1));
		Assert.assertEquals(null, voData.get(1));
		Assert.assertEquals(null, voData.get(1));*/ 
		Assert.assertEquals(new Long(9), voData.get(0).getId());
		Assert.assertEquals(new Long(20), voData.get(1).getId());
	}
	
	@Test
	public void testMandal_GetUserAccessRegions(){
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadreInfo = new UserCadresInfoVO();
		userCadreInfo.setUserAccessType("MANDAL");
		userCadreInfo.setUserAccessValue("1");
		userCadreInfo.setUserID(new Long(1)); 
		List villages = DummyCadreData.getVillages();
		EasyMock.expect(cadreDAO.findVillagesByTehsilID(userCadreInfo.getUserAccessValue())).andReturn(villages);
		
		List cadreSizeVillageWise = new ArrayList();
		Object[] villageWiseCadres1 = {new Long(2), new Long(33)};
		Object[] villageWiseCadres2 = {new Long(4), new Long(125)};
		Object[] villageWiseCadres3 = {new Long(5), new Long(52)};
		Object[] villageWiseCadres4 = {new Long(9), new Long(45)};
		Object[] villageWiseCadres5 = {new Long(12), new Long(116)};
		Object[] villageWiseCadres6 = {new Long(15), new Long(24)};
		Object[] villageWiseCadres7 = {new Long(16), new Long(24)};
		Object[] villageWiseCadres8 = {new Long(18), new Long(24)};
		Object[] villageWiseCadres9 = {new Long(21), new Long(24)};
		Object[] villageWiseCadres10 = {new Long(27), new Long(24)};
		cadreSizeVillageWise.add(villageWiseCadres1); cadreSizeVillageWise.add(villageWiseCadres2); cadreSizeVillageWise.add(villageWiseCadres3); 
		cadreSizeVillageWise.add(villageWiseCadres4); cadreSizeVillageWise.add(villageWiseCadres5); cadreSizeVillageWise.add(villageWiseCadres6); 
		cadreSizeVillageWise.add(villageWiseCadres7); cadreSizeVillageWise.add(villageWiseCadres8); cadreSizeVillageWise.add(villageWiseCadres9); 
		cadreSizeVillageWise.add(villageWiseCadres10);
		EasyMock.expect(cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID())).andReturn(cadreSizeVillageWise);
		
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		
		userCadreInfo = service.getUserAccessRegions(userCadreInfo);
		List<SelectOptionVO> voData = userCadreInfo.getRegionLevelZeroCadres();

		/*Assert.assertEquals(null, voData.get(1));
		Assert.assertEquals(null, voData.get(1));
		Assert.assertEquals(null, voData.get(1));
		Assert.assertEquals(null, voData.get(1));*/
		Assert.assertEquals(new Long(20), voData.get(0).getId());
	}

	@Test
	public void testPositive_GetFormatedData(){
		Map<Long, String> userAccessRegions = new HashMap<Long, String>();
		List data = new ArrayList();
		Object[] obj1 = {new Long(1), "state1"};
		Object[] obj2 = {new Long(2), "state2"};
		Object[] obj3 = {new Long(3), "state3"};
		Object[] obj4 = {new Long(4), "state4"};
		Object[] obj5 = {new Long(5), "state5"};
		data.add(obj1); data.add(obj2); data.add(obj3); data.add(obj4); data.add(obj5); 
		CadreManagementService service = new CadreManagementService();
		StringBuilder sb = service.getFormatedData(data, userAccessRegions);
		Assert.assertEquals("1,2,3,4,5,", sb.toString());
		Assert.assertEquals(5, userAccessRegions.size()); 
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void testNegitive_GetFormatedData(){
		Map<Long, String> userAccessRegions = new HashMap<Long, String>();
		List data = new ArrayList();
		 
		CadreManagementService service = new CadreManagementService();
		StringBuilder sb = service.getFormatedData(data, userAccessRegions);
		Assert.assertEquals("", sb.toString());
		Assert.assertEquals(0, userAccessRegions.size()); 
	}
	
	//----------------------------------------------------

	@SuppressWarnings("unchecked")
	@Test
	public void testCountryLevel_GetRegionLevelCadresCount(){
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadres = new UserCadresInfoVO ();
		userCadres.setUserID(new Long(1));
		userCadres.setUserAccessType("COUNTRY");
		userCadres.setUserAccessValue("1");
		List dummyData = new ArrayList();
		Object[] dummy1 = {"COUNTRY","6"};
		Object[] dummy2 = {"STATE","8"};
		Object[] dummy3 = {"DISTRICT","12"};
		Object[] dummy4 = {"MANDAL","23"}; 
		dummyData.add(dummy1);
		dummyData.add(dummy2);
		dummyData.add(dummy3);
		dummyData.add(dummy4);
		EasyMock.expect(cadreDAO.findCadresByLevels(new Long(1),IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		userCadres.setRegionLevelCadres(service.getCadreLevelCadresCount(userCadres));
		//userCadres = service.getCadreLevelCadresCount(userCadres);
		Long countryLevelCadres = userCadres.getRegionLevelCadres().get("COUNTRY");
		Long stateLevelCadres = userCadres.getRegionLevelCadres().get("STATE");
		Long districtLevelCadres = userCadres.getRegionLevelCadres().get("DISTRICT");
		Long mandalLevelCadres = userCadres.getRegionLevelCadres().get("MANDAL");
		Assert.assertEquals( new Long(6),countryLevelCadres);
		Assert.assertEquals(new Long(8),stateLevelCadres);
		Assert.assertEquals(new Long(12),districtLevelCadres);
		Assert.assertEquals(new Long(23),mandalLevelCadres);
		
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void testStateLevel_GetRegionLevelCadresCount(){
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadres = new UserCadresInfoVO ();
		userCadres.setUserID(new Long(1));
		userCadres.setUserAccessType("STATE");
		userCadres.setUserAccessValue("1");
		List dummyData = new ArrayList();
		Object[] dummy2 = {"STATE","8"};
		Object[] dummy3 = {"DISTRICT","12"};
		Object[] dummy4 = {"MANDAL","23"}; 
		dummyData.add(dummy2);
		dummyData.add(dummy3);
		dummyData.add(dummy4);
		EasyMock.expect(cadreDAO.findCadresByLevels(new Long(1), IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		userCadres.setRegionLevelCadres(service.getCadreLevelCadresCount(userCadres));
		Long stateLevelCadres = userCadres.getRegionLevelCadres().get("STATE");
		Long districtLevelCadres = userCadres.getRegionLevelCadres().get("DISTRICT");
		Long mandalLevelCadres = userCadres.getRegionLevelCadres().get("MANDAL"); 
		Assert.assertEquals(new Long(8),stateLevelCadres);
		Assert.assertEquals(new Long(12),districtLevelCadres);
		Assert.assertEquals(new Long(23),mandalLevelCadres);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDistrictLevel_GetRegionLevelCadresCount(){
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadres = new UserCadresInfoVO ();
		userCadres.setUserID(new Long(1));
		userCadres.setUserAccessType("DISTRICT");
		userCadres.setUserAccessValue("1");
		List dummyData = new ArrayList();
		Object[] dummy3 = {"DISTRICT","12"};
		Object[] dummy4 = {"MANDAL","23"}; 
		dummyData.add(dummy3);
		dummyData.add(dummy4);
		EasyMock.expect(cadreDAO.findCadresByLevels(new Long(1),IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		userCadres.setRegionLevelCadres(service.getCadreLevelCadresCount(userCadres));
		Long districtLevelCadres = userCadres.getRegionLevelCadres().get("DISTRICT");
		Long mandalLevelCadres = userCadres.getRegionLevelCadres().get("MANDAL");
		Assert.assertEquals(new Long(12),districtLevelCadres);
		Assert.assertEquals(new Long(23),mandalLevelCadres);
	}
	

	
	@SuppressWarnings("unchecked")
	@Test
	public void testMandalLevel_GetRegionLevelCadresCount(){
		CadreManagementService service = new CadreManagementService();
		UserCadresInfoVO userCadres = new UserCadresInfoVO ();
		userCadres.setUserID(new Long(1));
		userCadres.setUserAccessType("MANDAL");
		userCadres.setUserAccessValue("1");
		List dummyData = new ArrayList();
		Object[] dummy4 = {"MANDAL","23"};  
		dummyData.add(dummy4);
		EasyMock.expect(cadreDAO.findCadresByLevels(new Long(1),IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		userCadres.setRegionLevelCadres(service.getCadreLevelCadresCount(userCadres)); 
		Long mandalLevelCadres = userCadres.getRegionLevelCadres().get("MANDAL"); 
		Assert.assertEquals(new Long(23),mandalLevelCadres);
	}
	
	@Test
	public void testGetCountryAllStatesCadres(){
		CadreManagementService service = new CadreManagementService();
		Object[] state1 = {1L,"AP",20L};
		Object[] state2 = {2L,"UP",15L};
		Object[] state3 = {3L,"MP",25L};
		List list = new ArrayList();
		list.add(state1); list.add(state2); list.add(state3);
		EasyMock.expect(cadreDAO.findStateCadresByCountry(1L,1L,IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(list);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<CadreRegionInfoVO> result = service.getCountryAllStatesCadres(1L, 1L);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals("STATE", result.get(0).getRegion());
		Assert.assertEquals("STATE", result.get(1).getRegion());
		Assert.assertEquals("STATE", result.get(2).getRegion());
		
	}
	
	@Test
	public void testGetStateAllDistrictsCadres(){
		CadreManagementService service = new CadreManagementService();
		Object[] dist1 = {1L,"Adilabad",20L};
		Object[] dist2 = {3L,"Cuddapah",15L};
		List list = new ArrayList();
		list.add(dist1); list.add(dist2);
		EasyMock.expect(cadreDAO.findDistCadresByState(1L,1L, IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(list);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<CadreRegionInfoVO> result = service.getStateAllDistrictsCadres(1L, 1L);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("DISTRICT", result.get(0).getRegion());
		Assert.assertEquals("DISTRICT", result.get(1).getRegion());
		
	}
	
	@Test
	public void testGetDistrictAllMandalsCadres(){
		CadreManagementService service = new CadreManagementService();
		Object[] mandal1 = {1L,"Mandal1",20L};
		Object[] mandal2 = {3L,"Mandal2",15L};
		List list = new ArrayList();
		list.add(mandal1); list.add(mandal2);
		EasyMock.expect(cadreDAO.findMandalCadresByDist(1L,1L,IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(list);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<CadreRegionInfoVO> result = service.getDistrictAllMandalsCadres(1L, 1L);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("MANDAL", result.get(0).getRegion());
		Assert.assertEquals("MANDAL", result.get(1).getRegion());
	}
	
	@Test
	public void testGetMandalAllVillagesCadres(){
		CadreManagementService service = new CadreManagementService();
		Object[] vill1 = {1L,"Village1",20L};
		Object[] vill2 = {3L,"Village2",15L};
		List list = new ArrayList();
		list.add(vill1); list.add(vill2);
		EasyMock.expect(cadreDAO.findVillageCadresByMandal(1L,1L,IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(list);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<CadreRegionInfoVO> result = service.getMandalAllVillagesCadres(1L, 1L);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals("VILLAGE", result.get(0).getRegion());
		Assert.assertEquals("VILLAGE", result.get(1).getRegion());
	}
	
	//service.getCadresByVillage()   dao.findCadresByVillage
	@Test
	public void testGetCadresByVillage(){
		CadreManagementService service = new CadreManagementService();
		List<Cadre> cadres = DummyCadreData.getCadres();
		Country country = new Country(1L,"India");
		EasyMock.expect(cadreDAO.findCadresByVillage(1L,1L,IConstants.CADRE_MEMBER_TYPE_ACTIVE)).andReturn(cadres);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		EasyMock.expect(countryDAO.get(1L)).andReturn(country);
		EasyMock.replay(countryDAO);
		service.setCountryDAO(countryDAO);
		
		Tehsil tehsil = new Tehsil(1L);
		tehsil.setTehsilName("tehsil_Name");
		EasyMock.expect(tehsilDAO.get(1L)).andReturn(tehsil);
		EasyMock.replay(tehsilDAO);
		service.setTehsilDAO(tehsilDAO);
		
		List<CadreInfo> result = service.getCadresByVillage(1L, 1L);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(new Long(1), result.get(0).getCadreLevel());
		Assert.assertEquals(new Long(2), result.get(1).getCadreLevel());
	}
	
	@Test
	public void testfindStatesByCountryID(){
		CadreManagementService service = new CadreManagementService();
		List dummyData = new ArrayList();
		Object[] row1 ={new Long(1L),"AP"};
		Object[] row2 ={new Long(2L),"MP"};
		Object[] row3 ={new Long(3L),"UP"};
		dummyData.add(row1);	dummyData.add(row2);	dummyData.add(row3);
		EasyMock.expect(cadreDAO.findStatesByCountryID("1")).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<SelectOptionVO> result=service.findStatesByCountryID("1");
		Assert.assertTrue(result.get(0) instanceof SelectOptionVO);
		Assert.assertEquals(new Long(1L), result.get(0).getId());
		Assert.assertEquals(3, result.size());
	} 
	@Test
	public void testFindDistrictsByState(){
		CadreManagementService service = new CadreManagementService();
		List dummyData = new ArrayList();
		Object[] row1 ={new Long(1L),"Nellore"};
		Object[] row2 ={new Long(2L),"Hyderabad"};
		Object[] row3 ={new Long(3L),"Medak"};
		dummyData.add(row1);	dummyData.add(row2);	dummyData.add(row3);
		EasyMock.expect(cadreDAO.findDistrictsByStateID("1")).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<SelectOptionVO> result=service.findDistrictsByState("1");
		Assert.assertTrue(result.get(0) instanceof SelectOptionVO);
		Assert.assertEquals(new Long(1L), result.get(0).getId());
		Assert.assertEquals(3, result.size());
	} 
	@Test
	public void testFindMandalsByDistrict(){
		CadreManagementService service = new CadreManagementService();
		List dummyData = new ArrayList();
		Object[] row1 ={new Long(1L),"Mandal1"};
		Object[] row2 ={new Long(2L),"Mandal2"};
		Object[] row3 ={new Long(3L),"Mandal3"};
		dummyData.add(row1);	dummyData.add(row2);	dummyData.add(row3);
		EasyMock.expect(cadreDAO.findMandalsByDistrictID("1")).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<SelectOptionVO> result=service.findMandalsByDistrict("1");
		Assert.assertTrue(result.get(0) instanceof SelectOptionVO);
		Assert.assertEquals(new Long(1L), result.get(0).getId());
		Assert.assertEquals(3, result.size());
	}
	//	public List<SelectOptionVO> findVillagesByTehsilID(String mandalID){
/*	@Test
	public void testFindVillagesByTehsilID(){
		CadreManagementService service = new CadreManagementService();
		List dummyData = new ArrayList();
		Object[] row1 ={new Long(1L),"Village1"};
		Object[] row2 ={new Long(2L),"Village2"};
		Object[] row3 ={new Long(3L),"Village3"};
		dummyData.add(row1);	dummyData.add(row2);	dummyData.add(row3);
		EasyMock.expect(cadreDAO.findVillagesByTehsilID("1")).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		List<SelectOptionVO> result=service.findVillagesByTehsilID("1");
		Assert.assertTrue(result.get(0) instanceof SelectOptionVO);
		Assert.assertEquals(new Long(1L), result.get(0).getId());
		Assert.assertEquals(3, result.size());
	}*/
	
	@Test
	public void testGetStateDistConstituencyMandalByMandalID(){
		Tehsil tehsil = new Tehsil(1L); tehsil.setTehsilName("Allur");
		Constituency constituency = new Constituency(1L); constituency.setName("Nellore");
		District district = new District(19L); district.setDistrictName("NelloreDistrict");
		State state = new State(1L,"Andhra Pradesh");
		district.setState(state);
		//constituency.setDistrict(district);
		tehsil.setDistrict(district);
		
		DelimitationConstituency delimitationConstituency = new DelimitationConstituency(1L);
		delimitationConstituency.setConstituency(constituency);
		DelimitationConstituencyMandal delConstMandal = new DelimitationConstituencyMandal();
		delConstMandal.setTehsil(tehsil);
	}
	
		
	/*@Test
	public void testGetCadreSearchResultsByInputCriteria(){
		CadreManagementService service = new CadreManagementService();
		List<Long> cadreIds = new ArrayList<Long>();
		
		//Dummy Cadre Ids
		List<Long> dummyCadreIds = DummyCadreData.getCadreIdsList();
		//EasyMock.expect(cadreDAO.findCadreIdsByMemberTypeAndCadreList("Active",cadreIds)).andReturn(dummyCadreIds);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		
		//List cadreResult = service.getCadreByCadreType("Active", cadreIds);
		
		if(cadreResult != null)
		{	
		Iterator lIt = cadreResult.listIterator();
		int i=1;
		while(lIt.hasNext()){
		Assert.assertEquals(new Long(i), (Long)lIt.next());
		}
		}
		 
	}*/
}
