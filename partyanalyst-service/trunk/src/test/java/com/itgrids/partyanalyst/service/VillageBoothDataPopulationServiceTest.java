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
		File file = new File("d:/Book1.xls");
		villageBoothDataPopulationService.readExcelAndInsertData(file, 2l);
		setComplete();
	}
	
}
