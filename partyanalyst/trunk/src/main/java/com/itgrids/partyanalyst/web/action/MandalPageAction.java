package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MandalPageAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private MandalInfoVO mandalInfoVO;
	private VillageDetailsVO villageDetailsVO;
	private JSONObject jsonObj = null;
	private String task = null;

	private static final Logger log = Logger.getLogger(MandalPageAction.class);
	private IBoothPopulationService boothPopulationService;
	private List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO;
	
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;	
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	public void setMandalInfoVO(MandalInfoVO mandalInfoVO) {
		this.mandalInfoVO = mandalInfoVO;
	}
	
	public MandalInfoVO getMandalInfoVO(){
		return mandalInfoVO;
	}
	
	public VillageDetailsVO getVillageDetailsVO() {
		return villageDetailsVO;
	}

	public void setVillageDetailsVO(VillageDetailsVO villageDetailsVO) {
		this.villageDetailsVO = villageDetailsVO;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public List<MandalAllElectionDetailsVO> getMandalAllElectionDetailsVO() {
		return mandalAllElectionDetailsVO;
	}

	public void setMandalAllElectionDetailsVO(			
			List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO) {
		this.mandalAllElectionDetailsVO = mandalAllElectionDetailsVO;
	}

	public IBoothPopulationService getBoothPopulationService() {
		return boothPopulationService;
	}

	public void setBoothPopulationService(
			IBoothPopulationService boothPopulationService) {
		this.boothPopulationService = boothPopulationService;
	}

	public String execute() throws Exception {
		
		String mandalID = request.getParameter("MANDAL_ID");
		String mandalName = request.getParameter("MANDAL_NAME");
		List<MandalInfoVO> mandalInfo = delimitationConstituencyMandalService.getCensusInfoForMandals(mandalID);
		for(MandalInfoVO mandalInfoVO : mandalInfo){
			mandalInfoVO.setMandalName(mandalName);
			Throwable ex = mandalInfoVO.getExceptionEncountered();
			if(ex!=null){
				log.error("exception raised while retrieving mandal details ", ex);
				return ERROR;
			}
			setMandalInfoVO(mandalInfoVO);
			break;
		}
		villageDetailsVO = delimitationConstituencyMandalService.getVillagesFormMandal(new Long(mandalID));
		villageDetailsVO.setMandalName(mandalName);
		Throwable ex = villageDetailsVO.getExceptionEncountered();
		if(ex!=null){
			log.error("exception raised while retrieving mandal details ", ex);
		}
		
		if(log.isDebugEnabled()){
			log.debug("size============================================"+mandalInfo.size());
			log.debug("size============================================"+(villageDetailsVO.getVillageCensusList()).size());
			log.debug("end of MandalPageAction.execute()");
		}
		return SUCCESS;
	}
	
	public String getMandalPartyResult() throws Exception{		
		
		String param = getTask();		
		try {
			jsonObj = new JSONObject(param);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		String mandalId = jsonObj.getString("mandal");
		String partyId = jsonObj.getString("party");
		String alliance = jsonObj.getString("alliance");
		
		//mandalAllElectionDetailsVO = boothPopulationService.getMandalAllElectionDetails(new Long(mandalId), new Long(partyId),new Boolean(alliance).booleanValue());
		
		System.out.println("List size = "+mandalAllElectionDetailsVO.size());
		
		if(mandalAllElectionDetailsVO!=null)				
			return Action.SUCCESS;
		else
			return Action.ERROR;
	}

	
}
