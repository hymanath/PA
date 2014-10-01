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

	public static final Logger LOG = Logger.getLogger(SurveyDataDetailsAction.class);
	
	private HttpServletRequest request;
	
	@Autowired
	private ICadreDashBoardService cadreDashBoardService;
	
	List<CadreRegisterInfo> returnList;
	
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

	public String execute(){
		
		return Action.SUCCESS;
	}
	
	public String getDashBoardBasicInfo(){
		
		
		return Action.SUCCESS;
	}
	
    public String getRecentlyRegisteredCadresInfo(){
		
		
		return Action.SUCCESS;
	}
    
   public String getAssemblyWiseCompletedPercentage(){
		
		
		return Action.SUCCESS;
	}
   
   public String getDistrictWiseCompletedPercentage(){
		
		
		return Action.SUCCESS;
	}
   
    public String getWorkStartedConstituencyCount(){
		  try{
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null){
					return Action.INPUT;
				}						
				returnList = cadreDashBoardService.getWorkStartedConstituencyCount();
				
			} 
			catch (Exception e)	{
				LOG.error("Exception raised in getWorkStartedConstituencyCount", e);
			}
		  
		  return Action.SUCCESS;
	 }
    
}
