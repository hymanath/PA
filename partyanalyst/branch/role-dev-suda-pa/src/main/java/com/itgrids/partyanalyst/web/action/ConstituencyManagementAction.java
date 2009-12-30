package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.CrossVotingEstimationService;

public class ConstituencyManagementAction extends ActionSupport implements ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConstituencyManagementAction.class);
	private CadreManagementService cadreManagementService;
	private CrossVotingEstimationService crossVotingEstimationService;
	
	
	private ConstituencyManagementVO constituencyManagementVO;
	private List<ProblemDetailsVO> problemDetailsList;
	private HttpServletRequest request;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villageList;
	private IRegionServiceData regionServiceData;
	

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

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

public List<ProblemDetailsVO> getProblemDetailsList() {
		return problemDetailsList;
	}

	public void setProblemDetailsList(List<ProblemDetailsVO> problemDetailsList) {
		this.problemDetailsList = problemDetailsList;
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

	public IRegionServiceData getRegionServiceData() {
		return regionServiceData;
	}

	public void setRegionServiceData(IRegionServiceData regionServiceData) {
		this.regionServiceData = regionServiceData;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

public String execute() throws Exception{
		
		log.debug("In execute of Constituency Management Action ********");
		
		constituencyManagementVO = new ConstituencyManagementVO();
		ProblemManagementVO problemManagementVO = new ProblemManagementVO();
		problemDetailsList = new ArrayList<ProblemDetailsVO>();		
		
		ProblemDetailsVO problemDetailsVO = new ProblemDetailsVO(); 
		problemDetailsVO.setDefinition("Impurity water");
		problemDetailsVO.setDescription("Polluted water is beign supplied");
		problemDetailsVO.setIdentifiedDate("03/04/2009");
		problemDetailsVO.setLocation("Madanapalle");
		problemDetailsVO.setSource("Party Analyst");
		
		ProblemDetailsVO problemDetailsVO1 = new ProblemDetailsVO();
		problemDetailsVO1.setDefinition("No Bus Service");
		problemDetailsVO1.setDescription("Bus service cancelled to village");
		problemDetailsVO1.setIdentifiedDate("01/03/2009");
		problemDetailsVO1.setLocation("Nagavaram");
		problemDetailsVO1.setSource("VIctim");
		
		problemDetailsList.add(problemDetailsVO);
		problemDetailsList.add(problemDetailsVO1);
		
		problemManagementVO.setProblemDetails(problemDetailsList);
		constituencyManagementVO.setProblemManagementVO(problemManagementVO);
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		//String result = SUCCESS;
		if(user == null)
			//result =  "NO_USER";
			return ERROR;
		log.debug("test");
		
		String accessType =user.getAccessType();
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
}


