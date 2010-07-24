package com.itgrids.partyanalyst.service;

import java.util.HashSet;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.BiElectionResultsMainVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
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
	
	/*public void testCheck(){
		BiElectionResultsMainVO obj = biElectionPageService.getMandalWiseResultsForSelectedPartiesInConstituency(342l);
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

		ElectionWiseMandalPartyResultListVO election = obj.getAllPartiesElecInfo();
		for(SelectOptionVO party:election.getElections())
			System.out.print(party.getName()+"\t");
		//createDataset(null);
		//ChartProducer.createLineChart(title, xAxis, yAxis, dataset, path, height, width, colors, thickLines);
	}*/
	
	/*public void testCheckSelectedForamt(){
		Set<String> parties = new HashSet<String>();
		parties.add("INC");parties.add("TRS");
		Set<String> elections = new HashSet<String>();
		elections.add("2004 Assembly");//elections.add("2009 Parliament");
		ElectionWiseMandalPartyResultListVO result = biElectionPageService.getResultsOfRuralUrbanAreaBeasedOnSelection(362l, 
				parties, elections, false, true);
	}*/
	
	public void testGetAllDistricts(){
		biElectionPageService.getAllTelanganaConstituencieswisePartiesResultsBasedOnExpectedPercentage("20");
	}
	
}
