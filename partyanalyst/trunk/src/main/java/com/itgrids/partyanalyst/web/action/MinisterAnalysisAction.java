package com.itgrids.partyanalyst.web.action;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IMinisterAnalysisService;
import com.opensymphony.xwork2.Action;

public class MinisterAnalysisAction {
	private List<SelectOptionVO> statesList;
	private IStaticDataService staticDataService;
	private IMinisterAnalysisService ministerAnalysisService;
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	public IMinisterAnalysisService getMinisterAnalysisService() {
		return ministerAnalysisService;
	}

	public void setMinisterAnalysisService(
			IMinisterAnalysisService ministerAnalysisService) {
		this.ministerAnalysisService = ministerAnalysisService;
	}

	public String execute(){
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		return Action.SUCCESS;
	}
}
