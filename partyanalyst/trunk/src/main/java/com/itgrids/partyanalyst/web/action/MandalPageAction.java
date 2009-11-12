package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.impl.DelimitationConstituencyMandalService;
import com.opensymphony.xwork2.ActionSupport;

public class MandalPageAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private List<MandalInfoVO> mandalInfoVOList;

	private static final Logger log = Logger.getLogger(MandalPageAction.class);
	
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;	
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	public void setMandalInfoVOList(List<MandalInfoVO> mandalInfoVOList) {
		this.mandalInfoVOList = mandalInfoVOList;
	}
	
	public List<MandalInfoVO> getMandalInfoVOList(){
		return mandalInfoVOList;
	}

	public String execute() throws Exception {
		
		String mandalID = request.getParameter("MANDAL_ID");
		String mandalName = request.getParameter("MANDAL_NAME");
		List<MandalInfoVO> mandalInfo = delimitationConstituencyMandalService.getCensusInfoForMandals(mandalID);
		for(MandalInfoVO mandalInfoVO : mandalInfo){
			mandalInfoVO.setMandalName(mandalName);
		}
		setMandalInfoVOList(mandalInfo);
		if(log.isDebugEnabled()){
			log.debug("size============================================"+mandalInfo.size());
			log.debug("end of MandalPageAction.execute()");
		}
		return SUCCESS;
	}
}
