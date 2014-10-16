package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class CadreDashBoardAction implements ServletRequestAware {

	private static Logger LOG = Logger.getLogger(CadreDashBoardAction.class);
	
	private HttpServletRequest request;
	
	private ICadreDashBoardService cadreDashBoardService;
	private List<CadreRegisterInfo> result;
	private CadreRegisterInfo info;
	private EntitlementsHelper entitlementsHelper;
	
	public ICadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}

	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<CadreRegisterInfo> getResult() {
		return result;
	}

	public void setResult(List<CadreRegisterInfo> result) {
		this.result = result;
	}

	public CadreRegisterInfo getInfo() {
		return info;
	}

	public void setInfo(CadreRegisterInfo info) {
		this.info = info;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREDASHBOARD")){
			noaccess = true ;
			
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		return Action.SUCCESS;
	}
	
	public String getCadreDashBoardBasicInfo(){
		try{
			String task = request.getParameter("task");
			if(task.equalsIgnoreCase("basicInfo")){
		       result = cadreDashBoardService.getDashBoardBasicInfo();
			}else if(task.equalsIgnoreCase("recentlyRegistered")){
				 result = cadreDashBoardService.getRecentlyRegisteredCadresInfo();
			}else if(task.equalsIgnoreCase("assemblyWise")){
				result = cadreDashBoardService.getAssemblyWiseCompletedPercentage(Long.parseLong(request.getParameter("assemblyId")),Long.parseLong(request.getParameter("stateId")));
			}else if(task.equalsIgnoreCase("districtWise")){
				result = cadreDashBoardService.getDistrictWiseCompletedPercentage(Long.parseLong(request.getParameter("districtId")),Long.parseLong(request.getParameter("stateId")));
			}else if(task.equalsIgnoreCase("workStartedConstituency")){
				result = cadreDashBoardService.getWorkStartedConstituencyCount();
			}else if(task.equalsIgnoreCase("candidateDataCollectionInfo")){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				result = cadreDashBoardService.getCandidateDataCollectionInfo(sdf.parse(request.getParameter("fromDate")),sdf.parse(request.getParameter("toDate")));
			}
		}catch(Exception e){
			LOG.error("Exception rised in getCadreDashBoardBasicInfo ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getWorkingMembersInfo(){
	  try{
		String task = request.getParameter("task");
		if(task.equalsIgnoreCase("workingCount")){
			info = cadreDashBoardService.getWorkingMembersInfo();
		}
	  }catch(Exception e){
		  LOG.error("Exception rised in getWorkingMembersInfo ",e);
	  }
		return Action.SUCCESS;
	}
    
	public String getLocationWiseRegistrationInfo(){
		try{
			String task = request.getParameter("task");
			if(task.equalsIgnoreCase("assemblyInfo")){
				result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"assembly",request.getParameter("fromDate"),request.getParameter("toDate"));
			}else if(task.equalsIgnoreCase("districtInfo")){
				result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"district",request.getParameter("fromDate"),request.getParameter("toDate"));
			}else if(task.equalsIgnoreCase("panchayatInfo")){
				result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"panchayat",request.getParameter("fromDate"),request.getParameter("toDate"));
			}else if(task.equalsIgnoreCase("boothInfo")){
				result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"booth",request.getParameter("fromDate"),request.getParameter("toDate"));
			}else if(task.equalsIgnoreCase("mandalInfo")){
				result = cadreDashBoardService.getLocationWiseRegistrationInfo(getIds(request.getParameter("ids")),"mandal",request.getParameter("fromDate"),request.getParameter("toDate"));
			}else if(task.equalsIgnoreCase("stateInfo")){
				result = cadreDashBoardService.getStateWiseRegistrationInfo(getIds(request.getParameter("ids")),request.getParameter("fromDate"),request.getParameter("toDate"));
			}else if(task.equalsIgnoreCase("boothNames")){
				result = cadreDashBoardService.getBoothsInConstituencies(Long.parseLong(request.getParameter("constituencyId")));
			}else if(task.equalsIgnoreCase("panchayatNames")){
				result = cadreDashBoardService.getPanchayatsInConstituencies(Long.parseLong(request.getParameter("constituencyId")));
			}else if(task.equalsIgnoreCase("assemblyNames")){
				result = cadreDashBoardService.getAssemblyConstituencies(request.getParameter("type"));
			}
		 }catch(Exception e){
			  LOG.error("Exception rised in getLocationWiseRegistrationInfo ",e);
		  }
		return Action.SUCCESS;
	}
	
	public List<Long> getIds(String idsString){
		List<Long> ids = new ArrayList<Long>();
		String[] idsArray = idsString.split(",");
		
		for(String id:idsArray){
			ids.add(Long.valueOf(id.trim()));
		}
		
		return ids;
	}
}
