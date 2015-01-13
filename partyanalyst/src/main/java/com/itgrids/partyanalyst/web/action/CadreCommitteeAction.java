package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreCommitteeAction   extends ActionSupport implements ServletRequestAware{
	
	private static final Logger         		LOG = Logger.getLogger(CadreCommitteeAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private ICadreCommitteeService   		 	cadreCommitteeService;
	private CadreCommitteeVO 					cadreCommitteeVO;
	private Long 								tdpCadreId;
	
	
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	public CadreCommitteeVO getCadreCommitteeVO() {
		return cadreCommitteeVO;
	}

	public void setCadreCommitteeVO(CadreCommitteeVO cadreCommitteeVO) {
		this.cadreCommitteeVO = cadreCommitteeVO;
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

	public void setCadreCommitteeService(ICadreCommitteeService cadreCommitteeService) {
			this.cadreCommitteeService = cadreCommitteeService;
	}
	
	
	public String execute()
	{
		//cadreCommitteeVO = cadreCommitteeService.getCadreDetailsByTdpCadreId(tdpCadreId);
		return Action.SUCCESS;
	}
}
