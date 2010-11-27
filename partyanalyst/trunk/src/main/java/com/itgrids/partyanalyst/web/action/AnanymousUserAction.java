package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AnanymousUserAction extends ActionSupport implements
ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AnanymousUserAction.class);
	
	private ServletContext context;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String task;
	org.json.JSONObject jObj;
	private List<String> gender = new ArrayList<String>();
	private List<SelectOptionVO> dobDay;
	private List<SelectOptionVO> dobYear;
	private List<SelectOptionVO> dobMonth;
	private Long result;
	
	//for problem management login
    private String redirectLoc = null;
    private String name = null;
    private Long stateId = null;
    private Long districtId = null;
    private Long localBodyId = null;
    private Long constituencyId = null;
    private Long localBodyElectionTypeId = null;
	
	private IAnanymousUserService  ananymousUserService;
	private IStaticDataService  staticDataService;  
	
	public Long getResult() {
		return result;
	}
	public void setResult(Long result) {
		this.result = result;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}
	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
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
	public org.json.JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(org.json.JSONObject obj) {
		jObj = obj;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
        this.response = response;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	public List<String> getGender() {
		return gender;
	}
	public void setGender(List<String> gender) {
		this.gender = gender;
	}
	public List<SelectOptionVO> getDobDay() {
		return dobDay;
	}
	public void setDobDay(List<SelectOptionVO> dobDay) {
		this.dobDay = dobDay;
	}
	public List<SelectOptionVO> getDobYear() {
		return dobYear;
	}
	public void setDobYear(List<SelectOptionVO> dobYear) {
		this.dobYear = dobYear;
	}
	public List<SelectOptionVO> getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(List<SelectOptionVO> dobMonth) {
		this.dobMonth = dobMonth;
	}
	
	public String getRedirectLoc() {
		return redirectLoc;
	}
	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}
	public void setLocalBodyElectionTypeId(Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
	}
	public String execute(){
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}		
		
		dobDay = new ArrayList<SelectOptionVO>();
		for(int i=1;i<=31;i++)
		{
			SelectOptionVO dayElement = new SelectOptionVO();
			dayElement.setId(new Long(i));
			dayElement.setName(i+"");
			dobDay.add(dayElement);
		}
		
		
		dobYear = new ArrayList<SelectOptionVO>();
		for(int i=1950;i<=2010;i++)
		{
			SelectOptionVO yearElement = new SelectOptionVO();
			yearElement.setId(new Long(i));
			yearElement.setName(i+"");
			dobYear.add(yearElement);
		}
		
		dobMonth = new ArrayList<SelectOptionVO>();
		
		SelectOptionVO janVO = new SelectOptionVO();
		janVO.setId(new Long(1));
		janVO.setName("January");		
		SelectOptionVO febVO = new SelectOptionVO();
		febVO.setId(new Long(2));
		febVO.setName("February");
		SelectOptionVO marVO = new SelectOptionVO();
		marVO.setId(new Long(3));
		marVO.setName("March");
		SelectOptionVO aprVO = new SelectOptionVO();
		aprVO.setId(new Long(4));
		aprVO.setName("April");
		SelectOptionVO mayVO = new SelectOptionVO();
		mayVO.setId(new Long(5));
		mayVO.setName("May");
		SelectOptionVO junVO = new SelectOptionVO();
		junVO.setId(new Long(6));
		junVO.setName("June");
		SelectOptionVO julVO = new SelectOptionVO();
		julVO.setId(new Long(7));
		julVO.setName("July");
		SelectOptionVO augVO = new SelectOptionVO();
		augVO.setId(new Long(8));
		augVO.setName("August");
		SelectOptionVO sepVO = new SelectOptionVO();
		sepVO.setId(new Long(9));
		sepVO.setName("September");
		SelectOptionVO octVO = new SelectOptionVO();
		octVO.setId(new Long(10));
		octVO.setName("October");
		SelectOptionVO novVO = new SelectOptionVO();
		novVO.setId(new Long(11));
		novVO.setName("November");
		SelectOptionVO decVO = new SelectOptionVO();
		decVO.setId(new Long(12));
		decVO.setName("December");
		
		dobMonth.add(janVO);
		dobMonth.add(febVO);
		dobMonth.add(marVO);
		dobMonth.add(aprVO);
		dobMonth.add(mayVO);
		dobMonth.add(junVO);
		dobMonth.add(julVO);
		dobMonth.add(augVO);
		dobMonth.add(sepVO);
		dobMonth.add(octVO);
		dobMonth.add(novVO);
		dobMonth.add(decVO);
		session = request.getSession();
		session.setAttribute("gender", gender);
		session.setAttribute("dobDay",dobDay);
		session.setAttribute("dobYear",dobYear);
		session.setAttribute("dobMonth", dobMonth);
		return Action.SUCCESS;
	}
	
	public String checkForUserNameAvailability(){
		 try {
				jObj = new JSONObject(getTask());
				System.out.println(jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 result = new Long(ananymousUserService.checkForUserNameAvalilability(jObj.getString("userName")).getResultCode());
		 System.out.println(" Check User :" + result);
		 
		 return SUCCESS;
	 }
}
