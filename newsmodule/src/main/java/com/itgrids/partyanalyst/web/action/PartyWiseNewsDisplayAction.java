package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISendUpdatesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyWiseNewsDisplayAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpSession session;
	private ResultStatus result;
	private String task;
	JSONObject jObj;
	private String scope;
	private String locationName;
	private Long locationValue;
	private Long partyId;
	private String partyName;
	private EntitlementsHelper entitlementsHelper;
	private List<SelectOptionVO> constituencyList , userAccessConstituencyList;
	
	private IPartyDetailsService partyDetailsService;
	private List<FileVO> newsCountByCategoryList;	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public ResultStatus getResult() {
		return result;
	}
	public void setResult(ResultStatus result) {
		this.result = result;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}
	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}
	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}
	
	public List<FileVO> getNewsCountByCategoryList() {
		return newsCountByCategoryList;
	}
	public void setNewsCountByCategoryList(List<FileVO> newsCountByCategoryList) {
		this.newsCountByCategoryList = newsCountByCategoryList;
	}
	public String execute(){
		
		return SUCCESS;
	}
	public String getPartyWiseNewsForALocation(){
		
		return SUCCESS;		
	}
	
	public String getPartyWiseNewsDetails(){		
		 try{	 
			 jObj = new JSONObject(getTask());
			 
		   }catch(Exception e){
				 e.printStackTrace(); 
		   }
		 
		 String locationType = jObj.getString("locationType");
		 Long locationId = jObj.getLong("locationId");
		 Long partyId = jObj.getLong("partyId");
		 Long startRecord = jObj.getLong("startRecord");
		 Long maxRecord = jObj.getLong("maxRecord");
		 String queryType = jObj.getString("queryType"); 
		
		newsCountByCategoryList = partyDetailsService.getNewsCountForALocation(partyId,locationType,locationId,startRecord,maxRecord,queryType);

		return Action.SUCCESS;
	}
	
}
