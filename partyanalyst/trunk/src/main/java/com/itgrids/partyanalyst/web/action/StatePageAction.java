/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 16, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.googlecode.jsonplugin.annotations.JSON;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ElectionGoverningBodyVO;
import com.itgrids.partyanalyst.dto.StateElectionsVO;
import com.itgrids.partyanalyst.dto.StatePageVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IStatePageService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CandidateDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.ActionSupport;



public class StatePageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

		
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(StatePageAction.class);
	
	private String stateId;
	private List<StateElectionsVO> stateElections;
	private StatePageVO statePage;
	private List<CensusVO> censusVO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private IStatePageService statePageService;
	private IRegionServiceData regionServiceDataImp;	
	private List<SelectOptionVO> districtData;
	private int districtNumber;
	private String stateMapName;
	private JSONObject jsObj;
	private String task;
	private ElectionGoverningBodyVO electionGoverningBodyVO;
	private String host = IConstants.DEPLOYED_HOST;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public ElectionGoverningBodyVO getElectionGoverningBodyVO() {
		return electionGoverningBodyVO;
	}

	public void setElectionGoverningBodyVO(
			ElectionGoverningBodyVO electionGoverningBodyVO) {
		this.electionGoverningBodyVO = electionGoverningBodyVO;
	}
	public String getStateMapName() {
		return stateMapName;
	}

	public void setStateMapName(String stateMapName) {
		this.stateMapName = stateMapName;
	}

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
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public List<SelectOptionVO> getDistrictData() {
		return districtData;
	}

	public void setDistrictData(List<SelectOptionVO> districtData) {
		this.districtData = districtData;
	}
	
    public int getDistrictNumber() {
		return districtNumber;
	}

	public void setDistrictNumber(int districtNumber) {
		this.districtNumber = districtNumber;
	}

	public JSONObject getJsObj() {
		return jsObj;
	}

	public void setJsObj(JSONObject jsObj) {
		this.jsObj = jsObj;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}

	public String execute() throws Exception{    	
    
		
		
    	statePage = statePageService.getStateDetails(Long.parseLong(stateId)); 
    	
    	String statename = statePage.getStateName();
    	stateMapName = StringUtils.replaceOnce(statename, " ", "_");
    	
    	districtData = regionServiceDataImp.getDistrictsByStateID(Long.parseLong(stateId));
     	   	
    	districtNumber = districtData.size();   	
    	
    	if(statePage.getStateName().equalsIgnoreCase("NoState") || statePage == null || districtNumber == 0)
    		return ERROR;
    	    	
		stateElections = statePageService.getStateElections(statePage.getStateId());
   	  	
   	  	censusVO = statePageService.getCensusDetailsOfAState(statePage.getStateId(),2001);   	  	
   	  	
   	  	if(statePage == null || districtData == null)  	  	       	  		
    	 return ERROR;
   	  	
   	 electionGoverningBodyVO = statePageService.getChiefMinisterForAState(Long.parseLong(stateId));
   	 
   	return SUCCESS;
    }
	
	public String ajaxCallHandler() {
		
		try {
			jsObj = new JSONObject(getTask());
			
			if(jsObj.getString("task").equals("getElectionTypes")){
			Long stateId = (Long.parseLong(jsObj.getString("stateId")));
			stateElections = statePageService.getStateElections(stateId);
			if(stateElections == null){
				return ERROR;
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
					
		}
		return SUCCESS;
	}

	
}
