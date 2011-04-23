package com.itgrids.partyanalyst.web.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class StaticDataAction  extends ActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(StaticDataAction.class);
	private HttpServletRequest request;
	private IRegionServiceData regionServiceDataImp;

	private IStaticDataService staticDataService;
	private List<SelectOptionVO> states;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> partyList;
	private List<SelectOptionVO> commonList;
	private EntitlementsHelper entitlementsHelper;
	
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}
	
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp){
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getStates(){
		return states;
	}
	public void setStates(List<SelectOptionVO> states){
		this.states = states;
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

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public List<SelectOptionVO> getCommonList() {
		return commonList;
	}

	public void setCommonList(List<SelectOptionVO> commonList) {
		this.commonList = commonList;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String getStatesList() throws Exception{
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_BOOTHWISE_RESULTS_REPORT))
			return ERROR;
		states = regionServiceDataImp.getStatesByCountryFromBooth(1L);
		Collections.sort(states);
		states.add(0, new SelectOptionVO(0L,"Select State"));
		
		partyList = staticDataService.getStaticParties();
		Collections.sort(partyList);
		partyList.add(0, new SelectOptionVO(0L,"Select Party"));
		return SUCCESS;
	}

	public String getList() throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("StaticDataAction.java getList() Started123....");
		String task = request.getParameter("task");
		JSONObject jsonObject = new JSONObject(task);
				
		String type = jsonObject.getString("type");
		String value = jsonObject.getString("value");
		if(log.isDebugEnabled()){
			log.debug("task:"+task);
			log.debug("type:"+type);
			log.debug("value:"+value);
		}
		if("STATE".equals(type)){
			commonList = regionServiceDataImp.getDistrictsByStateIDFromBooth(new Long(value));
		} else if("DISTRICT".equals(type)){
			commonList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(value));
		} else if("CONSTITUENCY".equals(type)){
			commonList = regionServiceDataImp.getMandalsByConstituencyID(new Long(value));
		}
		if(log.isDebugEnabled())
			log.debug("commonList.size():::"+commonList.size());
		return SUCCESS;
	}

}
