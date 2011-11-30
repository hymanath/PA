package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class PartyManagementAction extends ActionSupport implements ServletRequestAware{

	
	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> partyList;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private IPartyDetailsService partyDetailsService;
	private GallaryVO gallaryVO;
	private ResultStatus result;
	private List<GallaryVO> gallaryList;
	public List<GallaryVO> getGallaryList() {
		return gallaryList;
	}

	public void setGallaryList(List<GallaryVO> gallaryList) {
		this.gallaryList = gallaryList;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public GallaryVO getGallaryVO() {
		return gallaryVO;
	}

	public void setGallaryVO(GallaryVO gallaryVO) {
		this.gallaryVO = gallaryVO;
	}

	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}

	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}

	private String task = null;
	JSONObject jObj = null;
	

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	public void setServletResponse(HttpServletResponse response){
		this.response =  response;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	
 public String execute()
 {
	 partyList= partyDetailsService.getAllPartysNames();
	 return SUCCESS;
	 
 }
 public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(jObj.getString("task").equalsIgnoreCase("saveDiscription"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("partyId"));
			gallaryVO.setDescription(jObj.getString("fileDesc"));
			result = partyDetailsService.saveDescription(gallaryVO);
		}
		else if(jObj.getString("task").equalsIgnoreCase("partyDescriptionUpdate"))
		{
		//	gallaryList = partyDetailsService.getCandidateProfileInfo(jObj.getLong("candidateId"));
		}
		return Action.SUCCESS;
}
}
