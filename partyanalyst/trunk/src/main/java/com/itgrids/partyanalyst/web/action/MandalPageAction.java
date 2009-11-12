package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.impl.DelimitationConstituencyMandalService;
import com.opensymphony.xwork2.ActionSupport;

public class MandalPageAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private MandalInfoVO mandalInfoVO;
	private VillageDetailsVO villageDetailsVO;

	private static final Logger log = Logger.getLogger(MandalPageAction.class);
	
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
}
