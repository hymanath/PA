package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.ActionSupport;

public class AlliancePartiesPageAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IStaticDataService staticDataService;
	private DistrictWisePartyResultVO alliancePartiesInDistrict;
	
	public DistrictWisePartyResultVO getAlliancePartiesInDistrict() {
		return alliancePartiesInDistrict;
	}

	public void setAlliancePartiesInDistrict(
			DistrictWisePartyResultVO alliancePartiesInDistrict) {
		this.alliancePartiesInDistrict = alliancePartiesInDistrict;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute()throws Exception{
		Long districtId = new Long(request.getParameter("districtId"));
		String districtName = request.getParameter("districtName");
		
		alliancePartiesInDistrict = staticDataService.getAllianceGroupsForElections(districtId);
		alliancePartiesInDistrict.setDistrictId(districtId);
		alliancePartiesInDistrict.setDistrictName(districtName);
		
		return SUCCESS;
	}
	
}
