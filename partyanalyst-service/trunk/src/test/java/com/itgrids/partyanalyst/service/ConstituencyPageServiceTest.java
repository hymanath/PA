package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.MandalWiseConstituencyElectionVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.RevenueVillageElectionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;

public class ConstituencyPageServiceTest extends BaseDaoTestCase{
	
	private IConstituencyPageService constituencyPageService;

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}
	
	/*public void test(){
		MandalAndRevenueVillagesInfoVO result = constituencyPageService.getTownshipWiseBoothDetailsForTehsil(844l, 2l);
		List<LocationWiseBoothDetailsVO> booths = (List<LocationWiseBoothDetailsVO>)result.getRevenueVillagesInfo();
		System.out.println("-----------"+booths.size());
		for(LocationWiseBoothDetailsVO obj:booths){
			System.out.println(obj.getLocationName()+" "+obj.getPopulation()+"--Valid Votes:"+obj.getVotesPolled());
		}
	}*/
	
	/*public void testForTownShipWiseResults(){
		List<PartyVotesEarnedVO> list = constituencyPageService.getTownshipWiseElectionsForTehsil(21819l, 2l);
		for(PartyVotesEarnedVO obj:list)
			System.out.println(obj.getPartyName()+"--"+obj.getPartyId()+"--"+obj.getVotesEarned());
	}*/
	
	/*public void test(){
		MandalWiseConstituencyElectionVO obj = constituencyPageService.getTownshipWiseBoothDetailsForTehsil(843l,1l);
		System.out.println(obj.getConstituencyRevenueVillagesVOs().size());
		for(ConstituencyRevenueVillagesVO obj1:obj.getConstituencyRevenueVillagesVOs()){
			System.out.println(obj1.getConstituencyName()+" "+obj1.getCandidateNamePartyAndStatus());
			for(RevenueVillageElectionVO obj2:obj1.getRevenueVillageElectionVO()){
				System.out.println(obj2.getTownshipName());
				System.out.println("------------------");
				for(SelectOptionVO booth:obj2.getBooths())
					System.out.println(booth.getName()+" "+booth.getId());
				System.out.println("------------------");
				for(PartyElectionResultVO obj3:obj2.getPartyElectionResultVOs())
					System.out.println(obj3.getVotesEarned()+" "+obj3.getVotesPercentage());
			}
				
		}
	}
	
	public void testGetTownshipWiseElectionsForTehsil(){
		constituencyPageService.getTownshipWiseElectionsForTehsil(21827l, 844l);
	}*/
	
	/*public void testGetConstituencyElecResults(){
		constituencyPageService.getConstituencyElecResults(403l, "2004",true);
	}*/
	
	public void test()
	{
		List<String> list = new ArrayList<String>();
		
		if(list.size() > 0)
		{
			System.out.println(list.size());
		}
		
		constituencyPageService.getCensusDetailsForAssemblyConstituency(231l,2009l,2001l);
		
	}

}
