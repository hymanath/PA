package com.itgrids.partyanalyst.service;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;

public class ConstituencyPageServiceTest extends BaseDaoTestCase{
	
	private IConstituencyPageService constituencyPageService;

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}
	
	public void test(){
		MandalAndRevenueVillagesInfoVO result = constituencyPageService.getTownshipWiseBoothDetailsForTehsil(844l, 2l);
		List<LocationWiseBoothDetailsVO> booths = (List<LocationWiseBoothDetailsVO>)result.getRevenueVillagesInfo();
		System.out.println("-----------"+booths.size());
		for(LocationWiseBoothDetailsVO obj:booths){
			System.out.println(obj.getLocationName()+" "+obj.getPopulation());
		}
	}

}
