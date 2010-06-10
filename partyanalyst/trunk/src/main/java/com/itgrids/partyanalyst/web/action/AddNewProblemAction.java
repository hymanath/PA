package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AddNewProblemAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddNewProblemAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villageList;
	private List<SelectOptionVO> hamletList;
	private List<SelectOptionVO> problemSources;
	private List<SelectOptionVO> parliamentConstituencyList;
	private IProblemManagementService problemManagementService;	
	private String accessType;
	private IRegionServiceData regionServiceDataImp;
	private CadreManagementService cadreManagementService;
	private IStaticDataService staticDataService;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}

	public List<SelectOptionVO> getVillageList() {
		return villageList;
	}
	
	public void setVillageList(List<SelectOptionVO> villageList) {
		this.villageList = villageList;
	}
	
	public List<SelectOptionVO> getHamletList() {
		return hamletList;
	}

	public void setHamletList(List<SelectOptionVO> hamletList) {
		this.hamletList = hamletList;
	}

	public List<SelectOptionVO> getProblemSources() {
		return problemSources;
	}

	public void setProblemSources(List<SelectOptionVO> problemSources) {
		this.problemSources = problemSources;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}	

	public String getAccessType() {
		return accessType;
	}
	
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}	

	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}

	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}	

	public String execute () throws Exception 
	{
		problemSources = new ArrayList<SelectOptionVO>();
		problemSources = problemManagementService.getAllTypesOfProblemSources();
		problemSources.add(0,new SelectOptionVO(0L,"Select Problem Source"));		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return ERROR;
				
		accessType =user.getAccessType();
		Long accessValue= new Long(user.getAccessValue());
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		villageList = new ArrayList<SelectOptionVO>();
		parliamentConstituencyList = new ArrayList<SelectOptionVO>();
		session = request.getSession();
		session.setAttribute("problemSources", problemSources);
		if("MLA".equals(accessType))
		{
			log.debug("Access Type = MLA ****");
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			
			stateList.add(list.get(0));			
			districtList.add(list.get(1));			
			constituencyList.add(list.get(2));
			mandalList = regionServiceDataImp.getMandalsByConstituencyID(accessValue);
			mandalList.add(0,new SelectOptionVO(0L,"Select Mandal"));
			session.setAttribute("stateList", stateList);
			session.setAttribute("districtList",districtList);
			session.setAttribute("constituencyList",constituencyList);
			session.setAttribute("mandalList",mandalList);
			
						
		}else if("COUNTRY".equals(accessType))
		{
			log.debug("Access Type = Country ****");
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			stateList.add(0,new SelectOptionVO(0L, "Select State"));
			session.setAttribute("stateList", stateList);
			
		}else if("STATE".equals(accessType)){
			log.debug("Access Type = State ****");
			
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);			
			stateList.add(obj2);
			districtList = staticDataService.getDistricts(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));
			session.setAttribute("stateList", stateList);
			session.setAttribute("districtList",districtList);
			
		}else if("DISTRICT".equals(accessType)){
			log.debug("Access Type = District ****");			
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituencyList.add(0, new SelectOptionVO(0l,"Select Constituency"));
			session.setAttribute("stateList", stateList);
			session.setAttribute("districtList",districtList);
			session.setAttribute("constituencyList",constituencyList);
			
		} else if("MP".equals(accessType)){
			log.debug("Access Type = MP ****");
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			stateList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
			session.setAttribute("stateList", stateList);
			session.setAttribute("parliamentConstituencyList",parliamentConstituencyList);
			session.setAttribute("constituencyList",constituencyList);
			log.debug("constituencyList.size():"+constituencyList.size());
			log.debug("parliamentConstituencyList.size():"+parliamentConstituencyList.size());
			
		}
		
		
		return SUCCESS;
	}
	
	public String getDistricts()
	{
		session = request.getSession();
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		
		Long stateId = jObj.getLong("id");
		districtList = new ArrayList<SelectOptionVO>();
		districtList = staticDataService.getDistricts(stateId);
		districtList.add(0,new SelectOptionVO(0l,"Select District"));
		session.setAttribute("districtList",districtList);
		return SUCCESS;
	}
	

}
