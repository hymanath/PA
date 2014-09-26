package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IBenefitAnalysisService;
import com.itgrids.partyanalyst.service.INewsAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BenefitAnalysisAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 8220075231227232574L;
	
	private HttpServletRequest request;
	private JSONObject jObj;
	private String task;
	private List<SelectOptionVO> resultList;
	private INewsAnalysisService newsAnalysisService;
	private IBenefitAnalysisService benefitAnalysisService;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
		
	public List<SelectOptionVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<SelectOptionVO> resultList) {
		this.resultList = resultList;
	}

	public INewsAnalysisService getNewsAnalysisService() {
		return newsAnalysisService;
	}

	public void setNewsAnalysisService(INewsAnalysisService newsAnalysisService) {
		this.newsAnalysisService = newsAnalysisService;
	}

	public IBenefitAnalysisService getBenefitAnalysisService() {
		return benefitAnalysisService;
	}

	public void setBenefitAnalysisService(
			IBenefitAnalysisService benefitAnalysisService) {
		this.benefitAnalysisService = benefitAnalysisService;
	}

	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String getAssemblyConstituenciesAndDistricts()
	{
		try 
		{
			LOG.info(" Entered into getAssemblyConstituenciesAndDistricts()  ");
			jObj = new JSONObject(getTask());
			 List<Long> states = new ArrayList<Long>();
				JSONArray stateId = jObj.getJSONArray("stateIds");
				if(stateId!=null && stateId.length()>0){
					for(int i=0; i<stateId.length();i++){
						states.add(Long.parseLong(stateId.getString(i)));
					}
				}
			if(jObj.getString("task").equalsIgnoreCase("assemblyConstituency"))
				resultList = newsAnalysisService.getAssemblyConstituenciesForStatesList(states);
			else if(jObj.getString("task").equalsIgnoreCase("districts"))
				resultList = newsAnalysisService.getDistrictsForStatesList(states);
		} catch (Exception e)
		{
			LOG.error(" Exception occured in getAssemblyConstituenciesAndDistricts()",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCategoryWiseBenifit(){
	  try{
		jObj = new JSONObject(getTask());
		
		
	  }catch(Exception e){
		  LOG.error(" Exception occured in getCategoryWiseBenifit ",e);
	  }
		return Action.SUCCESS;
	}

}
