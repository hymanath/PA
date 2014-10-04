package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.opensymphony.xwork2.Action;

public class CadreDashBoardAction implements ServletRequestAware {

	private static Logger LOG = Logger.getLogger(CadreDashBoardAction.class);
	
	private HttpServletRequest request;
	
	private ICadreDashBoardService cadreDashBoardService;
	private List<CadreRegisterInfo> result;
	private CadreRegisterInfo info;
	
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

	public String execute(){
		
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
    
}
