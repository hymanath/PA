package com.itgrids.partyanalyst.service;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.BiElectionResultsMainVO;
import com.itgrids.partyanalyst.dto.ConstituencyMandalVO;
import com.itgrids.partyanalyst.dto.ElectionDataVO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class ByeElectionPageServiceTest extends BaseDaoTestCase{

	private IBiElectionPageService biElectionPageService;

	public IBiElectionPageService getBiElectionPageService() {
		return biElectionPageService;
	}

	public void setBiElectionPageService(
			IBiElectionPageService biElectionPageService) {
		this.biElectionPageService = biElectionPageService;
	}
	
	public void testCheck(){
		BiElectionResultsMainVO obj = biElectionPageService.getMandalWiseResultsForSelectedPartiesInConstituency(363l);
		System.out.println("========"+obj.getUrbanRuralConstiResults().size());
		for(ElectionDataVO election:obj.getUrbanRuralConstiResults()){
			System.out.println(election.getElectionType()+"\t"+election.getElectionYear());
			for(SelectOptionVO party:election.getPartiesHeading())
				System.out.print(party.getName()+"\t");
			System.out.println();
			for(ConstituencyMandalVO conMan:election.getConstituencyMandalInfo()){
				System.out.print(conMan.getConstituencyName()+"\t"+conMan.getTehsilName()+"\t");
				for(PartyInfoVO party:conMan.getPartiesReslts())
					System.out.print(party.getPartyTotalVotes()+"\t"+party.getPercentageOfVotes()+"\t");
				System.out.println();
			}
		}
	}
	
}
