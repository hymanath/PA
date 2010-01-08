package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IDistrictPageService;
import com.itgrids.partyanalyst.service.impl.DistrictPageService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class DistrictPageAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String districtId;
	private String districtName;
	private HttpServletRequest request;
	private IDistrictPageService districtPageService;	
	private List<SelectOptionVO> constituencies ;
	private List<MandalVO> mandals ;
	private String constituencyId;
	private final Logger log = Logger.getLogger(DistrictPageAction.class);
	
	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<MandalVO> getMandals() {
		return mandals;
	}

	public void setMandals(List<MandalVO> mandals) {
		this.mandals = mandals;
	}

	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}

	public IDistrictPageService getDistrictPageService() {
		return districtPageService;
	}

	public void setDistrictPageService(IDistrictPageService districtPageService) {
		this.districtPageService = districtPageService;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute() throws Exception
	{
		districtId = request.getParameter("districtId");
		districtName = request.getParameter("districtName");
		
		constituencies = districtPageService.getConstituenciesForDistrict(Long.parseLong(districtId));
			if(constituencies == null){
				if(log.isDebugEnabled())
					log.error("Failed to get Constituency Data");
				return Action.ERROR;
			}
		
		mandals = districtPageService.getMandalsForDistrict(Long.parseLong(districtId));
			if(mandals == null){
				if(log.isDebugEnabled())
					log.error("Failed to get Mandal Data");
				return Action.ERROR;
			}
			
		setConstituencies(constituencies);
		setMandals(mandals);
		this.setDistrictName(districtName);
		this.setDistrictId(districtId);
		
		System.out.println("District Id = "+districtId+" & District Name = "+districtName);
		return Action.SUCCESS;
	}

}
