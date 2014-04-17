package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.PartyActivitiesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IPartyActivitiesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;

public class PartyActivitiesAction  implements ServletRequestAware{
  
	private static final Logger LOG = Logger.getLogger(PartyActivitiesAction.class);
	private HttpServletRequest request;
	private IPartyActivitiesService partyActivitiesService;
	private PartyActivitiesVO activities;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> districts,assemblies;
	private JSONObject jObj;
	private String task;
	private String status;
	private List<PartyActivitiesVO> partyActivitiesList;
	private String url;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public IPartyActivitiesService getPartyActivitiesService() {
		return partyActivitiesService;
	}

	public void setPartyActivitiesService(
			IPartyActivitiesService partyActivitiesService) {
		this.partyActivitiesService = partyActivitiesService;
	}
	
	public PartyActivitiesVO getActivities() {
		return activities;
	}

	public void setActivities(PartyActivitiesVO activities) {
		this.activities = activities;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getDistricts() {
		return districts;
	}

	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PartyActivitiesVO> getPartyActivitiesList() {
		return partyActivitiesList;
	}

	public void setPartyActivitiesList(List<PartyActivitiesVO> partyActivitiesList) {
		this.partyActivitiesList = partyActivitiesList;
	}

	public List<SelectOptionVO> getAssemblies() {
		return assemblies;
	}

	public void setAssemblies(List<SelectOptionVO> assemblies) {
		this.assemblies = assemblies;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String execute(){
		HttpSession session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		 if(regVO == null)
			 return Action.ERROR;
		districts = staticDataService.getDistricts(1l);
		return Action.SUCCESS;
	}

	public String getNewsToUpdateKeywords(){
	  try{
		String fromDateStr = request.getParameter("fromDate");
		String toDateStr = request.getParameter("toDate");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String[] categoryarr = null;
		String[] districtarr = null;
		String category= request.getParameter("categories");
		  if(category != null && category.trim().length() > 0)
		     categoryarr = category.trim().toString().split(",");
		  List<Long> categoryIds = new ArrayList<Long>();
		  if(categoryarr != null && categoryarr.length > 0)
		  {
			  for(int i=0;i<categoryarr.length;i++)
				  categoryIds.add(new Long(categoryarr[i]));
		  
		  }
		  String districts= request.getParameter("districts");
		  if(districts != null && districts.trim().length() > 0)
			  districtarr = districts.trim().split(",");
		  List<Long> districtIds = new ArrayList<Long>();
		  if(districtarr != null && districtarr.length > 0)
		  {
			  for(int i=0;i<districtarr.length;i++)
				  districtIds.add(new Long(districtarr[i]));
		  
		  }
		Date fromDate = null;
		Date toDate = null;
		
		if(fromDateStr != null && fromDateStr.trim().length() > 0){
			fromDate = format.parse(fromDateStr);
		}
		if(toDateStr != null && toDateStr.trim().length() > 0){
			toDate = format.parse(toDateStr);
		}
		activities = partyActivitiesService.getNewsToUpdateKeywords(categoryIds, fromDate, toDate, districtIds,Integer.parseInt(request.getParameter("startIndex")),Integer.parseInt(request.getParameter("results")));
	  }catch(Exception e){
		  LOG.error("Exception rised in getNewsToUpdateKeywords ",e);
	  }
		return Action.SUCCESS;
	}
	
	public String updateKeywords(){
	 try{
		jObj = new JSONObject(getTask());
		String keywords = jObj.getString("keywords");
		List<String> keywordsList = new ArrayList<String>();
		PartyActivitiesVO activity = null;
		List<PartyActivitiesVO> activities = new ArrayList<PartyActivitiesVO>();
		String[] keywordsArray = keywords.split(",");
		for(String keyword:keywordsArray){
			keywordsList.add(keyword);
		}
		org.json.JSONArray newsJSONArray = jObj.getJSONArray("selectedNews");
		 for(int i = 0;i<newsJSONArray.length();i++){
			 JSONObject jSONObject= newsJSONArray.getJSONObject(i);
			 activity = new PartyActivitiesVO();
			 activity.setPartyId(jSONObject.getLong("partyId"));
			 activity.setId(jSONObject.getLong("fileId"));
			 activity.setKeywordsList(keywordsList);
			 activities.add(activity);
		 }
		
		 status = partyActivitiesService.updateKeywords(activities);
		
	 }catch(Exception e){
		 LOG.error("Exception rised in updateKeywords ",e);
		 status = "error occured";
	 }
	 return Action.SUCCESS;
	}
	
	public String getActivitiesCount(){
	 try{
		    jObj = new JSONObject(getTask());
		    String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long locationType = jObj.getLong("locationType");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate = null;
			String[] locationarr = null;
			String[] partyarr = null;
			String[] categoriesarr = null;
			
			if(fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = format.parse(fromDateStr);
			}
			if(toDateStr != null && toDateStr.trim().length() > 0){
				toDate = format.parse(toDateStr);
			}
			String locations= jObj.getString("locationIds");
			  if(locations != null && locations.trim().length() > 0)
				  locationarr = locations.trim().split(",");
			  List<Long> locationIds = new ArrayList<Long>();
			  if(locationarr != null && locationarr.length > 0)
			  {
				  for(int i=0;i<locationarr.length;i++)
					  locationIds.add(new Long(locationarr[i]));
			  
			  }
			  
			  String partys = jObj.getString("partyIds");
			  if(partys != null && partys.trim().length() > 0)
				  partyarr = partys.trim().split(",");
			  List<Long> partyIds = new ArrayList<Long>();
			  if(partyarr != null && partyarr.length > 0)
			  {
				  for(int i=0;i<partyarr.length;i++)
					  partyIds.add(new Long(partyarr[i]));
			  
			  }
			  
			  String categories = jObj.getString("categories");
			  if(categories != null && categories.trim().length() > 0)
				  categoriesarr = categories.trim().split(",");
			  List<Long> categoryIds = new ArrayList<Long>();
			  if(categoriesarr != null && categoriesarr.length > 0)
			  {
				  for(int i=0;i<categoriesarr.length;i++)
					  categoryIds.add(new Long(categoriesarr[i]));
			  }
			  String type = jObj.getString("reportType");
			  if(type.equalsIgnoreCase("dataTable")){
		         partyActivitiesList = partyActivitiesService.getCategoryWiseActivities(fromDate, toDate, locationType, locationIds, partyIds,categoryIds);
		         return Action.SUCCESS;
			  }else if(type.equalsIgnoreCase("excel")){
				  url = partyActivitiesService.generateExcelForActivities(fromDate, toDate, locationType, locationIds, partyIds, categoryIds);
				  return "url";
			  }
	 }catch(Exception e){
		 LOG.error("Exception rised in getActivitiesCount ",e);
	 }
		return Action.SUCCESS;		
	}
	
	public String activitiesAnalysis(){
	  try{
		HttpSession session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		 if(regVO == null)
			 return Action.ERROR;
		districts = staticDataService.getDistricts(1l);
		ConstituencyInfoVO infoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(2l, 1l);
		assemblies = infoVO.getConstituencies();
	  }catch(Exception e){
		  LOG.error("Exception rised in activitiesAnalysis ",e);
	  }
		return Action.SUCCESS;
	}
}
