package com.itgrids.partyanalyst.web.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;


import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.service.impl.InfluencingPeopleService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MessageCenterAction  extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String task;
	JSONObject jObj;
	private ResultStatus resultStatus;
	private HttpSession session;
	private HttpServletRequest request;
	private RegionServiceDataImp regionServiceDataImp;
	private Long accessValue;
	private String accessType;
	private List<SelectOptionVO> statesList,sublevelsList,districtList,constituenciesList,subRegions,
	booths,hamletsOrWards;
	private InfluencingPeopleService influencingPeopleService;
	private StaticDataService staticDataService;
	private List<SmsVO> mobileNOs;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}


	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}
	
    //override
	
	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}
	


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}


	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	
	public Long getAccessValue() {
		return accessValue;
	}


	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
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
	
	
	public List<SelectOptionVO> getSublevelsList() {
		return sublevelsList;
	}


	public void setSublevelsList(List<SelectOptionVO> sublevelsList) {
		this.sublevelsList = sublevelsList;
	}
	
	public InfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}


	public void setInfluencingPeopleService(
			InfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	
	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}


	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}
	
	public StaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(StaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}


	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
	
	public List<SelectOptionVO> getSubRegions() {
		return subRegions;
	}


	public void setSubRegions(List<SelectOptionVO> subRegions) {
		this.subRegions = subRegions;
	}
	
	
	public List<SelectOptionVO> getBooths() {
		return booths;
	}


	public void setBooths(List<SelectOptionVO> booths) {
		this.booths = booths;
	}
	
	
	public List<SelectOptionVO> getHamletsOrWards() {
		return hamletsOrWards;
	}


	public void setHamletsOrWards(List<SelectOptionVO> hamletsOrWards) {
		this.hamletsOrWards = hamletsOrWards;
	}
	
	


	public List<SmsVO> getMobileNOs() {
		return mobileNOs;
	}


	public void setMobileNOs(List<SmsVO> mobileNOs) {
		this.mobileNOs = mobileNOs;
	}


	@SuppressWarnings("unused")
	public String execute()throws Exception
	{
		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		
		accessType=registrationVO.getAccessType();
		accessValue=new Long(registrationVO.getAccessValue());
		
		if (registrationVO != null) 
		{
			if (!registrationVO.getIsAdmin().equals("true")){
				  return ERROR;
			}
		} 
		else{
			return ERROR;
		}
		
		sublevelsList=influencingPeopleService.getInfluenceRange();
		statesList=regionServiceDataImp.getUserStateList(accessType, accessValue);
		
		districtList = regionServiceDataImp.getDistrictsByStateID(statesList.get(0).getId());
		
		return Action.SUCCESS;
		
	}
	public String getSublevels(){
		String param=getTask();
		String nameReturned="";
		
		try{
			jObj=new JSONObject(param);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String task=jObj.getString("task");
		Long locationId=jObj.getLong("locationId");
		
		if(task.equalsIgnoreCase("getDistricts")){
			districtList = staticDataService.getDistricts(locationId);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));
			nameReturned="districts";
		}else if(jObj.getString("task").equals("getConstituencies")){
			String areaType = jObj.getString("areaType");
			
			if(areaType!=""){
				constituenciesList = regionServiceDataImp.getConstituenciesByAreaTypeInDistrict(locationId, areaType);
			}
			else{
				constituenciesList = regionServiceDataImp.getConstituenciesByDistrictID(new Long(jObj.getString("locationId")));
			}
			constituenciesList.add(0,new SelectOptionVO(0l,"Select Constituencies"));
			nameReturned="constituencies";
		}else if(jObj.getString("task").equalsIgnoreCase("subRegionsInConstituency"))
		{
			Long constituencyId = jObj.getLong("locationId");
			String areaType = jObj.getString("areaType");
			subRegions = getRegionServiceDataImp().getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, areaType);
			
			if(areaType.equalsIgnoreCase("URBAN")){
				subRegions.add(0,new SelectOptionVO(0l,"Select Muncipality"));
			}else{
				subRegions.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}
			nameReturned="subRegionsOfConstituency";
		}else if(jObj.getString("task").equalsIgnoreCase("boothsInTehsilOrMunicipality"))
		{
			
			Long constituencyId = jObj.getLong("constId");
			booths = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(locationId, new Long(IConstants.PRESENT_ELECTION_YEAR), constituencyId);
			nameReturned="booths";
		} else if(jObj.getString("task").equalsIgnoreCase("hamletsOrWardsInRegion"))
		{
			if(locationId !=0){
				hamletsOrWards = getRegionServiceDataImp().getHamletsOrWards(locationId, IConstants.PRESENT_YEAR);
			}
			nameReturned="hamletsOrWards";
		
		}
		
		
		return nameReturned;
	}
	public String getMobileNumbers(){
		String param=getTask();
		try{
			jObj=new JSONObject(param);
			String ids=jObj.getString("ids");
			mobileNOs=influencingPeopleService.getMobileNumbersOfIds(ids);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	

}
