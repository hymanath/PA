package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class StrattegtReportsAction extends ActionSupport implements
ServletRequestAware{

	private HttpServletRequest request ;
	private HttpSession session;
	private List<SelectOptionVO> constituenciesList;
	private Long constituencyId;
	private ICrossVotingEstimationService crossVotingEstimationService;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}


	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}


	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public String execute()
	{
		session = request.getSession();
		String type = request.getParameter("type");
		RegistrationVO regvo = (RegistrationVO) session.getAttribute("USER");
		List<SelectOptionVO> userAccessConstiList = null;
		if(regvo == null)
			return "input";
	  if(regvo.getAccessType() != null){
		if("MLA".equalsIgnoreCase(regvo.getAccessType())){
			userAccessConstiList = new ArrayList<SelectOptionVO>();
			SelectOptionVO constituencyVo = new SelectOptionVO();
			Long constiId = Long.valueOf(regvo.getAccessValue().trim());
			constituencyVo.setName(crossVotingEstimationService.getConstituencyName(constiId));
			constituencyVo.setId(constiId);
			userAccessConstiList.add(constituencyVo);
		}else if("STATE".equalsIgnoreCase(regvo.getAccessType()) || "MP".equalsIgnoreCase(regvo.getAccessType()) || "DISTRICT".equalsIgnoreCase(regvo.getAccessType()) || "COUNTRY".equalsIgnoreCase(regvo.getAccessType())){
			Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			userAccessConstiList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regvo.getRegistrationID(),electionYear,electionTypeId);
		}else{
			return Action.ERROR;
		}
		Set<Long> constituencyIds = new HashSet<Long>();
		for(SelectOptionVO vo:userAccessConstiList){
			constituencyIds.add(vo.getId());
		}
		if(type != null && type.equalsIgnoreCase("criticalPanchayats")){
		    constituenciesList =  crossVotingEstimationService.getRuralAndRurlaUrbanConstis(constituencyIds);
		}else{
			constituenciesList = userAccessConstiList;
		}
		if(constituenciesList.size() == 1){
			constituencyId = constituenciesList.get(0).getId();
		}
	  }else{
		  return Action.ERROR;
	  }
		
		return Action.SUCCESS;
	}
}
