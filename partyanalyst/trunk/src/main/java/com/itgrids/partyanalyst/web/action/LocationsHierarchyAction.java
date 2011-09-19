package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegionalMappingInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LocationsHierarchyAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2319254812860276099L;
	private static final Logger log = Logger.getLogger(LocationsHierarchyAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private IRegionServiceData regionServiceDataImp;
	private IStaticDataService staticDataService;
	JSONObject jObj = null;
	private String task = null;
	private List<SelectOptionVO> regionsList;
	private List<BoothInfo> boothsCompleteDetails;
	private Set<RegionalMappingInfoVO> regions;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public List<SelectOptionVO> getRegionsList() {
		return regionsList;
	}

	public void setRegionsList(List<SelectOptionVO> regionsList) {
		this.regionsList = regionsList;
	}	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<BoothInfo> getBoothsCompleteDetails() {
		return boothsCompleteDetails;
	}

	public void setBoothsCompleteDetails(List<BoothInfo> boothsCompleteDetails) {
		this.boothsCompleteDetails = boothsCompleteDetails;
	}	

	public Set<RegionalMappingInfoVO> getRegions() {
		return regions;
	}

	public void setRegions(Set<RegionalMappingInfoVO> regions) {
		this.regions = regions;
	}

	public String execute() throws Exception {
			
		return Action.SUCCESS;
		
	}
	public String ajaxCallHandler() throws Exception{
		session = request.getSession();
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		   
			if(jObj.getString("task").equalsIgnoreCase("statesInCountry"))
			{
				//to fetch states in country
				List<SelectOptionVO> states= getRegionServiceDataImp().getStatesByCountryForSearch(1l); 
				Collections.sort(states);
				states.add(0, new SelectOptionVO(0l,"Select Location"));
				setRegionsList(states);
			}	

			else if(jObj.getString("task").equalsIgnoreCase("stateSelect"))
			{
				//to fetch states in country
				List<SelectOptionVO> states= getRegionServiceDataImp().getStatesByCountry(1l); 
				states.add(0, new SelectOptionVO(0l,"Select Location"));
				setRegionsList(states);
				Collections.sort(states);
			}

		 else if(jObj.getString("task").equalsIgnoreCase("districtsInState"))
		{
			//to fetch all districts in state
			Long stateId = jObj.getLong("id");
			List<SelectOptionVO> districts = getRegionServiceDataImp().getDistrictsByStateID(stateId);
			districts.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(districts);
			if(jObj.getString("address").equalsIgnoreCase("OfficialAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.DISTRICTS_O, districts);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.DISTRICTS, districts);
			}
			if(jObj.getString("address").equalsIgnoreCase("cadreLevel") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.DISTRICTS_C, districts);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("newProblemPost"))
			{
				session.setAttribute(ISessionConstants.DISTRICTS_AP, districts);
			}
		} else if(jObj.getString("task").equalsIgnoreCase("constituenciesInDistrict"))
		{
			//to fetch all assembly constituencies in district
			Long districtId = jObj.getLong("id");
			List<SelectOptionVO> constituencies = getRegionServiceDataImp().getConstituenciesByDistrictID(districtId);
			constituencies.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(constituencies);
			if(jObj.getString("address").equalsIgnoreCase("OfficialAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencies);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencies);
			}
			if(jObj.getString("address").equalsIgnoreCase("cadreLevel") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C, constituencies);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("newProblemPost"))
			{
				session.setAttribute(ISessionConstants.CONSTITUENCIES_AP, constituencies);
			}
		} else if(jObj.getString("task").equalsIgnoreCase("subRegionsInConstituency"))
		{
			//to fetch all types of regions like tehsils, municipalities, corporations, grater corporations in a constituency
			Long constituencyId = jObj.getLong("id");
			String scope = jObj.getString("areaType");
			List<SelectOptionVO> subRegions = getRegionServiceDataImp().getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, scope);
			subRegions.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(subRegions);
			if(jObj.getString("address").equalsIgnoreCase("OfficialAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.MANDALS_O, subRegions);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.MANDALS, subRegions);
			}
			if(jObj.getString("address").equalsIgnoreCase("cadreLevel") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.MANDALS_C, subRegions);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("newProblemPost"))
			{
				session.setAttribute(ISessionConstants.MANDALS_AP, subRegions);
			}
		}  else if(jObj.getString("task").equalsIgnoreCase("hamletsOrWardsInRegion"))
		{
			//to get hamlets if the selected area is of type rural , to get wards if the selected area type is urban, both(hamlets and wards) if the selected area is of type urban-rural
			Long locationId = jObj.getLong("id");
			List<SelectOptionVO> hamletsOrWards = new ArrayList<SelectOptionVO>();
			if(locationId !=0){
			 hamletsOrWards = getRegionServiceDataImp().getHamletsOrWards(locationId, IConstants.PRESENT_YEAR);
			}
			 hamletsOrWards.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(hamletsOrWards);
			if(jObj.getString("address").equalsIgnoreCase("OfficialAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.VILLAGES_O, hamletsOrWards);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.VILLAGES, hamletsOrWards);
			}
			if(jObj.getString("address").equalsIgnoreCase("cadreLevel") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.VILLAGES_C, hamletsOrWards);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("newProblemPost"))
			{
				session.setAttribute(ISessionConstants.VILLAGES_AP, hamletsOrWards);
			}
		} else if(jObj.getString("task").equalsIgnoreCase("localElectionBodiesOfDistrict"))
		{
			//to get all local election bodies in a  district
			Long locationId = jObj.getLong("id");
			List<SelectOptionVO> localElectionBodies = getRegionServiceDataImp().getLocalElectionBodiesOfADistrict(locationId);
			localElectionBodies.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(localElectionBodies);
		}  else if(jObj.getString("task").equalsIgnoreCase("wardsInALocalElectionBody"))
		{
			//to get all wards in a local election body 
			Long locationId = jObj.getLong("id");
			List<SelectOptionVO> localElectionBodies = getRegionServiceDataImp().getWardsInALocalElectionBody(locationId);
			localElectionBodies.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(localElectionBodies);
		} else if(jObj.getString("task").equalsIgnoreCase("getConstNotInGivenAreaType"))
		{
			//to get all constituenciesByAreaTypeInDistrict  
			Long locationId = jObj.getLong("id");
			String areaType = jObj.getString("areaType");
			List<SelectOptionVO> constituencies = getRegionServiceDataImp().getConstituenciesByAreaTypeInDistrict(locationId, areaType);
			constituencies.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(constituencies);
			if(jObj.getString("address").equalsIgnoreCase("cadreLevel") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.CONSTITUENCIES_C, constituencies);
			}
		} else if(jObj.getString("task").equalsIgnoreCase("parliamentsInState"))
		{
			//to get all constituenciesByAreaTypeInDistrict  
			Long locationId = jObj.getLong("id");
			
			List<SelectOptionVO> constituencies = staticDataService.getConstituenciesByElectionTypeAndStateId(1l,locationId).getConstituencies();
			constituencies.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(constituencies);
		} else if(jObj.getString("task").equalsIgnoreCase("assembliesInParliament"))
		{
			//to get all constituenciesByAreaTypeInDistrict  
			Long locationId = jObj.getLong("id");
			ConstituencyInfoVO constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(locationId);
			
			List<SelectOptionVO> constituencies = constituencyInfoVO.getAssembyConstituencies();
			constituencies.add(0, new SelectOptionVO(0l,"Select Location"));
			setRegionsList(constituencies);
		} else if(jObj.getString("task").equalsIgnoreCase("boothsInTehsilOrMunicipality"))
		{
			//to get all booths in tehsil or municipality  
			Long locationId = jObj.getLong("id");
			Long constituencyId = jObj.getLong("constId");
			List<SelectOptionVO> booths = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(locationId, new Long(IConstants.PRESENT_ELECTION_YEAR), constituencyId);
			booths.add(0, new SelectOptionVO(0l, "Select Location"));	
			setRegionsList(booths);
			if(jObj.getString("address").equalsIgnoreCase("OfficialAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.BOOTHS_O, booths);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.BOOTHS, booths);
			}
			if(jObj.getString("address").equalsIgnoreCase("cadreLevel") && jObj.getString("taskType").equalsIgnoreCase("cadreReg"))
			{
				session.setAttribute(ISessionConstants.BOOTHS_C, booths);
			}
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("newProblemPost"))
			{
				session.setAttribute(ISessionConstants.BOOTHS_AP, booths);
			}
		}  else if(jObj.getString("task").equalsIgnoreCase("boothsInWard"))
		{
			//to get all booths in ward  
			Long locationId = jObj.getLong("id");
			Long constituencyId = jObj.getLong("constId");
			List<SelectOptionVO> booths = getRegionServiceDataImp().getboothsInWard(locationId, new Long(IConstants.PRESENT_ELECTION_YEAR), constituencyId);
			booths.add(0, new SelectOptionVO(0l, "Select Location"));
			setRegionsList(booths);
			if(jObj.getString("address").equalsIgnoreCase("currentAdd") && jObj.getString("taskType").equalsIgnoreCase("newProblemPost"))
			{
				session.setAttribute(ISessionConstants.BOOTHS_AP, booths);
			}
		} else if(jObj.getString("task").equalsIgnoreCase("localBodiesInConstituency"))
		{
			//to get all booths in ward  
			Long locationId = jObj.getLong("id");
			String year = jObj.getString("year");
			List<SelectOptionVO> localBodies = getRegionServiceDataImp().getLocalElectionBodiesInConstituency(locationId, year);
			localBodies.add(0, new SelectOptionVO(0l, "Select Location"));
			setRegionsList(localBodies);
		} 	
		
		return Action.SUCCESS;
	
	}
	
	public String getBoothCompleteDetails()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		String boothIds = jObj.getString("boothIds");
		String areaType = jObj.getString("areaType");
		
		boothsCompleteDetails = getRegionServiceDataImp().getBoothCompleteDetails(areaType, boothIds);
		return Action.SUCCESS;	
	}
	/**
	 * This method is used in boothLocalBody mapper admin interface, which is intended to fetch all the local
	 * bodies exists in dist and if any one of them are mapped to a constituency level, then they are also fetched.
	 * This id to provide a user to map or unmap the local bodies to a constituency level.
	 * @return
	 */
	public String getLocalBodiesInDistAndConst()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		Long distId = jObj.getLong("districtId");
		Long constId = jObj.getLong("constituencyId");
		String year = jObj.getString("year");
		regions = getRegionServiceDataImp().getLocalBodiesInDistAndConst(distId, constId, year);
		
	 return Action.SUCCESS;	
	}
	/**
	 * This method is used in boothLocalBody mapper admin interface, which is intended to fetch all the wards exists in local
	 * bodies and if any one of them are mapped to a constituency level, then they are also fetched.
	 * This id to provide a user to map or unmap the wards in local bodies to a constituency level.
	 * @return
	 */
	public String getWardsInLocalBodiesAndConst()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		Long localBodyId = jObj.getLong("localBodyId");
		Long constituencyId = jObj.getLong("constituencyId");
		String year = jObj.getString("year");
		regions = getRegionServiceDataImp().getWardsInLocalBodyAndConst(localBodyId, constituencyId, year);
		 return Action.SUCCESS;
	}
	
	/**
	 * This method is used in boothLocalBody mapper admin interface, which is intended to fetch all the booths exists in local
	 * bodies and if any one of them are mapped to a constituency level, then they are also fetched.
	 * This id to provide a user to map or unmap the booths in local bodies to a constituency level.
	 * @return
	 */
	public String getboothsInLocalBodiesAndConst()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		Long localBodyId = jObj.getLong("localBodyId");
		Long constituencyId = jObj.getLong("constituencyId");
		String year = jObj.getString("year");
		regions = getRegionServiceDataImp().getboothsInLocalBodiesAndConst(localBodyId, constituencyId, year);
		 return Action.SUCCESS;
	}
	
	public String getboothsInWardsAndConst()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		Long wardId = jObj.getLong("wardId");
		Long constituencyId = jObj.getLong("constituencyId");
		String year = jObj.getString("year");
		regions = getRegionServiceDataImp().getboothsInWardsAndConst(wardId, constituencyId, year);
		
		return Action.SUCCESS;
	}
	
	
}
