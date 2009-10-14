/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 16, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.dto.StatePageVO;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.opensymphony.xwork2.ActionSupport;



public class StatePageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

		
	private static final long serialVersionUID = 1L;
	
	private String stateId;
	private List<StateElectionsVO> stateElections;
	private StatePageVO statePage;
	private List<CensusVO> censusVO;
	 private HttpServletRequest request;
	  private HttpServletResponse response;
	  private HttpSession session;
	  private IStatePageService statePageService;
	
	  
	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public List<StateElectionsVO> getStateElections() {
		return stateElections;
	}

	public void setStateElections(List<StateElectionsVO> stateElections) {
		this.stateElections = stateElections;
	}

	public StatePageVO getStatePage() {
		return statePage;
	}

	public void setStatePage(StatePageVO statePage) {
		this.statePage = statePage;
	}

     

    public List<CensusVO> getCensusVO() {
		return censusVO;
	}

	public void setCensusVO(List<CensusVO> censusVO) {
		this.censusVO = censusVO;
	}
	
	  public IStatePageService getStatePageService() {
		return statePageService;
	  }

	  public void setStatePageService(IStatePageService statePageService) {
		this.statePageService = statePageService;
	  }

	public void setServletRequest(HttpServletRequest request) {
		 this.request = request;
   	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
   
	}
	
    public String execute() throws Exception{
    	
    	statePage = statePageService.getStateDetails(Long.parseLong(stateId));
    	
    	if(statePage.getStateName().equalsIgnoreCase("NoState") || statePage == null)
    	return ERROR;
    	
    	
    		stateElections = statePageService.getStateElections(statePage.getStateId());
       	  	
       	  	censusVO = statePageService.getCensusDetails(statePage.getStateId(), 2001);
       	  	
       	  	
       	  	if(stateElections == null)
       	  	return ERROR;
       	  	       	  		
    	
    return SUCCESS;
    }
}
