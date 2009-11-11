package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.enums.DelimitationConstituencyType;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.impl.DelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyMandalServiceTest {

	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IConstituencyDAO constituencyDAO;
	private ICensusDAO censusDAO;
	
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}

	/*
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
		EasyMock.expect(cadreDAO.findCadresByLevels(new Long(1))).andReturn(dummyData);
		EasyMock.replay(cadreDAO);
		service.setCadreDAO(cadreDAO);
		userCadres = service.getCadreLevelCadresCount(userCadres);
		Long countryLevelCadres = userCadres.getRegionLevelCadres().get("COUNTRY");
		Long stateLevelCadres = userCadres.getRegionLevelCadres().get("STATE");
		Long districtLevelCadres = userCadres.getRegionLevelCadres().get("DISTRICT");
		Long mandalLevelCadres = userCadres.getRegionLevelCadres().get("MANDAL");
		Assert.assertEquals( new Long(6),countryLevelCadres);
		Assert.assertEquals(new Long(8),stateLevelCadres);
		Assert.assertEquals(new Long(12),districtLevelCadres);
		Assert.assertEquals(new Long(23),mandalLevelCadres);
		
	}
	
	*/
	@Before
	public void init(){
		delimitationConstituencyMandalDAO = EasyMock.createMock(IDelimitationConstituencyMandalDAO.class);
		delimitationConstituencyDAO = EasyMock.createMock(IDelimitationConstituencyDAO.class);
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
		censusDAO = EasyMock.createMock(ICensusDAO.class);
	}
	
	/**
	 *  testing for non existing constituency and change constituency
	 */
	@Test
	public void testForGetMandalsForDelConstituency(){
		Constituency constituency = new Constituency(1L);
		constituency.setDeformDate(Calendar.getInstance().getTime());
		EasyMock.expect(constituencyDAO.get(1L)).andReturn(constituency);
		EasyMock.replay(constituencyDAO);
		
		List<DelimitationConstituency> delimitationConstituency = new ArrayList<DelimitationConstituency>();
		DelimitationConstituency dc1 = new DelimitationConstituency(); dc1.setDelimitationConstituencyID(1L);
		DelimitationConstituency dc2 = new DelimitationConstituency(); dc2.setDelimitationConstituencyID(2L);
		delimitationConstituency.add(dc1);
		delimitationConstituency.add(dc2);
		
		EasyMock.expect(delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(1L)).andReturn(delimitationConstituency);
		EasyMock.replay(delimitationConstituencyDAO);
		
		List<DelimitationConstituencyMandal> presentDelimitationConstituencyMandals = new ArrayList<DelimitationConstituencyMandal>();
		Tehsil tehsil1 = new Tehsil(1L); tehsil1.setTehsilName("Tehsil1");
		Tehsil tehsil2 = new Tehsil(2L); tehsil1.setTehsilName("Tehsil2");
		
		DelimitationConstituencyMandal dcMandal1 = new DelimitationConstituencyMandal(); dcMandal1.setDcm_id(1L); dcMandal1.setTehsil(tehsil1);
		DelimitationConstituencyMandal dcMandal2 = new DelimitationConstituencyMandal(); dcMandal2.setDcm_id(1L); dcMandal2.setTehsil(tehsil2);
		
		presentDelimitationConstituencyMandals.add(dcMandal1);
		presentDelimitationConstituencyMandals.add(dcMandal2);
		EasyMock.expect(delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(1L)).andReturn(presentDelimitationConstituencyMandals);
		List<Census> censusList = new ArrayList<Census>();
		
		Census mandalCensus1 = new Census();
		mandalCensus1.setTehsilId(1L); 
		mandalCensus1 = fillCensusData(mandalCensus1);
		Census mandalCensus2 = new Census();
		mandalCensus2.setTehsilId(2L); 
		mandalCensus2 = fillCensusData(mandalCensus2);

		censusList.add(mandalCensus1);
		censusList.add(mandalCensus2);
		EasyMock.expect(censusDAO.findByYearAndTehsilIDs(IConstants.CENSUS_YEAR, "1,2")).andReturn(censusList);
		
		List<DelimitationConstituencyMandal> previousDelimitationConstituencyMandals = new ArrayList<DelimitationConstituencyMandal>();
		Tehsil tehsil11 = new Tehsil(11L); tehsil1.setTehsilName("Tehsil11");
		Tehsil tehsil21 = new Tehsil(21L); tehsil1.setTehsilName("Tehsil21");
		Tehsil tehsil31 = new Tehsil(31L); tehsil1.setTehsilName("Tehsil31");
		DelimitationConstituencyMandal dcMandal11 = new DelimitationConstituencyMandal(); dcMandal11.setDcm_id(1L); dcMandal11.setTehsil(tehsil11);
		DelimitationConstituencyMandal dcMandal21 = new DelimitationConstituencyMandal(); dcMandal21.setDcm_id(1L); dcMandal21.setTehsil(tehsil21);
		DelimitationConstituencyMandal dcMandal31 = new DelimitationConstituencyMandal(); dcMandal31.setDcm_id(1L); dcMandal31.setTehsil(tehsil31);
		
		previousDelimitationConstituencyMandals.add(dcMandal11);
		previousDelimitationConstituencyMandals.add(dcMandal21);
		previousDelimitationConstituencyMandals.add(dcMandal31);
		EasyMock.expect(delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(2L)).andReturn(previousDelimitationConstituencyMandals);
List<Census> censusList1 = new ArrayList<Census>();
		
		Census mandalCensus11 = new Census();
		mandalCensus11.setTehsilId(11L); 
		mandalCensus11 = fillCensusData(mandalCensus11);
		Census mandalCensus21 = new Census();
		mandalCensus21.setTehsilId(21L); 
		mandalCensus21 = fillCensusData(mandalCensus21);
		Census mandalCensus31 = new Census();
		mandalCensus31.setTehsilId(31L); 
		mandalCensus31 = fillCensusData(mandalCensus31);

		censusList1.add(mandalCensus11);
		censusList1.add(mandalCensus21);
		censusList1.add(mandalCensus31);
		EasyMock.expect(censusDAO.findByYearAndTehsilIDs(IConstants.CENSUS_YEAR, "11,21,31")).andReturn(censusList1);
		
		
		EasyMock.replay(delimitationConstituencyMandalDAO);
		EasyMock.replay(censusDAO);
		
		DelimitationConstituencyMandalService service = new DelimitationConstituencyMandalService();
		service.setConstituencyDAO(constituencyDAO);
		service.setDelimitationConstituencyMandalDAO(delimitationConstituencyMandalDAO);
		service.setDelimitationConstituencyDAO(delimitationConstituencyDAO);
		service.setCensusDAO(censusDAO);
		DelimitationConstituencyMandalResultVO actualResult = service.getMandalsForDelConstituency(1L);
		Assert.assertEquals(DelimitationConstituencyType.NON_EXISTING_CONSTITUENCY, actualResult.getConstituencyType());
		Assert.assertEquals(2, actualResult.getPresentMandals().size());
		Assert.assertEquals(3, actualResult.getMandalsBeforeDelimitationConstituency().size());
		
	}
	
	public Census fillCensusData(Census mandalCensus){

		mandalCensus.setTotalPopulation(10000L);		 
		mandalCensus.setTotalMalePopulation(8000L);
		mandalCensus.setTotalFemalePopulation(2000L);
		
		mandalCensus.setPopulationSC(2000L);
		mandalCensus.setFemaleSC(200L);
		mandalCensus.setMaleSC(1800L);

		mandalCensus.setPopulationST(1500L);
		mandalCensus.setFemaleST(500L);
		mandalCensus.setMaleST(1000L);

		mandalCensus.setPopulationLiterates(7500L);
		mandalCensus.setFemaleLiterates(2500L);
		mandalCensus.setMaleLiterates(5000L);

		mandalCensus.setPopulationIlliterates(5000L);
		mandalCensus.setFemaleIlliterates(1000L);
		mandalCensus.setMaleIlliterates(4000L);


		mandalCensus.setWorkingPopulation(7000L);
		mandalCensus.setWorkingFemale(2000L);
		mandalCensus.setWorkingMale(5000L);
		return mandalCensus;
	}
	
}
