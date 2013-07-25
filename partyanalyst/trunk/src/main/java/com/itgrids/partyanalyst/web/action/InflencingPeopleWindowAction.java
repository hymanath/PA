package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class InflencingPeopleWindowAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private static final Logger LOG = Logger.getLogger(InflencingPeopleWindowAction.class);
	private HttpSession session;
	private String task = null;
	private IInfluencingPeopleService influencingPeopleService;
	private List<InfluencingPeopleVO> influencingPeopleVO;
	private List<SelectOptionVO> constituencyesList;
	JSONObject jObj = null;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	
	public List<InfluencingPeopleVO> getInfluencingPeopleVO() {
		return influencingPeopleVO;
	}

	public void setInfluencingPeopleVO(List<InfluencingPeopleVO> influencingPeopleVO) {
		this.influencingPeopleVO = influencingPeopleVO;
	}
	
	public List<SelectOptionVO> getConstituencyesList() {
		return constituencyesList;
	}

	public void setConstituencyesList(List<SelectOptionVO> constituencyesList) {
		this.constituencyesList = constituencyesList;
	}

	public String execute()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
		{
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String getInfluencingPeopleList()
	{
		try {
			LOG.debug("Enterd into getInfluencingPeopleList() method in InflencingPeopleWindowAction Action");
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			Long userId = regVO.getRegistrationID();
			String type = jObj.getString("type");
			influencingPeopleVO = influencingPeopleService.getAllInfluencingPeopleBasedOnUserAndType(userId,type);
		} catch (Exception e) {
			LOG.error("Exception raised in getInfluencingPeopleList() method in InflencingPeopleWindowAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getUserConstituencesList()
	{
		try {
			LOG.debug("Enterd into getUserConstituencesList() method in InflencingPeopleWindowAction Action");
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
			Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
			Long userId = regVO.getRegistrationID();
			String type = jObj.getString("type");
			constituencyesList = influencingPeopleService.getUserAccessConstituencyes(userId,electionYear,electionTypeId,type);
		} catch (Exception e) {
			LOG.error("Exception raised in getUserConstituencesList() method in InflencingPeopleWindowAction Action",e);
		}
		return Action.SUCCESS;
		
	}
}
