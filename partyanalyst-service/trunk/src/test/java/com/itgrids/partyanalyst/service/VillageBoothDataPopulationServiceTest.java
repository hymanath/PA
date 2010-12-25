package com.itgrids.partyanalyst.service;

import java.io.File;

import org.appfuse.dao.BaseDaoTestCase;

public class VillageBoothDataPopulationServiceTest extends BaseDaoTestCase{

	private IVillageBoothDataPopulationService villageBoothDataPopulationService;

	public IVillageBoothDataPopulationService getVillageBoothDataPopulationService() {
		return villageBoothDataPopulationService;
	}

	public void setVillageBoothDataPopulationService(
			IVillageBoothDataPopulationService villageBoothDataPopulationService) {
		this.villageBoothDataPopulationService = villageBoothDataPopulationService;
	}
	
	public void testReadExcelAndInsertData(){
		long start = System.currentTimeMillis();
		File file = new File("d:/NelloreMapping2004.xls");
		villageBoothDataPopulationService.readExcelAndInsertData(file, 5l, false);
		long end = System.currentTimeMillis();
		System.out.println((end-start)/1000);
		setComplete();
	}
	
}
