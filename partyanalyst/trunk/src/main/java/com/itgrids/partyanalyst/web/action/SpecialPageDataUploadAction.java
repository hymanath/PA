package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.service.ISpecialPageDataUploadService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
public class SpecialPageDataUploadAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private List<SelectOptionVO> specialPagesList;
	
	private ISpecialPageDataUploadService specialPageDataUploadService;
	private ISpecialPageService specialPageService;
	
    private String savedStatus;
	
	public String getSavedStatus() {
		return savedStatus;
	}

	public void setSavedStatus(String savedStatus) {
		this.savedStatus = savedStatus;
	}
	
	
	
	public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}

	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}

	public ISpecialPageDataUploadService getSpecialPageDataUploadService() {
		return specialPageDataUploadService;
	}

	public void setSpecialPageDataUploadService(
			ISpecialPageDataUploadService specialPageDataUploadService) {
		this.specialPageDataUploadService = specialPageDataUploadService;
	}

	public List<SelectOptionVO> getSpecialPagesList() {
		return specialPagesList;
	}

	public void setSpecialPagesList(List<SelectOptionVO> specialPagesList) {
		this.specialPagesList = specialPagesList;
	}

	private static final Logger log = Logger.getLogger(SpecialPageDataUploadAction.class);

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute()throws Exception{
		
		specialPagesList = specialPageService.getAllSpecialPages();
		return Action.SUCCESS;
	}
	
     public String saveSpecialPageTextAction(){
		
		String specialPageText = (String)request.getParameter("specialPageText");
		
		net.sf.json.JSONObject specialPageJsonObj = (net.sf.json.JSONObject)JSONSerializer.toJSON(specialPageText);
		
		Long id = specialPageJsonObj.getLong("id");
		String text = specialPageJsonObj.getString("text");
		
		savedStatus = specialPageService.saveSpecialPageText(id,text);
		
		return Action.SUCCESS;
		
	}
	

}
