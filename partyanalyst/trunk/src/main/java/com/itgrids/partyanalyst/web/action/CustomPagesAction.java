package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CustomPagesAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private ICandidateDetailsService candidateDetailsService; 
	private List<SelectOptionVO> candidatesList;
	private Long candidateId;
	private List<FileVO> fileVO;
	JSONObject jObj;
	private IPartyDetailsService partyDetailsService;
	private List<SelectOptionVO> partyList;
	private ISpecialPageService specialPageService;
	private List<SelectOptionVO> specialPages;
	private GallaryVO gallaryVO;
	private ResultStatus result;
	private List<CustomPageVO> customPageVO;
	
	
	
	public List<CustomPageVO> getCustomPageVO() {
		return customPageVO;
	}
	public void setCustomPageVO(List<CustomPageVO> customPageVO) {
		this.customPageVO = customPageVO;
	}
	public ResultStatus getResult() {
		return result;
	}
	public void setResult(ResultStatus result) {
		this.result = result;
	}
	public List<FileVO> getFileVO() {
		return fileVO;
	}
	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}
	public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}
	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}
	public List<SelectOptionVO> getSpecialPages() {
		return specialPages;
	}
	public void setSpecialPages(List<SelectOptionVO> specialPages) {
		this.specialPages = specialPages;
	}
	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}
	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}
	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public List<SelectOptionVO> getCandidatesList() {
		return candidatesList;
	}
	public void setCandidatesList(List<SelectOptionVO> candidatesList) {
		this.candidatesList = candidatesList;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	
	
	public String execute() throws Exception {
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (user != null) 
		{
			
			if (!user.getIsAdmin().equals("true"))
				  return ERROR;
			
			//candidate lists
	        candidatesList = candidateDetailsService.getCandidatesOfAUser(user.getRegistrationID());
			
			specialPages = specialPageService.getSpecialPageIdsList();
			partyList= partyDetailsService.getAllPartysNames();
			
			
		} 
		else
			return ERROR;
		
		return Action.SUCCESS;
	}
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("customPagesType"))
		{
			fileVO = candidateDetailsService.customPagesType();
		}
		else if(jObj.getString("task").equalsIgnoreCase("createCustomPages"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setPageName(jObj.getString("pageName"));
			gallaryVO.setPageId(jObj.getLong("pageId"));
			gallaryVO.setCustomPageName(jObj.getString("customPageName"));
			gallaryVO.setCustomPageType(jObj.getLong("customPageType"));
			
			result = candidateDetailsService.createCustomPages(gallaryVO);
		}
		if(jObj.getString("task").equalsIgnoreCase("getCustomPageNameAndType"))
		{
			customPageVO = candidateDetailsService.getCustomPages(jObj.getLong("pageId"),jObj.getString("pageName"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("updateCustomPages"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setPageName(jObj.getString("pageName"));
			gallaryVO.setPageId(jObj.getLong("pageId"));
			gallaryVO.setCustomPageName(jObj.getString("customPageName"));
			gallaryVO.setCustomPageType(jObj.getLong("customPageType"));
			
			result = candidateDetailsService.updateCustomPages(gallaryVO);
		}
		
		return Action.SUCCESS;
	}

}
