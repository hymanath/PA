package com.itgrids.partyanalyst.service;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.service.impl.BoothDataValidationService;
import com.itgrids.partyanalyst.util.DummyPartyResultsData;
import com.itgrids.partyanalyst.util.MockData;

public class BoothDataValidationServiceTest {

	private ITehsilDAO tehsilDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private BoothDataValidationService boothDataValidationService;
	
	@Before
	public void setUp() throws Exception {
		tehsilDAO = EasyMock.createMock(ITehsilDAO.class);
		constituencyElectionDAO = EasyMock.createMock(IConstituencyElectionDAO.class);
		boothDataValidationService = new BoothDataValidationService();
		boothDataValidationService.setConstituencyElectionDAO(constituencyElectionDAO);
		boothDataValidationService.setTehsilDAO(tehsilDAO);
	}
	
	@Test
	public void checkVillageCensusCode(){
		EasyMock.expect(tehsilDAO.findByTehsilNameAndDistrict("Kondapuram", new Long(19))).andReturn(MockData.getTehsils());
		EasyMock.replay(tehsilDAO);
		BoothInfo boothRecord = new BoothInfo();
		boothRecord.setCensusCode("222332# 3456");
		boothRecord.setPartNo("12_ramulapalem");
		boothDataValidationService.checkAndInsertBooth(new Constituency(new Long(128)), boothRecord, new Long(19), "Atmakur", new ArrayList<String>());
		EasyMock.verify(tehsilDAO);
	}
}
