package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.DistrictWiseConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionsDetailedResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;

public class partyInfluenceAction extends ActionSupport implements ServletContextAware,ServletConfigAware{
	
	DistrictWiseConstituencyElectionResultsVO districtResults;

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletConfig(ServletConfig arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public DistrictWiseConstituencyElectionResultsVO getDistrictResults() {
		return districtResults;
	}

	public void setDistrictResults(
			DistrictWiseConstituencyElectionResultsVO districtResults) {
		this.districtResults = districtResults;
	}

	public String execute()
	{
		System.out.println("in party Influence Action ******");
		
		ConstituencyElectionResultVO prpresultsVO1 = new ConstituencyElectionResultVO();
		prpresultsVO1.setCandidateId(new Long(1));
		prpresultsVO1.setCandidateName("Candidatename");
		prpresultsVO1.setHasRebel(false);
		prpresultsVO1.setPartyName("PRP");
		prpresultsVO1.setPercentageOfVotes("20");
		prpresultsVO1.setVotesEarned("15000");
		prpresultsVO1.setYear("2009");
		
		ConstituencyElectionResultVO incresultsVO1 = new ConstituencyElectionResultVO();
		incresultsVO1 .setCandidateId(new Long(1));
		incresultsVO1 .setCandidateName("Candidatename");
		incresultsVO1 .setHasRebel(false);
		incresultsVO1 .setPartyName("INC");
		incresultsVO1 .setPercentageOfVotes("40");
		incresultsVO1.setVotesEarned("35000");
		incresultsVO1 .setYear("2009");
		
		ConstituencyElectionResultVO incresultsVO2 = new ConstituencyElectionResultVO();
		incresultsVO2 .setCandidateId(new Long(1));
		incresultsVO2 .setCandidateName("Candidatename");
		incresultsVO2 .setHasRebel(true);
		incresultsVO2 .setPartyName("INC");
		incresultsVO2 .setPercentageOfVotes("40");
		incresultsVO2.setVotesEarned("35000");
		incresultsVO2 .setYear("2004");
		
		//
		
		ConstituencyElectionResults cResults1 = new ConstituencyElectionResults();
		cResults1.setElectionResultForPartyOne(prpresultsVO1);
		cResults1.setElectionResultForPartyTwo(incresultsVO1);
		
		ConstituencyElectionResults cResults2 = new ConstituencyElectionResults();
		cResults2.setElectionResultForPartyOne(incresultsVO2);
		
		//
		
		ConstituencyElectionsDetailedResultVO electionResults = new ConstituencyElectionsDetailedResultVO();
		electionResults.setConstituencyId(new Long(1));
		electionResults.setConstituencyName("Nellore");
		electionResults.setDistrictId(new Long(1));
		electionResults.setVotesPercentageDiff("20");
		electionResults.setConstituencyElectionResultsOne(cResults1);
		electionResults.setConstituencyElectionResultsTwo(cResults2);		
		
		List<ConstituencyElectionsDetailedResultVO> electionResultsList =  new ArrayList<ConstituencyElectionsDetailedResultVO>();
		electionResultsList.add(electionResults);
		
		//
		
		districtResults = new DistrictWiseConstituencyElectionResultsVO();
		
		districtResults.setDistrictId(new Long(1));
		districtResults.setDistrictName("Nellore");
		districtResults.setDistrictVotesPercentageDiff("25");
		districtResults.setStateName("Andhra Pradesh");
		districtResults.setConstituencyElectionsDetailedResults(electionResultsList);
		
		setDistrictResults(districtResults);
		
		return Action.SUCCESS;
	}

}
