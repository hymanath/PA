package com.itgrids.partyanalyst.service;

import java.io.File;

import org.appfuse.dao.BaseDaoTestCase;


public class BoothPopulationServiceTest extends BaseDaoTestCase{
	
	private IBoothPopulationService boothPopulationService;

	public IBoothPopulationService getBoothPopulationService() {
		return boothPopulationService;
	}

	public void setBoothPopulationService(
			IBoothPopulationService boothPopulationService) {
		this.boothPopulationService = boothPopulationService;
	}

	public void testDataUpload(){
		try{
			boothPopulationService.readExcelAndPopulateBoothData(new File("C:/Documents and Settings/Thaniga/Desktop/Datafiles28-04-10/BoothData/1.Nellore/2009/Nellore_boothdata_2009.xls"), "2009", 2l, false);
			setComplete();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
