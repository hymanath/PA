package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BenfitVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IBenefitAnalysisService;
import com.itgrids.partyanalyst.service.INewsAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BenefitAnalysisAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 8220075231227232574L;
	
	private HttpServletRequest request;
	private JSONObject jObj;
	private String task;
	private List<SelectOptionVO> resultList;
	private INewsAnalysisService newsAnalysisService;
	private IBenefitAnalysisService benefitAnalysisService;
	private List<BenfitVO> categoryBenefits;
	private HttpSession session ; 
	private Long partyId;
	private Long categoryId;
	private Long benefitId;
	private Long stateId;
	private String fromDate;
	private String toDate;
	private Integer startValue;
	private Integer endValue;
	private List<BenfitVO> countResult;
	private List<NewsActivityVO> newsResult;
	private String buildType;
	private String type;
	private Long candidateId;
	private Long locationId;
	private String name;
	
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Integer getStartValue() {
		return startValue;
	}

	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}

	public Integer getEndValue() {
		return endValue;
	}

	public void setEndValue(Integer endValue) {
		this.endValue = endValue;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
		
	public List<SelectOptionVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<SelectOptionVO> resultList) {
		this.resultList = resultList;
	}

	public INewsAnalysisService getNewsAnalysisService() {
		return newsAnalysisService;
	}

	public void setNewsAnalysisService(INewsAnalysisService newsAnalysisService) {
		this.newsAnalysisService = newsAnalysisService;
	}

	public IBenefitAnalysisService getBenefitAnalysisService() {
		return benefitAnalysisService;
	}

	public void setBenefitAnalysisService(
			IBenefitAnalysisService benefitAnalysisService) {
		this.benefitAnalysisService = benefitAnalysisService;
	}
	
	public List<BenfitVO> getCategoryBenefits() {
		return categoryBenefits;
	}

	public void setCategoryBenefits(List<BenfitVO> categoryBenefits) {
		this.categoryBenefits = categoryBenefits;
	}

	
	
	public List<BenfitVO> getCountResult() {
		return countResult;
	}

	public void setCountResult(List<BenfitVO> countResult) {
		this.countResult = countResult;
	}

	public List<NewsActivityVO> getNewsResult() {
		return newsResult;
	}

	public void setNewsResult(List<NewsActivityVO> newsResult) {
		this.newsResult = newsResult;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String execute()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute(IConstants.USER);
		if(user == null){
			return "error";
		}
		return Action.SUCCESS;
	}
	
	public String getAssemblyConstituenciesAndDistricts()
	{
		try 
		{
			LOG.info(" Entered into getAssemblyConstituenciesAndDistricts()  ");
			jObj = new JSONObject(getTask());
			 List<Long> states = new ArrayList<Long>();
				JSONArray stateId = jObj.getJSONArray("stateIds");
				if(stateId!=null && stateId.length()>0){
					for(int i=0; i<stateId.length();i++){
						states.add(Long.parseLong(stateId.getString(i)));
					}
				}
			if(jObj.getString("task").equalsIgnoreCase("assemblyConstituency"))
				resultList = newsAnalysisService.getAssemblyConstituenciesForStatesList(states);
			else if(jObj.getString("task").equalsIgnoreCase("districts"))
				resultList = newsAnalysisService.getDistrictsForStatesList(states);
		} catch (Exception e)
		{
			LOG.error(" Exception occured in getAssemblyConstituenciesAndDistricts()",e);
		}
		
		return Action.SUCCESS;
	}
	
	
	
	public String getCandidateGroupWiseBenifit(){
		  try{
			jObj = new JSONObject(getTask());
			
			Date[] dates = getDates(jObj.getString("type"),jObj.getString("fromDate"),jObj.getString("toDate"));
			
			countResult = benefitAnalysisService.getCandidateGroupWiseBenifit(jObj.getLong("groupId"),dates[0],dates[1],jObj.getLong("stateId"),jObj.getLong("partyId"));
		  }catch(Exception e){
			  LOG.error(" Exception occured in getCandidateGroupWiseBenifit ",e);
		  }
		  
		 return Action.SUCCESS;
	}

	public String getCandidateGroupWiseBenifitNews(){
		  try{
			
			Date[] dates = getDates(type,fromDate,toDate);
			newsResult = benefitAnalysisService.getCandidateGroupWiseBenifitNews(dates[0],dates[1],candidateId,benefitId,startValue,endValue);
		  }catch(Exception e){
			  LOG.error(" Exception occured in getCandidateGroupWiseBenifitNews ",e);
		  }
		  
		 return Action.SUCCESS;
	}
	
	public String getCandidateGroups(){
		try{
			
			countResult = benefitAnalysisService.getCandidateGroups();
			
		}catch(Exception e){
			 LOG.error(" Exception occured in getCandidateGroups ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public Date[] getDates(String type,String fromDateStr,String toDateStr) throws Exception{
		 Date[] dates = new Date[2];
		 Date fromDate = null;
		 Date toDate = null;
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 if(type.equalsIgnoreCase("monthly")){
				String[] monthStr = fromDateStr.split("/");
				int month = Integer.parseInt(monthStr[0].trim());
				int year = Integer.parseInt(monthStr[1].trim());
				Calendar fromCalendar = Calendar.getInstance();
				  fromCalendar.set(Calendar.MONTH, month-1);
				  fromCalendar.set(Calendar.YEAR, year);
				  fromCalendar.set(Calendar.DAY_OF_MONTH,1);
				 fromDate = fromCalendar.getTime();
				  
			    Calendar toCalendar = Calendar.getInstance();
			      toCalendar.set(Calendar.MONTH, month-1);
			      toCalendar.set(Calendar.YEAR, year);
			      toCalendar.set(Calendar.DAY_OF_MONTH,toCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			     toDate = toCalendar.getTime();
			}else{
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
		 dates[0] = fromDate;
		 dates[1] = toDate;
		 return dates;
	}

	  public String getCategoryBenifitNews(){
			session = request.getSession();
	        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
	        if(user == null){
	        	return ERROR;
	        }
	        
	        return Action.SUCCESS;
		}
	  
	  public String getCategoryWiseBenifit(){
		  try{
			jObj = new JSONObject(getTask());
			
		    // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// String startDate = jObj.getString("fromDate");
			// String endDate = jObj.getString("toDate");
			 
			 Long stateId = jObj.getLong("stateId");
			 
			 Date[] dates = getDates(jObj.getString("type"),jObj.getString("fromDate"),jObj.getString("toDate"));		
			 
			 String[] partyId  = jObj.getString("partyIds").split(",");
				List<Long> partyIds = new ArrayList<Long>();
				for(String id:partyId){
					partyIds.add(Long.valueOf(id.trim()));
				}
			categoryBenefits = benefitAnalysisService.getCategoryWiseBenifit(dates[0], dates[1], stateId, partyIds);
			
		  }catch(Exception e){
			  LOG.error(" Exception occured in getCategoryWiseBenifit ",e);
		  }
			return Action.SUCCESS;
		}
	  
	  public String getCategoryBenifitWiseNews(){
		  try{
			
			Date[] dates = getDates(type,fromDate,toDate);
    		 Integer startIndex = null;
    		 Integer maxIndex = null;
    		 if(startValue != null && startValue > 0){
    			 startIndex = startValue;
    		 }
    		 if(endValue != null && endValue > 0){
    			 maxIndex = endValue;
    		 }
			
    		 newsResult = benefitAnalysisService.getCategoryBenifitWiseNews(dates[0],dates[1],partyId,categoryId,benefitId,stateId);
			
		 }catch(Exception e){
			  LOG.error(" Exception occured in getCategoryWiseBenifit ",e);
		  }
			return Action.SUCCESS;
		}
	  
	  public String getLocationWiseBenifit(){
		  try{
			jObj = new JSONObject(getTask());
			
			 Date[] dates = getDates(jObj.getString("type"),jObj.getString("fromDate"),jObj.getString("toDate"));		
			 if(jObj.getString("task").equalsIgnoreCase("districtWiseBenefits"))	{
				 countResult = benefitAnalysisService.getDistrictWiseBenifit(dates[0], dates[1], jObj.getLong("stateId"), jObj.getLong("partyId"));
			 }
			 else if(jObj.getString("task").equalsIgnoreCase("parliamentWiseBenefits")){
				 countResult = benefitAnalysisService.getConstituencyWiseBenifit(dates[0], dates[1], jObj.getLong("stateId"), jObj.getLong("partyId"),"parliament");
			 }
			 else if(jObj.getString("task").equalsIgnoreCase("assemblyWiseBenefits")){
				 countResult = benefitAnalysisService.getConstituencyWiseBenifit(dates[0], dates[1], jObj.getLong("stateId"), jObj.getLong("partyId"),"assembly");
			 }
			 Collections.sort(countResult,benefitSort);
		  }catch(Exception e){
			  LOG.error(" Exception occured in getLocationWiseBenifit ",e);
		  }
			return Action.SUCCESS;
		}
	  
	  public String getLocationBenifitNews(){
		  try{
			
			Date[] dates = getDates(type,fromDate,toDate);
			 newsResult = benefitAnalysisService.getLocationBenifitNews(dates[0],dates[1],locationId,benefitId,startValue,endValue,name);
		  }catch(Exception e){
			  LOG.error(" Exception occured in getLocationBenifitNews ",e);
		  }
		  
		 return Action.SUCCESS;
	}
	  
	public Comparator<BenfitVO> benefitSort = new Comparator<BenfitVO>()
	{
				  
	   public int compare(BenfitVO vo1, BenfitVO vo2)
		{
	       return (vo2.getNegCount().intValue()) - (vo1.getNegCount().intValue());
		}
	};
}
