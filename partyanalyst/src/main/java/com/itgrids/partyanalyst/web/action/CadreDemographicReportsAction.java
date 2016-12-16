package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
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
	
	//USER WISE DEMOGRAPHIC REPORTS..
	public String userWiseCadreDemographicReport(){
		return Action.SUCCESS;
	}
	public String casteCategoryWiseCadreSummaryReport(){
	    try{
	        jobj = new JSONObject(getTask());
	        String accessType = jobj.getString("accessType");
	        
	        List<Long> locationIds = new ArrayList<Long>(0);
	        JSONArray locationArray=jobj.getJSONArray("locationArray");
			if(locationArray!=null &&  locationArray.length()>0){
				for( int i=0;i<locationArray.length();i++){
					String locationIdString = locationArray.getString(i);
					if(locationIdString!=null && Long.valueOf(locationIdString) > 0l){
						locationIds.add(Long.valueOf(Long.valueOf(locationIdString)));
					}
				}
			}
			cadreCountsVO = cadreRegistrationServiceNew.priviledgedCasteCategoryWiseTdpCadreSummaryReport(locationIds ,accessType );
	    }catch(Exception e){
	      LOG.error("Exception raised at casteCategoryWiseCadreSummaryReport() in cadreDemographicReportsAction", e);
	    }
	    return Action.SUCCESS;
   }
	public String stateWiseCadreCasteCounts(){
	    try{
	        jobj = new JSONObject(getTask());
	        String accessType = jobj.getString("accessType");
	        
	        List<Long> locationIds = new ArrayList<Long>(0);
	        JSONArray locationArray=jobj.getJSONArray("locationArray");
			if(locationArray!=null &&  locationArray.length()>0){
				for( int i=0;i<locationArray.length();i++){
					String locationIdString = locationArray.getString(i);
					if(locationIdString!=null && Long.valueOf(locationIdString) > 0l){
						locationIds.add(Long.valueOf(Long.valueOf(locationIdString)));
					}
				}
			}
			
			Double limit = jobj.getDouble("limit");
	       cadreCountsVOList = cadreRegistrationServiceNew.privilegedStateWiseTdpCadreCasteCounts(locationIds, accessType,limit);
	    }catch(Exception e){
	      LOG.error("Exception raised at stateWiseCadreCasteCounts() in cadreDemographicReportsAction", e);
	    }
	    return Action.SUCCESS;
   }
	
	public String districtWiseCadreCasteCounts(){
	    try{
	        jobj = new JSONObject(getTask());
	        
	        List<Long> locationIds = new ArrayList<Long>(0);
	        JSONArray locationArray=jobj.getJSONArray("locationArray");
			if(locationArray!=null &&  locationArray.length()>0){
				for( int i=0;i<locationArray.length();i++){
					String locationIdString = locationArray.getString(i);
					if(locationIdString!=null && Long.valueOf(locationIdString) > 0l){
						locationIds.add(Long.valueOf(Long.valueOf(locationIdString)));
					}
				}
			}
			
			Double limit = jobj.getDouble("limit");
	       cadreCountsVOList = cadreRegistrationServiceNew.privilegedDistrictWiseTdpCadreCasteCounts(locationIds , limit , "district");  
	    }catch(Exception e){
	      LOG.error("Exception raised at districtWiseCadreCasteCounts() in cadreDemographicReportsAction", e);
	    }
	    return Action.SUCCESS;
   }
	
	public String constituencyWiseCadreCasteCounts(){
	    try{
	        jobj = new JSONObject(getTask());
	        
	        List<Long> locationIds = new ArrayList<Long>(0);
	        JSONArray locationArray=jobj.getJSONArray("locationArray");
			if(locationArray!=null &&  locationArray.length()>0){
				for( int i=0;i<locationArray.length();i++){
					String locationIdString = locationArray.getString(i);
					if(locationIdString!=null && Long.valueOf(locationIdString) > 0l){
						locationIds.add(Long.valueOf(Long.valueOf(locationIdString)));
					}
				}
			}
			
			Double limit = jobj.getDouble("limit");
	       cadreCountsVOList = cadreRegistrationServiceNew.privilegedConstituencyWiseTdpCadreCasteCounts(locationIds , limit , "constituency");
	    }catch(Exception e){
	      LOG.error("Exception raised at constituencyWiseCadreCasteCounts() in cadreDemographicReportsAction", e);
	    }
	    return Action.SUCCESS;
   }
	
  public String ageWiseCadreSummaryReport(){
		try{
			 jobj = new JSONObject(getTask());
			 String accessType = jobj.getString("accessType");
		     List<Long> locationIds = getLocationIds(jobj.getJSONArray("locationArray"));
		     
			 cadreCountsVO = cadreRegistrationServiceNew.privilegedAgeWiseTdpCadreSummaryReport(locationIds , accessType);
		}catch(Exception e){
			LOG.error("Exception raised at ageWiseCadreSummaryReport() in cadreDemographicReportsAction", e);
		}
		return Action.SUCCESS;
	}
    
    public String getLocationWisegeWiseCadreCounts(){
		
		try{
			 jobj = new JSONObject(getTask());
			 List<Long> locationIds = getLocationIds(jobj.getJSONArray("locationArray"));
			 String searchType  = jobj.getString("searchType");
			
			cadreCountsVOList = cadreRegistrationServiceNew.privilegedLocationWisegeWiseTdpCadreCounts(locationIds,searchType);
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWisegeWiseTdpCadreCounts() in cadreDemographicReportsAction", e);
		}
		return Action.SUCCESS;
	}
    public List<Long>  getLocationIds(JSONArray locationArray){
    	List<Long> locationIds = new ArrayList<Long>(0);
    	try{
 			if(locationArray!=null &&  locationArray.length()>0){
 				for( int i=0;i<locationArray.length();i++){
 					String locationIdString = locationArray.getString(i);
 					if(locationIdString!=null && Long.valueOf(locationIdString) > 0l){
 						locationIds.add(Long.valueOf(Long.valueOf(locationIdString)));
 					}
 				}
 			}
		}catch(Exception e){
			LOG.error("Exception raised at getLocationIds() in cadreDemographicReportsAction", e);
		}
    	return locationIds;
    }
    
    
    public String getGenderSummaryCountsByUser(){
		
		try{
			jobj = new JSONObject(getTask());
			String accessType = jobj.getString("accessType");
		    List<Long> locationIds = getLocationIds(jobj.getJSONArray("locationArray"));
			cadreCountsGenderVO = cadreRegistrationServiceNew.privilegedStateWiseCadreGenderCounts(locationIds,accessType);
		}catch(Exception e){
			LOG.error("Exception raised at getGenderSummaryCountsByUser() in cadreDemographicReportsAction", e);
		}
		return Action.SUCCESS;
	}
	public String locationWiseCadreGenderCountsByUser(){
		
		try{
			jobj = new JSONObject(getTask());
			List<Long> locationIds = getLocationIds(jobj.getJSONArray("locationArray"));
			String searchType  = jobj.getString("searchType");
			cadreCountsGenderVOList = cadreRegistrationServiceNew.privilegedLocationWiseCadreGenderCounts(locationIds,searchType);
		}catch(Exception e){  
			LOG.error("Exception raised at locationWiseCadreGenderCountsByUser() in cadreDemographicReportsAction", e);
		}
		
		return Action.SUCCESS;
	}
    
    
}

