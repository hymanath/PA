package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreCountsGenderVO;
import com.itgrids.partyanalyst.dto.CadreCountsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICadreRegistrationServiceNew;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreDemographicReportsAction  extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = -3507936702681837368L;
	private static final Logger LOG = Logger.getLogger(CadreDemographicReportsAction.class);
	
	//Instance Variables
	private HttpServletRequest  request;
	private HttpSession session;
	private String 	task;
	private JSONObject  jobj;
	private CadreCountsVO cadreCountsVO;
	private List<CadreCountsVO> cadreCountsVOList;
	private CadreCountsGenderVO cadreCountsGenderVO;
	private List<CadreCountsGenderVO> cadreCountsGenderVOList;
	
	//Attributes
	private ICadreRegistrationServiceNew cadreRegistrationServiceNew; 
	
	//setters And Getters
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJobj() {
		return jobj;
	}
	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
   public void setCadreRegistrationServiceNew(
			ICadreRegistrationServiceNew cadreRegistrationServiceNew) {
		this.cadreRegistrationServiceNew = cadreRegistrationServiceNew;
	}
   
	public CadreCountsVO getCadreCountsVO() {
	   return cadreCountsVO;
    }
	public void setCadreCountsVO(CadreCountsVO cadreCountsVO) {
		this.cadreCountsVO = cadreCountsVO;
	}
	
	
	public List<CadreCountsVO> getCadreCountsVOList() {
		return cadreCountsVOList;
	}
	public void setCadreCountsVOList(List<CadreCountsVO> cadreCountsVOList) {
		this.cadreCountsVOList = cadreCountsVOList;
	}
	
	
	public CadreCountsGenderVO getCadreCountsGenderVO() {
		return cadreCountsGenderVO;
	}
	public void setCadreCountsGenderVO(CadreCountsGenderVO cadreCountsGenderVO) {
		this.cadreCountsGenderVO = cadreCountsGenderVO;
	}
	
	public List<CadreCountsGenderVO> getCadreCountsGenderVOList() {
		return cadreCountsGenderVOList;
	}
	public void setCadreCountsGenderVOList(
			List<CadreCountsGenderVO> cadreCountsGenderVOList) {
		this.cadreCountsGenderVOList = cadreCountsGenderVOList;
	}
	//implementation methods
	public void setServletRequest(HttpServletRequest request) {
		this.request = request; 
	}
	
	//business methods
	public String execute(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CADRE_REGISTRATION_2016_DASHBOARD".trim())) || entitlements.contains("CADRE_REGISTRATION_2016_DASHBOARD_ADMIN_ENTITLEMENT".trim())){
				noaccess = true ;
			}
		
			if(noaccess){
				return "error";
			}
		}
		return Action.SUCCESS;
	}
	
	public String ageWiseTdpCadreSummaryReport(){
		
		try{
			 //Long stateId = 1L;
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
				
			 cadreCountsVO = cadreRegistrationServiceNew.ageWiseTdpCadreSummaryReport(stateId);
		}catch(Exception e){
			LOG.error("Exception raised at ageWiseTdpCadreSummaryReport() in cadreDemographicReportsAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWisegeWiseTdpCadreCounts(){
		
		try{
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
			 Long districtId = jobj.getLong("districtId");
			 String searchType = jobj.getString("searchType");
			
			cadreCountsVOList = cadreRegistrationServiceNew.getLocationWisegeWiseTdpCadreCounts(stateId,districtId,searchType);
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWisegeWiseTdpCadreCounts() in cadreDemographicReportsAction", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String casteCategoryWiseTdpCadreSummaryReport(){
		
		try{
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
				
			 cadreCountsVO = cadreRegistrationServiceNew.casteCategoryWiseTdpCadreSummaryReport(stateId);
		}catch(Exception e){
			LOG.error("Exception raised at casteCategoryWiseTdpCadreSummaryReport() in cadreDemographicReportsAction", e);
		}
		return Action.SUCCESS;
	}
	public String stateWiseTdpCadreCasteCounts(){
		
		try{
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
			 Double limit = jobj.getDouble("limit");
				
			 cadreCountsVOList = cadreRegistrationServiceNew.stateWiseTdpCadreCasteCounts(stateId,limit);  
		}catch(Exception e){
			LOG.error("Exception raised at stateWiseTdpCadreCasteCounts() in cadreDemographicReportsAction", e);
		}
		return Action.SUCCESS;
	}
	public String districtWiseTdpCadreCasteCounts(){
		
		try{
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
			 Long districtId = jobj.getLong("districtId");
			 Double limit = jobj.getDouble("limit");
			 cadreCountsVOList = cadreRegistrationServiceNew.districtWiseTdpCadreCasteCounts(stateId,districtId,limit);
		}catch(Exception e){
			LOG.error("Exception raised at districtWiseTdpCadreCasteCounts() in cadreDemographicReportsAction", e);
		}
		
		return Action.SUCCESS;
	}
	public String constituencyWiseTdpCadreCasteCounts(){
		
		try{
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
			 Long districtId = jobj.getLong("districtId");
			 Double limit = jobj.getDouble("limit");
			 cadreCountsVOList = cadreRegistrationServiceNew.constituencyWiseTdpCadreCasteCounts(stateId,districtId,limit);
		}catch(Exception e){
			LOG.error("Exception raised at constituencyWiseTdpCadreCasteCounts() in cadreDemographicReportsAction", e);
		}
		
		return Action.SUCCESS;
	}
	public String stateWiseCadreGenderCounts(){
		
		try{
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
				
			 cadreCountsGenderVO = cadreRegistrationServiceNew.stateWiseCadreGenderCounts(stateId);
		}catch(Exception e){
			LOG.error("Exception raised at stateWiseCadreGenderCounts() in cadreDemographicReportsAction", e);
		}
		return Action.SUCCESS;
	}
	public String locationWiseCadreGenderCounts(){
		
		try{
			jobj = new JSONObject(getTask());
			 Long stateId = jobj.getLong("stateId");
			 Long districtId = jobj.getLong("districtId");
			 String searchType = jobj.getString("searchType");
			
			 cadreCountsGenderVOList = cadreRegistrationServiceNew.locationWiseCadreGenderCounts(stateId,districtId,searchType);
		}catch(Exception e){
			LOG.error("Exception raised at locationWiseCadreGenderCounts() in cadreDemographicReportsAction", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String userWiseCadreDemographicReport(){
		return Action.SUCCESS;
	}
	
}

