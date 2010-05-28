package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.CrossVotingEstimationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyManagementAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConstituencyManagementAction.class);
	private CadreManagementService cadreManagementService;
	private IProblemManagementService problemManagementService;
	private CrossVotingEstimationService crossVotingEstimationService;
	private ConstituencyManagementVO constituencyManagementVO;
	private HttpServletRequest request;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villageList;
	private List<SelectOptionVO> hamletList;
	private IRegionServiceData regionServiceData;
	private List<SelectOptionVO> problemSources;	
	private List<SelectOptionVO> statesList, districtsList, constituenciesList; 
	private IProblemManagementReportService problemManagementReportService;
	private String accessType;
	private List<InfluencingPeopleVO> influencingPeopleVO;
	private String task = null;
	JSONObject jObj = null;
	private String cmTask;
	
	
	public String getCmTask() {
		return cmTask;
	}

	public void setCmTask(String cmTask) {
		this.cmTask = cmTask;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
		
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public CrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			CrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
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

	public IRegionServiceData getRegionServiceData() {
		return regionServiceData;
	}

	public void setRegionServiceData(IRegionServiceData regionServiceData) {
		this.regionServiceData = regionServiceData;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
			
	public List<SelectOptionVO> getProblemSources() {
		return problemSources;
	}

	public void setProblemSources(List<SelectOptionVO> problemSources) {
		this.problemSources = problemSources;
	}
	
	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}	

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<InfluencingPeopleVO> getInfluencingPeopleVO() {
		return influencingPeopleVO;
	}

	public void setInfluencingPeopleVO(List<InfluencingPeopleVO> influencingPeopleVO) {
		this.influencingPeopleVO = influencingPeopleVO;
	}
	
	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}


	public String execute() throws Exception{
		
		log.debug("In execute of Constituency Management Action ********");

		SelectOptionVO probSource1 = new SelectOptionVO(2L, IConstants.CALL_CENTER);
		SelectOptionVO probSource2 = new SelectOptionVO(3L, IConstants.USER);
		SelectOptionVO probSource3 = new SelectOptionVO(4L, IConstants.EXTERNAL_PERSON);
		problemSources = new ArrayList<SelectOptionVO>();
		problemSources.add(probSource1);
		problemSources.add(probSource2);
		problemSources.add(probSource3);
		
		problemSources = problemManagementService.getAllTypesOfProblemSources();	
				
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
		
		if("MLA".equals(accessType))
		{
			log.debug("Access Type = MLA ****");
			List<SelectOptionVO> list = regionServiceData.getStateDistrictByConstituencyID(accessValue);
			
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			
			constituencyList.add(new SelectOptionVO(0L,"Select Constituency"));
			constituencyList.add(list.get(2));
			mandalList = regionServiceData.getMandalsByConstituencyID(accessValue);			
						
		}else if("COUNTRY".equals(accessType))
		{
			log.debug("Access Type = Country ****");
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			stateList.add(0,new SelectOptionVO(0L, "Select State"));
			
		}else if("STATE".equals(accessType)){
			log.debug("Access Type = State ****");
			
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select State");
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);
			stateList.add(obj1);
			stateList.add(obj2);
			
		}else if("DISTRICT".equals(accessType)){
			log.debug("Access Type = District ****");			

			List<SelectOptionVO> list = regionServiceData.getStateDistrictByDistrictID(accessValue);
			stateList.add(list.get(0));
			districtList.add(new SelectOptionVO(0l,"Select District"));
			districtList.add(list.get(1));
			constituencyList = regionServiceData.getConstituenciesByDistrictID(accessValue);
			
			
		}else if("MANDAL".equals(accessType)){
			log.debug("Access Type = Mandal ****");
			
			List<SelectOptionVO> list = cadreManagementService.getStateDistConstituencyMandalByMandalID( accessValue);
			stateList.add(list.get(0));
			districtList.add(list.get(1));
			mandalList.add(new SelectOptionVO(0L,"Select Mandal"));
			mandalList.add(list.get(2));
			
			
		}else if("MP".equals(accessType)){
			log.debug("Access Type = MP ****");
			stateList = regionServiceData.getStateByParliamentConstituencyID(accessValue);
			SelectOptionVO state = stateList.get(0);
			Long stateID = state.getId();
			Long year = regionServiceData.getLatestParliamentElectionYear(stateID);
			log.debug("year:::::"+year);
			log.debug("stateID:::::"+stateID);
			if(year!=null){
				constituencyList = crossVotingEstimationService.getAssembliesForParliament(accessValue,year);
			}	
			log.debug("constituencyList.size():"+constituencyList.size());		
		}
	
		return SUCCESS;
	}
	
	public String getInfluencingPeopleByLocation()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		String accessType = jObj.getString("accessType");
		Long accessValue = jObj.getLong("accessValue");
		
		influencingPeopleVO = problemManagementReportService.findInfluencingPeopleInfoInLocation(accessType, accessValue);
		log.debug("influencingPeopleVO.size()::::::::::::::::::"+influencingPeopleVO.size());
		return SUCCESS;
		
	}

	
}


