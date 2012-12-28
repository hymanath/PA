package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class VotersEditSubAction  extends ActionSupport implements ServletRequestAware,Preparable,ModelDriven<VoterHouseInfoVO>{

	
	JSONObject jObj = null;
	private String task = null;
	private HttpServletRequest request;
	private HttpSession session;
	private VoterHouseInfoVO voterHouseInfoVO;
	private List<SelectOptionVO> partyGroupList;
	private List<VoterHouseInfoVO> userCategorysList;
	private IStaticDataService staticDataService;
	private IVotersAnalysisService votersAnalysisService;
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
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

	public VoterHouseInfoVO getVoterHouseInfoVO() {
		return voterHouseInfoVO;
	}

	public void setVoterHouseInfoVO(VoterHouseInfoVO voterHouseInfoVO) {
		this.voterHouseInfoVO = voterHouseInfoVO;
	}
	
	

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public List<SelectOptionVO> getPartyGroupList() {
		return partyGroupList;
	}

	public void setPartyGroupList(List<SelectOptionVO> partyGroupList) {
		this.partyGroupList = partyGroupList;
	}

	public List<VoterHouseInfoVO> getUserCategorysList() {
		return userCategorysList;
	}

	public void setUserCategorysList(List<VoterHouseInfoVO> userCategorysList) {
		this.userCategorysList = userCategorysList;
	}

	public String execute() throws Exception{
		return SUCCESS;
	}
	
	
	
	public void prepare() throws Exception {
	
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		
		partyGroupList=staticDataService.getStaticStateParties(1l);
		partyGroupList.add(0, new SelectOptionVO(0l,"select party"));
		userCategorysList=votersAnalysisService.getUserCategoryValues();
		userCategorysList.add(0, new VoterHouseInfoVO(0l,"Select Category"));
		
	}
	
	
	public VoterHouseInfoVO getModel() {
		// TODO Auto-generated method stub
			return voterHouseInfoVO;
	}


}
