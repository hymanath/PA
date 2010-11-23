package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

public class BoothMapperServiceTest extends BaseDaoTestCase{
	
	private IBoothMapperService boothMapperService;

	public IBoothMapperService getBoothMapperService() {
		return boothMapperService;
	}

	public void setBoothMapperService(IBoothMapperService boothMapperService) {
		this.boothMapperService = boothMapperService;
	}
	
	public void testSaveBoothLocalElectionBodyMappingInfo(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(67000l);boothIds.add(67001l);boothIds.add(67002l);boothIds.add(67003l);boothIds.add(67004l);boothIds.add(67005l);
		boothMapperService.saveBoothLocalElectionBodyMappingInfo(boothIds,null, 488l, false);
		setComplete();
	}
	
	public void testSaveAssemblyLocalBodyMappingInfo(){
		List<Long> localBodyOrWardIds = new ArrayList<Long>();
		localBodyOrWardIds.add(7696l);
		localBodyOrWardIds.add(7697l);
		localBodyOrWardIds.add(7698l);
		localBodyOrWardIds.add(7699l);
		boothMapperService.saveAssemblyLocalBodyMappingInfo(488l, localBodyOrWardIds,null, 232l, "2009", true);
		setComplete();
	}

}
