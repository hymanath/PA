package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadreVolunteerVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TdpCadreVolunteerAction extends ActionSupport implements ServletRequestAware{
	private static final Logger LOG = Logger.getLogger(TdpCadreReportAction.class);
	
	private HttpServletRequest request;
	
	private String 	task;
	private JSONObject jobj;
	
	private ITdpCadreReportService  tdpCadreReportService;
	private InputStream inputStream;
	private TdpCadreVolunteerVO tdpCadreVolunteerVO;
	private HttpSession session;
	private ResultStatus resultStatus;
	private List<GenericVO> constituencyList;
	private EntitlementsHelper 					entitlementsHelper;
	private Boolean 							isExist;
	
	
	
	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public List<GenericVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<GenericVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public TdpCadreVolunteerVO getTdpCadreVolunteerVO() {
		return tdpCadreVolunteerVO;
	}

	public void setTdpCadreVolunteerVO(TdpCadreVolunteerVO tdpCadreVolunteerVO) {
		this.tdpCadreVolunteerVO = tdpCadreVolunteerVO;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

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

	public ITdpCadreReportService getTdpCadreReportService() {
		return tdpCadreReportService;
	}

	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}

	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	
	public String execute()
	{
		
		constituencyList = tdpCadreReportService.getGHMCConstituencies();
		return Action.SUCCESS;
		
	}
	
	public String saveCadreInfo()
	{
		try {
			LOG.info("Entered into saveCadreDetails method in CadreRegistrationAction Action");
			session = request.getSession();
			resultStatus = tdpCadreReportService.saveCadreRegistration(tdpCadreVolunteerVO);
			if(resultStatus.getResultCode() == 0)
				inputStream = new StringBufferInputStream("success");
			else
			inputStream = new StringBufferInputStream("fail");
		
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String volunteersDetailsPage()
	{
		try {
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO != null)
			{
				/*if(entitlementsHelper.checkForEntitlementToViewReport(regVO,IConstants.GHMC_CADRE_MEGA_DRIVE_USER) || 
						 entitlementsHelper.checkForEntitlementToViewReport(regVO,IConstants.GHMC_CADRE_MEGA_DRIVE_USER_GROUP))
				{
					constituencyList = tdpCadreReportService.getGHMCConstituencies();
					return Action.SUCCESS;
				}*/
				List<String> entitlements = null;
			    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			      entitlements = regVO.getEntitlements();
			      if(entitlements.contains(IConstants.GHMC_CADRE_MEGA_DRIVE_USER)||entitlements.contains(IConstants.GHMC_CADRE_MEGA_DRIVE_USER_GROUP)){
			    constituencyList = tdpCadreReportService.getGHMCConstituencies();
			     return Action.SUCCESS;
			      }

				else
				{
					return Action.ERROR;
				}
			}
			else
			{
				return Action.INPUT;
			}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in volunteersDetailsPage method in CadreRegistrationAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getValunteersInfoByLocation()
	{
		try {
			LOG.info("Entered into getValunteersInfoByLocation method in CadreRegistrationAction Action");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO != null)
			{
				jobj = new JSONObject(getTask());
				if(jobj.getString("task").equalsIgnoreCase("basicInfo"))
				{
				Long consituencyId = jobj.getLong("consituencyId");
				String searchType = jobj.getString("searchType");
				tdpCadreVolunteerVO = tdpCadreReportService.getConstituencyWiseVolunteerInfo(consituencyId,searchType);
				}
				if(jobj.getString("task").equalsIgnoreCase("getVolunteerInfoByDevice"))
				{
				Long consituencyId = jobj.getLong("consituencyId");
				String searchType = jobj.getString("searchType");
				String deviceType = jobj.getString("deviceType");
				tdpCadreVolunteerVO = tdpCadreReportService.getConstituencyWiseVolunteerInfoByDevice(deviceType,consituencyId,searchType);
				}
				
				return Action.SUCCESS;
			}
			else
			{
				return Action.INPUT;
			}
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String assignConstiteuncyForValeenteer()
	{
		try {
			LOG.info("Entered into assignConstiteuncyForValeenteer method in CadreRegistrationAction Action");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO != null)
			{
				jobj = new JSONObject(getTask());
				Long consituencyId = jobj.getLong("consituencyId");
				Long valunteerId = jobj.getLong("valunteerId");
				
				resultStatus = tdpCadreReportService.assignConstiteuncyForValeenteer(consituencyId,valunteerId);
			}
		
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String isExistUSerByMobileAndEmail()
	{
		try {
			LOG.info("Entered into isExistUSerByMobileAndEmail method in CadreRegistrationAction Action");
			
				jobj = new JSONObject(getTask());
				String mobileNo = jobj.getString("mobileNo");
				String emailId = jobj.getString("emailId");
				
				isExist = tdpCadreReportService.isExistUSerByMobileAndEmail(mobileNo,emailId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in isExistUSerByMobileAndEmail method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
}
