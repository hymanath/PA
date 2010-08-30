
package com.itgrids.partyanalyst.web.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author Mohan
 *
 */
public class PartyResultsCriteriaAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private List<SelectOptionVO> partyList;
	private IStaticDataService staticDataService;

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}
	
	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
		
	public String execute() {	
		
		session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null)
			return INPUT;
		if(!EntitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_RESULTS_REPORT))
			return ERROR;
		partyList = staticDataService.getStaticParties();
		
		return SUCCESS;
		
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

}
