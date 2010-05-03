package com.itgrids.partyanalyst.service;

import java.io.File;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.MPTCElectionResultVO;

public class MuncipleDataUploadServiceTest extends BaseDaoTestCase{

	private IMuncipleDataUploadService muncipleDataUploadService;

	public IMuncipleDataUploadService getMuncipleDataUploadService() {
		return muncipleDataUploadService;
	}

	public void setMuncipleDataUploadService(
			IMuncipleDataUploadService muncipleDataUploadService) {
		this.muncipleDataUploadService = muncipleDataUploadService;
	}
	
	public void testUpload() throws Exception{
		
		File file = new File("c:/updated_muncipal data 2005.xls");
		long start = System.currentTimeMillis();
		MPTCElectionResultVO result = muncipleDataUploadService.readExcelDataForMuncipalities(file, 5l, 1l, "2005");
		setComplete();
		long end = System.currentTimeMillis();
		System.out.println("Total Time Taken::"+((end-start)/1000));
		result.getExceptionEncountered().printStackTrace();
	}
	
}
