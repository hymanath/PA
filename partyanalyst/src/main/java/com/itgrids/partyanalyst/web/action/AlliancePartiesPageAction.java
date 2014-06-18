package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.ActionSupport;

public class AlliancePartiesPageAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1785514915993862303L;
	transient private HttpServletRequest request;
	private IStaticDataService staticDataService;
	private DistrictWisePartyResultVO alliancePartiesInDistrict;
	
	public DistrictWisePartyResultVO getAlliancePartiesInDistrict() {
		return alliancePartiesInDistrict;
	}

	public void setAlliancePartiesInDistrict(final 
			DistrictWisePartyResultVO alliancePartiesInDistrict) {
		this.alliancePartiesInDistrict = alliancePartiesInDistrict;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(final IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

	public String execute(){
		final Long districtId = Long.valueOf(request.getParameter("districtId"));
		final String districtName = request.getParameter("districtName");
		
		alliancePartiesInDistrict = staticDataService.getAllianceGroupsForElections(districtId);
		alliancePartiesInDistrict.setDistrictId(districtId);
		alliancePartiesInDistrict.setDistrictName(districtName);
		
		return SUCCESS;
	}
	
}
