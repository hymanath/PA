package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class MahanaduDashBoardAction implements ServletRequestAware {
	
	private HttpServletRequest request;
	private IMahanaduDashBoardService mahanaduDashBoardService;
	private List<MahanaduVisitVO> mahanaduVisitList;
	private String status;
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardAction.class); 

	private String task;
	private JSONObject jObj;
	private Long eventId;
	private HttpSession session;
	private EntitlementsHelper entitlementsHelper;
	private MahanaduVisitVO mahanaduVisitVO;
	private MahanaduEventVO mahanaduEventVO;
	private Long longVal;
	private List<MahanaduEventVO> mahanaduEventVOList = new ArrayList<MahanaduEventVO>(0);
	private List<IdNameVO> idNameVoList = new ArrayList<IdNameVO>(0);
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setServletRequest(HttpServletRequest request) {		
		this.request = request;
	}
	
	public void setMahanaduDashBoardService(
			IMahanaduDashBoardService mahanaduDashBoardService) {
		this.mahanaduDashBoardService = mahanaduDashBoardService;
	}

	public List<MahanaduVisitVO> getMahanaduVisitList() {
		return mahanaduVisitList;
	}

	public void setMahanaduVisitList(List<MahanaduVisitVO> mahanaduVisitList) {
		this.mahanaduVisitList = mahanaduVisitList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
   
	public MahanaduVisitVO getMahanaduVisitVO() {
		return mahanaduVisitVO;
	}
	public void setMahanaduVisitVO(MahanaduVisitVO mahanaduVisitVO) {
		this.mahanaduVisitVO = mahanaduVisitVO;
	}
	public MahanaduEventVO getMahanaduEventVO() {
		return mahanaduEventVO;
	}
	public void setMahanaduEventVO(MahanaduEventVO mahanaduEventVO) {
		this.mahanaduEventVO = mahanaduEventVO;
	}
	
	public List<MahanaduEventVO> getMahanaduEventVOList() {
		return mahanaduEventVOList;
	}
	public void setMahanaduEventVOList(List<MahanaduEventVO> mahanaduEventVOList) {
		this.mahanaduEventVOList = mahanaduEventVOList;
	}
	public Long getLongVal() {
		return longVal;
	}
	public void setLongVal(Long longVal) {
		this.longVal = longVal;
	}
	public List<IdNameVO> getIdNameVoList() {
		return idNameVoList;
	}
	public void setIdNameVoList(List<IdNameVO> idNameVoList) {
		this.idNameVoList = idNameVoList;
	}
	
	public String execute(){
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU_MAIN_DASHBOARD_ENTITLEMENT") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU_MAIN_DASHBOARD_ADMIN_ENTITLEMENT") ){
			return Action.SUCCESS;
		}
		else if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
	    	 return Action.SUCCESS;
		}else{
			return "error";
		}
	     
		
	}
	
	public String populateLatestInfo(){
		try{
		   mahanaduDashBoardService.getTodayTotalVisitorsForSchedular();
		}catch(Exception e){
			LOG.error("Exception rised in populateLatestInfo()",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getTodayTotalVisitorsForWeb(){
		try{
		   mahanaduDashBoardService.getTodayTotalVisitorsForWeb();
		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalVisitorsForWeb()",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getTodayTotalAndCurrentUsersInfo(){
		try{
			/*String fromDateStr = request.getParameter("fromDate");
			String toDateStr = request.getParameter("toDate");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && toDateStr.length() > 0){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}*/
			
			jObj = new JSONObject(getTask());
			
			mahanaduVisitList = mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfoList(jObj.getLong("eventId"),jObj.getString("dateValues"));
		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfo()",e);
		}
		return Action.SUCCESS;
	}
	public String getMahanaduCadreVisitNewInfo(){
		return Action.SUCCESS;
	}
	public String getTodayTotalVisitorsInfo(){
		
		try
		{
			jObj = new JSONObject(getTask());			
			mahanaduVisitVO=mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfoListNew(jObj.getLong("eventId"),jObj.getString("date"));
		}catch(Exception e){
			LOG.info("Error occured in getTodayTotalVisitorsInfo()",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseMembersCountInCampus(){
		try {
			LOG.info("Entered into getDistrictWiseMembersCountInCampus");
			
			jObj = new JSONObject(getTask());
			List<Long> statesList = new ArrayList<Long>(0);
			JSONArray arr = jObj.getJSONArray("stateIds");
			
			if(arr != null && arr.length() > 0){
				for (int i=0;i<arr.length();i++) {
					statesList.add(Long.parseLong(arr.getInt(i)+""));
				}
			}
			
			mahanaduEventVO = mahanaduDashBoardService.getDistrictWiseTotalAndPresentCadre(jObj.getLong("eventId"),statesList,jObj.getString("date"));
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictWiseMembersCountInCampus", e);
		}
		return Action.SUCCESS;
	}
	
	public String getTodayCount(){
		try {
			longVal = mahanaduDashBoardService.getTodayCount(Long.parseLong(request.getParameter("eventId").toString()));
		} catch (Exception e) {
			LOG.error("Exception raised at getTodayCount", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getHourWiseNowInCampusCadresCount(){
		try {
			jObj = new JSONObject(getTask());
			mahanaduEventVOList = mahanaduDashBoardService.getHourWiseNowInCampusCadresCount(jObj.getString("dayVal"),jObj.getLong("eventId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getHourWiseNowInCampusCadresCount", e);
		}
		return Action.SUCCESS;
	}
	
	public String getEventDates(){
		try {
			jObj = new JSONObject(getTask());
			idNameVoList = mahanaduDashBoardService.getEventDates(jObj.getLong("eventId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getEventDates", e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseMembersCountInCampus(){
		try {
			jObj = new JSONObject(getTask());
			List<Long> statesList = new ArrayList<Long>(0);
			JSONArray arr = jObj.getJSONArray("stateIds");
			
			if(arr != null && arr.length() > 0){
				for (int i=0;i<arr.length();i++) {
					statesList.add(Long.parseLong(arr.getInt(i)+""));
				}
			}
			
			mahanaduEventVO = mahanaduDashBoardService.getConstituencyWiseMembersCountInCampus(jObj.getLong("eventId"),statesList,jObj.getString("date"));
		} catch (Exception e) {
			LOG.error("Exception riased at getConstituencyWiseMembersCountInCampus ", e);
		}
		return Action.SUCCESS;
	}
}
