package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICasteReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class VoterAddressAction implements
ServletRequestAware{

	private HttpServletRequest request;
	
	private ICasteReportService casteReportService;
	private List<SelectOptionVO> locations;
	private EntitlementsHelper entitlementsHelper;
	private String url;
	
	public ICasteReportService getCasteReportService() {
		return casteReportService;
	}

	public void setCasteReportService(ICasteReportService casteReportService) {
		this.casteReportService = casteReportService;
	}

	public List<SelectOptionVO> getLocations() {
		return locations;
	}

	public void setLocations(List<SelectOptionVO> locations) {
		this.locations = locations;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String execute(){
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return "input";
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return "input";
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return "error";*/
		List<String> entitlements = null;
	    if(user.getEntitlements() != null && user.getEntitlements().size()>0){
	      entitlements = user.getEntitlements();
	      if(user == null && !entitlements.contains(IConstants.ADMIN_PAGE)){
	    	  return "input";
	      }
	      if(!entitlements.contains(IConstants.ADMIN_PAGE))
	    	  return "error";
		
		locations =  casteReportService.getDistricts(1l);
		
	    }
		return Action.SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String getVoterAddress(){
		Long districtId = Long.valueOf(request.getParameter("districtId"));
		Long publicationId  = Long.valueOf(request.getParameter("publicationId"));
		String type  = request.getParameter("type");
		String cids = request.getParameter("cids");
		List<Long> cidsList = null;
		
		if(cids != null && cids.length() > 0)
		{
			cidsList = new ArrayList<Long>(0);
			String[] cidArr = cids.split(",");
			for(String cid :cidArr)
				cidsList.add(Long.valueOf(cid.trim()));
		}
		url = casteReportService.getVoterAddress(districtId,publicationId,type,cidsList);
		return Action.SUCCESS;
	}
}
