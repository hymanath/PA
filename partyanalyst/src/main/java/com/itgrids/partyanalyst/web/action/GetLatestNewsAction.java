/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 23, 2010
 */
package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dto.EPaperVO;
import com.itgrids.partyanalyst.dto.NewsPaperURLsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IEPaperService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class GetLatestNewsAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(GetLatestNewsAction.class);
	
	private String task = null;
	JSONObject jObj = null;
	private HttpServletRequest request;
	private HttpSession session;
	private IEPaperService epaperService;
	private NewsPaperURLsVO newsPaperURLsVO;
	private List<SelectOptionVO> allDistrictsList;
		
	private Long stateID;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
		
	public IEPaperService getEpaperService() {
		return epaperService;
	}

	public void setEpaperService(IEPaperService epaperService) {
		this.epaperService = epaperService;
	}	

	public NewsPaperURLsVO getNewsPaperURLsVO() {
		return newsPaperURLsVO;
	}

	public void setNewsPaperURLsVO(NewsPaperURLsVO newsPaperURLsVO) {
		this.newsPaperURLsVO = newsPaperURLsVO;
	}	
	
	public List<SelectOptionVO> getAllDistrictsList() {
		return allDistrictsList;
	}

	public void setAllDistrictsList(List<SelectOptionVO> allDistrictsList) {
		this.allDistrictsList = allDistrictsList;
	}

	public String execute() throws Exception
	{	
		newsPaperURLsVO =  new NewsPaperURLsVO();
			
		String accessType = "STATE";
		Long accessValue = 1l;
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Task::"+jObj.getString("task"));
		allDistrictsList = new ArrayList<SelectOptionVO>();
		if(jObj.getString("task").equalsIgnoreCase("distPapersUrl"))
		{	
			if("MLA".equals(accessType) || "MP".equals(accessType) || "STATE".equals(accessType) || "DISTRICT".equals(accessType))
			{	
				log.debug(accessType);
				log.debug(accessValue);
				if("DISTRICT".equals(accessType))
					stateID = epaperService.getStateIdFromDistrictByDistrictId(accessValue);
				else if("STATE".equals(accessType))
					stateID = new Long(accessValue);
				else
				stateID= epaperService.getStateIdFromConstitunecyByAccessValue(accessValue);
				allDistrictsList = epaperService.getDistrictsForState(stateID);
				allDistrictsList.set(0, new SelectOptionVO(0l,"Select District"));
				/*
				 stateID= epaperService.getStateIdFromConstitunecyByAccessValue(accessValue);
				allDistrictsList = epaperService.getDistrictsForState(stateID);
				allDistrictsList.add(new SelectOptionVO(0l,"Select District")); 
				   */
			}
			newsPaperURLsVO.setDistrictsList(allDistrictsList);
			newsPaperURLsVO.setEPapersURLsVO(epaperService.getEPapers(accessType, accessValue));			
		} if(jObj.getString("task").equalsIgnoreCase("getSelectedDistPaper"))
		{
			String districtId = jObj.getString("selected");
			newsPaperURLsVO.setEPapersURLsVO(epaperService.getEPapersForDistrict(new Long(districtId)));			
		}
		
		return Action.SUCCESS;
	}

	

}
