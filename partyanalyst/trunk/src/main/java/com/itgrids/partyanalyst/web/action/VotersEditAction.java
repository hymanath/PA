package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class VotersEditAction  extends ActionSupport implements ServletRequestAware,Preparable,ModelDriven<VoterHouseInfoVO>{
	private IVotersAnalysisService votersAnalysisService;
	JSONObject jObj = null;
	private String task = null;
	private HttpServletRequest request;
	private HttpSession session;
	private VoterHouseInfoVO voterHouseInfoVO;
	private VoterHouseInfoVO voterHouseInfoVO1;
	private Long voterId;
	private ResultStatus result;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> partyGroupList;
	
	private List<VoterHouseInfoVO> userCategorysList;
	private String resultStr;
	private List<SelectOptionVO> voterCategoryValues;
	private SelectOptionVO selectOptionVO;
	private String save;
	
	//private String windowTask = null;
	
	
	
	public List<SelectOptionVO> getPartyGroupList() {
		return partyGroupList;
	}

	public List<SelectOptionVO> getVoterCategoryValues() {
		return voterCategoryValues;
	}

	public void setVoterCategoryValues(List<SelectOptionVO> voterCategoryValues) {
		this.voterCategoryValues = voterCategoryValues;
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

	public List<VoterHouseInfoVO> getUserCategorysList() {
		return userCategorysList;
	}

	public void setUserCategorysList(List<VoterHouseInfoVO> userCategorysList) {
		this.userCategorysList = userCategorysList;
	}

	public VoterHouseInfoVO getVoterHouseInfoVO1() {
		return voterHouseInfoVO1;
	}

	public void setVoterHouseInfoVO1(VoterHouseInfoVO voterHouseInfoVO1) {
		this.voterHouseInfoVO1 = voterHouseInfoVO1;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public void setPartyGroupList(List<SelectOptionVO> partyGroupList) {
		this.partyGroupList = partyGroupList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
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

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	
	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String execute() throws Exception{
		
		//session.setAttribute(ISessionConstants.WINDOW_TASK,windowTask);
/*		HttpSession session = request.getSession();
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		try{
			if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) !=null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE).equals(true))
			{
				String accessType =user.getAccessType();
				Long accessValue= new Long(user.getAccessValue());
				
			}*/
		
		return SUCCESS;
	}
	

public String saveVoterDetails(){
	
	HttpSession session = request.getSession();
	RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
	Long userId = null;
	if(user != null && user.getRegistrationID() != null)
	    userId = user.getRegistrationID();
	else 
	  return "error";
	voterHouseInfoVO.setUserId(userId);
	String voterId = request.getParameter("voterId");
	String boothId = request.getParameter("boothId");
	if(voterId != null && voterId.trim().length() > 0)
	 voterHouseInfoVO.setVoterId(new Long(voterId));
	if(boothId != null && boothId.trim().length() > 0)
		 voterHouseInfoVO.setBoothId(new Long(boothId));
	 votersAnalysisService.updateVoterDetails(voterHouseInfoVO);
	request.setAttribute("voterId", voterHouseInfoVO.getVoterId());
	resultStr = SUCCESS;
	request.setAttribute("resultStr", resultStr);
	return Action.SUCCESS;

}
	
	public void prepare() throws Exception {
   if(request.getParameter("save") == null){
	String voterId = request.getParameter("voterId");
	String boothId = request.getParameter("boothId");
	HttpSession session = request.getSession();
	RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
	Long userId = user.getRegistrationID();
	//VoterHouseInfoVO voterHouseInfoVO=new VoterHouseInfoVO();
	
	//List<SelectOptionVO> partyGroupList=null;
	if(voterId !=null)
	{
		voterHouseInfoVO1=votersAnalysisService.getBoothDetailsForVoter(new Long(boothId));
		voterHouseInfoVO= votersAnalysisService.getVoterPersonalDetailsByVoterId(new Long(voterId),userId);
		
		voterHouseInfoVO.setBoothName(voterHouseInfoVO1.getBoothName());
		voterHouseInfoVO.setVilliageCovered(voterHouseInfoVO1.getVilliageCovered());
		voterHouseInfoVO.setPanchayatName(voterHouseInfoVO1.getPanchayatName());
		
	}
   }
  }
	
	public VoterHouseInfoVO getModel() {
		// TODO Auto-generated method stub
			return getVoterHouseInfoVO();
	}
	
	public String callAjaxHandler(){
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getVoterCategories")){
			System.out.println("with in the action");
			Long voterCategoryId=new Long(jObj.getString("voterCategory"));
			String letters=jObj.getString("letters");
			voterCategoryValues = votersAnalysisService.getVoterCategoryValues(voterCategoryId,letters);
			System.out.println("voterCategoryValues value is:"+voterCategoryValues);
		}
		else if(jObj.getString("task").equalsIgnoreCase("storeValues"))
		{
				String groupName = jObj.getString("name");
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
					return ERROR;
				
				
				selectOptionVO = votersAnalysisService.storeGroupName(user.getRegistrationID(),groupName);
				
				return  "category";
		}
		else if(jObj.getString("task").equalsIgnoreCase("getCategoryValues"))
		{
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
				return ERROR;
				voterCategoryValues = votersAnalysisService.findVoterCategoryValues(user.getRegistrationID());
				voterCategoryValues.add(0, new SelectOptionVO(0l,"Select Group"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("storeCategoeryValues"))
		{
				String name =  jObj.getString("categoryValue");
				Long id = jObj.getLong("categoryId");
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
				return ERROR;
				
				selectOptionVO = votersAnalysisService.storeCategoryVakues(user.getRegistrationID(),name,id);
				return  "category";
		}
		
		
		
		return Action.SUCCESS;
	}
	
	}
