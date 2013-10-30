package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class UserAction extends ActionSupport implements ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6962300034621699459L;

	private HttpServletRequest request;	
	
	private List<String> gender = new ArrayList<String>();
	private List<SelectOptionVO> parties;
	private IStaticDataService staticDataService;
	private IRegistrationService registrationService;	
	private IRegionServiceData regionServiceDataImp;
	private String registrationType;	
	private List<String> userType = new ArrayList<String>();
	private String userAccessType;
	private String userAccessValue;
	private String userAccessLocation;
	private List<SelectOptionVO> regionList;
	JSONObject jObj;
	private String task;
	
	 private List<SelectOptionVO> states;
	 private List<SelectOptionVO> districts;
	 private List<SelectOptionVO> constituencies;
	    public List<SelectOptionVO> getStates() {
		return states;
	}

	public void setStates(List<SelectOptionVO> states) {
		this.states = states;
	}


		public List<SelectOptionVO> getDistricts() {
		return districts;
	}

	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}

	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}


		
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public String getUserAccessLocation() {
		return userAccessLocation;
	}

	public void setUserAccessLocation(String userAccessLocation) {
		this.userAccessLocation = userAccessLocation;
	}

	public List<SelectOptionVO> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<SelectOptionVO> regionList) {
		this.regionList = regionList;
	}

	public String getUserAccessValue() {
		return userAccessValue;
	}

	public void setUserAccessValue(String userAccessValue) {
		this.userAccessValue = userAccessValue;
	}

	public String getUserAccessType() {
		return userAccessType;
	}

	public void setUserAccessType(String userAccessType) {
		this.userAccessType = userAccessType;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public List<String> getGender() {
		return gender;
	}
	public void setGender(List<String> gender) {
		this.gender = gender;
	}

	public List<SelectOptionVO> getParties() {
		return parties;
	}
	public void setParties(List<SelectOptionVO> parties) {
		this.parties = parties;
	}	
	
	public List<String> getUserType() {
		return userType;
	}

	public void setUserType(List<String> userType) {
		this.userType = userType;
	}

	public String registration() throws Exception
	{
		RegistrationVO registrationVO = (RegistrationVO) request.getSession().getAttribute(IConstants.USER);
		
		states = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		//constituencies = staticDataService.getConstituenciesByElectionTypeAndStateId(new Long(2), Long.parseLong(registrationVO.getState())).getConstituencies();
		districts = new ArrayList<SelectOptionVO>(0);
		 //constituencies = new ArrayList<SelectOptionVO>(0);
		 HttpSession session = request.getSession();	
		 session.setAttribute("states", states);
		//session.setAttribute("districts", districts);
		//session.setAttribute("constituencies", constituencies);
		if(registrationVO !=null && registrationVO.getIsAdmin().equalsIgnoreCase("true"))
		{
			List<String> type = new ArrayList<String>();
			if(type.size()==0){
				type.add("COUNTRY");
				type.add("STATE");
				type.add("DISTRICT");
				type.add("MLA");
				type.add("MP");			
			}
			if(userType.size() == 0)
			{
				userType.add("Party");
				userType.add("Politician");			
			}
			if(gender.size() == 0){
				gender.add("Male");
				gender.add("Female");
			}		
			
			parties = staticDataService.getStaticParties();
			//HttpSession session = request.getSession();
			session.setAttribute("type", type);
			session.setAttribute("userType", userType);
			session.setAttribute("gender", gender);
			session.setAttribute("parties", parties);
			
			
			
			return Action.SUCCESS;
		}
		else
			return IConstants.NOT_LOGGED_IN;
	}
	
	public String subUserRegistration()
	{
		HttpSession session = request.getSession();
		List<String> type = new ArrayList<String>();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		
		RegistrationVO regVO = registrationService.getDetailsOfUserByUserId(user.getRegistrationID());
		
		if(userType.size() == 0)
		{
			userType.add(regVO.getUserType());
		}
		
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}
		
		SelectOptionVO partyVO = new SelectOptionVO();
		partyVO.setId(regVO.getParty());
		partyVO.setName(regVO.getPartyShortName());		
		
		parties = new ArrayList<SelectOptionVO>();
		parties.add(partyVO);
		
		userAccessType = regVO.getAccessType();
		userAccessValue = regVO.getAccessValue();
		userAccessLocation = regionServiceDataImp.getRegionNameByRegionId(new Long(userAccessValue), userAccessType);
		
		if("COUNTRY".equalsIgnoreCase(regVO.getAccessType()))
		{
			type.add("COUNTRY");
			type.add("STATE");
			type.add("DISTRICT");
			type.add(IConstants.ASSEMBLY_ELECTION_TYPE);
			type.add(IConstants.PARLIAMENT_ELECTION_TYPE);		
			
			userAccessLocation = "India";
		}
		else if(IConstants.STATE.equalsIgnoreCase(regVO.getAccessType()))
		{
			type.add("STATE");
			type.add("DISTRICT");
			type.add(IConstants.ASSEMBLY_ELECTION_TYPE);
			type.add(IConstants.PARLIAMENT_ELECTION_TYPE);
		}
		else if(IConstants.DISTRICT.equalsIgnoreCase(regVO.getAccessType()))
		{
			type.add("DISTRICT");
			type.add(IConstants.ASSEMBLY_ELECTION_TYPE);
		}
		else if(IConstants.MLA.equalsIgnoreCase(regVO.getAccessType()))
		{			
			type.add(IConstants.ASSEMBLY_ELECTION_TYPE);		
		}
		else if(IConstants.MP.equalsIgnoreCase(regVO.getAccessType()))
		{		
			type.add(IConstants.ASSEMBLY_ELECTION_TYPE);
			type.add(IConstants.PARLIAMENT_ELECTION_TYPE);
		}
		
		states = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		
		session.setAttribute("states", states);
		session.setAttribute("type", type);
		session.setAttribute("userType", userType);
		session.setAttribute("gender", gender);
		session.setAttribute("parties", parties);
		
		return Action.SUCCESS;
	}
	
	
	public String getSubUserAccessValue()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		if(jObj.getString("reportLevel").equalsIgnoreCase(IConstants.STATE))
		{
			regionList = regionServiceDataImp.getStatesByCountry(new Long(jObj.getString("mainUserLocationId")));
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase(IConstants.DISTRICT))
		{
			regionList = regionServiceDataImp.getDistrictsByStateID(new Long(jObj.getString("mainUserLocationId")));
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
		{
			regionList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(jObj.getString("mainUserLocationId")));
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
		{
			regionList = regionServiceDataImp.getParliamentConstituenciesByDistrict(new Long(jObj.getString("mainUserLocationId")));
		}
		
		return Action.SUCCESS;
	}	
}
