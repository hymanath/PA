package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.CrossVotingEstimationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class CadreRegisterPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CadreManagementService cadreManagementService;
	private CrossVotingEstimationService crossVotingEstimationService;
	private static final Logger log = Logger.getLogger(CadreRegisterPageAction.class);

	private UserCadresInfoVO userCadresInfoVO = new UserCadresInfoVO();
	private HttpServletRequest request;
	private HttpSession session;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villageList;
	private IRegionServiceData regionServiceData;
	
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

	
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	public void setRegionServiceData(IRegionServiceData regionServiceData){
		this.regionServiceData = regionServiceData;
	}

	public void setCrossVotingEstimationService(
			CrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	
	public void setServletRequest(HttpServletRequest request) {		
		this.request = request;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String execute(){
		if(log.isDebugEnabled())
			log.debug("CadreRegisterPageAction.execute() start");
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		String accessType =regVO.getAccessType();
		Long accessValue= new Long(regVO.getAccessValue());

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
		return Action.SUCCESS;
	}
	

}
