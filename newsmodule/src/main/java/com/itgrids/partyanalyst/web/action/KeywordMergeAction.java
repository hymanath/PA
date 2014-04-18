package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IKeywordsService;
import com.opensymphony.xwork2.Action;

public class KeywordMergeAction  implements ServletRequestAware{
	private HttpServletRequest request;
	
	private IKeywordsService keywordsService;
	
	private List<SelectOptionVO> keywordsList;
	
	private SelectOptionVO selectOptionVO;

	public List<SelectOptionVO> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<SelectOptionVO> keywordsList) {
		this.keywordsList = keywordsList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public IKeywordsService getKeywordsService() {
		return keywordsService;
	}

	public void setKeywordsService(IKeywordsService keywordsService) {
		this.keywordsService = keywordsService;
	}

	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}

	public String execute(){
		
		HttpSession session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null){
			return Action.ERROR;
		}else if(regVO.getUserAccessType() == null){
		  return "fail";
		}else if(!regVO.getUserAccessType().equalsIgnoreCase("Admin")){
			return "fail";
		}else{
			return Action.SUCCESS;
		}	
		
	}
	
	public String getAllKeyWords(){
		HttpSession session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null){
			return Action.ERROR;
		}else if(regVO.getUserAccessType() == null){
		  return "fail";
		}else if(!regVO.getUserAccessType().equalsIgnoreCase("Admin")){
			return "fail";
		}else{
			String searchStr = request.getParameter("searchStr");
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			Integer maxIndex = Integer.parseInt(request.getParameter("results"));
			selectOptionVO = keywordsService.getkeywords(searchStr, startIndex, maxIndex);
		}
		return Action.SUCCESS;
	}
}
